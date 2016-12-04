package com.westear.ssm.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.westear.ssm.common.util.EncryptPsw;
import com.westear.ssm.model.User;
import com.westear.ssm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	static {
		System.out.println("==============>in the controller(user)");
	}
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/addUser",method=RequestMethod.GET)
	public String addUser(Model model){
		System.out.println("in the Controller(user/addUser)");
		User user = new User();
		user.setId("wwwwwwwww");
		user.setUsername("westear");
		user.setPsw(EncryptPsw.encryptByMD("westear514*"));
		this.userService.addUser(user);
		model.addAttribute("adduser",user);
		return "addUser";
	}
	
	@RequestMapping(value="/login")
	public String login(){
		return "common/login";
	}
	
	@RequestMapping(value="/userLogin")
	public String loginUser(HttpServletRequest req, HttpServletResponse resp, 
			User user,Model model){
		user = this.userService.login(req, resp, user);
		if(user != null){
			model.addAttribute("user", user);
			return "common/home";
		}else{
			return "common/login";
		}
	}
}
