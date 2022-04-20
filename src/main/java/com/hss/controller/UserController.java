package com.hss.controller;

import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hss.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hss.bean.User;
import com.hss.service.UserService;

@Controller
public class UserController {

	private final static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	/**
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param valcode 验证码
	 * @return
	 */
	@RequestMapping(value="/login",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String login(@RequestParam("username") final String username
			,@RequestParam("password") final String password
			,@RequestParam("valcode") final String valcode) {
		/**
		 * 1.验证码比较
		 */
		logger.info("验证码valcode=" + valcode);
		/**
		 * 2.执行登录
		 */
		Map<String, Object> map = userService.loginUserLock(username);
		boolean flags = Boolean.valueOf(map.get("flag").toString());
		if(flags) {//被限制登录
			return "登录失败，因"+username+"用户超过了限制登录次数，已被禁止登录。还剩："+map.get("lockTime")+"分钟。";
		}else {//没有被限制登录
			//(执行登录功能)
			User user = userService.login(username, password);
			//判断是否登录成功
			if(ObjectUtil.isNotEmpty(user)) {//登录成功
				//清空对应的所有key
				return "/success.jsp";
			}else {//登录不成功
				String result = userService.loginValdate(username);
				return result;
			}
		}
	}

}
