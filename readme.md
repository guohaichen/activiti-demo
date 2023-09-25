activiti 依赖默认集成了spring security，用户名默认user ；

activiti默认会创建如下数据库

| **表分类**   | **表名**              | **解释**                                           |
| ------------ | --------------------- | -------------------------------------------------- |
| 一般数据     |                       |                                                    |
|              | [ACT_GE_BYTEARRAY]    | 通用的流程定义和流程资源                           |
|              | [ACT_GE_PROPERTY]     | 系统相关属性                                       |
| 流程历史记录 |                       |                                                    |
|              | [ACT_HI_ACTINST]      | 历史的流程实例                                     |
|              | [ACT_HI_ATTACHMENT]   | 历史的流程附件                                     |
|              | [ACT_HI_COMMENT]      | 历史的说明性信息                                   |
|              | [ACT_HI_DETAIL]       | 历史的流程运行中的细节信息                         |
|              | [ACT_HI_IDENTITYLINK] | 历史的流程运行过程中用户关系                       |
|              | [ACT_HI_PROCINST]     | 历史的流程实例                                     |
|              | [ACT_HI_TASKINST]     | 历史的任务实例                                     |
|              | [ACT_HI_VARINST]      | 历史的流程运行中的变量信息                         |
| 流程定义表   |                       |                                                    |
|              | [ACT_RE_DEPLOYMENT]   | 部署单元信息                                       |
|              | [ACT_RE_MODEL]        | 模型信息                                           |
|              | [ACT_RE_PROCDEF]      | 已部署的流程定义                                   |
| 运行实例表   |                       |                                                    |
|              | [ACT_RU_EVENT_SUBSCR] | 运行时事件                                         |
|              | [ACT_RU_EXECUTION]    | 运行时流程执行实例                                 |
|              | [ACT_RU_IDENTITYLINK] | 运行时用户关系信息，存储任务节点与参与者的相关信息 |
|              | [ACT_RU_JOB]          | 运行时作业                                         |
|              | [ACT_RU_TASK]         | 运行时任务                                         |
|              | [ACT_RU_VARIABLE]     | 运行时变量表                                       |

Activiti 中包含一些名词，

- 流程部署：
    将画好的流程图.bpmn .png文件，流程名称，key等信息保存到数据库中
- 流程实例(执行)：
    以请假流程的审批举例，每次发起请假申请都需要创建一个流程实例，就是创建一次执行记录。
    每个流程实例中包含多个任务，每个节点都是一个任务
- 历史记录：
    当任务完成时会保存到，历史任务表中。
    当一个流程实例中的所有任务都完成时，这个流程实例就结束了，保存到历史流程实例表中。

#### 工作流引擎各service功能

| service名称       | service作用                                                  |
| ----------------- | ------------------------------------------------------------ |
| RepositoryService | activiti的资源管理类提供了管理和控制流程发布包和流程定义的操作。使用工作流建模工具设计的业务流程图需要使用此service将流程定义文件的内容部署到计算机。 |
| RuntimeService    | activiti的流程运行管理类，可以从这个服务类中获取很多关于流程执行相关的信息 |
| TaskService       | activiti的任务管理类，可以从这个类中获取任务的信息。         |
| HistoryService    | activiti的历史管理类，可以查询历史信息，执行流程时，引擎会保存很多数据（根据配置），比如流程实例启动时间，任务的参与者， 完成任务的时间，每个流程实例的执行路径，等等。 这个服务主要通过查询功能来获得这些数据。 |
| ManagerService    | activiti的引擎管理类，提供了对 Activiti 流程引擎的管理和维护功能，这些功能不在工作流驱动的应用程序中使用，主要用于 Activiti 系统的日常维护。 |

#### 流程

创建Activiti工作流主要包含以下几步：

1、定义流程，按照BPMN的规范，使用流程定义工具，用**流程符号**把整个流程描述出来

2、部署流程，把画好的流程定义文件，加载到数据库中，生成表的数据

3、启动流程，使用java代码来操作数据库表中的内容

##### 流程变量

${user} 在设置`assignee`收到任务的人时，可以使用变量表达式处理，动态的设置负责人等； 

#### 监听器

> 可以使用监听器来完成 acitiviti 流程中很多业务，例如这里在创建任务后，使用监听器自动完成第一个一个任务（场景是用户发起申请，这个申请自动完成，流转到下一流程。）

```xml
#在流程图文件中，配置任务监听器；
<userTask id="user_app_id" activiti:assignee="${user}" name="用户发起申请">
  <extensionElements>
    <activiti:taskListener class="com.example.activiti.demo.activiti.service.AutoCompleteFirstTaskListener" event="create">
    </activiti:taskListener>
  </extensionElements>
</userTask>
```

```java
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
```

