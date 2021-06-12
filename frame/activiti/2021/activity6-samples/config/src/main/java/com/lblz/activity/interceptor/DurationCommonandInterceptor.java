package com.lblz.activity.interceptor;

import org.activiti.engine.impl.interceptor.AbstractCommandInterceptor;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author lblz
 * @deacription 统计执行时间的命令拦截器
 * @date 2021/5/15 21:29
 **/
public class DurationCommonandInterceptor extends AbstractCommandInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DurationCommonandInterceptor.class);

    @Override
    public <T> T execute(CommandConfig config, Command<T> command) {
        long start = System.currentTimeMillis();//取执行时间的毫秒数
        try {
            return this.getNext().execute(config,command);
        }finally {
            long duration = System.currentTimeMillis() - start;
            LOGGER.info("{} 执行时间 = {} 毫秒",command.getClass().getSimpleName(),duration);
        }
    }
}
