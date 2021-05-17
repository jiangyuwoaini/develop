package com.lblz.activity.coreapi;


import com.google.common.collect.Maps;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceBuilder;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lblz
 * @deacription
 * @date 2021/5/16 13:59
 **/
public class RunTimeServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RunTimeServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    //根据流程key进行启动
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testStartProcess(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        HashMap<String, Object> variables = Maps.newHashMap();
        variables.put("key1","value1");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", variables);
        LOGGER.info("processInstance = {}",processInstance);
    }

    //根据流程id进行启动
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testStartProcessById(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessDefinition processDefinition = activitiRule.getRepositoryService()
                .createProcessDefinitionQuery()
                .list().get(0);
//                .singleResult();
        HashMap<String, Object> variables = Maps.newHashMap();
        variables.put("key1","value1");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
        LOGGER.info("processInstance = {}",processInstance);
    }

    //build进行启动
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testStartProcessInstanceByBuild(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        HashMap<String, Object> variables = Maps.newHashMap();
        variables.put("key1","value1");
        ProcessInstanceBuilder processInstanceBuilder = runtimeService.createProcessInstanceBuilder();
        ProcessInstance processInstance = processInstanceBuilder.businessKey("businessKey001")
                .processDefinitionKey("my-process")
                .variables(variables)
                .start();
        LOGGER.info("processInstance = {}",processInstance);
    }

    //设置流程变量
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testVariables(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        HashMap<String, Object> variables = Maps.newHashMap();
        variables.put("key1","value1");
        variables.put("key2","value2");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", variables);
        LOGGER.info("processInstance = {}",processInstance);
        runtimeService.setVariable(processInstance.getId(),"key3","value3");
        Map<String, Object> variables1 = runtimeService.getVariables(processInstance.getId());
        LOGGER.info("variables1 = {}",variables1);
    }

    //查询流程实例
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testProcessInstanceQuery(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        HashMap<String, Object> variables = Maps.newHashMap();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", variables);
        LOGGER.info("processInstance = {}",processInstance);
        runtimeService.setVariable(processInstance.getId(),"key3","value3");
        ProcessInstance processInstance1 = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
        LOGGER.info("processInstance1 = {}",processInstance1);
    }

    //查询流程的执行流
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testExecutionQuery(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        HashMap<String, Object> variables = Maps.newHashMap();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", variables);
        LOGGER.info("processInstance = {}",processInstance);
        runtimeService.setVariable(processInstance.getId(),"key3","value3");
        List<Execution> executionList = runtimeService.createExecutionQuery()
                .listPage(0, 100);
        for (Execution execution : executionList) {
            LOGGER.info("execution = {}",execution);
        }
    }

    //触发userTask流程 似乎报错了...
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.trigger.bpmn20.xml"})
    public void testTrigger(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");
        Execution execution = runtimeService.createExecutionQuery()
                .activityId("someTask").list().get(0);
//                .singleResult();
        LOGGER.info("execution = {}",execution);
        runtimeService.trigger(execution.getId());
        execution = runtimeService.createExecutionQuery()
                .activityId("someTask").list().get(0);
        LOGGER.info("execution = {}",execution);
    }

    //Received触发
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.signal-received.dpmn20.xml"})
    public void testReceived(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");
        Execution execution = runtimeService.createExecutionQuery()
                .signalEventSubscriptionName("my-signal")
                .singleResult();
        LOGGER.info("execution = {}",execution);
        runtimeService.signalEventReceived("my-signal"); //触发信号
        execution = runtimeService.createExecutionQuery()
                .signalEventSubscriptionName("my-signal")
                .singleResult();
        LOGGER.info("execution = {}",execution);
    }

    //基于MessageReceived启动
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.message-received.dpmn20.xml"})
    public void testMessageReceived(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");
        Execution execution = runtimeService.createExecutionQuery()
                .messageEventSubscriptionName("my-message")
                .singleResult();
        LOGGER.info("execution = {}",execution);
        runtimeService.messageEventReceived("my-message",execution.getId()); //触发信号
        execution = runtimeService.createExecutionQuery()
                .messageEventSubscriptionName("my-message")
                .singleResult();
        LOGGER.info("execution = {}",execution);
    }

    //基于MessageReceived启动流程
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.message-received.dpmn20.xml"})
    public void testMessageStart(){
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService
//                .startProcessInstanceByKey("my-process")
                .startProcessInstanceByMessage("my-message");
        LOGGER.info("processInstance = {}",processInstance);
    }
}
