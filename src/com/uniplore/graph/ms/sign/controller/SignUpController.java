package com.uniplore.graph.ms.sign.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String signUp(UserPO user,Model model) throws Exception{
		//System.out.println(user.toString());   //判断是否正确的接收到从客户端传来的用户信息
		
		//将user信息保存到数据库中
		userService.saveUserInfo(user);
		
		//数据回显到sign.jsp页面中
		model.addAttribute("user", user);
		
		//成功发送邮件之后，提醒用户到邮箱中激活
		return "ms/login";
	}
	
	@RequestMapping(value="/CheckUserNameExisted",method=RequestMethod.POST)
	public @ResponseBody Integer checkUserNameExisted(HttpServletRequest request)throws Exception{
		String userName = request.getParameter("userName");
		//System.out.println("接收到的用户名为:"+userName);
		Integer id = userService.queryUserExisted(userName);
		return id;
	}
	
	@RequestMapping(value="/CheckEmailExisted",method=RequestMethod.POST)
	public @ResponseBody Integer checkEmailExisted(HttpServletRequest request) throws Exception{
		String email = request.getParameter("email");
		//System.out.println("接收到的email为:"+email);
		Integer id = userService.queryEmailExisted(email);
		return id;
	}
	
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
