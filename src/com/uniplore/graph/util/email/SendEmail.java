package com.uniplore.graph.util.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.uniplore.graph.common.entity.IPerson;
import com.uniplore.graph.ms.sign.entity.UserPO;
import com.uniplore.graph.util.email.GMailAuthenticator;
public class SendEmail {

	public SendEmail(String desAddress,IPerson person,String emailContent) throws Exception{
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
	        
	        MimeMessage message = new MimeMessage(session);
	        Address fromAddress = new InternetAddress(from,"优联博睿");
	        Address toAddress = new InternetAddress(desAddress);

	        message.setFrom(fromAddress);
	        message.setRecipient(Message.RecipientType.TO, toAddress);

	        message.setSubject("激活Graph Analysis账户");
	        message.setSentDate(new Date());
	        message.setContent(emailContent,"text/html;charset=utf-8");
	        Transport transport = session.getTransport();
	        transport.connect(host, from, authcode);
	        message.saveChanges();
	        Transport.send(message);
	        transport.close();

	    }catch(Exception ex){
	    	throw new RuntimeException(ex);
	    }
	}
}
