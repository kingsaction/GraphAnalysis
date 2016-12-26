package com.uniplore.graph.ms.sign.entity;

import java.util.Date;

/**
 * 
 * 版权所有  2016-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述    该类主要用户注册用户时，保存用户的信息
 * 类名称    com.uniplore.graph.ms.sign.entity.UserPO       
 * 创建人    朱君鹏
 * 创建时间  2016年12月26日 下午4:12:12     
 * 修改人  
 * 修改时间  2016年12月26日 下午4:12:12     
 * 修改备注     
 * @version  1.0
 */
public class UserPO {

	private Integer id;
	private String email;
	private String userName;
	private String password;
	private Boolean accountState;
	private Date createTime;
	private Date activeTime;
	
	
	public UserPO() {
		super();
	}

	public UserPO(Integer id, String email, String userName, String password,
			Boolean accountState, Date createTime, Date activeTime) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.accountState = accountState;
		this.createTime = createTime;
		this.activeTime = activeTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAccountState() {
		return accountState;
	}

	public void setAccountState(Boolean accountState) {
		this.accountState = accountState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	@Override
	public String toString() {
		return "UserPO [id=" + id + ", email=" + email + ", userName="
				+ userName + ", password=" + password + ", accountState="
				+ accountState + ", createTime=" + createTime + ", activeTime="
				+ activeTime + "]";
	}
}
