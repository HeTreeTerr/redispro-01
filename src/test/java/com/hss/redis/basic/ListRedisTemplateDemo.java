package com.hss.redis.basic;

import java.util.List;

import com.hss.service.ListCommandService;
import com.hss.service.LogisticsService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hss.service.UserService;

/**
 * redis
 * 1. list数据类型操作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class ListRedisTemplateDemo {

	private final static Logger logger = Logger.getLogger(ListRedisTemplateDemo.class);
	
	private static final String cardId = "1009688";

	@Autowired
	private ListCommandService listCommandService;

	@Autowired
	private LogisticsService logisticsService;

	/**
	 * list 赋值
	 */
	@Test
	public void test() {
		listCommandService.listAdd();
		logger.info("添加成功");
	}

	/**
	 * list 获值
	 */
	@Test
	public void test2() {
		List<String> list = listCommandService.listRange();
		logger.info("redisTemplate list类型数据操作："+list.toString());
	}

	/**
	 * 实现list 分页
	 */
	@Test
	public void test3() {
		int pageNum = 1;//当前页
		int pageSize = 3;//每页显示3条数据
		List<String> list = listCommandService.listRangPageHelper(pageNum, pageSize);
		
		for(String s : list) {
			logger.info(s);
		}		
	}

	@Test
	public void test4() {
		//初始化物流信息
		logisticsService.listQueueInit(cardId);
		logger.info("初始化物流信息成功");
	}
	
	@Test
	public void test5() {
		//更新物流信息
		logisticsService.listQueueTouch(cardId);
		logger.info("物流信息更新成功");
	}
	
	@Test
	public void test6() {
		//用户查询物流信息
		List<String> listSucc = logisticsService.listQueueSucc(cardId);
		logger.info("用户查询物流进度---------->");
		logger.info(listSucc);
	}
	
	@Test 
	public void test7() {
		//物流公司查询物流信息
		List<String>listWait = logisticsService.listQueueWait(cardId);
		logger.info("物流公司查询剩余任务--------->");
		logger.info(listWait);
	}
}
