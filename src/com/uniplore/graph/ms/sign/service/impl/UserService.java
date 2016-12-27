package com.uniplore.graph.ms.sign.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniplore.graph.ms.sign.dao.impl.UserDao;
import com.uniplore.graph.ms.sign.entity.UserPO;
import com.uniplore.graph.ms.sign.service.IUserService;
import com.uniplore.graph.utils.email.SendEmail;
import com.uniplore.graph.utils.time.ComputeTime;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void saveUserInfo(UserPO user) throws Exception {
		//设置用户的状态为0，也就是账户还没有被激活
		boolean flag = false;
		user.setAccountState(flag);
		
		//设置创建用户的时间
		Date date = new Date();
		user.setCreateTime(date);
		
		//生成一个特定的UUID
		String uuid = UUID.randomUUID().toString();
		
		//给用户设置uuid
		user.setActiveCode(uuid);
		
		//保存用户信息到数据库中
		userDao.saveUserInfo(user);
		
		//在服务层设置发送邮件的内容，规定只能设置html格式的内容
		String emailContent = "<h3>欢迎您使用Graph Analysis提供的服务!</h3><h3>您的账户已经成功的创建，请点击或复制以下链接激活账号(30分钟之内有效)：</h3><h3><a href='http://localhost:8080/graphanalysis/ms/sign/ActiveAccount?code="+user.getActiveCode()+"'>http://localhost:8080/graphanalysis/ms/sign/ActiveAccount?code="+user.getActiveCode()+"</a>"+"</h3>"+"<h3>请妥善保管这封电子邮件，您的账号名为:"+user.getUserName()+"</h3>"+"<h3>如果您忘记了密码，可以在用户登录界面通过'找回密码'链接，重置您的密码。</h3><h3>Graph Analysis同您一同成长，感谢您的注册</h3>";

		//发送邮件给用户，提醒其激活用户,同时传递了用户的激活码
		new SendEmail(user.getEmail(),user,emailContent);
	}

	@Override
	public boolean activeUserAccount(String code) throws Exception {
		
		UserPO user = new UserPO();
		//首先应该判断此code是否在数据库中是存在的，如果不存在就直接结束整个过程
		//根据code去获取用户的id
		Integer queryID = userDao.queryIDbyActiveCode(code);
		
		if(queryID > 0 ){
			//设置只能激活一次，也就是去查询用户的active_time，看是否为空，如果为空说明还没有被激活，
			//如果不为空，说明已经激活了，此时应该不允许用户再次激活
			Date queryActiveTime = userDao.queryActiveTime(queryID);
			if (queryActiveTime != null) {
				return true;
			}
			//获取到当前时间
			Date activeTime = new Date();
		    //System.out.println("当前时间为:"+activeTime);
		    
			//从数据库中拿出创建账户的时间
			Date createTime = userDao.queryCreateTime(code);
			//System.out.println("创建账户的时间为:"+createTime);
			
			//计算当前时间和创建账户的时间差
			long computeTime = ComputeTime.computeTime(createTime,activeTime);
			//System.out.println("创建账户和激活账户之间的时间差:"+computeTime);
			
			if(computeTime > 30){
				//当在给定的时间内不能激活用户，当用户再次点击这个链接时，要求用户重新进行注册
				//此时首先应该删除用户原来的注册信息
				userDao.deleteUserByID(queryID);
				return false;
			}
			
			//更新用户的状态，将active_state设置为1
			boolean accountState = true;
			user.setAccountState(accountState);
			
			//将激活时间设置到user中
			user.setActiveTime(activeTime);
			user.setId(queryID);
			//如果时间没有超过30分钟，将激活时间写入到数据库中
			userDao.updateActiveTime(user);
			return true;
		}else{
			//否则说明用户的激活码根本就是错误的，直接退出程序
			return false;
		}
		
		
	}

}
