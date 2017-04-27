package com.uniplore.graph.util.mybatisutils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
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
  private Properties properties;
  
  @Override
  public DataSource getDataSource() {
    /* MyBatis自带的数据库连接池
    PooledDataSource pooledDataSource = new PooledDataSource();
    pooledDataSource.setDriver(properties.getProperty("driverName"));
    pooledDataSource.setUrl(properties.getProperty("url"));
    pooledDataSource.setUsername(properties.getProperty("userName"));
    pooledDataSource.setPassword(properties.getProperty("password"));
    return pooledDataSource;
    */
    //阿里巴巴druid连接池
    DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setDriverClassName(properties.getProperty("driverName"));
    druidDataSource.setUrl(properties.getProperty("url"));
    druidDataSource.setUsername(properties.getProperty("userName"));
    druidDataSource.setPassword(properties.getProperty("password"));
    return druidDataSource;
    
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
