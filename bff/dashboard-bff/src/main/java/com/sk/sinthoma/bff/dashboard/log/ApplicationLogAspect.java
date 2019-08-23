/**
 * ApplicationLogAspect.java
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

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ApplicationLogAspect {

    @Pointcut("within(com.sk.sinthoma.bff.dashboard.controller.*)")
    public void inControllerPackage() {
    }

    @Pointcut("within(com.sk.sinthoma.bff.dashboard.service.*)")
    public void inServicePackage() {

    }

    @Before("inControllerPackage()")
    public void doControllerLog() {

    }

}
