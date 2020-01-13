package com.hss.bean;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -2321234627917759590L;

	private Integer id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private Integer age;
	
	/**
	 * 获取key值
	 * @return
	 */
	public static String getKeyName() {
		return "user:";
	} 
	
	public static String getUser() {
		return "user";
	}
	
	/**
	 * 锁定限制登录key:  user:loginTime:lock:用户名
	 * @return
	 */
	public static String getLoginTimeLockKey(String uname) {
		
		return "user:loginTime:lock:"+uname;
	}
	
	/**
	 * 登录失败次数key user:loginCount:fail:用户名
	 * @param username
	 * @return
	 */
	public static String getLoginCountFailKey(String username) {
		return "user:loginCount:fail:"+username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", age="
				+ age + "]";
	}
	
}
