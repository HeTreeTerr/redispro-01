package com.hss.redis.basic;

import com.hss.service.StringCommandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hss.service.UserService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * RedisTemplate
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class StringRedisTemplateDemo {

	@Autowired
	private StringCommandService stringCommandService;

	/**
	 * 测试string RedisTemplate
	 */
	@Test
	public void t1() {
		String key = "applicationName123";
		String result = stringCommandService.getString(key);
		System.out.println("String返回值："+result);
	}

}
