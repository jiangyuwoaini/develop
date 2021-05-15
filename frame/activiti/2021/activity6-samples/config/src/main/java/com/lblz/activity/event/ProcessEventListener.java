package com.lblz.activity.event;

import lombok.extern.flogger.Flogger;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lblz
 * @deacription 流程event
 * @date 2021/5/12 21:54
 **/
public class ProcessEventListener implements ActivitiEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessEventListener.class);

    @Override
    public void onEvent(ActivitiEvent event) {
        ActivitiEventType eventType = event.getType();//取出事件类型
        if(ActivitiEventType.PROCESS_STARTED.equals(eventType)){
            LOGGER.info("流程启动{}\t{}",event.getType(),event.getProcessInstanceId());
        }else if(ActivitiEventType.PROCESS_COMPLETED.equals(eventType)){
            LOGGER.info("流程结束{}\t{}",event.getType(),event.getProcessInstanceId());
        }

    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
