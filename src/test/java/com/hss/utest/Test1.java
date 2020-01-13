package com.hss.utest;

import org.junit.Test;

import com.hss.bean.UserExcel;

public class Test1 {

	@Test
	public void test1() {
		UserExcel excel = new UserExcel();
		excel.setName("hss");
		System.out.println("-------before--------");
		System.out.println(excel);
		setValue(excel);
		System.out.println("---------after---------");
		System.out.println(excel);
	}
	
	public void setValue(UserExcel excel) {
		excel.setAge(18);
		excel.setSex(0);
	}
}
