package com.uniplore.graph.dsm.db.entity;

public class EdgeDataVO {
  private String id;
  private String source;
  private String target;
  private Integer weight;

  public EdgeDataVO() {
    super();
  }
  
  /**
   * 功能: 带参数的构造函数.
   * @param id  边编号
   * @param source  边的起始点
   * @param target  边的终点
   * @param weight  边的权重
   */
  public EdgeDataVO(String id, String source, String target, Integer weight) {
    super();
    this.id = id;
    this.source = source;
    this.target = target;
    this.weight = weight;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }
 
}
