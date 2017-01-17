package com.uniplore.graph.dsm.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class DispatcherMain {

	@RequestMapping(value="/dsm/main")
	public String dispatcherMain(){
		return "dsm/main";
	}
}
