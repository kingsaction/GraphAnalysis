package com.uniplore.graph.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniplore.graph.ms.dao.IUserDao;
import com.uniplore.graph.ms.dao.impl.UserDao;

@Service
public class TestService {
	
	@Autowired
	private IUserDao userDao;
	
	@RequestMapping(value="/test")
	public void test(){
		userDao.test();
	}
}
