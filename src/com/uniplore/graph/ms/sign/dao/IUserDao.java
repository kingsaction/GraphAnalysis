package com.uniplore.graph.ms.sign.dao;

import com.uniplore.graph.ms.sign.entity.UserPO;

public interface IUserDao {
	
	public void saveUserInfo(UserPO user) throws Exception;   //保存用户的注册信息到数据库中
}
