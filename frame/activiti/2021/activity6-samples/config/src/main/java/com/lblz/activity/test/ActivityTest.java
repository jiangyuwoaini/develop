package com.lblz.activity.test;

import com.lblz.activity.pojo.Users;
import org.activiti.engine.ProcessEngineConfiguration;


import org.activiti.engine.identity.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


/**
 * @classname Activitytest
 * @deacription todo
 * @author j y
 * @date 2021/4/26 21:19
 * @version 1.0
 **/
public class ActivityTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityTest.class);

    @Test
    public void testCreateProcessConfig_One(){
        ProcessEngineConfiguration configuration  //基于默认配置activiti.cfg.xml 加载流程引擎
                = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
        LOGGER.info("configuration ={}",configuration);
    }

    @Test
    public void testCreateProcessConfig_Two(){
        ProcessEngineConfiguration configuration
                = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        LOGGER.info("configuration ={}",configuration);
    }
}
