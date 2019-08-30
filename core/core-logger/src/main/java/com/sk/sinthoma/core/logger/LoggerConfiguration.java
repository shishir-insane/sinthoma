/**
 * LoggerConfiguration.java - core-logger
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sk.sinthoma.core.logger.filter.RequestAndResponseLoggingFilter;

@Configuration
public class LoggerConfiguration {

    /**
     * Logger.
     *
     * @return the logger
     */
    @Bean
    public Logger logger() {
	return new Logger();
    }

    /**
     * Logger aspect.
     *
     * @param logger the logger
     * @return the logger aspect
     */
    @Bean
    public LoggerAspect loggerAspect(Logger logger) {
	return new LoggerAspect(logger);
    }

    /**
     * Once per request filter.
     *
     * @return the once per request filter
     */
    @Bean
    public OncePerRequestFilter oncePerRequestFilter() {
	return new RequestAndResponseLoggingFilter();
    }
}