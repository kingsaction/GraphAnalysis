package com.uniplore.graph.dsm.db.entity;

import com.alibaba.fastjson.JSON;

public class EdgeVO {
  private EdgeDataVO data;
  private String group;
  private boolean removed;
  private boolean selected;
  private boolean selectable;
  private boolean locked;
  private boolean grabbed;
  private boolean grabbable;
  private String classess;
  
  public EdgeVO() {
    super();
  }

  /**
   * 功能:带参数构造函数.
   * @param data  节点数据
   * @param group  cytoscape组属性
   * @param removed  cytoscape属性
   * @param selected  cytoscape属性
   * @param selectable  cytoscape属性
   * @param locked   cytoscape属性
   * @param grabbed   cytoscape属性
   * @param grabbable  cytoscape属性
   * @param classess  cytoscape属性
   */
  public EdgeVO(EdgeDataVO data, String group, boolean removed, boolean selected, 
      boolean selectable, boolean locked,boolean grabbed, boolean grabbable, String classess) {
    super();
    this.data = data;
    this.group = group;
    this.removed = removed;
    this.selected = selected;
    this.selectable = selectable;
    this.locked = locked;
    this.grabbed = grabbed;
    this.grabbable = grabbable;
    this.classess = classess;
  }

  public EdgeDataVO getData() {
    return data;
  }

  public void setData(EdgeDataVO data) {
    this.data = data;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public boolean isRemoved() {
    return removed;
  }

  public void setRemoved(boolean removed) {
    this.removed = removed;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public boolean isSelectable() {
    return selectable;
  }

  public void setSelectable(boolean selectable) {
    this.selectable = selectable;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public boolean isGrabbed() {
    return grabbed;
  }

  public void setGrabbed(boolean grabbed) {
    this.grabbed = grabbed;
  }

  public boolean isGrabbable() {
    return grabbable;
  }

  public void setGrabbable(boolean grabbable) {
    this.grabbable = grabbable;
  }

  public String getClassess() {
    return classess;
  }

  public void setClassess(String classess) {
    this.classess = classess;
  }
  
  /**
   * 功能: 单元测试.
   * @param args  参数
   */
  public static void main(String[] args) {
    EdgeDataVO data = new EdgeDataVO("e01", "n01", "n01", 100);
    EdgeVO edgeVo = new EdgeVO(data, "edges", false, false, true, false, false, false, "");
    String jsonString = JSON.toJSONString(edgeVo);
    System.out.println(jsonString);
  }
}
