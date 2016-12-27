package com.uniplore.graph.ms.sign.entity;

import java.util.Date;

import com.uniplore.graph.ms.common.entity.IPerson;

/**
 * 
 * 版权所有  2016-ACMIS Lab  
 * 项目名称  graphanalysis       
 * 类描述    注册时保存用户的信息
 * 类名称    com.uniplore.graph.ms.sign.entity.UserPO       
 * 创建人    朱君鹏
 * 创建时间  2016年12月26日 下午9:26:24     
 * 修改人  
 * 修改时间  2016年12月26日 下午9:26:24     
 * 修改备注     
 * @version  1.0
 */
public class UserPO implements IPerson{

	private Integer id;                //唯一标识用户的id，使用mysql的自增主键
	private String email;              //用户的email
	private String userName;           //用户名，用户自己设定
	private String password;           //用户密码
	private Boolean accountState;      //用户状态，注册必须要激活用户
	private Date createTime;           //账户的创建时间
	private Date activeTime;           //账户的激活事件
	private String activeCode;         //账户的激活码，发送邮件时使用
	
	/**
	* 
	* <p>Title: 构造函数 </p>  
	* <p>Description: 无参数构造函数</p>
	*/
	public UserPO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	* 
	* <p>Title: 构造函数</p>  
	* <p>Description: 构造函数，全部参数都有</p>  
	* @param id
	* @param email
	* @param userName
	* @param password
	* @param accountState
	* @param createTime
	* @param activeTime
	* @param activeCode
	*/
	public UserPO(Integer id, String email, String userName, String password,
			Boolean accountState, Date createTime, Date activeTime,
			String activeCode) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.accountState = accountState;
		this.createTime = createTime;
		this.activeTime = activeTime;
		this.activeCode = activeCode;
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
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	
	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserPO [id=" + id + ", email=" + email + ", userName="
				+ userName + ", password=" + password + ", accountState="
				+ accountState + ", createTime=" + createTime + ", activeTime="
				+ activeTime + ", activeCode=" + activeCode + "]";
	}
}
