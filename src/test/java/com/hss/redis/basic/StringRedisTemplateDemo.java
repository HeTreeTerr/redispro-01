package com.hss.redis.basic;

import com.hss.service.StringCommandService;
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

		StringCommandService stringCommand = ctx.getBean(StringCommandService.class);
		String key = "applicationName123";
		String result = stringCommand.getString(key);
		System.out.println("String返回值："+result);
		
		ctx.close();
	}

}
