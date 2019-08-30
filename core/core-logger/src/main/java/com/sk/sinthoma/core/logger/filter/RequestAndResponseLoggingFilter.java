/**
 * RequestAndResponseLoggingFilter.java - core-logger
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.logger.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestAndResponseLoggingFilter extends OncePerRequestFilter {
    private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(MediaType.valueOf("text/*"),
	    MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
	    MediaType.valueOf("application/*+json"), MediaType.valueOf("application/*+xml"),
	    MediaType.MULTIPART_FORM_DATA);

    /* (non-Javadoc)
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {
	if (isAsyncDispatch(request)) {
	    filterChain.doFilter(request, response);
	} else {
	    doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
	}
    }

    /**
     * Do filter wrapped.
     *
     * @param request     the request
     * @param response    the response
     * @param filterChain the filter chain
     * @throws ServletException the servlet exception
     * @throws IOException      Signals that an I/O exception has occurred.
     */
    protected void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response,
	    FilterChain filterChain) throws ServletException, IOException {
	try {
	    beforeRequest(request, response);
	    filterChain.doFilter(request, response);
	} finally {
	    afterRequest(request, response);
	    response.copyBodyToResponse();
	}
    }

    /**
     * Before request.
     *
     * @param request  the request
     * @param response the response
     */
    protected void beforeRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {
	if (log.isInfoEnabled()) {
	    logRequestHeader(request, request.getRemoteAddr() + "|>");
	}
    }

    /**
     * After request.
     *
     * @param request  the request
     * @param response the response
     */
    protected void afterRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {
	if (log.isInfoEnabled()) {
	    logRequestBody(request, request.getRemoteAddr() + "|>");
	    logResponse(response, request.getRemoteAddr() + "|<");
	}
    }

    /**
     * Log request header.
     *
     * @param request the request
     * @param prefix  the prefix
     */
    private static void logRequestHeader(ContentCachingRequestWrapper request, String prefix) {
	val queryString = request.getQueryString();
	if (queryString == null) {
	    log.info("{} {} {}", prefix, request.getMethod(), request.getRequestURI());
	} else {
	    log.info("{} {} {}?{}", prefix, request.getMethod(), request.getRequestURI(), queryString);
	}
	Collections.list(request.getHeaderNames())
		.forEach(headerName -> Collections.list(request.getHeaders(headerName))
			.forEach(headerValue -> log.info("{} {}: {}", prefix, headerName, headerValue)));
	log.info("{}", prefix);
    }

    /**
     * Log request body.
     *
     * @param request the request
     * @param prefix  the prefix
     */
    private static void logRequestBody(ContentCachingRequestWrapper request, String prefix) {
	val content = request.getContentAsByteArray();
	if (content.length > 0) {
	    logContent(content, request.getContentType(), request.getCharacterEncoding(), prefix);
	}
    }

    /**
     * Log response.
     *
     * @param response the response
     * @param prefix   the prefix
     */
    private static void logResponse(ContentCachingResponseWrapper response, String prefix) {
	val status = response.getStatus();
	log.info("{} {} {}", prefix, status, HttpStatus.valueOf(status).getReasonPhrase());
	response.getHeaderNames().forEach(headerName -> response.getHeaders(headerName)
		.forEach(headerValue -> log.info("{} {}: {}", prefix, headerName, headerValue)));
	log.info("{}", prefix);
	val content = response.getContentAsByteArray();
	if (content.length > 0) {
	    logContent(content, response.getContentType(), response.getCharacterEncoding(), prefix);
	}
    }

    /**
     * Log content.
     *
     * @param content         the content
     * @param contentType     the content type
     * @param contentEncoding the content encoding
     * @param prefix          the prefix
     */
    private static void logContent(byte[] content, String contentType, String contentEncoding, String prefix) {
	val mediaType = MediaType.valueOf(contentType);
	val visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
	if (visible) {
	    try {
		val contentString = new String(content, contentEncoding);
		Stream.of(contentString.split("\r\n|\r|\n")).forEach(line -> log.info("{} {}", prefix, line));
	    } catch (final UnsupportedEncodingException e) {
		log.info("{} [{} bytes content]", prefix, content.length);
	    }
	} else {
	    log.info("{} [{} bytes content]", prefix, content.length);
	}
    }

    /**
     * Wrap request.
     *
     * @param request the request
     * @return the content caching request wrapper
     */
    private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
	if (request instanceof ContentCachingRequestWrapper) {
	    return (ContentCachingRequestWrapper) request;
	} else {
	    return new ContentCachingRequestWrapper(request);
	}
    }

    /**
     * Wrap response.
     *
     * @param response the response
     * @return the content caching response wrapper
     */
    private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
	if (response instanceof ContentCachingResponseWrapper) {
	    return (ContentCachingResponseWrapper) response;
	} else {
	    return new ContentCachingResponseWrapper(response);
	}
    }
}