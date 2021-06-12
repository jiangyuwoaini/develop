package com.lblz.activity.coreapi;


import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.*;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author lblz
 * @deacription RepostoryService服务详解
 * @date 2021/5/16 12:13
 **/
public class RepostoryServiceTest {
    private static final  Logger LOGGER = LoggerFactory.getLogger(RepostoryServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    public void testRepostory(){
        RepositoryService repositoryService = activitiRule.getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.name("测试部署资源") //添加流程图
                         .addClasspathResource("my-process.bpmn20.xml")
                         .addClasspathResource("second_approve.bpmn20.xml");
        Deployment deploy = deploymentBuilder.deploy(); //将流程资源部署到数据库

        DeploymentBuilder deploymentBuilder1 = repositoryService.createDeployment();
        deploymentBuilder1.name("测试部署资源1") //可以进行多次部署
                .addClasspathResource("my-process.bpmn20.xml")
                .addClasspathResource("second_approve.bpmn20.xml");
        Deployment deploy1 = deploymentBuilder1.deploy(); //可以进行多次部署

        LOGGER.info("deploy = {}",deploy);
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery(); //创建查询结果集 查询流程？ 好像是诶
        List<Deployment> deploymentList = deploymentQuery
//                                .deploymentId(deploy.getId())
                                .orderByDeploymenTime().asc()
                                .listPage(0,100);
//                                .singleResult();
        for (Deployment deployment : deploymentList) {
            LOGGER.info("deployment = {}",deployment);
        }
        LOGGER.info("deployment.size 总数 = {}",deploymentList.size());
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery() //查询流程定义文件 //查询好像跟上面查询是一样的 不过可以设置查询条件等等
//                .deploymentId(deployment.getId())       //好像不跟上面一样- -
                  .orderByProcessDefinitionKey().asc()
                  .listPage(0, 100);
        for (ProcessDefinition processDefinition : processDefinitions) {
            LOGGER.info("processDefinition = {} ,version = {} ,key = {}, id = {}",
                    processDefinition,
                    processDefinition.getVersion(),
                    processDefinition.getKey(),
                    processDefinition.getId());
        }
    }

    /**
     * @Author Jy
     * remark 流程的启动和挂载
     * @Param []
     * @return void
     **/
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testSuspend(){
        RepositoryService repositoryService = activitiRule.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().list().get(0);//.singleResult();
        LOGGER.info("processDefinition.id = {}",processDefinition.getId());
        repositoryService.suspendProcessDefinitionById(processDefinition.getId());
        try {
            LOGGER.info("开始启动");
            activitiRule.getRuntimeService().startProcessInstanceById(processDefinition.getId());
            LOGGER.info("开始成功");
        }catch (Exception e){
            LOGGER.info("启动失败");
            LOGGER.info(e.getMessage());
        }
        repositoryService.activateProcessDefinitionById(processDefinition.getId()); //流程激活
        LOGGER.info("开始启动");
        activitiRule.getRuntimeService().startProcessInstanceById(processDefinition.getId());
        LOGGER.info("开始成功");
    }
    @Test
    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    public void testCandidateend() {
        RepositoryService repositoryService = activitiRule.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().list().get(0);//.singleResult();
            LOGGER.info("processDefinition.id = {}",processDefinition.getId());
            repositoryService.addCandidateStarterGroup(processDefinition.getId(),"user");
            repositoryService.addCandidateStarterGroup(processDefinition.getId(),"group");
        List<IdentityLink> identityLinksForProcessDefinition  //查询流程定义
                = repositoryService.getIdentityLinksForProcessDefinition(processDefinition.getId());
            for (IdentityLink identityLink : identityLinksForProcessDefinition) {
            LOGGER.info("identityLink = {}",identityLink);
        }
            repositoryService.deleteCandidateStarterGroup(processDefinition.getId(),"groupM");
            repositoryService.deleteCandidateStarterGroup(processDefinition.getId(),"user");
    }
}
