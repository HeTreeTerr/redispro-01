package com.hss.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.hss.bean.User;
import com.hss.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	RedisTemplate<String, String> redisTemplate;
	
	//作用相当于：redisTemplate.opsForHash()。简化代码，指定泛型
	@Resource(name="redisTemplate")
	HashOperations<String, Integer, User> hash;
	
	//作用相当于：redisTemplate.opsForValue()
	@Resource(name="redisTemplate")
	ValueOperations<String, String> string;
	
	//作用相当于：redisTemplate.opsForList()
	@Resource(name="redisTemplate")
	ListOperations<String, String> list;
	
	/**
	 * 通过某个key获取某个值
	 * 如果key在Redis中不存在，到数据库进行查询
	 * 如果存在，就到redis中查询
	 */
	@Override
	public String getString(String key) {
		
		ValueOperations<String, String> string = redisTemplate.opsForValue();
		
		//设置有效时长，并可以自定义时长有效单位
		//redisTemplate.expire("java1802", 2, TimeUnit.MINUTES);
		//一般先设置值，再设置时长
		redisTemplate.opsForValue().set("java2019", "我的小乖乖", 2, TimeUnit.HOURS);
		
		//判断Redis是否存在值
		if(redisTemplate.hasKey(key)) {
			//在Redis中取值，并返回
			System.out.println("redis中取值");
			return string.get(key);
		}else {
			//查询数据库
			String result = "RedisTemplate模板练习";
			
			string.set(key, result);
			System.out.println("在Mysql数据库中取出并返回");
			return result;
		}	
	}

	@Override
	public User login(String username, String password) {
		
		return null;
	}

	/**
	 * 登录不成功相应操作
	 */
	@Override
	public String loginValdate(String uname) {
		//记录登录次数错误次数
		String key = User.getLoginCountFailKey(uname);
		
		int num = 5;//登录错误的次数
		if(!redisTemplate.hasKey(key)) {
			//是第一次登录失败次数为1  user:loginCount:fail:用户名进行赋值，同时设置失效期
			//注意：redisTemplate中在赋值时不能直接赋值并设置失效期（会设置失败）
			redisTemplate.opsForValue().set(key, "1");//先赋值
			redisTemplate.expire(key, 2, TimeUnit.MINUTES);//再设置有效时间（2分钟）
			return "登录失败，当前还允许输入错误"+(num-1)+"次。";
		}else {//如果存在
			//查询登录失败次数的key的结果
			long longFailCount = Long.parseLong(redisTemplate.opsForValue().get(key));
			System.out.println(longFailCount);
			if(longFailCount < 4) {//代表如果当前登录次数<4，还有资格尝试
				//对指定key增加指定数量
				Long i = 1L;
				//redisTemplate.opsForValue().increment(key, i);
				string.increment(key, i);
				System.out.println("加一成功");
				//redisTemplate.opsForValue().increment("doubleKey",5);
				Long seconds = redisTemplate.getExpire(key, TimeUnit.SECONDS);
				System.out.println("有效期:"+seconds);
				return "登录失败，在"+seconds+"内还允许输入错误"+(num-longFailCount-1)+"次";
			}else {//超过指定登录次数
				//限制登录key存在，同时设置限制登录时间锁定1小时
				redisTemplate.opsForValue().set(User.getLoginTimeLockKey(uname),"1");
				redisTemplate.expire(User.getLoginTimeLockKey(uname), 1, TimeUnit.HOURS);
				return "因登录失败次数超过限制"+num+"次,已对其限制登录1小时";
			}
		}
	}

	@Override
	public Map<String, Object> loginUserLock(String uname) {
		String key = User.getLoginTimeLockKey(uname);
		Map<String, Object> map = new HashMap<String,Object>();
		if(redisTemplate.hasKey(key)) {
			//如果存在key，被限制
			long lockTime = redisTemplate.getExpire(key, TimeUnit.MINUTES);//以分钟为单位返回
			
			map.put("flag",true);
			map.put("lockTime",lockTime);//还剩多长时间（小时单位锁定：给用户返回分钟）
			
		}else{
			//如果不存在，没有被限制
			map.put("flag",false);
		}
		return map;
	}

	@Override
	public void addUser(User u) {
		//将对象以hash类型存入
		//redisTemplate.opsForHash().put("user", u.getId(), u);
		hash.put(User.getUser(), u.getId(), u);
		
	}

	@Override
	public User selectById(int userId) {
		//1.先判断Redis中是否存在该key，如果存在，从Redis中取出，并返回即可
		//redisTemplate.hasKey(key);
		//boolean boo = redisTemplate.opsForHash().hasKey(User.getUser(), userId);
		boolean boo = false;
		try {
			boo = hash.hasKey(User.getUser(), userId);
		} catch (Exception e) {
			e.printStackTrace();
			boo = false;
		}
		
		if(boo) {
			//User user = (User)redisTemplate.opsForHash().get(User.getUser(),userId+"");
			User user  = hash.get(User.getUser(),userId);
			System.out.println("redis中查询的数据");
			return user;
		}else {//如果不存在，从数据库中查询，取出赋给Redis,并返回
			User user = new User();
			user.setId(userId);
			user.setName("hss");
			user.setUsername("地瓜");
			user.setAge(18);
			user.setPassword("666");
			//redisTemplate.opsForHash().put("user", user.getId(), user);
			hash.put(User.getUser(), user.getId(), user);
			System.out.println("数据库中查询的数据");
			return user;
		}
	}

	/**
	 * list类型
	 */
	@Override
	public void listAdd() {
		String key = "news:top10";
//		list.leftPush(key,"京东手机魅族x8");
//		list.leftPush(key,"京东手机魅族x8手机壳（送钢化膜）");
//		list.leftPushAll(key, "ccc","ddd","eee");
//		list.rightPushIfPresent(key, "0qwerdf");
		list.rightPushAll(key, "1qwerdf","2qwerdf","3qwerdf"
				,"4qwerdf","5qwerdf","6qwerdf","7qwerdf","8qwerdf"
				,"9qwerdf");
		
	}

	@Override
	public List<String> listRange() {
		String key = "news:top10";
		List<String> list1 = list.range(key, 0, -1);
		return list1;
	}

	@Override
	public List<String> listRangPageHelper(Integer pageNum, Integer pageSize) {
		String key = "news:top10";
		/*
		 * startNum:(pageNum-1)*pageSize;
		 * 
		 * stop:pageSize*pageNum-1
		 */
		Integer start = (pageNum-1)*pageSize;
		Integer stop = pageSize*pageNum-1;
		List<String> list1 = list.range(key, start, stop);
		Long count = list.size(key);
		System.out.println("总记录数是："+count);
		return list1;
	}
	
	//################ list 订单流程示例
	//1付款完成后，会根据用户的收货地址和商家发货地址生成一个队列（任务）
	@Override
	public void listQueueInit(String cardId) {
		String key = "prod:"+cardId;//初始化的key,待有多少任务要完成		
		list.leftPushAll(key, "1商家出货","2小哥发件",
				"3北京海定区某小区--》首都机场","4北京机场--》南京机场"
				,"5机场--》建业区","6建业区--》买家收货");
	}
	
	//2触发事件
	@Override
	public void listQueueTouch(String cardId) {
		String key = "prod:"+cardId+":succ";//已完成任务队列
		
		list.rightPopAndLeftPush("prod:"+cardId, key);
		
	}
	
	//3查询：客户查询：我的快递到哪了
	@Override
	public List<String> listQueueSucc(String cardId){
		String key = "prod:"+cardId+":succ";//已完成任务队列
		return list.range(key, 0, -1);
	}
	
	//4物流查询：当前快递还有多少任务没有执行
	@Override
	public List<String> listQueueWait(String cardId){
		String key = "prod:"+cardId;//待完成的任务
		return list.range(key, 0, -1);
	}
}
