package com.example.springBootScheduled.springBootScheduled.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Aspect
@Component
public class TaskInterceptor {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${scheduled.aop.host}")
    String host;

    /**
     *
     * execution表达式说明：
     * 1、第一个*号：表示返回类型，*号表示所有的类型。
     * 2、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.example.springBootScheduled.component包、子孙包下所有类的方法。
     * 3、第二个*号：表示类名，*号表示所有的类。
     * 4、*(..)：最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
     */
    @Around("execution(* com.example.springBootScheduled.springBootScheduled.component..*.*(..)) && @annotation(org.springframework.scheduling.annotation.Scheduled)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        String currentThread = Thread.currentThread().getName();
        if (!allowedBatchExec()) {
            logger.info("Skip batch ({}): {}", currentThread, methodName);
            return null;
        }
        logger.info("Begin batch ({}): {}", currentThread, methodName);
//        Stopwatch stopWatch = Stopwatch.createStarted();
        try {
            return pjp.proceed();
        } catch (Exception e) {
            logger.error("batch error: {}", methodName, e);
            return null;
        } finally {
//            logger.info("End batch ({}): {}, elapsed = {} (ms)", currentThread, methodName, stopWatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }

    private boolean allowedBatchExec() {
        if (getHostName().equals(host)) {
            return true;
        }
        return false;
    }

    private String getHostName(){
        String IP = "";
        String host = "";
        try
        {
            InetAddress ia = InetAddress.getLocalHost();
            host = ia.getHostName();//获取计算机主机名
            IP= ia.getHostAddress();//获取计算机IP
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
//        logger.info("HOST="+host+",IP="+IP);
        return host;
    }

}
