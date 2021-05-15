package com.lblz.activity.test;


import com.google.common.collect.Maps;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.DateFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @ClassName DemoMain
 * @Deacription TODO
 * @Author J y
 * @Date 2021/4/24 22:44
 * @Version 1.0
 **/
public class DemoMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoMain.class);
    public static void main(String[] args) throws ParseException {
        LOGGER.info("程序启动成功");
        //一、创建流程引擎
        ProcessEngine processEngine = getProcessEngine();
        //二、部署流程文件
        ProcessDefinition processDefinition = getProcessDefinition(processEngine);
        //三、启动会流程
        ProcessInstance processInstance = getProcessDefinition(processEngine, processDefinition);
        //处理流程任务
        processDispose(processEngine, processInstance);
    }

    private static void processDispose(ProcessEngine processEngine, ProcessInstance processInstance) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        while(processInstance != null && !processInstance.isEnded()) {
            TaskService taskService = processEngine.getTaskService(); //提交访问task服务
            List<Task> taskList = taskService.createTaskQuery().list();//任务列表
            for (Task task : taskList) {
                LOGGER.info("待处理任务[{}]", task.getName());
                Map<String, Object> variables = getStringObjectMap(processEngine, scanner, task); //对执行的变量进行获取
                taskService.complete(task.getId(), variables);//结束本次任务
                processInstance = processEngine.getRuntimeService()
                        .createProcessInstanceQuery()//创建一个查询流程的实例
                        .processInstanceId(processInstance.getId()) //根据id查询流程实例
                        .singleResult();
            }
            LOGGER.info("待处理数量[{}]", taskList.size());
            LOGGER.info("程序结束");
        }
        scanner.close();
    }


    private static Map<String, Object> getStringObjectMap(ProcessEngine processEngine, Scanner scanner, Task task) throws ParseException {
        FormService formService = processEngine.getFormService(); //获取表单服务
        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormProperty> formProperties = taskFormData.getFormProperties(); //获取表单字段
        Map<String, Object> variables = Maps.newConcurrentMap();
        for (FormProperty property : formProperties) {
            String lineStr = null;
            if (StringFormType.class.isInstance(property.getType())) {
                LOGGER.info("请输入 {}:", property.getName());
                lineStr = scanner.nextLine();
                variables.put(property.getId(), lineStr);
            } else if (DateFormType.class.isInstance(property.getType())) {
                LOGGER.info("请输入{}?格式(yyyy-MM-dd)", property.getName());
                lineStr = scanner.nextLine();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse(lineStr);
                variables.put(property.getId(), date);
            } else {
                LOGGER.info("暂不支持类型{}", property.getType());
            }
            LOGGER.info("你输入的内容是[{}]", lineStr);
        }
        return variables;
    }

    private static ProcessInstance getProcessDefinition(ProcessEngine processEngine, ProcessDefinition processDefinition) {
        RuntimeService runtimeService = processEngine.getRuntimeService(); //获取执行服务
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());//根据id执行流程
        LOGGER.info("流程启动[{}]",processInstance.getProcessDefinitionKey());
        return processInstance;
    }

    private static ProcessDefinition getProcessDefinition(ProcessEngine processEngine) {
        RepositoryService repositoryService = processEngine.getRepositoryService(); //提供对流程定义和部署存储库的访问的服务。
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment(); //创建部署生成器
        deploymentBuilder.addClasspathResource("second_approve.bpmn"); //通过io流程读取并添加流程 添加到DeploymentEntity
        Deployment deployment = deploymentBuilder.deploy(); //为资源提供activity引擎
        String deploymentId = deployment.getId();
        ProcessDefinition processDefinition //根据id获取流程定义对象
                = repositoryService
                .createProcessDefinitionQuery()//查询流程定义
                .deploymentId(deploymentId) //对流程进行部署 根据id
                .singleResult(); //返回查询结果实体 如果没有结果 返回null
        LOGGER.info("流程定义文件[{}],流程id",processDefinition.getName(),deploymentId);
        return processDefinition;
    }

    private static ProcessEngine getProcessEngine() {
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration(); //创建流程引擎 基于配置
        ProcessEngine processEngine = cfg.buildProcessEngine(); //构建流程引擎 对引擎进行初始化
        String name = processEngine.getName();//获取流程引擎name
        String version = ProcessEngine.VERSION;//获取流程引擎的版本
        LOGGER.info("流程引擎名称[{}],版本[{}]",name,version);
        return processEngine;
    }
}
