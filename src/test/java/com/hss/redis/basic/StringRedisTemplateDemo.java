package com.hss.redis.basic;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hss.service.UserService;

/**
 * RedisTemplate
 */
public class StringRedisTemplateDemo {

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
