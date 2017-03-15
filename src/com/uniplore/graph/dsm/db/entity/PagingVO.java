package com.uniplore.graph.dsm.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 实现分页功能的bean类.
 * @author 朱君鹏
 *
 */
public @ToString @Data @NoArgsConstructor @AllArgsConstructor class PagingVO {
  private Integer currentPage ;  //当前页，当分页时，该变量表示当前是哪一页
  private Integer pageCount;     //查询返回的行数，也就是每一页包含多少行数据
}
