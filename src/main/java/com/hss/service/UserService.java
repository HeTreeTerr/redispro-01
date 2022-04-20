package com.hss.service;

import java.util.Map;

import com.hss.bean.User;

public interface UserService {

	/**
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	User login(String username,String password);
	
	/**
	 * 
	 * @param uname 用户名
	 * @return
	 */
	String loginValdate(String uname);
	
	/**
	 * 1判断当前登录用户是否被限制登录
	 * @param uname
	 * @return
	 */
	Map<String, Object> loginUserLock(String uname);

	/**
	 * 添加用户
	 * @param u
	 */
	void addUser(User u);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	User selectById(int userId);
}
