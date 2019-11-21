package com.vick.framework.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zyz
 * @since 2019-11-21
 */
@Slf4j
@Aspect
@Component
public class PerformanceAdvice {

    //@Pointcut("execution(* com.vick..service.impl.*.*(..))")
    @Pointcut("execution(* com.vick..controller.*.*(..))")
    private void pointCutMethodService() {
    }

    @Around("pointCutMethodService()")
    public Object doAroundService(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long end = System.currentTimeMillis();
        log.error("Call Method: {}, Elapse: {}ms, Parameter: {}", pjp.getSignature().toShortString(), end - begin, pjp.getArgs());
        return obj;
    }

}
