package com.sk.sinthoma.user.bdd;

import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import com.sk.sinthoma.user.UserManagerApplication;

import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = UserManagerApplication.class, loader = SpringBootContextLoader.class)
@Slf4j
public class CucumberSpringContextConfiguration {
    
    @Before
    public void setUp() {
	log.info("Spring Context Initialized For Executing Cucumber Tests-");
    }

}
