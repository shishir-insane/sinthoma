/**
 * LoggerAspect.java - core-logger
 * Copyright 2019 Shishir Kumar
 * Licensed under the GNU Lesser General Public License v3.0
 */
package com.sk.sinthoma.core.logger;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Aspect
@Component
public class LoggerAspect {

    private final Logger logger;

    private final LoggerMsgArgsGenerator logMsgArgsGenerator;

    private Set<WarnPoint> warnPoints;
    private ScheduledExecutorService warnService;

    /**
     * Instantiates a new logger aspect.
     *
     * @param logger the logger
     */
    @Autowired
    public LoggerAspect(Logger logger) {
	logMsgArgsGenerator = new LoggerMsgArgsGenerator();
	this.logger = logger;
    }

    /**
     * Construct.
     */
    @PostConstruct
    protected void construct() {
	warnPoints = new ConcurrentSkipListSet<>();
	warnService = Executors.newSingleThreadScheduledExecutor();
	warnService.scheduleAtFixedRate(() -> {
	    for (final WarnPoint wp : warnPoints) {
		final long duration = System.nanoTime() - wp.getStart();
		if (isOver(duration, wp.getLoggable())) {
		    log(LogLevel.WARN, "#{}({}): in {} and still running (max {})", wp.getPoint(), wp.getLoggable(),
			    logMsgArgsGenerator.warnBefore(wp.getPoint(), wp.getLoggable(), duration));
		    warnPoints.remove(wp);
		}
	    }
	}, 1L, 1L, TimeUnit.SECONDS);
    }

    /**
     * Public method.
     */
    @Pointcut("execution(public * *(..))" + " && !execution(String *.toString())" + " && !execution(int *.hashCode())"
	    + " && !execution(boolean *.canEqual(Object))" + " && !execution(boolean *.equals(Object))")
    protected void publicMethod() {
    }

    /**
     * Loggable method.
     *
     * @param loggable the loggable
     */
    @Pointcut("@annotation(loggable)")
    protected void loggableMethod(Loggable loggable) {
    }

    /**
     * Loggable class.
     *
     * @param loggable the loggable
     */
    @Pointcut("@within(loggable)")
    protected void loggableClass(Loggable loggable) {
    }

    /**
     * Log execution method.
     *
     * @param joinPoint the join point
     * @param loggable  the loggable
     * @return the object
     * @throws Throwable the throwable
     */
    @Around(value = "publicMethod() && loggableMethod(loggable)", argNames = "joinPoint,loggable")
    public Object logExecutionMethod(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
	return logMethod(joinPoint, loggable);
    }

    /**
     * Log execution class.
     *
     * @param joinPoint the join point
     * @param loggable  the loggable
     * @return the object
     * @throws Throwable the throwable
     */
    @Around(value = "publicMethod() && loggableClass(loggable) && !loggableMethod(com.sk.sinthoma.core.logger.Loggable)", argNames = "joinPoint,loggable")
    public Object logExecutionClass(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
	return logMethod(joinPoint, loggable);
    }

