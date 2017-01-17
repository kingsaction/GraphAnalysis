package com.uniplore.graph.dsm.db.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniplore.graph.dsm.db.entity.DbPO;

@Controller
@RequestMapping(value="/dsm/db")
public class dbController {

	@RequestMapping(value="/connection",method=RequestMethod.POST)
	public String connectionDatabase(DbPO dbPO){
		//查看是否正确的接受到从客户端传来的参数
		System.out.println("从客户端接收到的连接参数为:"+dbPO.toString());
		
		
		return null;
	}
}
