/**
 * LoggableErrorLevelTest.java
 * core-logger
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
package com.sk.sinthoma.core.logger;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class LoggableErrorLevelTest {

    @Rule
    public OutputCaptureRule capture = new OutputCaptureRule();

    @Autowired
    private SomeService2 someService2;

    @BeforeClass
    public static void setErrorLogging() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME, LogLevel.ERROR);
    }

    @Test
    public void warn2SecWarnDisabledTest() {
	someService2.withWarn2Sec();
	final Pattern pattern = Pattern
		.compile(
			"(WARN com\\.sk\\.sinthoma\\.core\\.logger\\.LoggableErrorLevelTest\\$SomeService2 "
				+ "- \\#withWarn2Sec\\(\\[\\]\\): in .* and still running \\(max PT0.002S\\))",
			Pattern.DOTALL);
	assertFalse(pattern.matcher(capture.toString()).find());
    }

    @Test
    public void notTraceTest() {
	someService2.withTrace();
	assertThat(capture.toString(),
		not(containsString("TRACE com.sk.sinthoma.core.logger.LoggableErrorLevelTest$SomeService2 - "
			+ "#withTrace([]): NULL in")));
    }

    @Test
    public void debugTest() {
	someService2.withDebug();
	assertThat(capture.toString(),
		not(containsString("DEBUG com.sk.sinthoma.core.logger.LoggableErrorLevelTest$SomeService2 - "
			+ "#withDebug([]): NULL in")));
    }

    @Test
    public void infoTest() {
	someService2.withInfo();
	assertThat(capture.toString(),
		not(containsString("INFO com.sk.sinthoma.core.logger.LoggableErrorLevelTest$SomeService2 - "
			+ "#withDebug([]): NULL in")));
    }

    @Test
    public void errorTest() {
	someService2.withError();
	assertThat(capture.toString(),
		containsString("ERROR com.sk.sinthoma.core.logger.LoggableErrorLevelTest$SomeService2 - "
			+ "#withError([]): NULL in"));
    }

    @Test
    public void offTest() {
	someService2.withOff();
	assertThat(capture.toString(),
		not(containsString("com.sk.sinthoma.core.logger.LoggableErrorLevelTest$SomeService2 - "
			+ "#withOff([]): NULL in")));
    }

    @Test
    public void fatalTest() {
	someService2.withFatal();
	assertThat(capture.toString(),
		containsString("ERROR com.sk.sinthoma.core.logger.LoggableErrorLevelTest$SomeService2 - "
			+ "#withFatal([]): NULL in"));
    }

    public static class SomeService2 {

	@Loggable(LogLevel.FATAL)
	public void withFatal() {

	}

	@Loggable(LogLevel.DEBUG)
	public void withDebug() {

	}

	@Loggable(LogLevel.INFO)
	public void withInfo() {

	}

	@Loggable(LogLevel.TRACE)
	public void withTrace() {

	}

	@Loggable(LogLevel.ERROR)
	public void withError() {

	}

	@Loggable(LogLevel.OFF)
	public void withOff() {

	}

	@Loggable(warnOver = 2, warnUnit = TimeUnit.SECONDS)
	public void withWarn2Sec() {
	    try {
		Thread.sleep(3000);
	    } catch (final InterruptedException ignore) {
	    }
	}
    }

    @Configuration
    @EnableAspectJAutoProxy
    @EnableLogger
    public static class Application {
	@Bean
	public SomeService2 someService2() {
	    return new SomeService2();
	}

    }

}