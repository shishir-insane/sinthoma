/**
 * SinthomaAuthSuccessHandler.java - sinthoma-web
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.web.user.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class SinthomaAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	    Authentication authentication) throws ServletException, IOException {

	final SavedRequest savedRequest = requestCache.getRequest(request, response);

	if (savedRequest == null) {
	    clearAuthenticationAttributes(request);
	    return;
	}
	final String targetUrlParam = getTargetUrlParameter();
	if (isAlwaysUseDefaultTargetUrl()
		|| ((targetUrlParam != null) && StringUtils.isNotBlank(request.getParameter(targetUrlParam)))) {
	    requestCache.removeRequest(request, response);
	    clearAuthenticationAttributes(request);
	    return;
	}

	clearAuthenticationAttributes(request);
    }

    /**
     * Sets the request cache.
     *
     * @param requestCache the new request cache
     */
    public void setRequestCache(RequestCache requestCache) {
	this.requestCache = requestCache;
    }
}