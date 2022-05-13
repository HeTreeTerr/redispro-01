package com.hss.service.impl.advancedOperation;

import com.hss.service.advancedOperation.LuaScriptService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_redis.xml"})
public class LuaScriptServiceImplTest {

    private final static Logger logger = Logger.getLogger(LuaScriptServiceImplTest.class);

    @Autowired
    private LuaScriptService luaScriptService;

    @Test
    public void setKey() {
        logger.info(luaScriptService.setKey("hss:ooo","纵观中原"));
    }

    @Test
    public void delKey() {
        logger.info(luaScriptService.delKey("hss:ooo","纵观中原"));
    }
}