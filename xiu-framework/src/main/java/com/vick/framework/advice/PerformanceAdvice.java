package com.vick.framework.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        Object[] params = pjp.getArgs();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.getNodeFactory().objectNode();
        for (int i = 0; i < params.length; i++) {
            if (params[i] instanceof BindingResult
                    || params[i] instanceof HttpServletRequest
                    || params[i] instanceof HttpServletResponse) {
                continue;
            }
            node.put("p" + i + "-" + params[i].getClass().getSimpleName(), mapper.writeValueAsString(params[i]));
        }
        long begin = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long end = System.currentTimeMillis();
        log.error("Call Method: {}, Elapse: {}ms, Parameter: {}", pjp.getSignature().toShortString(), end - begin, node.toString());
        return obj;
    }

}
