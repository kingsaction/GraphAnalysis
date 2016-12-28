package com.uniplore.graph.ms.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniplore.graph.ms.login.dao.ILoginDao;
import com.uniplore.graph.ms.login.service.ILoginService;
import com.uniplore.graph.ms.sign.entity.UserPO;

@Service
@Transactional
public class LoginService implements ILoginService {

	@Autowired
	private ILoginDao loginDao;
	
	@Override
	public Integer queryIDbyEmail(String email) throws Exception {
		return loginDao.queryIDbyEmail(email);
	}

	@Override
	public Integer queryIDbyUserName(String userName) throws Exception {
		return loginDao. queryIDbyUserName(userName);
	}

	@Override
	public String queryPasswordbyID(Integer id) throws Exception {
		return loginDao.queryPasswordbyID(id);
	}

	@Override
	public boolean queryStatebyID(Integer id) throws Exception {
		return loginDao.queryStatebyID(id);
	}

	@Override
	public UserPO queryInfobyID(Integer id) throws Exception {
		return loginDao.queryInfobyID(id);
	}

}
