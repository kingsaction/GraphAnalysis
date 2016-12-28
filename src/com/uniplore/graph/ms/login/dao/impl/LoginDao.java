package com.uniplore.graph.ms.login.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uniplore.graph.ms.login.dao.ILoginDao;
import com.uniplore.graph.ms.login.mapper.LoginMapper;
import com.uniplore.graph.ms.sign.entity.UserPO;

@Repository
public class LoginDao implements ILoginDao {

	@Autowired
	private LoginMapper lm;
	
	@Override
	public Integer queryIDbyEmail(String email) throws Exception {
		return lm.queryIDbyEmail(email);
	}

	@Override
	public Integer queryIDbyUserName(String userName) throws Exception {
		return lm.queryIDbyUserName(userName);
	}

	@Override
	public String queryPasswordbyID(Integer id) throws Exception {
		return lm.queryPasswordbyID(id);
	}

	@Override
	public boolean queryStatebyID(Integer id) throws Exception {
		return lm.queryStatebyID(id);
	}

	@Override
	public UserPO queryInfobyID(Integer id) throws Exception {
		return lm.queryInfobyID(id);
	}

}
