package com.lblz.activity.delegate;


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lblz
 * @deacription
 * @date 2021/5/8 1:23
 **/
public class MDCErrorDelegage implements JavaDelegate {
    private static final Logger LOGGER = LoggerFactory.getLogger(MDCErrorDelegage.class);


    @Override
    public void execute(DelegateExecution execution) {
        LOGGER.info("run MDCErrorDelegage");
//        throw new RuntimeException("only test");
    }
}
