package com.uniplore.graph.ms.entity;

public class TestUser implements java.io.Serializable { // 实现了一个序列化接口，
													// session序列化的功能，当tomcat一停止，
													// 就将session信息序列化到磁盘中，tomcat再次启动时会自动的加载这些信息

	private int id; // 对应数据库表中的主键
	private String email;
	private String userName;
	private String pwd;
	private Integer accountState;
	private String activeCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getAccountState() {
		return accountState;
	}

	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", userName=" + userName + ", pwd=" + pwd + ", accountState="
				+ accountState + ", activeCode=" + activeCode + "]";
	}

	public TestUser(int id, String email, String userName, String pwd, Integer accountState, String activeCode) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.pwd = pwd;
		this.accountState = accountState;
		this.activeCode = activeCode;
	}

	public TestUser() {
		super();
		// TODO Auto-generated constructor stub
	}

}
