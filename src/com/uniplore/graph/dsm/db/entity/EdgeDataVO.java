package com.uniplore.graph.dsm.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @Data @AllArgsConstructor @NoArgsConstructor class EdgeDataVO {
  private String id;
  private String source;
  private String target;
  private Integer weight;

}
