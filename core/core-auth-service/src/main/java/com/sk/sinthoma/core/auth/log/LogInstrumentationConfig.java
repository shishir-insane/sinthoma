/**
 * LogInstrumentationConfig.java - core-auth-service
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.auth.log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableAsync
@EnableScheduling
public class LogInstrumentationConfig extends AsyncConfigurerSupport implements SchedulingConfigurer {

    @Autowired
    private BeanFactory beanFactory;

    /**
     * Executor.
     *
     * @return the executor
     */
    @Bean
    public Executor executor() {
	final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
	threadPoolTaskExecutor.setCorePoolSize(1);
	threadPoolTaskExecutor.setMaxPoolSize(1);
	threadPoolTaskExecutor.initialize();

	return new LazyTraceExecutor(beanFactory, threadPoolTaskExecutor);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.scheduling.annotation.AsyncConfigurerSupport#
     * getAsyncExecutor()
     */
    @Override
    public Executor getAsyncExecutor() {
	final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
	threadPoolTaskExecutor.setCorePoolSize(1);
	threadPoolTaskExecutor.setMaxPoolSize(1);
	threadPoolTaskExecutor.initialize();

	return new LazyTraceExecutor(beanFactory, threadPoolTaskExecutor);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.scheduling.annotation.SchedulingConfigurer#configureTasks
     * (org.springframework.scheduling.config.ScheduledTaskRegistrar)
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
	scheduledTaskRegistrar.setScheduler(schedulingExecutor());
    }

    /**
     * Scheduling executor.
     *
     * @return the executor
     */
    @Bean(destroyMethod = "shutdown")
    public Executor schedulingExecutor() {
	return Executors.newScheduledThreadPool(1);
    }
}