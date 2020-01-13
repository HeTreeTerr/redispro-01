package com.hss.service;

import java.util.List;
import java.util.Map;

import com.hss.bean.User;

public interface UserService {

	/**
	 * String存和取测试
	 */
	public String getString(String key);
	
	/**
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public User login(String username,String password);
	
	/**
	 * 
	 * @param uname 用户名
	 * @return
	 */
	public String loginValdate(String uname);
	
	/**
	 * 1判断当前登录用户是否被限制登录
	 * @param uname
	 * @return
	 */
	public Map<String, Object> loginUserLock(String uname);
	
	/**
	 * LIST类型的案例操作
	 */
	public void listAdd();
	
	public List<String> listRange();
	
	/**
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<String> listRangPageHelper(Integer pageNum,Integer pageSize);
	
	/**
	 * 添加用户
	 * @param u
	 */
	public void addUser(User u);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public User selectById(int userId);
	
	/**
	 * 
	 * @param cardId
	 */
	public void listQueueInit(String cardId);
	
	/**
	 * 
	 * @param cardId
	 */
	public void listQueueTouch(String cardId);
	
	/**
	 * 
	 * @param cardId
	 * @return
	 */
	public List<String> listQueueSucc(String cardId);
	
	/**
	 * 
	 * @param cardId
	 * @return
	 */
	public List<String> listQueueWait(String cardId);
}
