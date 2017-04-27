package com.uniplore.graph.util.mybatisutils;

import com.alibaba.druid.pool.DruidDataSource;
import com.uniplore.graph.dsm.db.entity.DbPO;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;


public class MybatisUtils implements DataSourceFactory {
  private Properties properties;   //初始化的配置参数，包括数据库用户名、数据库密码、数据库用户名、数据库url
  
  @Override
  public DataSource getDataSource() {
    //MyBatis自带的数据库连接池
    //PooledDataSource dataSource = new PooledDataSource();
    //dataSource.setDriver(properties.getProperty("driverName"));
    //dataSource.setUrl(properties.getProperty("url"));
    //dataSource.setUsername(properties.getProperty("userName"));
    //dataSource.setPassword(properties.getProperty("password"));
    //return dataSource;
    
    //本例采用阿里巴巴druid连接池，当然你也可以采用c3p0、DBCP等连接池，但是Druid的性能最好
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName(properties.getProperty("driverName"));
    dataSource.setUrl(properties.getProperty("url"));
    dataSource.setUsername(properties.getProperty("userName"));
    return dataSource;
    
  }

  @Override
  public void setProperties(Properties properties) {
    this.properties = properties;
  }
  
  /**
   * 功能: 使用mybatis创建Connection对象，使用了mybatis的特性.
   * @param dbPo  数据库连接参数
   * @return  返回值
   */
  public static Connection getConnection(DbPO dbPo) {
    String driverName = dbPo.getDriverName();
    String url = null;
    String dataBaseName = dbPo.getDataBaseName();
    if (driverName != null && driverName.contains("mysql")) {
      if (dataBaseName == null) {
        url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber();
      } else {
        url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() + "/"
          + dataBaseName;
      }
    } else if (driverName != null && driverName.contains("postgresql")) {
      if (dataBaseName == null) {
        url = "jdbc:postgresql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() + "/?";
      } else {
        url = "jdbc:postgresql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() + "/" 
          + dataBaseName ;
      }
      
    } else if (driverName != null && driverName.contains("pivotal")) {
      url = "jdbc:pivotal:greenplum://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() 
        + ";DatabaseName=" + dataBaseName ;
    } else if (driverName != null && driverName.contains("oracle")) {
      url = "jdbc:oracle:thin:@" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() + ":" 
        + dataBaseName ;
    }
    String userName = dbPo.getUserName();
    String password = dbPo.getPassword();
    
    Properties properties = new Properties();
    properties.setProperty("driverName", driverName);
    properties.setProperty("url", url);
    properties.setProperty("userName", userName);
    properties.setProperty("password", password);
    MybatisUtils mybatisUtils = new MybatisUtils();
    mybatisUtils.setProperties(properties);
    DataSource dataSource = mybatisUtils.getDataSource();
    TransactionFactory transactionFactory = new JdbcTransactionFactory();
    Environment environment = new Environment("development", transactionFactory, dataSource);
    Configuration config = new Configuration(environment);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
    SqlSession session = sqlSessionFactory.openSession();
    Connection connection = null;
    try {
      connection = session.getConnection();
    } catch (Exception ex) {
      throw new RuntimeException(ex.getMessage());
    }
    return connection;
  }
  
  /**
   * 功能:获取mybatis连接session.
   * @param dbPo   数据库连接参数
   * @return   返回值
   */
  public static SqlSession getSqlSession(DbPO dbPo) {
    String driverName = dbPo.getDriverName();
    String url = null;
    String dataBaseName = dbPo.getDataBaseName();
    if (driverName != null && driverName.contains("mysql")) {
      if (dataBaseName == null) {
        url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber();
      } else {
        url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() + "/"
          + dataBaseName;
      }
    } else if (driverName != null && driverName.contains("postgresql")) {
      if (dataBaseName == null) {
        url = "jdbc:postgresql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() + "/?";
      } else {
        url = "jdbc:postgresql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() + "/" 
           + dataBaseName ;
      }
  
    } else if (driverName != null && driverName.contains("pivotal")) {
      url = "jdbc:pivotal:greenplum://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() 
          + ";DatabaseName=" + dataBaseName ;
    } else if (driverName != null && driverName.contains("oracle")) {
      url = "jdbc:oracle:thin:@" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber() + ":" 
          + dataBaseName ;
    }
    String userName = dbPo.getUserName();
    String password = dbPo.getPassword();

    Properties properties = new Properties();
    properties.setProperty("driverName", driverName);
    properties.setProperty("url", url);
    properties.setProperty("userName", userName);
    properties.setProperty("password", password);
    MybatisUtils mybatisUtils = new MybatisUtils();
    mybatisUtils.setProperties(properties);
    DataSource dataSource = mybatisUtils.getDataSource();
    TransactionFactory transactionFactory = new JdbcTransactionFactory();
    Environment environment = new Environment("development", transactionFactory, dataSource);
    Configuration config = new Configuration(environment);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
    SqlSession sesssion = sqlSessionFactory.openSession();
    return sesssion;
  }
}
