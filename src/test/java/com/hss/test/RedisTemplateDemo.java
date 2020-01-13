package com.hss.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hss.service.UserService;

public class RedisTemplateDemo {

	/**
	 * 测试string RedisTemplate
	 */
	@Test
	public void t1() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring_redis.xml");
		
		UserService userService = ctx.getBean(UserService.class);
		String key = "applicationName123";
		String result = userService.getString(key);
		System.out.println("String返回值："+result);
		
		ctx.close();
	}

}
