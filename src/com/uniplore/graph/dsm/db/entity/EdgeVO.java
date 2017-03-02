package com.uniplore.graph.dsm.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @Data  @NoArgsConstructor @AllArgsConstructor class EdgeVO {
  private EdgeDataVO data;
  private String group;
  private boolean removed;
  private boolean selected;
  private boolean selectable;
  private boolean locked;
  private boolean grabbed;
  private boolean grabbable;
  private String classess;
}
