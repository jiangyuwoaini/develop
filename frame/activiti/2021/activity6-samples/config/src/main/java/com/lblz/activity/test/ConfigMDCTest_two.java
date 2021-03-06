package com.lblz.activity.test;

import org.activiti.engine.logging.LogMDC;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lblz
 * @deacription activiti流程日志
 * @date 2021/5/6 22:37
 **/
public class ConfigMDCTest_two {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDbTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mdc.cfg.xml");

    @Test
    @Deployment(resources = {"my-process_mdcerror.bpmn20.xml"}) //在单元测试启动之前 部署流程
    public void test(){
//        LogMDC.setMDCEnabled(true); //开启mdc日志
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");

        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        activitiRule.getTaskService().complete(task.getId());
    }
}
