package com.hss.redis.basic;

import com.hss.service.HashCommandService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hss.bean.User;
import com.hss.service.UserService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * redis
 * hash 数据类型操作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class HashRedisTemplateDemo {

	private final static Logger logger = Logger.getLogger(HashRedisTemplateDemo.class);

	@Autowired
	private HashCommandService hashCommandService;

	/**
	 * 测试hash RedisTemplate
	 * 赋值
	 */
	@Test
	public void setHashValue() {

		User user = new User();
		user.setId(1);
		user.setName("hss");
		user.setUsername("地瓜");
		user.setAge(18);
		user.setPassword("666");
		hashCommandService.put(user);
		logger.info("hash put success");
	}

	/**
	 * 测试hash RedisTemplate
	 * 取值
	 */
	@Test
	public void getHashValue() {
		User user = hashCommandService.get(1);
		logger.info("hash get user=" + user);
	}
}
