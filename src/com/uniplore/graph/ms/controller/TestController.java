package com.uniplore.graph.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniplore.graph.ms.service.ITestService;

/*
 * 
 * 版权所有  2016-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述  
 * 类名称    com.uniplore.graph.ms.controller.TestController       
 * 创建人    朱君鹏
 * 创建时间  2016年12月20日 下午3:47:48     
 * 修改人  
 * 修改时间  2016年12月20日 下午3:47:48     
 * 修改备注     
 * @version  1.0
 */
@Controller
public class TestController {

	@Autowired
	private ITestService testService;
	
	/**
	 * 
	 * @Title  test  
	 * @Description TODO(这里用一句话描述这个方法的作用)  
	 * @return
	 */
	@RequestMapping(value="/test")
	public String test() throws Exception{
		throw new RuntimeException("发生了错误，并抛出"); //模拟异常的处理过程，一定要加throws Exception，否则不能处理
		/*testService.test();
		return "ms/test";*/
		//return "redirect:test1";   //页面重定向，url会发生变化，request不可以共享
		//return "forward:test1";    //页面转发，url不变，request可以共享
	}
	
	/**
	 * 
	 * @Title  test1  
	 * @Description TODO(这里用一句话描述这个方法的作用)  
	 * @param name
	 */
	@RequestMapping(value="/test1")
	public void test1(String name) {
		System.out.println("哈哈");
	}
}
