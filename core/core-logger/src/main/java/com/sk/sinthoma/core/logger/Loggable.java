/**
 * Loggable.java - core-logger
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.logger;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.logging.LogLevel;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface Loggable {

    /**
     * Value.
     *
     * @return the log level
     */
    LogLevel value() default LogLevel.INFO;

    /**
     * Name.
     *
     * @return the string
     */
    String name() default "";

    /**
     * Entered.
     *
     * @return true, if successful
     */
    boolean entered() default true;

    /**
     * Skip result.
     *
     * @return true, if successful
     */
    boolean skipResult() default false;

    /**
     * Skip args.
     *
     * @return true, if successful
     */
    boolean skipArgs() default false;

    /**
     * Ignore.
     *
     * @return the class<? extends throwable>[]
     */
    Class<? extends Throwable>[] ignore() default {};

    /**
     * Warn over.
     *
     * @return the long
     */
    long warnOver() default -1;

    /**
     * Warn unit.
     *
     * @return the time unit
     */
    TimeUnit warnUnit() default TimeUnit.MINUTES;
}