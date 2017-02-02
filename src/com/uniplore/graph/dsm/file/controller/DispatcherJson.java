package com.uniplore.graph.dsm.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispatcherJson {

	@RequestMapping(value="/JsonTypeDataAnalysis")
	public String dispatcherJson(){
		return "/dsm/file/json/json";
	}
}
