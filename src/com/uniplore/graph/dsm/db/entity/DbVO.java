package com.uniplore.graph.dsm.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能: 封装从客户端出来的数据库名、选择的表名、选择的源点、选择的目标点.
 * @author 朱君鹏
 *
 */
public @Data @NoArgsConstructor @AllArgsConstructor class DbVO {
  private String dbName ;
  private String tableName;
  private String sourceNode ;
  private String targetNode;
}
