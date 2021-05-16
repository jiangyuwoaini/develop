package com.lblz.activity.event;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author lblz
 * @deacription job
 * @date 2021/5/15 21:29
 **/
public class JobEventListener implements ActivitiEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobEventListener.class);

    @Override
    public void onEvent(ActivitiEvent event) {
        ActivitiEventType eventType = event.getType();//取出事件类型
        String name = eventType.name();
        if(name.startsWith("TIMER") || name.startsWith("JOB")){
            LOGGER.info("监听到Job事件{}\t{}",eventType,event.getProcessInstanceId());
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
