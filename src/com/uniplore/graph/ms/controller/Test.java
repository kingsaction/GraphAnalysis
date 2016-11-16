package com.uniplore.graph.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test {
	@RequestMapping(value="/test")
	public String test(){
		return "common/header/header-apple";
	}
}
