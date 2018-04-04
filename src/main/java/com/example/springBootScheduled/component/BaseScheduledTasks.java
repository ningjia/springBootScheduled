package com.example.springBootScheduled.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 默认的@Scheduled样例
 */
@Component
public class BaseScheduledTasks {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * @Scheduled详解
     *
     * @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
     * @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
     * @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
     * @Scheduled(cron="") ：通过cron表达式定义规则
     */
    @Scheduled(fixedDelay = 5000)
//    @Scheduled(fixedDelayString = "${scheduled.time.delayed}") //规则由配置文件中读取
    public void reportCurrentTime() {
        logger.info("现在时间：" + dateFormat.format(new Date()));
    }

}
