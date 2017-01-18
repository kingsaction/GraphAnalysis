package com.uniplore.graph.dsm.db.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uniplore.graph.dsm.db.entity.DbPO;
import com.uniplore.graph.dsm.db.service.IDbService;

@Controller
@RequestMapping(value="/dsm/db")
public class dbController {

	@Autowired
	private IDbService dbService;
	/**
	 * 接收用户客户端传来的参数，使用JDBC建立连接测试是否能够成功的连接数据库
	 * @param dbPO
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/connection",method=RequestMethod.POST)
	public @ResponseBody String testConnectDatabase(DbPO dbPO)throws Exception{
		//查看是否正确的接受到从客户端传来的参数
		//System.out.println("从客户端接收到的连接参数为:"+dbPO.toString());

		return dbService.connectDataBase(dbPO);   //service层完成连接数据库的功能
	}
}
