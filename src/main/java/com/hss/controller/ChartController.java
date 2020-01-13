package com.hss.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hss.bean.Chart;
import com.hss.bean.Msg;

@Controller
@RequestMapping("/chart")
public class ChartController {

	 @RequestMapping(value = "/jsonMap", method=RequestMethod.GET)
	 @ResponseBody
	public Map<String, Object> jsonMap() {
		 Map<String, Object> map = new LinkedHashMap<String, Object>();
		 //时间列表
		 String times = "2019-01,2019-02,2019-03,2019-04";
		 //核心值
		 String nums = "994,995,996,997";
		 
		 String[] timeArr = stringToStrArr(times);
		 Integer[] numArr = stringToIntArr(nums);
		 
		 map.put("timeArr", timeArr);
		 map.put("numArr", numArr);
		return map;
	}
	 
	 @RequestMapping(value = "/jsonArr", method=RequestMethod.GET)
	 @ResponseBody
	public Msg jsonArr() {
		 
		 Object[] resultArr = new Object[4];
		 
		 Chart chart1 = new Chart();
		 chart1.setName("2019-01");
		 chart1.setValue(994L);
		 
		 Chart chart2 = new Chart();
		 chart2.setName("2019-02");
		 chart2.setValue(995L);
		 
		 Chart chart3 = new Chart();
		 chart3.setName("2019-03");
		 chart3.setValue(996L);
		 
		 Chart chart4 = new Chart();
		 chart4.setName("2019-04");
		 chart4.setValue(997L);
		 
		 resultArr[0] = chart1;
		 resultArr[1] = chart2;
		 resultArr[2] = chart3;
		 resultArr[3] = chart4;
		 
		return Msg.success().add("resultArr",resultArr );
	}
	 
	//String字符串转String[] 
	protected String[] stringToStrArr(String str) {
		if(str == null || str.equals("")) {
			return null;
		}
		String[] nums = null;
		nums = str.split(",");
		
		return nums;
	}
	
	//String字符串转为Integer[]
	protected Integer[] stringToIntArr(String str) {
		if(str == null || str.equals("")) {
			return null;
		}
		String[] nums = null;
		nums = str.split(",");
		Integer[] num=new Integer[nums.length];
		for(int i=0;i<num.length;i++){
		    num[i]=Integer.valueOf(nums[i]);
		    System.out.println(num[i]);
		}
		return num;
	}
}
