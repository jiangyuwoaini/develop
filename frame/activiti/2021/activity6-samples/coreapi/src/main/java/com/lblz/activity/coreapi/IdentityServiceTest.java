package com.lblz.activity.coreapi;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author lblz
 * @deacription 身份管理服务
 * @date 2021/5/16 16:28
 **/
public class IdentityServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdentityServiceTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    public void testIdentity(){
        IdentityService identityService = activitiRule.getIdentityService();
        User user1 = identityService.newUser("user1");
        user1.setEmail("user1@126.com");
        User user2 = identityService.newUser("user2");
        user2.setEmail("user2@126.com");
        identityService.saveUser(user1);
        identityService.saveUser(user2);

        Group group1 = identityService.newGroup("group1");//创建用户组
        identityService.saveGroup(group1);
        Group group2 = identityService.newGroup("group2");//创建用户组
        identityService.saveGroup(group2);
        //创建他们之间的关系
        identityService.createMembership("user1","group1");
        identityService.createMembership("user2","group1");
        identityService.createMembership("user1","group2");
        User user11 = identityService.createUserQuery().userId("user1").singleResult();
        user11.setLastName("jim");
        //床架你查询对象
        List<User> userList = identityService.createUserQuery().memberOfGroup("group1").listPage(0, 100);
        for (User user : userList) {
            LOGGER.info("user = {}",user);
        }
        List<Group> gorGroupList = identityService.createGroupQuery().groupMember("user1").listPage(0, 100);
        for (Group group : gorGroupList) {
            LOGGER.info("group = {}",group);
        }
    }
}
