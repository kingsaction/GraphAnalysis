package com.uniplore.graph.ms.sign.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uniplore.graph.ms.sign.dao.IUserDao;
import com.uniplore.graph.ms.sign.entity.UserPO;
import com.uniplore.graph.ms.sign.mapper.UserMapper;

@Repository
public class UserDao implements IUserDao {

	@Autowired
	private UserMapper um;
	
	@Override
	public void saveUserInfo(UserPO user) throws Exception {
		um.saveUserInfo(user);
	}

}
