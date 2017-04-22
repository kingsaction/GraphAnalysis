package com.uniplore.graph.util.jdbcutils;

import com.uniplore.graph.dsm.db.entity.DbPO;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtils {
  /**
   * 功能:连接数据库.
   * @param dbPo   连接数据库所需要的参数
   *  @return  返回connection对象
   */
  public static Connection getConnection(DbPO dbPo) {
    // 使用JDBC连接数据库
    String driverName = dbPo.getDriverName();// 首先应该得到其驱动，判断究竟是何种数据库
    //System.out.println("驱动名为:" + driverName);
    String url = null;
    String dataBaseName = dbPo.getDataBaseName();
    if (driverName != null && driverName.contains("mysql")) {
      if (dataBaseName == null) {
        url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber();
      } else {
        url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() + "/"
          + dataBaseName;
        System.out.println(url);
      }
    } else if (driverName != null && driverName.contains("postgresql")) {
      url = "jdbc:postgresql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() + "/" 
        + dataBaseName ;
    } else if (driverName != null && driverName.contains("pivotal")) {
      url = "jdbc:pivotal:greenplum://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() 
        + ";DatabaseName=" + dataBaseName ;
    } else if (driverName != null && driverName.contains("oracle")) {
      url = "jdbc:oracle:thin:@" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() + ":" 
        + dataBaseName ;
    }
    String user = dbPo.getUserName();
    String password = dbPo.getPassword();
    Connection connection;
    try {
      Class.forName(driverName);
      // 连接数据库
      connection = DriverManager.getConnection(url, user, password);
    } catch (Exception ex) {
      throw new RuntimeException(ex.getMessage());
    }
    return connection;
  }
  
  /**
   * 功能: 关闭连接流.
   * @param connection   连接对象
   * @throws Exception   异常对象
   */  
  public static void close(Connection connection) throws Exception {
    if (connection != null) {
      connection.close();
    }
  }
  
}
