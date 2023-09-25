package com.example.activiti.demo.activiti.service;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * @author cgh
 * @create 2023-09-22
 * 自动完成流程实例的第一个任务，因为第一个任务为发起人提交的，提交即完成，
 * 1. 这里是在bpm文件中加了监听器，所以只对该流程生效。
 * 具体见bpm文件
<activiti:taskListener class="com.example.activiti.demo.activiti.service.AutoCompleteFirstTaskListener" event="create">
</activiti:taskListener>
 */
@Slf4j
@Component
public class AutoCompleteFirstTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        TaskService taskService = ProcessEngines.getDefaultProcessEngine().getTaskService();
        log.info("自动完成该节点任务,任务名称:{},负责人:{}", delegateTask.getName(), delegateTask.getAssignee());
        taskService.complete(delegateTask.getId());
    }
}
