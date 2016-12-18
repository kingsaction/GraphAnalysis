package com.uniplore.graph.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniplore.graph.ms.dao.ITestUserDao;
import com.uniplore.graph.ms.dao.impl.TestUserDao;

public interface ITestService {
	
	public void test();
}
