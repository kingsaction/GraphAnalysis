package com.uniplore.graph.ms.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/ms/login")
public class LoginController {
	
	/**
	 * 
	 * @Title  loginSys  
	 * @Description 登录到系统 
	 * @param user
	 */
	@RequestMapping(value="/Login",method=RequestMethod.POST)
	public void loginSys(String name, String password){
		System.out.println("接收到的名字为:"+name);
		System.out.println("接收到的密码为:"+password);
		
		//判断接收到的名字究竟是username还是email地址，只要判断字符串中是否包含@符号即可
		boolean nameIsEmail = name.contains("@");
		System.out.println("是否是email"+nameIsEmail);
	}
	
	/**
	 * 
	 * @Title  logoutSys  
	 * @Description 退出系统
	 */
	@RequestMapping(value="/Logout",method=RequestMethod.GET)
	public void logoutSys(){
		
	}
}
