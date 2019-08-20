/**
 * Logger.java
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

import java.util.Objects;

import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

@Component
public final class Logger {

    public void log(LogLevel level, Class<?> clazz, String message, Object... args) {
	log(LoggerFactory.getLogger(clazz), level, message, args);
    }

    public void log(LogLevel level, String name, String message, Object... args) {
	log(LoggerFactory.getLogger(name), level, message, args);
    }

    public boolean isEnabled(LogLevel level, Class<?> clazz) {
	return isEnabled(LoggerFactory.getLogger(clazz), level);
    }

    public boolean isEnabled(LogLevel level, String name) {
	return isEnabled(LoggerFactory.getLogger(name), level);
    }

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