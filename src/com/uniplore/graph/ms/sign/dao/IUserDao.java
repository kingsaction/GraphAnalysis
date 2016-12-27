package com.uniplore.graph.ms.sign.dao;

import java.util.Date;

import com.uniplore.graph.ms.sign.entity.UserPO;

public interface IUserDao {
	
	public void saveUserInfo(UserPO user) throws Exception;   //保存用户的注册信息到数据库中
	
	public Date queryCreateTime(String code) throws Exception;  //查询账户的创建时间
	
	public void updateActiveTime(UserPO user) throws Exception;   //将激活时间写入到数据库中

	public Integer queryIDbyActiveCode(String code) throws Exception; //根据用户的激活码，查询用户的ID是否存在
	
	public void deleteUserByID(Integer id) throws Exception;  //根据用户的id删除用户

	public Date queryActiveTime(Integer id) throws Exception;  //根据用户的id查询用户的激活时间，如果没有激活返回null
	
	
}
