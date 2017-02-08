package com.uniplore.graph.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.uniplore.graph.util.email.GMailAuthenticator;
import com.uniplore.graph.util.email.SendEmail;

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
		StringWriter writer = new StringWriter();
		customException.printStackTrace(new PrintWriter(writer, true));
		String error = writer.toString();  //得到堆栈的错误信息
		
		try {
	        String host = "smtp.qq.com";     //邮件服务器
	        String from = "13101900@qq.com";   //发送邮件的QQ
	        String authcode = "ecdudaqyndcibhag";  //对于QQ的个人邮箱而言，密码使用的是客户端的授权码，而不是用户的邮箱密码
	        Properties props = System.getProperties();
	        props.put("mail.smtp.host", host);
	        props.setProperty("mail.transport.protocol", "smtp");  // 发送邮件协议名称
	        props.put("mail.smtp.auth", "true");   //开启授权
	        props.put("mail.smtp.user", from);
	        props.put("mail.smtp.password", authcode);
	        props.put("mail.smtp.port", "587");   //smtp邮件服务器的端口号，必须是587,465调试时失败
	        props.setProperty("mail.smtp.ssl.enable", "true");
	        props.setProperty("mail.smtp.connectiontimeout", "5000");
	        
	        Session session = Session.getDefaultInstance(props,new GMailAuthenticator("13101900@qq.com", "ecdudaqyndcibhag"));
	       
	        props.put("mail.debug", "true");
	        
	        MimeMessage emailMessage = new MimeMessage(session);
	        Address fromAddress = new InternetAddress(from,"优联博睿");
	        Address toAddress = new InternetAddress("13101900@qq.com");

	        emailMessage.setFrom(fromAddress);
	        emailMessage.setRecipient(Message.RecipientType.TO, toAddress);

	        emailMessage.setSubject("报错信息");
	        emailMessage.setSentDate(new Date());
	        emailMessage.setContent(error,"text/html;charset=utf-8");
	        Transport transport = session.getTransport();
	        transport.connect(host, from, authcode);
	        emailMessage.saveChanges();
	        Transport.send(emailMessage);
	        transport.close();

	    }catch(Exception ex){
	    	throw new RuntimeException(ex);
	    }
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("common/error/error");    //error是逻辑视图名，视图解析器会将其解析为真正的物理视图error.jsp
		
		return modelAndView;
	}

}
