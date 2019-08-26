/**
 * PerfLogConfiguration.java
 * user-manager
 * Copyright 2019 Shishir Kumar
 * 
 * Licensed under the GNU Lesser General Public License v3.0
 * Permissions of this license are conditioned on making available complete 
 * source code of licensed works and modifications under the same license 
 * or the GNU GPLv3. Copyright and license notices must be preserved. 
 * 
 * Contributors provide an express grant of patent rights. However, a larger 
 * work using the licensed work through interfaces provided by the licensed 
 * work may be distributed under different terms and without source code for 
 * the larger work.
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

    @Pointcut("execution(* com.sk.sinthoma.core.auth.*.*.*(..))")
    public void monitor() {
	log.info("Pointcut defined for execution(* com.sk.sinthoma.core.auth.*.*.*(..))");
    }

    @Bean
    public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
	return new PerformanceMonitorInterceptor(false);
    }

    @Bean
    public Advisor performanceMonitorAdvisor() {
	final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	pointcut.setExpression("com.sk.sinthoma.core.auth.log.PerfLogConfiguration.monitor()");
	return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
    }
}