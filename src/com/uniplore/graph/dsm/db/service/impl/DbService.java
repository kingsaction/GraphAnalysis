package com.uniplore.graph.dsm.db.service.impl;

import com.uniplore.graph.dsm.db.entity.DbPO;
import com.uniplore.graph.dsm.db.service.IDbService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DbService implements IDbService {

  @Override
  public String connectDataBase(DbPO dbPo) throws Exception {
    // 使用JDBC连接数据库
    String driverName = dbPo.getDriverName();// 首先应该得到其驱动，判断究竟是何种数据库‘
    String url = null;
    if (driverName != null && driverName.contains("mysql")) {
      url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber()
        + "?connectTimeout=3000&socketTimeout=3000"; // 设置连接超时的时间均是3s，如果3s未连接成功则直接终止连接
    }
    String user = dbPo.getUserName();
    String password = dbPo.getPassword();
    try {
      Class.forName(driverName);
      // 连接数据库
      Connection connection = DriverManager.getConnection(url, user, password);
      if (dbPo.getIpAddress().length() != 0 && connection != null) {
        return "数据库连接成功";
      }
    } catch (Exception ex) {
      String message = ex.getMessage(); // 会打印出真实的数据库连接错误信息
      if (message.contains("Access denied")) {
        return "用户名和密码无效";
      } else if (message.contains("Communications link failure")) {
        return "与数据库通信时出错，不能连接到数据库服务器，请检查服务器是否正在运行以及您是否有权访问请求的数据库";
      } else {
        return "数据库连接失败";
      }
    }
    return "数据库连接失败";
  }

  @Override
  public List<String> showDataBase(DbPO dbPo) throws Exception {
    //建立一数组，用于存放此ip地址下所有的数据库
    List<String> dataBaseList = new ArrayList<String>();

    //使用JDBC连接数据库
    Class.forName(dbPo.getDriverName());
    
    String url = null;
    if (dbPo.getDriverName() != null && dbPo.getDriverName().contains("mysql")) {
      url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber()
        + "?connectTimeout=3000&socketTimeout=3000"; // 设置连接超时的时间均是3s，如果3s未连接成功则直接终止连接
    }
    // 连接数据库
    Connection connection = DriverManager.getConnection(url, dbPo.getUserName(),
        dbPo.getPassword());
    
    /* 功能： 连接上数据库之后，获取数据库中所有的数据库名
     * MySQL数据库必须采用getCatalogs()方法
     * 但是对于其他的数据库，采用的是getSchemas()方法
     * */
    ResultSet dbNames = connection.getMetaData().getCatalogs();   
    while (dbNames.next()) {
      String dbName = dbNames.getString("TABLE_CAT");
      //System.out.println("TABLE_CAT = " + dbName );  //判断是否正确的接收到表
      dataBaseList.add(dbName);
    }
    
    if (dataBaseList.size() != 0) {
      return dataBaseList;
    }
    
    return null;
   
  }
  
  
  @Override
  public List<String> showTable(DbPO dbPo,String dbName) throws Exception {
    List<String> tableList = new ArrayList<String>();
    //使用JDBC连接数据库
    Class.forName(dbPo.getDriverName());

    String url = null;
    if (dbPo.getDriverName() != null && dbPo.getDriverName().contains("mysql")) {
      url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber()
        + "/" + dbName + "?connectTimeout=3000&socketTimeout=3000";
    }
    //System.out.println("url为:" + url);
    // 连接数据库
    Connection connection = DriverManager.getConnection(url, dbPo.getUserName(), 
        dbPo.getPassword());
    
    ResultSet tables = connection.getMetaData().getTables(null, null, "%", null);
    while (tables.next()) {
      String table = tables.getString(3);
      //System.out.println(table);
      tableList.add(table);
    }
    //System.out.println(tableList.size());
    if (tableList.size() != 0 ) {
      return tableList;
    }
    return null;
  }

  @Override
  public List<String> showColumn(DbPO dbPo, String dbName, String tableName) throws Exception {
    List<String> columnList = new ArrayList<String>();
    //使用JDBC连接数据库
    Class.forName(dbPo.getDriverName());
    
    String url  = "";
    if (dbPo.getDriverName() != null && dbPo.getDriverName().contains("mysql")) {
      url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber()
          + "/" + dbName  + "?connectTimeout=3000&socketTimeout=3000";
    }
    //System.out.println("url为:" + url);
    Connection connection = DriverManager.getConnection(url, dbPo.getUserName(), 
            dbPo.getPassword());
    ResultSet columns = connection.getMetaData().getColumns(null, null, tableName, null);
    while (columns.next()) {
      String column = columns.getString("COLUMN_NAME");
      //System.out.println(column);
      columnList.add(column);
    }
    
    if (columnList.size() != 0) {    //如果该数据库中有表则返回columnList，否则返回空
      return columnList;
    }
    
    return null;
  }

}
