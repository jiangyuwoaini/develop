package com.lblz.activity.interceptor;

import org.activiti.engine.debug.ExecutionTreeUtil;
import org.activiti.engine.impl.agenda.AbstractOperation;
import org.activiti.engine.impl.interceptor.DebugCommandInvoker;
import org.activiti.engine.logging.LogMDC;

/**
 * @author lblz
 * @deacription
 * @date 2021/5/8 1:58
 **/
public class MDCCcommandInvoker extends DebugCommandInvoker {

    @Override
    public void executeOperation(Runnable runnable) {
        boolean mdcEnabled = LogMDC.isMDCEnabled(); //mdc是否关闭
        LogMDC.setMDCEnabled(true); //设置开启mdc
        if (runnable instanceof AbstractOperation) {
            AbstractOperation operation = (AbstractOperation) runnable;
            if (operation.getExecution() != null) {
                LogMDC.putMDCExecution(operation.getExecution()); //如果是可操作对象 则放到logmdc上下文里面
            }
        }
        super.executeOperation(runnable);
        LogMDC.clear();//对mdc进行清理
        if(!mdcEnabled){ //如果进来之前不生效 则设置为不生效
            LogMDC.setMDCEnabled(false);
        }
    }
}
