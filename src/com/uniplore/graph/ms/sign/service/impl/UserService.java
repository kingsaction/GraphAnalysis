package com.uniplore.graph.ms.sign.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniplore.graph.ms.sign.dao.impl.UserDao;
import com.uniplore.graph.ms.sign.entity.UserPO;
import com.uniplore.graph.ms.sign.service.IUserService;

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
		userDao.saveUserInfo(user);
	}

}
