package com.hss.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @author lenovo
 *
 */
@Component
public class MyTask {

	// 间隔5秒执行
	@Scheduled(cron = "0/5 * * * * ? ")
	public void taskCycle() {
		System.out.println("使用SpringMVC框架配置定时任务");
	}
}
