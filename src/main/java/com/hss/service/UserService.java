package com.hss.service;

import java.util.Map;

import com.hss.bean.User;

public interface UserService {

	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	User login(String username,String password);
	
	/**
	 * 登录失败限制次数
	 * @param uname 用户名
	 * @return
	 */
	String loginValdate(String uname);
	
	/**
	 * 判断当前登录用户是否被限制登录
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
	 * 查询用户
	 * @param userId
	 * @return
	 */
	User selectById(int userId);
}
