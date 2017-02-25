package com.uniplore.graph.dsm.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @Data @NoArgsConstructor @AllArgsConstructor class NodeDataVO {
  private String id;    //标记节点的id
  private String name;  //标记节点的名字
  private Integer weight;  //标记节点的权重，节点出现的次数
}
