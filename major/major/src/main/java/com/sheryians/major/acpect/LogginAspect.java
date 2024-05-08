package com.sheryians.major.acpect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginAspect {
    
    @Before("execution(* com.sheryians.major.controller.LoginController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("LoggingAspect: Method " + joinPoint.getSignature() + " is about to be executed.");
        System.out.println( "helllllllllllllllllllllllllllllllllllo");

    }

    @After("execution(* com.sheryians.major.controller.LoginController.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("LoggingAspect: Method " + joinPoint.getSignature() + " is executed.");
    }

    @Around("execution(* com.sheryians.major.controller.LoginController.*(..))")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("PerformanceMonitoringAspect: Method " + joinPoint.getSignature() +
                " executed in " + executionTime + " milliseconds");
        return result;
    }
}