    /**
     * Log method.
     *
     * @param joinPoint the join point
     * @param loggable  the loggable
     * @return the object
     * @throws Throwable the throwable
     */
    public Object logMethod(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
	final long start = System.nanoTime();
	WarnPoint warnPoint = null;
	Object returnVal;

	if (isLevelEnabled(joinPoint, loggable) && (loggable.warnOver() >= 0)) {
	    warnPoint = new WarnPoint(joinPoint, loggable, start);
	    warnPoints.add(warnPoint);
	}

	if (loggable.entered()) {
	    log(loggable.value(), "#{}({}): entered", joinPoint, loggable,
		    logMsgArgsGenerator.enter(joinPoint, loggable));
	}

	try {
	    returnVal = joinPoint.proceed();

	    final long nano = System.nanoTime() - start;
	    if (isOver(nano, loggable)) {
		log(LogLevel.WARN, "#{}({}): {} in {} (max {})", joinPoint, loggable,
			logMsgArgsGenerator.warnAfter(joinPoint, loggable, returnVal, nano));
	    } else {
		log(loggable.value(), "#{}({}): {} in {}", joinPoint, loggable,
			logMsgArgsGenerator.after(joinPoint, loggable, returnVal, nano));
	    }
	    return returnVal;
	} catch (final Throwable ex) {
	    if (contains(loggable.ignore(), ex)) {
		log(LogLevel.ERROR, "#{}({}): thrown {}({}) from {}[{}] in {}", joinPoint, loggable,
			logMsgArgsGenerator.error(joinPoint, loggable, System.nanoTime() - start, ex));
	    } else {
		log(LogLevel.ERROR, "#{}({}): thrown {}({}) from {}[{}] in {}", joinPoint, loggable,
			logMsgArgsGenerator.errorWithException(joinPoint, loggable, System.nanoTime() - start, ex));
	    }
	    throw ex;
	} finally {
	    if (warnPoint != null) {
		warnPoints.remove(warnPoint);
	    }
	}
    }

    /**
     * Log.
     *
     * @param level     the level
     * @param message   the message
     * @param joinPoint the join point
     * @param loggable  the loggable
     * @param args      the args
     */
    private void log(LogLevel level, String message, ProceedingJoinPoint joinPoint, Loggable loggable, Object... args) {
	if (loggable.name().isEmpty()) {
	    logger.log(level, ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass(), message,
		    args);
	} else {
	    logger.log(level, loggable.name(), message, args);
	}
    }

    /**
     * Checks if is level enabled.
     *
     * @param joinPoint the join point
     * @param loggable  the loggable
     * @return true, if is level enabled
     */
    private boolean isLevelEnabled(ProceedingJoinPoint joinPoint, Loggable loggable) {
	return loggable.name().isEmpty()
		? logger.isEnabled(LogLevel.WARN,
			((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass())
		: logger.isEnabled(LogLevel.WARN, loggable.name());
    }

    /**
     * Checks if is over.
     *
     * @param nano     the nano
     * @param loggable the loggable
     * @return true, if is over
     */
    private boolean isOver(long nano, Loggable loggable) {
	return (loggable.warnOver() >= 0)
		&& (TimeUnit.NANOSECONDS.toMillis(nano) > loggable.warnUnit().toMillis(loggable.warnOver()));
    }

    /**
     * Contains.
     *
     * @param array the array
     * @param exp   the exp
     * @return true, if successful
     */
    private boolean contains(Class<? extends Throwable>[] array, Throwable exp) {
	boolean contains = false;
	for (final Class<? extends Throwable> type : array) {
	    if (instanceOf(exp.getClass(), type)) {
		contains = true;
		break;
	    }
	}
	return contains;
    }

    /**
     * Instance of.
     *
     * @param child  the child
     * @param parent the parent
     * @return true, if successful
     */
    private boolean instanceOf(Class<?> child, Class<?> parent) {
	boolean instance = child.equals(parent)
		|| ((child.getSuperclass() != null) && instanceOf(child.getSuperclass(), parent));
	if (!instance) {
	    for (final Class<?> iface : child.getInterfaces()) {
		instance = instanceOf(iface, parent);
		if (instance) {
		    break;
		}
	    }
	}
	return instance;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @EqualsAndHashCode(of = "point")
    
    /**
     * Instantiates a new warn point.
     *
     * @param point    the point
     * @param loggable the loggable
     * @param start    the start
     */
    @AllArgsConstructor
    
    /**
     * Gets the start.
     *
     * @return the start
     */
    @Getter
    protected static class WarnPoint implements Comparable<WarnPoint> {

	private final ProceedingJoinPoint point;
	private final Loggable loggable;
	private final long start;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(WarnPoint obj) {
	    return Long.compare(obj.getStart(), start);
	}
    }
}