package com.hss.redis.basic;

import com.hss.service.StringCommandService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hss.service.UserService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * String 基础操作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class StringRedisTemplateDemo {

	private final static Logger logger = Logger.getLogger(StringRedisTemplateDemo.class);

	@Autowired
	private StringCommandService stringCommandService;

	/**
	 * 测试string RedisTemplate
	 */
	@Test
	public void setAndGet() {
		String key = "applicationName123";
		String result = stringCommandService.setAndGet(key);
		logger.info("String返回值："+result);
	}

	/**
	 * 当key不存在时赋值
	 */
	@Test
	public void setIfAbsent(){
		String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
		Boolean setIfAbsent = stringCommandService.setIfAbsent("myLock1", value, 30L);
		logger.info("setIfAbsent = " + setIfAbsent);
	}
}
