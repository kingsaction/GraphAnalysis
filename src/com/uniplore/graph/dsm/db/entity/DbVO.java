package com.uniplore.graph.dsm.db.entity;

/**
 * 功能: 封装从客户端出来的数据库名、选择的表名、选择的源点、选择的目标点.
 * @author 朱君鹏
 *
 */
public class DbVO {
  private String dbName ;
  private String tableName;
  private String sourceNode ;
  private String targetNode;
  
  public DbVO() {
    super();
  }

  /**
   * 功能: 带参数的构造函数.
   * @param dbName  封装数据库名
   * @param tableName  封装表名
   * @param sourceNode  封装源点
   * @param targetNode   封装目标点
   */
  public DbVO(String dbName, String tableName, String sourceNode, String targetNode) {
    super();
    this.dbName = dbName;
    this.tableName = tableName;
    this.sourceNode = sourceNode;
    this.targetNode = targetNode;
  }

  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getSourceNode() {
    return sourceNode;
  }

  public void setSourceNode(String sourceNode) {
    this.sourceNode = sourceNode;
  }

  public String getTargetNode() {
    return targetNode;
  }

  public void setTargetNode(String targetNode) {
    this.targetNode = targetNode;
  }

  @Override
  public String toString() {
    return "DbVO [dbName=" + dbName + ", tableName=" + tableName + ", sourceNode=" + sourceNode 
      + ", " + "targetNode=" + targetNode + "]";
  }
  

}
