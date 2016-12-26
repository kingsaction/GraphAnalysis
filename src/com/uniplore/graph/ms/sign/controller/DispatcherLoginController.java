package com.uniplore.graph.ms.sign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class DispatcherLoginController {

	@RequestMapping(value="/sign_in")
	public String dispatcherLogin(){
		return "ms/login";
	}
}
