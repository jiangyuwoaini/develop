package com.lblz.activity.test;

import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.logging.LogMDC;
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
 * @deacription activiti事件监听器配合
 * @date 2021/5/6 22:37
 **/
public class ConfigEventLogTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDbTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-eventlog.cfg.xml");

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"}) //在单元测试启动之前 部署流程
    public void test(){
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        List<EventLogEntry> eventLogEntriesByProcessInstanceId = activitiRule.getManagementService().getEventLogEntriesByProcessInstanceId(processInstance.getProcessInstanceId());
        for (EventLogEntry eventLogEntry : eventLogEntriesByProcessInstanceId) {
            LOGGER.info("eventLogEntry.type = {},eventLogEntry.data={}",eventLogEntry.getType(),new String(eventLogEntry.getData()));
        }
        LOGGER.info("eventLogEntry.size() ={}",eventLogEntriesByProcessInstanceId.size());
    }
}
