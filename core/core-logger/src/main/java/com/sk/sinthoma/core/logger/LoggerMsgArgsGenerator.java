/**
 * LoggerMsgArgsGenerator.java
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

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import lombok.NoArgsConstructor;

@NoArgsConstructor
final class LoggerMsgArgsGenerator {

    public Object[] enter(ProceedingJoinPoint joinPoint, Loggable loggable) {
	return new Object[] { methodName(joinPoint), methodArgs(joinPoint, loggable) };
    }

    public Object[] warnBefore(ProceedingJoinPoint joinPoint, Loggable loggable, long nano) {
	return new Object[] { methodName(joinPoint), methodArgs(joinPoint, loggable), durationString(nano),
		warnDuration(loggable) };
    }

    public Object[] warnAfter(ProceedingJoinPoint joinPoint, Loggable loggable, Object result, long nano) {
	return new Object[] { methodName(joinPoint), methodArgs(joinPoint, loggable), methodResults(result, loggable),
		durationString(nano), warnDuration(loggable) };
    }

    public Object[] after(ProceedingJoinPoint joinPoint, Loggable loggable, Object result, long nano) {
	return new Object[] { methodName(joinPoint), methodArgs(joinPoint, loggable), methodResults(result, loggable),
		durationString(nano) };
    }

    public Object[] error(ProceedingJoinPoint joinPoint, Loggable loggable, long nano, Throwable err) {
	return new Object[] { methodName(joinPoint), methodArgs(joinPoint, loggable), errClass(err), errMsg(err),
		errSourceClass(err), errLine(err), durationString(nano) };
    }

    public Object[] errorWithException(ProceedingJoinPoint joinPoint, Loggable loggable, long nano, Throwable err) {
	return new Object[] { methodName(joinPoint), methodArgs(joinPoint, loggable), errClass(err), errMsg(err),
		errSourceClass(err), errLine(err), durationString(nano), err };
    }

    private String warnDuration(Loggable loggable) {
	return Duration.ofMillis(loggable.warnUnit().toMillis(loggable.warnOver())).toString();
    }

    private String methodName(JoinPoint joinPoint) {
	return ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
    }

    private String methodArgs(JoinPoint joinPoint, Loggable loggable) {
	return loggable.skipArgs() ? ".." : argsToString(joinPoint.getArgs());
    }

    private String methodResults(Object result, Loggable loggable) {
	return loggable.skipResult() ? ".." : argsToString(result);
    }

    private String errClass(Throwable err) {
	return err.getClass().getName();
    }

    private String errMsg(Throwable err) {
	return err.getMessage();
    }

    private int errLine(Throwable err) {
	if (err.getStackTrace().length > 0) {
	    return err.getStackTrace()[0].getLineNumber();
	}
	return -1;
    }

    private String errSourceClass(Throwable err) {
	if (err.getStackTrace().length > 0) {
	    return err.getStackTrace()[0].getClassName();
	}
	return "somewhere";
    }

    private String durationString(long nano) {
	return Duration.ofMillis(TimeUnit.NANOSECONDS.toMillis(nano)).toString();
    }

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