/**
 * AppConfig.java - sinthoma-web
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class AppConfig {

    /**
     * Layout dialect.
     *
     * @return the layout dialect
     */
    @Bean
    public LayoutDialect layoutDialect() {
	return new LayoutDialect();
    }

    /**
     * Log filter.
     *
     * @return the commons request logging filter
     */
    @Bean
    public CommonsRequestLoggingFilter logFilter() {
	final CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
	filter.setIncludeQueryString(true);
	filter.setIncludePayload(true);
	filter.setMaxPayloadLength(10000);
	filter.setIncludeHeaders(false);
	filter.setAfterMessagePrefix("REQUEST DATA : ");
	return filter;
    }
}
