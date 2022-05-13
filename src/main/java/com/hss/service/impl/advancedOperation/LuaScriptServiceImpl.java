package com.hss.service.impl.advancedOperation;

import com.hss.service.advancedOperation.LuaScriptService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LuaScriptServiceImpl implements LuaScriptService {

    private final static Logger logger = Logger.getLogger(LuaScriptServiceImpl.class);

    private final static String SET_LUA_SCRIPT =
            "return redis.call('set',KEYS[1],ARGV[1])";

    private final static String DEL_LUA_SCRIPT =
            "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n" +
                    "then\n" +
                    "return redis.call(\"del\",KEYS[1])\n" +
                    "else\n" +
                    "return 0\n" +
                    "end";

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public String setKey(String key, String value) {
        logger.info("==============lua setKey begin");
        //redis脚本对象
        DefaultRedisScript<String> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(String.class);
        defaultRedisScript.setScriptText(SET_LUA_SCRIPT);

        //执行
        String exeRes = redisTemplate.execute(defaultRedisScript, Collections.singletonList(key), value);
        logger.info("==============lua setKey end");
        return exeRes;
    }

    @Override
    public Long delKey(String key, String value) {
        logger.info("==============lua delKey begin");
        //redis脚本对象
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(Long.class);
        defaultRedisScript.setScriptText(DEL_LUA_SCRIPT);

        //执行
        Long exeRes = redisTemplate.execute(defaultRedisScript, Collections.singletonList(key), value);
        logger.info("==============lua delKey end");
        return exeRes;
    }
}
