/**
 * LoggerTest.java
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class LoggerTest {

    @Rule
    public OutputCaptureRule capture = new OutputCaptureRule();

    @Autowired
    private Logger logger;

    @Test
    public void logTrace() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.TRACE);
	logger.log(LogLevel.TRACE, "logger name", "trace message");
	assertThat(capture.toString(), containsString("TRACE logger name - trace message"));
    }

    @Test
    public void logTraceError() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.TRACE);
	logger.log(LogLevel.TRACE, "logger name", "trace message", new Exception("error message"));
	final Pattern pattern = Pattern.compile("TRACE logger name - trace message.*java.lang.Exception: error message",
		Pattern.DOTALL | Pattern.MULTILINE);
	assertTrue(pattern.matcher(capture.toString()).find());
    }

    @Test
    public void logDebug() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.DEBUG);
	logger.log(LogLevel.DEBUG, "logger name", "debug message");
	assertThat(capture.toString(), containsString("DEBUG logger name - debug message"));
    }

    @Test
    public void logDebugError() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.DEBUG);
	logger.log(LogLevel.DEBUG, "logger name", "debug message", new Exception("error message"));
	final Pattern pattern = Pattern.compile("DEBUG logger name - debug message.*java.lang.Exception: error message",
		Pattern.DOTALL | Pattern.MULTILINE);
	assertTrue(pattern.matcher(capture.toString()).find());
    }

    @Test
    public void logInfo() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.INFO);
	logger.log(LogLevel.INFO, "logger name", "info message");
	assertThat(capture.toString(), containsString("INFO logger name - info message"));
    }

    @Test
    public void logInfoError() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.INFO);
	logger.log(LogLevel.INFO, "logger name", "info message", new Exception("error message"));
	final Pattern pattern = Pattern.compile("INFO logger name - info message.*java.lang.Exception: error message",
		Pattern.DOTALL | Pattern.MULTILINE);
	assertTrue(pattern.matcher(capture.toString()).find());
    }

    @Test
    public void logWarning() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.WARN);
	logger.log(LogLevel.WARN, "logger name", "warn message");
	assertThat(capture.toString(), containsString("WARN logger name - warn message"));
    }

    @Test
    public void logWarningError() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.WARN);
	logger.log(LogLevel.WARN, "logger name", "warn message", new Exception("error message"));
	final Pattern pattern = Pattern.compile("WARN logger name - warn message.*java.lang.Exception: error message",
		Pattern.DOTALL | Pattern.MULTILINE);
	assertTrue(pattern.matcher(capture.toString()).find());
    }

    @Test
    public void logError() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.ERROR);
	logger.log(LogLevel.ERROR, "logger name", "error message");
	assertThat(capture.toString(), containsString("ERROR logger name - error message"));
    }

    @Test
    public void logErrorError() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.ERROR);
	logger.log(LogLevel.ERROR, "logger name", "error message", new Exception("error message"));
	final Pattern pattern = Pattern.compile("ERROR logger name - error message.*java.lang.Exception: error message",
		Pattern.DOTALL | Pattern.MULTILINE);
	assertTrue(pattern.matcher(capture.toString()).find());
    }

    @Test
    public void logFatal() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.FATAL);
	logger.log(LogLevel.FATAL, "logger name", "fatal message");
	assertThat(capture.toString(), containsString("ERROR logger name - fatal message"));
    }

    @Test
    public void logFatalError() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.FATAL);
	logger.log(LogLevel.FATAL, "logger name", "fatal message", new Exception("error message"));
	final Pattern pattern = Pattern.compile("ERROR logger name - fatal message.*java.lang.Exception: error message",
		Pattern.DOTALL | Pattern.MULTILINE);
	assertTrue(pattern.matcher(capture.toString()).find());
    }

    @Test
    public void logTraceErrorOff() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.OFF);
	logger.log(LogLevel.TRACE, "logger name", "trace message", new Exception("error message"));
	final Pattern pattern = Pattern.compile("TRACE logger name - trace message.*java.lang.Exception: error message",
		Pattern.DOTALL | Pattern.MULTILINE);
	assertFalse(pattern.matcher(capture.toString()).find());
    }

    @Test
    public void isLogTraceEnabled() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.TRACE);
	assertTrue(logger.isEnabled(LogLevel.TRACE, "logger name"));
    }

    @Test
    public void isLogDebugEnabled() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.DEBUG);
	assertTrue(logger.isEnabled(LogLevel.DEBUG, "logger name"));
    }

    @Test
    public void isLogInfoEnabled() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.INFO);
	assertTrue(logger.isEnabled(LogLevel.INFO, "logger name"));
    }

    @Test
    public void isLogErrorEnabled() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.ERROR);
	assertTrue(logger.isEnabled(LogLevel.ERROR, "logger name"));
    }

    @Test
    public void isLogFatalEnabled() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.FATAL);
	assertTrue(logger.isEnabled(LogLevel.FATAL, "logger name"));
    }

    @Test
    public void isLogTraceDisabled() {
	LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(org.slf4j.Logger.ROOT_LOGGER_NAME,
		LogLevel.OFF);
	assertFalse(logger.isEnabled(LogLevel.TRACE, "logger name"));
    }

    @Configuration
    @Import(Logger.class)
    public static class Application {

    }
}