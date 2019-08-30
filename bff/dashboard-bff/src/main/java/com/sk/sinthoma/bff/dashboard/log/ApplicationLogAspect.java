/**
 * ApplicationLogAspect.java - dashboard-bff
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.bff.dashboard.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ApplicationLogAspect {

    /**
     * In controller package.
     */
    @Pointcut("within(com.sk.sinthoma.bff.dashboard.controller.*)")
    public void inControllerPackage() {
    }

    /**
     * In service package.
     */
    @Pointcut("within(com.sk.sinthoma.bff.dashboard.service.*)")
    public void inServicePackage() {

    }

    /**
     * Do controller log.
     */
    @Before("inControllerPackage()")
    public void doControllerLog() {

    }

}
