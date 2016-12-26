package com.uniplore.graph.ms.sign.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniplore.graph.ms.sign.entity.UserPO;
import com.uniplore.graph.ms.sign.service.IUserService;
/**
 * 
 * 版权所有  2016-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述    接收sign.jsp的输入参数，并将信息传入到dao层，将其信息保存到数据库中
 * 类名称    com.uniplore.graph.ms.sign.controller.SignUpController       
 * 创建人    朱君鹏
 * 创建时间  2016年12月26日 下午4:17:27     
 * 修改人  
 * 修改时间  2016年12月26日 下午4:17:27     
 * 修改备注     
 * @version  1.0
 */
@Controller
@RequestMapping(value="/ms/sign")
public class SignUpController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/SignUp",method=RequestMethod.POST)
	public void SignUp(UserPO user,Model model) throws Exception{
		System.out.println(user.toString());   //判断是否正确的接收到从客户端传来的用户信息
		
		//将user信息保存到数据库中
		userService.saveUserInfo(user);
		
		//数据回显到sign.jsp页面中
		model.addAttribute("user", user);
	}
}
