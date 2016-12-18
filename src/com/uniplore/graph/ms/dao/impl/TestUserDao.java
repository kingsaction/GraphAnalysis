package com.uniplore.graph.ms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uniplore.graph.ms.dao.ITestUserDao;
import com.uniplore.graph.ms.mapper.TestMapper;

@Repository
public class TestUserDao implements ITestUserDao{

	@Autowired
	private TestMapper tm;
	@Override
	public void test() {
		System.out.println(tm.findByEmail("13101900@qq.com"));
	}

}
