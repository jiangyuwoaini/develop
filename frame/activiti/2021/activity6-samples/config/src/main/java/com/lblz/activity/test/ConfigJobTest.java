package com.lblz.activity.test;

import com.lblz.activity.event.CustomEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEventImpl;
import org.activiti.engine.runtime.Job;
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
 * @deacription activiti job定时器
 * @date 2021/5/6 22:37
 **/
public class ConfigJobTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDbTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-job.cfg.xml");

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"}) //在单元测试启动之前 部署流程
    public void test() throws InterruptedException {
        LOGGER.info("start");
        List<Job> jobList = activitiRule.getManagementService().createTimerJobQuery().listPage(0, 100);
        for (Job job : jobList) {
            LOGGER.info("定时任务{},默认重试次数={}",job,job.getRetries());
        }
        LOGGER.info("定时任务总数 = {}",jobList.size());
        Thread.sleep(1000*100);
        LOGGER.info("end");
    }
}
