/**
 * PerfLogConfiguration.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableAspectJAutoProxy
@Aspect
@Slf4j
public class PerfLogConfiguration {

    /**
     * Monitor.
     */
    @Pointcut("execution(* com.sk.sinthoma.core.auth.*.*.*(..))")
    public void monitor() {
	log.info("Pointcut defined for execution(* com.sk.sinthoma.core.auth.*.*.*(..))");
    }

    /**
     * Performance monitor interceptor.
     *
     * @return the performance monitor interceptor
     */
    @Bean
    public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
	return new PerformanceMonitorInterceptor(false);
    }

    /**
     * Performance monitor advisor.
     *
     * @return the advisor
     */
    @Bean
    public Advisor performanceMonitorAdvisor() {
	final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	pointcut.setExpression("com.sk.sinthoma.core.auth.log.PerfLogConfiguration.monitor()");
	return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
    }
}