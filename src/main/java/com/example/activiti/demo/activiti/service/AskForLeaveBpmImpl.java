package com.example.activiti.demo.activiti.service;

import com.example.activiti.demo.activiti.mock.MockUserAndDepartment;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cgh
 * @create 2023-09-22
 */
@Service
@Slf4j
public class AskForLeaveBpmImpl implements AskForLeaveBpm {

    @Override
    public String createProcessInstance(String userId, String processKey) {
        MockUserAndDepartment mockUserAndDepartment = new MockUserAndDepartment();
        //根据userId，查询部门领导，用于审批
        String username = mockUserAndDepartment.queryUsername(userId);
        String departManagerId = mockUserAndDepartment.queryDepartManager(userId);
        log.info("userId:{},departManager:{}", username, departManagerId);
        //创建processEngine
        RuntimeService runtimeService = ProcessEngines.getDefaultProcessEngine().getRuntimeService();

        Map<String, Object> kv = new HashMap<>();
        //user 和 depart_leader为流程图的变量
        kv.put("user", username);
        kv.put("depart_leader", departManagerId);
        //启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, kv);

        //返回流程实例
        return processInstance.getId();
    }

    @Override
    public String buildProcess() {
        RepositoryService repositoryService = ProcessEngines.getDefaultProcessEngine().getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/ask-for-leave-test.bpmn20.xml")
                .name("请假流程")
                .deploy();
        return "流程部署成功，id为: " + deploy.getId() + ", 名称为：" + deploy.getName();
    }
}

