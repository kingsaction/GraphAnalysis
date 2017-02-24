package com.uniplore.graph.dsm.db.entity;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;

/**
 * 功能：封装节点对象.
 * @author 朱君鹏
 *
 */
public class NodeVO {
  private NodeDataVO data;
  private String group;
  private boolean removed;
  private boolean selected;
  private boolean selectable;
  private boolean locked;
  private boolean grabbed;
  private boolean grabbable;
  private String classess;

  

  public NodeVO() {
    super();
  }
  
  


  /**
   * 功能： 带参数的构造函数.
   * @param data  cytoscape节点数据
   * @param group  cytoscape组属性
   * @param removed  cytoscape删除属性
   * @param selected  cytoscape属性
   * @param selectable cytoscape属性
   * @param locked  cytoscape属性
   * @param grabbed  cytoscape属性
   * @param grabbable   cytoscape属性
   * @param classess  cytoscape属性
   */
  public NodeVO(NodeDataVO data, String group, boolean removed, boolean selected, 
      boolean selectable, boolean locked, boolean grabbed, boolean grabbable, String classess) {
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

  public NodeDataVO getData() {
    return data;
  }

  public void setData(NodeDataVO data) {
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
   * 功能: 单元测试看是否能够正确的构造出想要的字符串.
   * @param args 形式参数
   */
  public static void main(String[] args) {
    //System.out.println("------构造第一个点------");
    NodeDataVO data1 = new NodeDataVO("n01","13101900@qq.com",4);
    NodeVO nodeVo1 = new NodeVO(data1,"nodes",false,false,true,false,false,true,"");
    String jsonString1 = JSON.toJSONString(nodeVo1);
    //System.out.println(jsonString1);
    
    //System.out.println("------构造第二个点------");
    NodeDataVO data2 = new NodeDataVO("n02","13101901@qq.com",10);
    NodeVO nodeVo2 = new NodeVO(data2,"nodes",false,false,true,false,false,true,"");
    String jsonString2 = JSON.toJSONString(nodeVo2);
    //System.out.println(jsonString2);
    
    //System.out.println("------构造第三个点------");
    NodeDataVO data3 = new NodeDataVO("n03","13101901@qq.com",10);
    NodeVO nodeVo3 = new NodeVO(data3,"nodes",false,false,true,false,false,true,"");
    String jsonString3 = JSON.toJSONString(nodeVo3);
    //System.out.println(jsonString3);
    
    //System.out.println("------构造第四个点------");
    NodeDataVO data4 = new NodeDataVO("n04","13101901@qq.com",10);
    NodeVO nodeVo4 = new NodeVO(data4,"nodes",false,false,true,false,false,true,"");
    String jsonString4 = JSON.toJSONString(nodeVo4);
    //System.out.println(jsonString4);
    
    //System.out.println("连接两个点");
    
    /*不同的点只需要在开始和结尾处拼接一个[]就可以*/
    String jsonString = "[" + jsonString1 + "," + jsonString2 + "," + "]";
    //System.out.println(jsonString);
    
    /*根据上面两个点构造一条边*/
    //System.out.println("------构造第一条边------");
    EdgeDataVO data5 = new EdgeDataVO("e01", "n01", "n02", 100);
    EdgeVO edgeVo1 = new EdgeVO(data5,"edges",false,false,true,false,false,true,"");
    String jsonString5 = JSON.toJSONString(edgeVo1);
    //System.out.println("-------构造边------:");
    //System.out.println(jsonString5);
    
    //System.out.println("------构造第二条边------");
    EdgeDataVO data6 = new EdgeDataVO("e02", "n03", "n04", 100);
    EdgeVO edgeVo2 = new EdgeVO(data6,"edges",false,false,true,false,false,true,"");
    String jsonString6 = JSON.toJSONString(edgeVo2);
    /*构造最终的输出结果*/
    //String outString = "[" + jsonString1 + "," + jsonString2 + "," + jsonString5 + "," + "]"; 
    String outString = "[" + jsonString1 + "," + jsonString2 + "," + jsonString5 + "," 
        + jsonString3 + "," + jsonString4 + "," + jsonString6 + "," + "]";
    //System.out.println("------最终结果------");
    //System.out.println(outString);
    
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("小明", 1);
    if (map.containsKey("小明")) {
      System.out.println("键已经存在");
      Object object = map.get("小明");  
      System.out.println(object);
    } else {
      map.put("xiaoming", 2);
    }
   
    
    System.out.println(map.toString());
  }
  
  
  
  
}
