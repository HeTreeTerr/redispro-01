package com.hss.redis.basic;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.hss.constant.RedisConstant;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.hss.bean.User;
import com.hss.util.RedisPoolUtil;

import redis.clients.jedis.Jedis;

/**
 * redis 基础操作
 * 1. 基础链接 ping/pong
 * 2. string 数据类型操作
 * 3. hash 数据类型操作
 */
public class RedisBasicDemo {

	private final static Logger logger = Logger.getLogger(RedisBasicDemo.class);

	/**
	 * Java端通过Jedis连接redis
	 * @param args
	 */
	public static void main(String[] args) {

		String host = RedisConstant.HOST;
		int port = RedisConstant.PORT;
		Jedis jedis = new Jedis(host, port);
		//密码
		jedis.auth(RedisConstant.PASSWORD);

		logger.info(jedis.ping());
		//关闭连接
		jedis.close();
	}

	/**
	 * redis
	 * string
	 * 取值/赋值
	 */
	@Test
	public void t1() {
		
		String host = RedisConstant.HOST;
		int port = RedisConstant.PORT;
		Jedis jedis = new Jedis(host, port);
		//密码
		jedis.auth(RedisConstant.PASSWORD);
		
		//写入String型数据
		jedis.set("strName", "java存入redis");

		//获取String型数据
		String strName = jedis.get("strName");
		
		logger.info("获取redis中的数据strName=" + strName);
		//关闭连接
		jedis.close();
	}
	
	/**
	 * Redis String作用：为了减轻数据库（MYSQL）的访问压力
	 * 需求：判断某KEY是否存在，如果存在，就从Redis中查询
	 * 		如果不存在，就查询数据库，且将查询出的数据存入Redis
	 */
	@Test
	public void t2() {
		
		Jedis jedis = RedisPoolUtil.getRedis();
		
		String key = "applicationName"; //key的名称
		
		if(jedis.exists(key)) {//redis中有值
			String result = jedis.get(key);
			System.out.println("Redis数据库中查询得到："+result);
		}else {
			//在数据库中查询
			String result = "jojo";
			jedis.set(key, result);
			System.out.println("mysql数据库中获得值："+result);
		}
		//关闭连接
		RedisPoolUtil.close(jedis);
	}
	
	/**
	 * Jedis完成Hash类型操作
	 * 需求：hash存储一个对象
	 * 		判断Redis中是否存在该key，如果存在直接返回对应值
	 * 		如果不存在，查询数据库，将数据库结果写入redis并返回给用户 
	 */
	@Test
	public void t3() {
		Jedis jedis = RedisPoolUtil.getRedis();
		String key = "users";
		
		if(jedis.exists(key)) {//判断key是否存在
			Map<String, String> map = jedis.hgetAll(key);
			System.out.println("--Redis中查询的结果是：");
			System.out.println(map.get("id")+"\t"+map.get("name")+"\t"+map.get("age"));
		}else {
			//查询数据库并返回结果
			String id = "1";
			String name = "hss";
			String age = "22";
			String remark = "remark";
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("name", name);
			map.put("age", age);
			map.put("remark", remark);
			jedis.hmset(key, map);
			System.out.println("数据库查询的结果："+
					"id="+id+"\t"+
					"name="+name+"\t"+
					"age="+age+"\t"+
					"remark="+remark+"\t");
		}
		
		//关闭连接
		RedisPoolUtil.close(jedis);
	}
	
	/**
	 * 对上面的方法进行优化
	 * Jedis完成Hash类型操作
	 * 使用User.id进行隔离
	 */
	@Test
	public void t4() {
		
		Jedis jedis = RedisPoolUtil.getRedis();
		
		int id = 2;
		String key = User.getKeyName()+id; //user:2
		
		if(jedis.exists(key)) {
			//redis中取出该对象
			Map<String, String> map = jedis.hgetAll(key);
			User user = new User();
			
			user.setId(Integer.parseInt(map.get("id")));
			user.setName(map.get("name"));
			user.setAge(Integer.parseInt(map.get("age")));
			user.setPassword(map.get("password"));
			user.setUsername(map.get("username"));
			
			System.out.println("Redis数据库中查询的对象："+user);
		}else {
			//mysql数据库查询
			User user = new User();
			user.setId(id);
			user.setName("hss");
			user.setUsername("tu_dou");
			user.setAge(18);
			user.setPassword("123456");
			
			Map<String, String> map = new HashMap<String, String>();
			
			map.put("id", user.getId()+"");
			map.put("name", user.getName());
			map.put("age", user.getAge()+"");
			map.put("password", user.getPassword());
			map.put("username", user.getUsername());
			
			jedis.hmset(key, map);
			System.out.println("数据库查询的结果："+user);
		}
		
		//关闭连接
		RedisPoolUtil.close(jedis);
	}

	/**
	 * Jedis 执行lua脚本
	 */
	@Test
	public void lua(){
		Jedis jedis = RedisPoolUtil.getRedis();
		String key = "strName1";
		String LUA_SCRIPT =
				"if(redis.call(\"EXISTS\",KEYS[1])==1)\n" +
						"then\n" +
						"return 1\n" +
						"else\n" +
						"return 0\n" +
						"end";

		Object result = jedis.evalsha(jedis.scriptLoad(LUA_SCRIPT), Arrays.asList(key), Collections.emptyList());
		System.out.println("result = " + result);
		//关闭连接
		RedisPoolUtil.close(jedis);
	}
}
