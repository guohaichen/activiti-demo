package com.example.activiti.demo.web.controller;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cgh
 * @create 2023-09-25
 * 测试表达式任务，在gateway-application-test.bpmn中根据 day的条件表达式，大于3天，小于3天流向不同的领导人审批
 */
@RestController
@RequestMapping
public class UELController {
    //部署流程
    @GetMapping("uel-process")
    public String uelProcess() {
        RepositoryService repositoryService = ProcessEngines.getDefaultProcessEngine().getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/gateway-application-test.bpmn20.xml")
                .name("出差申请流程")
                .deploy();
        return "流程 id: " + deploy.getId() + " key: " + deploy.getKey() + "name: " + deploy.getName();
    }

    //创建实例
    @GetMapping("uel-instance")
    public String uelInstance() {
        RuntimeService runtimeService = ProcessEngines.getDefaultProcessEngine().getRuntimeService();
        Map<String, Object> varKV = new HashMap<>();
        varKV.put("user", "前端开发工程师");
        varKV.put("depart_leader", "开发经理");
        varKV.put("bp_leader", "开发总监");
        varKV.put("day", 4);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("gateway-application-test", varKV);

        return "流程定义id：" + processInstance.getProcessDefinitionId() +
                "流程实例id：" + processInstance.getId() +
                "当前活动Id：" + processInstance.getActivityId();

    }
    @Autowired
    TaskService taskService;

    @GetMapping("uel-complete")
    public void completeTask(){
        taskService.complete("2fc6a5b2-5b6b-11ee-91fd-b025aa37ec89");
    }
}
