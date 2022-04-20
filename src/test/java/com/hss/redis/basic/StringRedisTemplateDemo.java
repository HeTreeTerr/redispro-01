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
	public void t1() {
		String key = "applicationName123";
		String result = stringCommandService.setAndGet(key);
		logger.info("String返回值："+result);
	}

}
