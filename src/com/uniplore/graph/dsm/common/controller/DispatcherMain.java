package com.uniplore.graph.dsm.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 当成功登陆之后，转发到主页上
 * @author 朱君鹏
 *
 */
@Controller
@RequestMapping(value="/")
public class DispatcherMain {

	@RequestMapping(value="/dsm/main")
	public String dispatcherMain(){
		return "dsm/main";
	}
}
