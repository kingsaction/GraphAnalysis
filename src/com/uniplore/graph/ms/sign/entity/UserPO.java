package com.uniplore.graph.ms.sign.entity;

import com.uniplore.graph.common.entity.IPerson;
import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @Data @NoArgsConstructor @AllArgsConstructor class UserPO implements IPerson{
  private Integer id;                //唯一标识用户的id，使用mysql的自增主键
  private String email;              //用户的email
  private String userName;           //用户名，用户自己设定
  private String password;           //用户密码
  private Boolean accountState;      //用户状态，注册必须要激活用户
  private Date createTime;           //账户的创建时间
  private Date activeTime;           //账户的激活事件
  private String activeCode;         //账户的激活码，发送邮件时使用
}
