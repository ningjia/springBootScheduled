# Spring Boot中使用@Scheduled
## 串行与并行执行
- 基础的定时任务@Scheduled测试（串行执行）：
    - 测试类：BaseScheduledTasks.java
    - 需确认ScheduleConfig.java中的'@Configuration'注解，是被注释的状态
    - 可观察日志，所有定时任务运行在一个线程中
- 并行执行的定时任务
    - 测试类：BaseScheduledTasks.java
    - 需确认ScheduleConfig.java中的'@Configuration'注解，没有被注释
    - 可观察日志，所有定时任务运行在多个不同的线程中

## 规则定义到配置文件
- properties配置文件中加入
```properties
    cron.expression=0 0 0/1 * * ? 
    task.exec.time.delayed=5000
```
- 代码中使用
```java
    @Scheduled(cron = "${cron.expression}")  
    public void demoServiceMethod() {  
    }  
    @Scheduled(fixedDelayString = "${task.exec.time.delayed}")  
    public void autoProcess() {  
    }  
```

## 通过AOP拦截限定定时任务的启动
- 代码参见TaskInterceptor.java


## Refer
- [Spring Boot中使用@Scheduled创建定时任务](http://blog.didispace.com/springbootscheduled/)
- [SpringBoot Schedule 配置](https://www.cnblogs.com/slimer/p/6222485.html)
- [动态定义、规则定义到配置文件、集群下的定时任务](http://rensanning.iteye.com/blog/2361912)
- [Scheduled中的多线程分析](https://unmi.cc/spring-schedule-runner-threads/)
- [图解@Scheduled定时任务的fixedRate,fixedDelay,cron执行差异](https://blog.csdn.net/applebomb/article/details/52400154)