package com.controller;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bean.ResponseBean;
import com.bean.userBean;
import com.dao.userDao;

@RestController
public class userController {

	@Autowired
	userDao dao;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
//	@PostMapping("/signUp")
//	public userBean saveUser(userBean bean) {
//		bean.setRole(1);
//		dao.addUser(bean);
//		return bean;
//
//	}
	
	
	@PostMapping("/signUp")
	public ResponseBean<userBean> saveUser(userBean bean) {
		bean.setRole(1);
		
		String enCodedPassword = bCryptPasswordEncoder.encode(bean.getPassword());
		bean.setPassword(enCodedPassword);
		
		dao.addUser(bean);
		ResponseBean<userBean> rb = new ResponseBean<>();
		rb.setData(bean);
		rb.setMsg("signup done");
		rb.setStatus(200);
		return rb;

	}
	
	@PostMapping("/login")
	public ResponseBean<userBean> authenticate(userBean bean){
		userBean user=dao.authenticate(bean);
		ResponseBean<userBean> rb = new ResponseBean<>();
		if(bCryptPasswordEncoder.matches(bean.getPassword(), user.getPassword())) {			
			
			rb.setData(user);
			rb.setMsg("login done");
			rb.setStatus(200);
		}
		else {
			rb.setData(bean);
			rb.setMsg("login failed");
			rb.setStatus(404);
		}
		return rb;
		
	}
	@GetMapping("/getuserbyid")
	public userBean getuserByid(@RequestParam("userId") int userId) {
		userBean user = dao.getUserbyid(userId);
		return user;
	}

	@GetMapping("/listUsers")
	public List<userBean> getAllusers() {

		List<userBean> users = dao.getallUsers();
		return users;

	}

	@GetMapping("/deleteUser")
	public userBean  deleteUser(@RequestParam("userId") int userId) {
		userBean user = dao.getUserbyid(userId);
		dao.deleteUserbyid(userId);
		return user;
		
		
	}
	@PostMapping("/editUser")
	public userBean updateUser(userBean user) {
		dao.updateUser(user);
		return user;
		
	}
	
	@PostMapping("/saveProfile")
	public userBean saveProfile(@ModelAttribute("user") userBean bean,MultipartFile file) {
		
		System.out.println(bean.getFirstName());
		System.out.println(file.getOriginalFilename());
		try {
			File f = new File("//Users//kirtanmodh//Documents//Junk",
					file.getOriginalFilename());
			f.createNewFile();// blank
			FileUtils.writeByteArrayToFile(f, file.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return bean;
		
	}
}
