package com.uniplore.graph.dsm.db.entity;

public class DbPO {

  private int id;                     //唯一的id标识每一个连接信息
  private String driverName;          //驱动名称
  private String dataBaseType;        //数据库类型
  private String ipAddress;           //ip或者主机地址
  private String portNumber;          //端口号
  private String connectionName;      //连接名称，自己定
  private String userName;            //连接用户名
  private String password;            //连接密码

  public DbPO() {
    super();
    // TODO Auto-generated constructor stub
  }

  /** 构造函数.
   * @param id   每个不同的数据库连接都有一个唯一的id进行标识
   * @param driverName  数据库驱动名
   * @param dataBaseType  数据库类型
   * @param ipAddress     连接ip地址
   * @param portNumber    数据库连接端口号
   * @param connectionName  数据库连接名
   * @param userName    数据库用户名
   * @param password    数据库密码
   */
  public DbPO(int id, String driverName, String dataBaseType, String ipAddress, String portNumber,
        String connectionName, String userName, String password) {
  super();
    this.id = id;
    this.driverName = driverName;
    this.dataBaseType = dataBaseType;
    this.ipAddress = ipAddress;
    this.portNumber = portNumber;
    this.connectionName = connectionName;
    this.userName = userName;
    this.password = password;
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getDriverName() {
    return driverName;
  }
  
  public void setDriverName(String driverName) {
    this.driverName = driverName;
  }
  
  public String getDataBaseType() {
    return dataBaseType;
  }
  
  public void setDataBaseType(String dataBaseType) {
    this.dataBaseType = dataBaseType;
  }
  
  public String getIpAddress() {
    return ipAddress;
  }
  
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }
  
  public String getPortNumber() {
    return portNumber;
  }
  
  public void setPortNumber(String portNumber) {
    this.portNumber = portNumber;
  }
  
  public String getConnectionName() {
    return connectionName;
  }
  
  public void setConnectionName(String connectionName) {
    this.connectionName = connectionName;
  }
  
  public String getUserName() {
    return userName;
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  @Override
  public String toString() {
    return "DbPO [id=" + id + ", driverName=" + driverName + ", dataBaseType=" + dataBaseType + ", "
        + "ipAddress=" + ipAddress + ", portNumber=" + portNumber + ", connectionName=" 
        + connectionName + ", userName=" + userName + ", password=" + password + "]";
  }
}
