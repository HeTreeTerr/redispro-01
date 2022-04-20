package com.hss.redis.basic;

import java.util.List;

import com.hss.service.ListCommandService;
import com.hss.service.LogisticsService;
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
		System.out.println("添加成功");
	}

	/**
	 * list 获值
	 */
	@Test
	public void test2() {
		List<String> list = listCommandService.listRange();
		System.out.println("redisTemplate list类型数据操作："+list.toString());
	}

	/**
	 * 实现list 分页
	 */
	@Test
	public void test3() {
		int pageNum = 3;//当前页
		int pageSize = 4;//每页显示3条数据
		List<String> list = listCommandService.listRangPageHelper(pageNum, pageSize);
		
		for(String s : list) {
			System.out.println(s);
		}		
	}

	@Test
	public void test4() {
		//初始化物流信息
		//String cardId = "1009688";
		logisticsService.listQueueInit(cardId);
		System.out.println("初始化物流信息成功");
	}
	
	@Test
	public void test5() {
		//更新物流信息
		logisticsService.listQueueTouch(cardId);
		System.out.println("物流信息更新成功");
	}
	
	@Test
	public void test6() {
		//用户查询物流信息
		List<String> listSucc = logisticsService.listQueueSucc(cardId);
		System.out.println("用户查询物流进度---------->");
		System.out.println(listSucc);
	}
	
	@Test 
	public void test7() {
		//物流公司查询物流信息
		List<String>listWait = logisticsService.listQueueWait(cardId);
		System.out.println("物流公司查询剩余任务--------->");
		System.out.println(listWait);
	}
}
