package com.uniplore.graph.dsm.db.entity;

public class NodeDataVO {
  private String id;    //标记节点的id
  private String name;  //标记节点的名字
  private Integer weight;  //标记节点的权重，节点出现的次数

  public NodeDataVO() {
    super();
  }
  
  /**
   * 功能: 带参数的构造函数.
   * @param id  节点的id
   * @param name  节点名字
   * @param weight  节点权重
   */
  public NodeDataVO(String id, String name, Integer weight) {
    super();
    this.id = id;
    this.name = name;
    this.weight = weight;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }
  
}
