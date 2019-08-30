/**
 * LoggerMsgArgsGenerator.java - core-logger
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.logger;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import lombok.NoArgsConstructor;

/**
 * Instantiates a new logger msg args generator.
 */
@NoArgsConstructor
final class LoggerMsgArgsGenerator {

    /**
     * Enter.
     *
     * @param joinPoint the join point
     * @param loggable  the loggable
     * @return the object[]
     */
    public Object[] enter(ProceedingJoinPoint joinPoint, Loggable loggable) {
	return new Object[] { methodName(joinPoint), methodArgs(joinPoint, loggable) };
    }

    /**
     * Warn before.
     *
     * @param joinPoint the join point
     * @param loggable  the loggable
     * @param nano      the nano
     * @return the object[]
     */
    public Object[] warnBefore(ProceedingJoinPoint joinPoint, Loggable loggable, long nano) {
	return new Object[] { methodName(joinPoint), methodArgs(joinPoint, loggable), durationString(nano),
		warnDuration(loggable) };
    }

    /**
     * Warn after.
     *
     * @param joinPoint the join point
     * @param loggable  the loggable
     * @param result    the result
     * @param nano      the nano
     * @return the object[]
     */
    public Object[] warnAfter(ProceedingJoinPoint joinPoint, Loggable loggable, Object result, long nano) {
	return new Object[] { methodName(joinPoint), methodArgs(joinPoint, loggable), methodResults(result, loggable),
		durationString(nano), warnDuration(loggable) };
    }

    /**
     * After.
     *
     * @param joinPoint the join point
     * @param loggable  the loggable
     * @param result    the result
     * @param nano      the nano
     * @return the object[]
     */
    public Object[] after(ProceedingJoinPoint joinPoint, Loggable loggable, Object result, long nano) {
	return new Object[] { methodName(joinPoint), methodArgs(joinPoint, loggable), methodResults(result, loggable),
		durationString(nano) };
    }

    /**
     * Error.
     *
     * @param joinPoint the join point
     * @param loggable  the loggable
     * @param nano      the nano
     * @param err       the err
     * @return the object[]
     */
    public Object[] error(ProceedingJoinPoint joinPoint, Loggable loggable, long nano, Throwable err) {
	return new Object[] { methodName(joinPoint), methodArgs(joinPoint, loggable), errClass(err), errMsg(err),
		errSourceClass(err), errLine(err), durationString(nano) };
    }

    /**
     * Error with exception.
     *
     * @param joinPoint the join point
     * @param loggable  the loggable
     * @param nano      the nano
     * @param err       the err
     * @return the object[]
     */
    public Object[] errorWithException(ProceedingJoinPoint joinPoint, Loggable loggable, long nano, Throwable err) {
	return new Object[] { methodName(joinPoint), methodArgs(joinPoint, loggable), errClass(err), errMsg(err),
		errSourceClass(err), errLine(err), durationString(nano), err };
    }

    /**
     * Warn duration.
     *
     * @param loggable the loggable
     * @return the string
     */
    private String warnDuration(Loggable loggable) {
	return Duration.ofMillis(loggable.warnUnit().toMillis(loggable.warnOver())).toString();
    }

    /**
     * Method name.
     *
     * @param joinPoint the join point
     * @return the string
     */
    private String methodName(JoinPoint joinPoint) {
	return ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
    }

    /**
     * Method args.
     *
     * @param joinPoint the join point
     * @param loggable  the loggable
     * @return the string
     */
    private String methodArgs(JoinPoint joinPoint, Loggable loggable) {
	return loggable.skipArgs() ? ".." : argsToString(joinPoint.getArgs());
    }

    /**
     * Method results.
     *
     * @param result   the result
     * @param loggable the loggable
     * @return the string
     */
    private String methodResults(Object result, Loggable loggable) {
	return loggable.skipResult() ? ".." : argsToString(result);
    }

    /**
     * Err class.
     *
     * @param err the err
     * @return the string
     */
    private String errClass(Throwable err) {
	return err.getClass().getName();
    }

    /**
     * Err msg.
     *
     * @param err the err
     * @return the string
     */
    private String errMsg(Throwable err) {
	return err.getMessage();
    }

    /**
     * Err line.
     *
     * @param err the err
     * @return the int
     */
    private int errLine(Throwable err) {
	if (err.getStackTrace().length > 0) {
	    return err.getStackTrace()[0].getLineNumber();
	}
	return -1;
    }

    /**
     * Err source class.
     *
     * @param err the err
     * @return the string
     */
    private String errSourceClass(Throwable err) {
	if (err.getStackTrace().length > 0) {
	    return err.getStackTrace()[0].getClassName();
	}
	return "somewhere";
    }

    /**
     * Duration string.
     *
     * @param nano the nano
     * @return the string
     */
    private String durationString(long nano) {
	return Duration.ofMillis(TimeUnit.NANOSECONDS.toMillis(nano)).toString();
    }

    /**
     * Args to string.
     *
     * @param arg the arg
     * @return the string
     */
    private String argsToString(Object arg) {
	String text;
	if (arg == null) {
	    return "NULL";
	} else if (arg.getClass().isArray()) {
	    if (arg instanceof Object[]) {
		text = objectArraysToString((Object[]) arg);
	    } else {
		text = primitiveArrayToString(arg);
	    }
	} else {
	    final String origin = arg.toString();
	    if ((arg instanceof String) || origin.isEmpty()) {
		text = String.format("'%s'", origin);
	    } else {
		text = origin;
	    }
	}
	return text;
    }

    /**
     * Object arrays to string.
     *
     * @param arg the arg
     * @return the string
     */
    private String objectArraysToString(Object... arg) {
	final StringBuilder bldr = new StringBuilder();
	bldr.append('[');
	for (final Object item : arg) {
	    if (bldr.length() > 1) {
		bldr.append(",").append(" ");
	    }
	    bldr.append(argsToString(item));
	}
	return bldr.append(']').toString();
    }

    /**
     * Primitive array to string.
     *
     * @param arg the arg
     * @return the string
     */
    private String primitiveArrayToString(Object arg) {
	String text;
	if (arg instanceof char[]) {
	    text = Arrays.toString((char[]) arg);
	} else if (arg instanceof byte[]) {
	    text = Arrays.toString((byte[]) arg);
	} else if (arg instanceof short[]) {
	    text = Arrays.toString((short[]) arg);
	} else if (arg instanceof int[]) {
	    text = Arrays.toString((int[]) arg);
	} else if (arg instanceof long[]) {
	    text = Arrays.toString((long[]) arg);
	} else if (arg instanceof float[]) {
	    text = Arrays.toString((float[]) arg);
	} else if (arg instanceof double[]) {
	    text = Arrays.toString((double[]) arg);
	} else if (arg instanceof boolean[]) {
	    text = Arrays.toString((boolean[]) arg);
	} else {
	    text = "[unknown]";
	}
	return text;
    }
}