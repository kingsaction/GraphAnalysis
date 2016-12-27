package com.uniplore.graph.ms.sign.dao.impl;

import java.util.Date;

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

	@Override
	public Date queryCreateTime(String code) throws Exception {
		return um.queryCreateTime(code);
	}

	@Override
	public void updateActiveTime(UserPO user) throws Exception {
		um.updateActiveTime(user);
	}
	
	@Override
	public Integer queryIDbyActiveCode(String code)throws Exception{
		Integer queryIDbyActiveCode = um.queryIDbyActiveCode(code);
		return queryIDbyActiveCode;
	}

	@Override
	public Date queryActiveTime(Integer id) throws Exception {
		return um.queryActiveTime(id);
	}

	@Override
	public void deleteUserByID(Integer id) throws Exception{
		um.deleteUserByID(id);
	}

}
