package com.uniplore.graph.dsm.db.controller;


import com.uniplore.graph.dsm.db.entity.DbPO;
import com.uniplore.graph.dsm.db.service.IDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/dsm/db")
public class DbController {
  @Autowired
  private IDbService dbService;

  /** 接收用户客户端传来的参数，使用JDBC建立连接测试是否能够成功的连接数据库.
  * @param dbPo 接收并保存数据库连接参数
  * @return  返回值
  * @throws Exception  提供全局异常处理机制 
  */
  @RequestMapping(value = "/connection",method = RequestMethod.POST)
  public @ResponseBody String testConnectDatabase(DbPO dbPo)throws Exception {
    //查看是否正确的接受到从客户端传来的参数
    //System.out.println("从客户端接收到的连接参数为:"+dbPO.toString());
    return dbService.connectDataBase(dbPo);   //service层完成连接数据库的功能
  }
}
