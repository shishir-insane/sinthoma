/**
 * LogInstrumentationConfig.java
 * dashboard-bff
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
package com.sk.sinthoma.bff.dashboard.log;

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

    @Bean
    public Executor executor() {
	final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
	threadPoolTaskExecutor.setCorePoolSize(1);
	threadPoolTaskExecutor.setMaxPoolSize(1);
	threadPoolTaskExecutor.initialize();

	return new LazyTraceExecutor(beanFactory, threadPoolTaskExecutor);
    }

    @Override
    public Executor getAsyncExecutor() {
	final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
	threadPoolTaskExecutor.setCorePoolSize(1);
	threadPoolTaskExecutor.setMaxPoolSize(1);
	threadPoolTaskExecutor.initialize();

	return new LazyTraceExecutor(beanFactory, threadPoolTaskExecutor);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
	scheduledTaskRegistrar.setScheduler(schedulingExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public Executor schedulingExecutor() {
	return Executors.newScheduledThreadPool(1);
    }
}