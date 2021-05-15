package com.lblz.activity.test;

import com.google.common.collect.Maps;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.logging.LogMDC;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * @author lblz
 * @deacription activiti历史记录不同等级配置 要养成良好的 重构习惯
 * @date 2021/5/6 22:37
 **/
public class ConfigHistoryLevelTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigHistoryLevelTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-history.cfg.xml");

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"}) //在单元测试启动之前 部署流程
    public void test(){
        //启动流程
        ConcurrentMap<String, Object> params = Maps.newConcurrentMap();
        params.put("keyStart1","value1");
        params.put("keyStart2","value2");
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process",params);
        //修改变量
        List<Execution> executions = activitiRule.getRuntimeService().createExecutionQuery().listPage(0, 100);//查询分页查询条件
        for (Execution execution: executions) {
            LOGGER.info("execution1 = {}",execution);
        }
        LOGGER.info("size = {}",executions.size());
        String id = executions.iterator().next().getId();//获取到当前执行节点的id
        activitiRule.getRuntimeService().setVariable(id,"keyStart1","value_1"); //修改变量 value内容
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        Map<String, String> mapParams = Maps.newHashMap();
        params.put("keyStart1","value1");
        params.put("keyStart2","value2");
        //提交表单task
        activitiRule.getFormService().submitStartFormData(task.getId(),mapParams); //将map内容以表单形式进行提交修改
        //输出历史内容
        //输出历史活动
        List<HistoricActivityInstance> historicActivityInstances = activitiRule.getHistoryService().createHistoricActivityInstanceQuery().listPage(0, 100);
        for (HistoricActivityInstance his: historicActivityInstances) {
            LOGGER.info("history = {}",historicActivityInstances);
        }
        LOGGER.info("history-size = {}",historicActivityInstances.size());
        //输出历史详情
        List<HistoricDetail> historicDetailsFrom = activitiRule.getHistoryService().createHistoricDetailQuery().formProperties().listPage(0, 100);
        for (HistoricDetail historicDetailForm : historicDetailsFrom) {
            LOGGER.info("HistoricDetailForm = {}",historicDetailForm);
        }
        LOGGER.info("HistoricDetailForm-size = {}",historicDetailsFrom.size());
        //输出历史表单
        List<HistoricDetail> historicDetailArray = activitiRule.getHistoryService().createHistoricDetailQuery().listPage(0, 100);
        for (HistoricDetail historicDetail : historicDetailArray) {
            LOGGER.info("historicDetail = {}",historicDetail);
        }
        LOGGER.info("historicDetails-size",historicDetailArray.size());
    }
}
