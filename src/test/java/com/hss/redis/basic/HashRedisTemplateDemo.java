package com.hss.redis.basic;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hss.bean.User;
import com.hss.service.UserService;

/**
 * redis
 * hash 数据类型操作
 */
public class HashRedisTemplateDemo {

	/**
	 * 测试hash RedisTemplate
	 * 赋值
	 */
	@Test
	public void setHashValue() {
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

	/**
	 * 测试hash RedisTemplate
	 * 取值
	 */
	@Test
	public void getHashValue() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring_redis.xml");
		
		UserService userService = ctx.getBean(UserService.class);
		
		User user = userService.selectById(3);
		System.out.println(user);
		
		System.out.println("-------hash---------");
		
		ctx.close();
	}
}
