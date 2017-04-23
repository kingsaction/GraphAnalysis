package com.uniplore.graph.dsm.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能： 数据库的连接信息.
 * @author 朱君鹏
 *
 */
public @Data @NoArgsConstructor @AllArgsConstructor class DbPO {

  private int id;                     //唯一的id标识每一个连接信息
  private String driverName;          //驱动名称
  private String dataBaseType;        //数据库类型
  private String ipAddress;           //ip或者主机地址
  private String portNumber;          //端口号
  private String connectionName;      //连接名称，自己定
  private String dataBaseName;       //mysql连接时不需要提供初始数据库，但是PostgreSQL和Greenplum需要
  private String userName;            //连接用户名
  private String password;            //连接密码
}
