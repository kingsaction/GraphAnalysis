package com.uniplore.graph.ms.sign.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniplore.graph.ms.sign.dao.impl.UserDao;
import com.uniplore.graph.ms.sign.entity.UserPO;
import com.uniplore.graph.ms.sign.service.IUserService;
import com.uniplore.graph.utils.email.SendEmail;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void saveUserInfo(UserPO user) throws Exception {
		//设置用户的状态为0，也就是账户还没有被激活
		boolean flag = false;
		user.setAccountState(flag);
		
		//设置创建用户的时间
		Date date = new Date();
		user.setCreateTime(date);
		
		//生成一个特定的UUID
		String uuid = UUID.randomUUID().toString();
		
		//给用户设置uuid
		user.setActiveCode(uuid);
		
		//保存用户信息到数据库中
		userDao.saveUserInfo(user);
		
		//在服务层设置发送邮件的内容，规定只能设置html格式的内容
		String emailContent = "<h3>欢迎您使用Graph Analysis提供的服务!</h3><h3>您的账户已经成功的创建，请点击或复制以下链接激活账号：</h3><h3><a href='http://localhost:8080/graphanalysis/ms/sign/activeAccount?code="+user.getActiveCode()+"'>http://localhost:8080/graphanalysis/ms/sign/activeAccount?code="+user.getActiveCode()+"</a>"+"</h3>"+"<h3>请妥善保管这封电子邮件，您的账号名为:"+user.getUserName()+"</h3>"+"<h3>如果您忘记了密码，可以在用户登录界面通过'找回密码'链接，重置您的密码。</h3><h3>Graph Analysis同您一同成长，感谢您的注册</h3>";

		//发送邮件给用户，提醒其激活用户,同时传递了用户的激活码
		new SendEmail(user.getEmail(),user,emailContent);
	}

}
