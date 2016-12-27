package com.uniplore.graph.ms.sign.service;

import com.uniplore.graph.ms.sign.entity.UserPO;


public interface IUserService {
	
	public void saveUserInfo(UserPO user) throws Exception;
	
	public boolean activeUserAccount(String code) throws Exception;

}
