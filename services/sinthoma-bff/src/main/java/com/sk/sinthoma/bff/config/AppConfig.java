package com.sk.sinthoma.bff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class AppConfig {

    @Bean
    public LayoutDialect layoutDialect() {
	return new LayoutDialect();
    }

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
	CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
	filter.setIncludeQueryString(true);
	filter.setIncludePayload(true);
	filter.setMaxPayloadLength(10000);
	filter.setIncludeHeaders(false);
	filter.setAfterMessagePrefix("REQUEST DATA : ");
	return filter;
    }
}
