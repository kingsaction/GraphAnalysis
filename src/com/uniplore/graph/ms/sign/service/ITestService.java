package com.uniplore.graph.ms.sign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniplore.graph.ms.sign.dao.ITestUserDao;
import com.uniplore.graph.ms.sign.dao.impl.TestUserDao;

public interface ITestService {
	
	public void test()throws Exception;
}
