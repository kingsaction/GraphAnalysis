package com.uniplore.graph.dsm.db.entity;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能：封装节点对象.
 * @author 朱君鹏
 *
 */
public @Data @NoArgsConstructor @AllArgsConstructor class NodeVO {
  private NodeDataVO data;
  private String group;
  private boolean removed;
  private boolean selected;
  private boolean selectable;
  private boolean locked;
  private boolean grabbed;
  private boolean grabbable;
  private String classess; 
}
