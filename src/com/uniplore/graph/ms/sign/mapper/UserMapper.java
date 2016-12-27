package com.uniplore.graph.ms.sign.mapper;

import java.util.Date;

import com.uniplore.graph.ms.sign.entity.UserPO;

public interface UserMapper {

	public void saveUserInfo(UserPO user) throws Exception;

	public Date queryCreateTime(String code) throws Exception;

	public void updateActiveTime(UserPO user) throws Exception; 
	
	public Integer queryIDbyActiveCode(String code)throws Exception;

	public Date queryActiveTime(Integer id)throws Exception;

	public void deleteUserByID(Integer id)throws Exception;
}
