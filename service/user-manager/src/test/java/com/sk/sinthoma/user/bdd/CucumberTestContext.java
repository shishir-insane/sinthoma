/**
 * CucumberTestContext.java
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
package com.sk.sinthoma.user.bdd;

import static io.restassured.RestAssured.given;
import static java.lang.ThreadLocal.withInitial;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Singleton to manage objects and share their state between step definitions.
 */
public enum CucumberTestContext {
    CONTEXT;

    private static final String PAYLOAD = "PAYLOAD";
    private static final String REQUEST = "REQUEST";
    private static final String RESPONSE = "RESPONSE";

    private final ThreadLocal<Map<String, Object>> threadLocal = withInitial(HashMap::new);

    private Map<String, Object> testContextMap() {
	return threadLocal.get();
    }

    public void set(String key, Object value) {
	testContextMap().put(key, value);
    }

    public Object get(String key) {
	return testContextMap().get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
	return clazz.cast(testContextMap().get(key));
    }

    public void setPayload(Object value) {
	set(PAYLOAD, value);
    }

    public Object getPayload() {
	return testContextMap().get(PAYLOAD);
    }

    public <T> T getPayload(Class<T> clazz) {
	return get(PAYLOAD, clazz);
    }

    public RequestSpecification getRequest() {
	final RequestSpecification req = get(REQUEST, RequestSpecification.class);
	return (null == req) ? given() : req;
    }

    public void setResponse(Response response) {
	set(RESPONSE, response);
    }

    public Response getResponse() {
	return get(RESPONSE, Response.class);
    }

    public void reset() {
	testContextMap().clear();
    }

}