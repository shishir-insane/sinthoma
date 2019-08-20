package com.sk.sinthoma.bff.dashboard.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class ApplicationLogAspect {
    
    @Pointcut("within(com.sk.sinthoma.bff.dashboard.controller.*)")
    public void inControllerPackage() {}
    
    @Pointcut("within(com.sk.sinthoma.bff.dashboard.service.*)")
    public void inServicePackage() {
	
    }
    
    @Before("inControllerPackage()")
    public void doControllerLog() {
	
    }
    
    
}
