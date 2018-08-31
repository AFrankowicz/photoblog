package com.zti.photoblog.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect for loggin execution of events.
 */
@Aspect
@Component
public class ExecutionAspect {

    @Around("@annotation(LogExecution)")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();

        System.out.println("Exectuting: " + joinPoint.getSignature());
        return proceed;
    }

}