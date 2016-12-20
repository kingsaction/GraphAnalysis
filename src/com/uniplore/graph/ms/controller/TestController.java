package com.uniplore.graph.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniplore.graph.ms.service.ITestService;

/**
 * 
 * 项目名称：graphanalysis   
 * 类名称：TestController   
 * 类描述：   
 * 创建人：朱君鹏   
 * 创建时间：2016年12月20日 下午3:25:27   
 * 修改人：朱君鹏   
 * 修改时间：2016年12月20日 下午3:25:27   
 * 修改备注：   
 * @version 1.0
 */
@Controller
public class TestController {

	@Autowired
	private ITestService testService;
	
	@RequestMapping(value="/test")
	public String test(){
		testService.test();
		return "ms/test";
		//return "redirect:test1";   //页面重定向，url会发生变化，request不可以共享
		//return "forward:test1";    //页面转发，url不变，request可以共享
	}
	
	@RequestMapping(value="/test1")
	public void test1() {
		System.out.println("哈哈");
	}
}
