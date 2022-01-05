package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.userBean;

@Repository
public class userDao {
	
	@Autowired
	JdbcTemplate stmt;
	
	public void addUser(userBean bean) {
		
		stmt.update("insert into users (firstname,email,password,role) values (?,?,?,?)",
				bean.getFirstName(),bean.getEmail(),
				bean.getPassword(),bean.getRole());
	}

	public userBean getUserbyid(int userid) {
		userBean bean =stmt.queryForObject("select * from users where userid=?",new BeanPropertyRowMapper<userBean>(userBean.class),userid);
		return bean;
	}
	
	public List<userBean> getallUsers(){
		
		return stmt.query("select * from users", new BeanPropertyRowMapper<userBean>(userBean.class));
		
	}

	public void deleteUserbyid(int userId) {
		stmt.update("delete from users where userid=?",userId);
	}

	public void updateUser(userBean user) {
		stmt.update("update users set firstname = ?,email=? where userid  = ? ", user.getFirstName(), user.getEmail(),user.getUserid());
		
	}
	

}
