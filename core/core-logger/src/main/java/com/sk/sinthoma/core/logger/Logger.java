/**
 * Logger.java - core-logger
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.logger;

import java.util.Objects;

import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

@Component
public final class Logger {

    /**
     * Log.
     *
     * @param level   the level
     * @param clazz   the clazz
     * @param message the message
     * @param args    the args
     */
    public void log(LogLevel level, Class<?> clazz, String message, Object... args) {
	log(LoggerFactory.getLogger(clazz), level, message, args);
    }

    /**
     * Log.
     *
     * @param level   the level
     * @param name    the name
     * @param message the message
     * @param args    the args
     */
    public void log(LogLevel level, String name, String message, Object... args) {
	log(LoggerFactory.getLogger(name), level, message, args);
    }

    /**
     * Checks if is enabled.
     *
     * @param level the level
     * @param clazz the clazz
     * @return true, if is enabled
     */
    public boolean isEnabled(LogLevel level, Class<?> clazz) {
	return isEnabled(LoggerFactory.getLogger(clazz), level);
    }

    /**
     * Checks if is enabled.
     *
     * @param level the level
     * @param name  the name
     * @return true, if is enabled
     */
    public boolean isEnabled(LogLevel level, String name) {
	return isEnabled(LoggerFactory.getLogger(name), level);
    }

    /**
     * Log.
     *
     * @param logger  the logger
     * @param level   the level
     * @param message the message
     * @param args    the args
     */
    private void log(org.slf4j.Logger logger, LogLevel level, String message, Object... args) {
	Objects.requireNonNull(level, "LogLevel must not be null.");
	switch (level) {
	case TRACE:
	    logger.trace(message, args);
	    break;
	case DEBUG:
	    logger.debug(message, args);
	    break;
	case INFO:
	    logger.info(message, args);
	    break;
	case WARN:
	    logger.warn(message, args);
	    break;
	case ERROR:
	case FATAL:
	    logger.error(message, args);
	    break;
	default:
	    break;
	}
    }

    /**
     * Checks if is enabled.
     *
     * @param logger the logger
     * @param level  the level
     * @return true, if is enabled
     */
    private boolean isEnabled(org.slf4j.Logger logger, LogLevel level) {
	Objects.requireNonNull(level, "LogLevel must not be null.");
	switch (level) {
	case TRACE:
	    return logger.isTraceEnabled();
	case DEBUG:
	    return logger.isDebugEnabled();
	case INFO:
	    return logger.isInfoEnabled();
	case WARN:
	    return logger.isWarnEnabled();
	case ERROR:
	case FATAL:
	    return logger.isErrorEnabled();
	default:
	    throw new IllegalArgumentException("LogLevel must be one of the enabled levels.");
	}
    }

}