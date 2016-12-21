package com.uniplore.graph.ms.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class HandlerException implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		
		CustomException customException = null;
		if (exception instanceof CustomException) {
			customException = (CustomException)exception;
		}else {
			customException = new CustomException("未知错误");
		}
		
		String message = customException.getMessage();   //获取到错误信息
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("ms/error");    //error是逻辑视图名，视图解析器会将其解析为真正的物理视图error.jsp
		
		return modelAndView;
	}

}
