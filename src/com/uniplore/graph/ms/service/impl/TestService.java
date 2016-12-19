package com.uniplore.graph.ms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniplore.graph.ms.dao.ITestUserDao;
import com.uniplore.graph.ms.service.ITestService;

@Service
@Transactional
public class TestService implements ITestService {

	@Autowired 
	private ITestUserDao userDao;
	
	@Override
	public void test() {
		userDao.test();
	}

}
