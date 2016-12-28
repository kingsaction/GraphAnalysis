package com.uniplore.graph.ms.login.dao;

import com.uniplore.graph.ms.sign.entity.UserPO;

public interface ILoginDao {

	public Integer queryIDbyEmail(String email)throws Exception;

	public Integer queryIDbyUserName(String userName)throws Exception;

	public String queryPasswordbyID(Integer id)throws Exception;

	public boolean queryStatebyID(Integer id) throws Exception;

	public UserPO queryInfobyID(Integer id)throws Exception;
}
