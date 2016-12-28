package com.uniplore.graph.ms.login.service;

import com.uniplore.graph.ms.sign.entity.UserPO;

public interface ILoginService {

	public Integer queryIDbyEmail(String email)throws Exception;   //根据用户的email查找用户的id，并且保证一个email对应唯一的一个账户

	public Integer queryIDbyUserName(String userName)throws Exception; //根据用户名查找数据库中是否存在相应的用户

	public  String queryPasswordbyID(Integer id)throws Exception;  //根据用户的id查找密码

	public boolean queryStatebyID(Integer id)throws Exception;  //根据用户id查找用户的激活状态

	public UserPO queryInfobyID(Integer id)throws Exception;   //根据用户的id获取用户的全部信息
}
