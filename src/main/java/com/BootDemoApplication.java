package com;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class BootDemoApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(BootDemoApplication.class, args);
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com")).build().apiInfo(myApiindfo());
	}

	@Bean
	public BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public ApiInfo myApiindfo() {
		// TODO Auto-generated method stub
		Contact c=new Contact("Harshil", "url", "emaiID");
		return new ApiInfo("Spring boot Demp", "Descprition", "1.0", "termsOfserviceUrl", c, "License", "LicenseUrl",new ArrayList<VendorExtension>());
	}

}


//password ram ==> db ram 
//ram@123.com ram encode --> 4545
//ram encode ==> k56lk766lk67lk6l7l67

//login --> select * from users where email = ram@123.com and password = ram; 


  
//xml
//annotation
//java 
