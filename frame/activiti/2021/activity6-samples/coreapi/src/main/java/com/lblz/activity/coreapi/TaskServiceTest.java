package com.lblz.activity.coreapi;

import com.google.common.collect.Maps;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.*;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author lblz
 * @deacription taskService详解
 * @date 2021/5/16 15:18
 **/
public class TaskServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    //对用户任务(UserTask)管理和流程的控制
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testTaskService(){
        HashMap<String, Object> variables = Maps.newHashMap();
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", variables);
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().list().get(0); //singleResult();
        LOGGER.info("task = {}", task.toString());
        LOGGER.info("task.deacription = {}", task.getDescription());
        taskService.setVariable(task.getId(),"key1","value1");
        taskService.setVariableLocal(task.getId(),"local1","value1");
        Map<String, Object> taskServiceVariables = taskService.getVariables(task.getId());
        Map<String, Object> taskServiceVariablesLocal = taskService.getVariablesLocal(task.getId());

        Map<String, Object> processVarables = activitiRule.getRuntimeService().getVariables(task.getExecutionId());
        LOGGER.info("本地变量1 = {}", taskServiceVariables);
        LOGGER.info("本地变量2 = {}", taskServiceVariablesLocal);
        LOGGER.info("t流程变量 = {}", processVarables);

        Map<String, Object> completeVar = Maps.newConcurrentMap();
        completeVar.put("ckey1","cValue1");
        taskService.complete(task.getId(),completeVar);
        Task task1 = taskService.createTaskQuery().taskId(task.getId()).singleResult();
        LOGGER.info("task1 = {}",task1);
    }

    //设置用户任务(UserTask)的权限信息(拥有者,候选人,办理人)
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testTaskServiceUser(){
        HashMap<String, Object> variables = Maps.newHashMap();
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", variables);
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().list().get(0); //singleResult();
        LOGGER.info("task = {}", task.toString());
        LOGGER.info("task.deacription = {}", task.getDescription());
        taskService.setOwner(task.getId(),"jimmy");
//        taskService.setAssignee(task.getId(),"jimmy");
        List<Task> taskList = taskService.createTaskQuery()
                .taskCandidateOrAssigned("jimmy")
                .taskUnassigned().listPage(0, 100);
        for (Task task1 : taskList) {
            try {
                taskService.claim(task.getId(),"jimmy");
            }catch (Exception e){
                LOGGER.error(e.getMessage(),e);
            }
        }
        List<IdentityLink> identityLinksForTask = taskService.getIdentityLinksForTask(task.getId());
        for (IdentityLink identityLink : identityLinksForTask) {
            LOGGER.info("identityLink = {}",identityLink);
        }

        List<Task> jimmys = taskService.createTaskQuery().taskAssignee("jimmy").listPage(0, 100);
        for (Task jimmy : jimmys) {
            HashMap<String, Object> vars = Maps.newHashMap();
            vars.put("ckey1","cvalue1");
            taskService.complete(jimmy.getId(),vars);
        }
        jimmys = taskService.createTaskQuery().taskAssignee("jimmy").listPage(0, 100);
        LOGGER.info(" 是否存在 {}", CollectionUtils.isEmpty(jimmys));
    }

    //针对用户任务添加任务附件、任务评论和事件记录
    @Test //附件
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testTaskAttachment(){
        HashMap<String, Object> variables = Maps.newHashMap();
        variables.put("message","my test message");
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", variables);
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().list().get(0); //singleResult();
        taskService.createAttachment("url",task.getId(),task.getProcessInstanceId(),"name","desc","/url/test.png");
        List<Attachment> taskAttachments = taskService.getTaskAttachments(task.getId());
        for (Attachment taskAttachment : taskAttachments) {
//            LOGGER.info("taskAttachment = {}",ToStringBuilder.reflectionToString(taskAttachment,ToStringStyle.JSON_STYLE));
            LOGGER.info("taskAttachment = {}",taskAttachment);
        }
    }

    //针对用户任务添加任务附件、任务评论和事件记录
    @Test //评论
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testTaskComment(){
        HashMap<String, Object> variables = Maps.newHashMap();
        variables.put("message","my test message");
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", variables);
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().list().get(0); //singleResult();
        taskService.setOwner(task.getId(),"user1");
        taskService.setAssignee(task.getId(),"jimmy");
        taskService.createAttachment("url",task.getId(),task.getProcessInstanceId(),"name","desc","/url/test.png");
        taskService.addComment(task.getId(),task.getProcessInstanceId(),"record note 1");
        taskService.addComment(task.getId(),task.getProcessInstanceId(),"record note 2");
        List<Comment> commentList = taskService.getTaskComments(task.getId()); //获取附件
        for (Comment comment : commentList) {
//            LOGGER.info("taskAttachment = {}",ToStringBuilder.reflectionToString(taskAttachment,ToStringStyle.JSON_STYLE));
            LOGGER.info("taskCommont = {}",task);
        }
        List<Event> taskEvents = taskService.getTaskEvents(task.getId());//获取事件记录
        for (Event taskEvent : taskEvents) {
            LOGGER.info("taskEvent = {}",taskEvent);
        }
    }
}
