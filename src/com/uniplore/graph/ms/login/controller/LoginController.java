package com.uniplore.graph.ms.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniplore.graph.ms.login.service.ILoginService;
import com.uniplore.graph.ms.sign.entity.UserPO;

@Controller
@RequestMapping(value="/ms/login")
public class LoginController {
	
	@Autowired
	private ILoginService loginService;
	/**
	 * 
	 * @Title  loginSys  
	 * @Description 登录到系统 
	 * @param user
	 */
	@RequestMapping(value="/Login",method=RequestMethod.POST)
	public String loginSys(String name, String password,HttpSession session,HttpServletRequest request)throws Exception{
		//System.out.println("接收到的名字为:"+name);
		//System.out.println("接收到的密码为:"+password);
		
		//判断接收到的名字究竟是username还是email地址，只要判断字符串中是否包含@符号即可
		boolean nameIsEmail = name.contains("@");
		//System.out.println("是否是email"+nameIsEmail);
		if(nameIsEmail){
			//如果登录时采用的是email登录，检查数据库中是否已经有该email
			Integer id = loginService.queryIDbyEmail(name);
			//System.out.println(id);
			if(id != null){
				//说明在数据库中存在这样的用户
				//接下来需要验证密码是否正确,根据用户id查找密码，验证密码是否匹配
				String dbPassword = loginService.queryPasswordbyID(id);
				//System.out.println(dbPassword);
				if(password.equals(dbPassword)){
					//说明从客户端传来的密码和数据库中的密码是一致的
					//接下来查看激活状态码active_state是否为1
					boolean state = loginService.queryStatebyID(id);
					//System.out.println("用户的状态为:"+state);
					if (true == state) {
						//说明用户已经被激活
						//System.out.println("用户已经被激活");
						//接下来获得用户的全部信息写入到session中，登录成功
						UserPO userInto = loginService.queryInfobyID(id);
						//System.out.println("获取到的用户信息为:"+userInto.toString());
						session.setAttribute("userInfo", userInto);
						//完成登录，跳转到指定的页面
						return "redirect:/dsm/main";
					}else{
						//说明用户还没有被激活，提醒用户激活
						System.out.println("用户还没有被激活");
						return null;
					}
				}else{
					//说明密码不一致，终止登录
					return null;
				}
			}else{
				//说明在数据库中不存在当前邮箱用户
				return null;
			}
		}else{
			//说明登录时采用的userName登录，需要判断用户名在数据库中是否存在
			Integer id = loginService.queryIDbyUserName(name);
			//System.out.println(id);
			if (id != null) {
				//说明在数据库中存在这样的用户
				//接下来需要验证密码是否正确,根据用户id查找密码，验证密码是否匹配
				String dbPassword = loginService.queryPasswordbyID(id);
				//System.out.println(dbPassword);
				if(password.equals(dbPassword)){
					//说明从客户端传来的密码和数据库中的密码是一致的
					//接下来查看激活状态码active_state是否为1
					boolean state = loginService.queryStatebyID(id);
					//System.out.println("用户的状态为:"+state);
					if(true == state){
						//说明用户已经被激活
						//System.out.println("用户已经被激活");
						//接下来获得用户的全部信息写入到session中，登录成功
						UserPO userInto = loginService.queryInfobyID(id);
						//System.out.println("获取到的用户信息为:"+userInto.toString());
						session.setAttribute("userInfo", userInto);
						//完成登录
						return "dsm/main";
					}else{
						//说明用户还没有被激活
						System.out.println("用户还没有被激活");
						return null;
					}
				}else{
					//说明密码不一致，终止登录
					return null;
				}
			}else{
				//说明在数据库中不存在当前用户，终止登录
				return null;
			}
		}
	}
	
	/**
	 * 
	 * @Title  logoutSys  
	 * @Description 退出系统
	 */
	@RequestMapping(value="/Logout",method=RequestMethod.GET)
	public String logoutSys(HttpSession session){
		//获取session信息
		UserPO userInfo = (UserPO)session.getAttribute("userInfo");
		if(userInfo != null){
			session.removeAttribute("userInfo");
			session.invalidate(); // 使得session失效
		}
		 return "redirect:/ms/login/Login"; // 重定向到登录页面
	}
}
