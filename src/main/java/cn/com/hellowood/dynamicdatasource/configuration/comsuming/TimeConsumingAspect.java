package cn.com.hellowood.dynamicdatasource.configuration.comsuming;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author msh11535
 */
@Aspect
@Component
public class TimeConsumingAspect {
    private static final Logger logger = LoggerFactory.getLogger(TimeConsumingAspect.class);
    private long startTime = 0;

//    @Pointcut("execution( * cn.com.hellowood.dynamicdatasource.controller.*.*(..))")
//    public void doAspect() {
//
//    }

    @Before("@annotation(Consuming)")
    public void getTimeConsuming(JoinPoint point) {
        startTime = System.currentTimeMillis();
    }


    @After("@annotation(Consuming)")
    public void afterReturning(JoinPoint point) {
        long spendTime = System.currentTimeMillis() - startTime;
        String className = point.getTarget().getClass().getSimpleName();
        String methodName = point.getSignature().getName();

        logger.info("{} {} costs {}ms ", className, methodName, spendTime);
    }

    /**
     * 声明环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
//    @Around("doAspect()")
//    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
//        long begin = System.nanoTime();
//        Object o = pjp.proceed();
//        long end = System.nanoTime();
//        logger.info(String.format("%s{%s}:{%s}", pjp.getTarget().getClass(), pjp.getSignature().getName(), (end - begin) / 1000000));
//        return o;
//    }

}
