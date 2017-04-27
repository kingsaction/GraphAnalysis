package com.uniplore.graph.util.mybatisutils;

import com.alibaba.druid.pool.DruidDataSource;
import com.uniplore.graph.dsm.db.entity.DbPO;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;
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
    //MyBatis自带的数据库连接
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
    SqlSession sesssion = sqlSessionFactory.openSession();
    Connection connection = null;
    try {
      connection = sesssion.getConnection();
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
  
  /**
   * 功能: 测试上述代码是否能够正常工作.
   * @param args  主函数参数
   */
  public static void main(String[] args) {
    Properties prop = new Properties();
    prop.setProperty("driverName", "com.mysql.jdbc.Driver");
    prop.setProperty("url", "jdbc:mysql://192.168.100.172:3306");
    prop.setProperty("userName", "root");
    prop.setProperty("password", "mysql");
    
    
    /*
    MybatisUtils mybatisUtils = new MybatisUtils();
    mybatisUtils.setProperties(prop);
    DataSource dataSource = mybatisUtils.getDataSource();*/
    /*try {
      connection = dataSource.getConnection();
      System.out.println(connection);
    } catch (SQLException ex) {
      String message = ex.getMessage();
      System.out.println(message);
    }*/
    //System.out.println(dataSource == null ? "dataSource为空" : "dataSource不为空") ;
    /*TransactionFactory transactionFactory = new JdbcTransactionFactory();
    Environment environment = new Environment("development", transactionFactory, dataSource);
    Configuration configuration = new Configuration(environment);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    SqlSession session = sqlSessionFactory.openSession();
    System.out.println("session:" + session);
    try {
      Connection connection = session.getConnection();
      System.out.println(connection);
    } catch (Exception ex) {
      String message = ex.getMessage();
      System.out.println(message);
    }*/
    
    // 参考资料 http://stackoverflow.com/questions/22517318/cant-find-some-mybatis-classes-to-import-in-getting-started-guide
    // mybatis中获取session http://www.programcreek.com/java-api-examples/index.php?class=org.apache.ibatis.session.SqlSession&method=getConnection
    // Druidp配置，不适用Spring，使用Java代码实现 http://www.itdadao.com/articles/c15a617684p0.html
  }
}
