package com.hss.bean;

/**
 * 用户数据导入
 * @author lenovo
 *
 */
public class UserExcel {
	/** 姓名  */
	private String name;
	/** 性别  */
	private Integer sex;
	/** 年龄  */
	private Integer age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "UserExcel [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
	
}
