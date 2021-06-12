//package com.lblz.activity.test;
//
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.runtime.Job;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
//import org.activiti.engine.test.ActivitiRule;
//import org.activiti.engine.test.Deployment;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//
///**
// * @author lblz
// * @deacription 基于spring构造activiti
// * @date 2021/5/6 22:37
// **/
//@RunWith(SpringJUnit4ClassRunner.class)
////@WebAppConfiguration //没有web容器环境 ，需要配置web容器配置
//@ContextConfiguration(locations = {"classpath:activiti-context.xml"})
//public class ConfigSpringTest {
//    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigSpringTest.class);
//
//    @Rule
//    @Autowired
//    public ActivitiRule activitiRule;
//
//    @Autowired
//    private RuntimeService runtimeService;
//
//    @Autowired
//    private TaskService taskService;
//    @Test
//    @Deployment(resources = {"my-process.bpmn20.xml"}) //在单元测试启动之前 部署流程
//    public void test(){
//        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
//        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
//        activitiRule.getTaskService().complete(task.getId());
//    }
//}
