package com.lblz.activity.event;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lblz
 * @deacription 自定义监听事件
 * @date 2021/5/12 21:54
 **/
public class CustomEventListener implements ActivitiEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomEventListener.class);

    @Override
    public void onEvent(ActivitiEvent event) {
        ActivitiEventType eventType = event.getType();//取出事件类型
        if(ActivitiEventType.CUSTOM.equals(eventType)){
            LOGGER.info("监听自定义事件{}",event.getType(),event.getProcessInstanceId());
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
