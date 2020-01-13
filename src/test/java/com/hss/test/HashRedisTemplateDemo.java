package com.hss.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hss.bean.User;
import com.hss.service.UserService;

public class HashRedisTemplateDemo {

	/**
	 * 测试hash RedisTemplate
	 */
	@Test
	public void t1() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring_redis.xml");
		
		UserService userService = ctx.getBean(UserService.class);
		
		User user = new User();
		user.setId(1);
		user.setName("hss");
		user.setUsername("地瓜");
		user.setAge(18);
		user.setPassword("666");
		
		userService.addUser(user);
		
		System.out.println("-------hash---------");
		
		ctx.close();
	}
	
	@Test
	public void t2() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring_redis.xml");
		
		UserService userService = ctx.getBean(UserService.class);
		
		User user = userService.selectById(3);
		System.out.println(user);
		
		System.out.println("-------hash---------");
		
		ctx.close();
	}
}
