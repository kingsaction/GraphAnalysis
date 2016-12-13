package com.uniplore.graph.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class DispatcherSignController {

	@RequestMapping(value="sign_up")
	public String dispatcherSign(){
		return "ms/sign";
	}
}
