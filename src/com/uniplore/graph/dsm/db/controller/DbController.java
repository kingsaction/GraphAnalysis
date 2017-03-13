package com.uniplore.graph.dsm.db.controller;


import com.alibaba.fastjson.JSON;
import com.uniplore.graph.dsm.db.entity.DbPO;
import com.uniplore.graph.dsm.db.entity.DbVO;
import com.uniplore.graph.dsm.db.service.IDbService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/dsm/db")
public class DbController {
  @Autowired
  private IDbService dbService;

  /** 接收用户客户端传来的参数，使用JDBC建立连接测试是否能够成功的连接数据库.
  * @param dbPo 接收并保存数据库连接参数
  * @return  Srting类型返回值，返回的时连接数据库返回的信息，该信息从数据库服务器端得到
  * @throws Exception  提供全局异常处理机制 
  */
  @RequestMapping(value = "/connection",method = RequestMethod.POST)
  public @ResponseBody String testConnectDatabase(DbPO dbPo)throws Exception {
    //查看是否正确的接受到从客户端传来的参数
    //System.out.println("从客户端接收到的连接参数为:"+dbPO.toString());
    return dbService.connectDataBase(dbPo);   //service层完成连接数据库的功能
  }
  
  /**
   * 功能描述: 当成功的连接上数据库之后，跳转到指定的显示页面.
   * @return 返回值
   * @throws Exception 抛出异常
   */
  @RequestMapping(value = "/dbPage",method = {RequestMethod.POST})
  public String dispatcherDb(HttpServletRequest request) throws Exception {
    return "/dsm/db/dbPage";
  }
  
  /**
   * 功能描述： 接收数据库的连接信息，将此ip下的所有数据库返回给用户.
   * @param dbPo  接收数据库的连接信息，封装为对象
   * @return   返回值，返回此连接下的所有的数据库名
   * @throws Exception  抛出异常
   */
  @RequestMapping(value = "/showDatabase",method = RequestMethod.POST)
  public @ResponseBody Map<String, Object> showDatabase(DbPO dbPo)throws Exception {
    //System.out.println("接收到的数据库连接信息为:" + dbPo.toString());
    
    List<String> dbList = dbService.showDataBase(dbPo);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("dbNames",dbList);
    return map;
  }
  
  /**
   * 功能说明: .
   * @param dbPo  接收客户端传来的数据库连接参数
   * @param dbName  接收从客户端传来的数据库名参数
   * @return  返回该数据库的所有表集合
   * @throws Exception  抛出异常
   */
  @RequestMapping(value = "/showTable",method =  RequestMethod.POST) 
  public @ResponseBody Map<String, Object> showTable(DbPO dbPo , @RequestParam(value = "dbName",
      required = true) String  dbName) throws Exception {
    //System.out.println("获取到的数据库连接信息为:" + dbPo.toString());
    //System.out.println("获取到的数据库名为:" + dbName);
    
    //使用JDBC连接数据库，并返回相应的表
    List<String> tableList = dbService.showTable(dbPo,dbName);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("tables", tableList);
    return map;
  }
  
  /**
   * 功能说明: 根据从客户端传来的表，返回该表中的列名.
   * @param dbPo  封装客户端传来的数据库连接信息参数
   * @param dbName 接收客户端传来的数据库名参数
   * @param tableName  接收客户端传来的表名参数
   * @return  返回该表对应的列名
   * @throws Exception  抛出异常，方便全局异常处理
   */
  @RequestMapping(value = "/showColumn",method = RequestMethod.POST)
  public @ResponseBody Map<String, Object> showColumn(DbPO dbPo,String dbName,String tableName) 
      throws Exception {
    //System.out.println("-----显示表时传来的参数-----");
    //System.out.println(dbPo.toString());
    //System.out.println(dbName);
    //System.out.println(tableName);
    
    //使用JDBC连接数据库，并获取表中的列名
    List<String> columnList = dbService.showColumn(dbPo,dbName,tableName);
    Map<String, Object>  map = new HashMap<String, Object>();
    map.put("columns", columnList);
    return map;
  }
  
  /**
   * 功能说明:  接收从客户端传来的参数，从指定的数据库表中获取到数据，将其构造成符合cytoscape格式的数据.
   * @param dbPo  接收数据库的连接参数
   * @param dbVo  接收数据库名、数据库表名、源点、目标点的
   * @return  返回JSON字符串，需要用alibaba开源的fastJSON将任意字符串转成JSON串
   * @throws Exception  抛出异常
  */
  @RequestMapping(value = "/dbDataFormatJson" ,method = RequestMethod.POST)
  public @ResponseBody String dbDataFormatJson(DbPO dbPo,DbVO dbVo) throws Exception {
    //System.out.println("从客户端接收到的数据库连接信息为:" + dbPo.toString());
    //System.out.println("从客户端接收到的表单信息为:" + dbVo.toString());
    
    String jsonContent = dbService.dbDataFormatJson(dbPo,dbVo);
    
    //将上述字符串重新解析
    Object parse = JSON.parse(jsonContent);
    String outputString = parse.toString();
    //System.out.println("------在控制器端得到的数据------");
    //System.out.println(outputString);
    //System.out.println("拼接完成");
    return outputString;
  }

  /**
   * 功能: 保存从客户端接收到的用户点击node的id值，并保存到session，供后续的事件获取.
   * @param request  客户端请求
   */
  @RequestMapping(value = "/saveNodeId" ,method = RequestMethod.POST)
  public @ResponseBody Map<String, Object> saveNodeId(HttpServletRequest request) {
    String nodeId = request.getParameter("nodeID");
    String color = request.getParameter("color");
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("nodeID", nodeId);
    map.put("color", color);
    return map;
  }
  
}
