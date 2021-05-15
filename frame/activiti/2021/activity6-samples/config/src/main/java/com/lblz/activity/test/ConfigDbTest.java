package com.lblz.activity.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lblz
 * @deacription 默认创建 预计指定文件创建流程引擎
 * @date 2021/5/6 21:19
 **/
public class ConfigDbTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDbTest.class);

    @Test
    public void testConfig_One(){ //在默认配置过程中,创建引擎会创建表 而销毁引擎则会销毁表
        ProcessEngineConfiguration configuration
                    = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
        LOGGER.info("configuration={}",configuration);
        ProcessEngine processEngine = configuration.buildProcessEngine();
        LOGGER.info("获取流程引擎{}",processEngine.getName());
        processEngine.close();
    }

    @Test
    public void testConfig_Two(){ //在默认配置过程中,创建引擎会创建表 而销毁引擎则会销毁表
        ProcessEngineConfiguration configuration
                = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti_druid.cfg.xml");
        LOGGER.info("configuration={}",configuration);
        ProcessEngine processEngine = configuration.buildProcessEngine();
        LOGGER.info("获取流程引擎{}",processEngine.getName());
        processEngine.close();
    }
}
