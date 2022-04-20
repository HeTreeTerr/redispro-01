package com.hss.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.hss.mapper.UserMapper;
import org.apache.log4j.Logger;
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

	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	//作用相当于：redisTemplate.opsForHash()。简化代码，指定泛型
	@Resource(name="redisTemplate")
	private HashOperations<String, Integer, User> hash;
	
	//作用相当于：redisTemplate.opsForValue()
	@Resource(name="redisTemplate")
	private ValueOperations<String, String> string;

	@Autowired
	private UserMapper userMapper;

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
			logger.info(longFailCount);
			if(longFailCount < 4) {//代表如果当前登录次数<4，还有资格尝试
				//对指定key增加指定数量
				Long i = 1L;
				//redisTemplate.opsForValue().increment(key, i);
				string.increment(key, i);
				logger.info("加一成功");
				//redisTemplate.opsForValue().increment("doubleKey",5);
				Long seconds = redisTemplate.getExpire(key, TimeUnit.SECONDS);
				logger.info("有效期:"+seconds);
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
			logger.info("redis中查询的数据");
			return user;
		}else {//如果不存在，从数据库中查询，取出赋给Redis,并返回
			User user = userMapper.selectById(userId);
			//redisTemplate.opsForHash().put("user", user.getId(), user);
			hash.put(User.getUser(), user.getId(), user);
			logger.info("数据库中查询的数据");
			return user;
		}
	}
}
