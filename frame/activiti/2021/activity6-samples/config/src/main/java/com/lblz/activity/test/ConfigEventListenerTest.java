package com.lblz.activity.test;

import com.lblz.activity.event.CustomEventListener;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.delegate.event.impl.ActivitiEventImpl;
import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author lblz
 * @deacription activiti事件监听器
 * @date 2021/5/6 22:37
 **/
public class ConfigEventListenerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDbTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-eventListener.cfg.xml");

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"}) //在单元测试启动之前 部署流程
    public void test(){
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        activitiRule.getRuntimeService().addEventListener(new CustomEventListener()); //添加一个事件
        activitiRule.getRuntimeService().dispatchEvent(new ActivitiEventImpl(ActivitiEventType.CUSTOM)); //添加一个事件

    }
}
