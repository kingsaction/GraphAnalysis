package com.uniplore.graph.ms.sign.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniplore.graph.ms.sign.service.IUserService;

@Controller
@RequestMapping(value="/ms/sign")
public class ActiveAccountController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/ActiveAccount",method={RequestMethod.GET,RequestMethod.POST})
	public String activeAccount(HttpServletRequest request) throws Exception{
		String code = request.getParameter("code");
		//System.out.println(code);  //判断是否成功的接收到QueryString
		
		//将接收到的code传到Service层
		if(userService.activeUserAccount(code)){
			//如果返回true，说明能够激活账户
			return "ms/success";
		}else{
			//由于种种原因，用户的账户没有激活，此时提醒用户重新注册，并激活
			return "ms/active_error";
		}
		
	}
}
