package com.uniplore.graph.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniplore.graph.ms.service.ITestService;

@Controller
public class TestController {

	@Autowired
	private ITestService testService;
	
	@RequestMapping(value="/test")
	public String test(){
		testService.test();
		return "ms/test";
	}
	
}
