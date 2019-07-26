/**
 * SinthomaAuthSuccessHandler.java
 * sinthoma-bff
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
package cok.sk.sinthoma.bff.user.security;

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

    public void setRequestCache(RequestCache requestCache) {
	this.requestCache = requestCache;
    }
}