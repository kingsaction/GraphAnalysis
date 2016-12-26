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
		
		//发送邮件给用户，提醒其激活用户
		new SendEmail(user.getEmail());
	}

}
