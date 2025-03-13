package edu.vlasenko.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Aspect for logging before
 */
@Aspect
public class LogBeforeAspect extends AbstractAspect {

    public LogBeforeAspect(String logLevel) {
        super(logLevel);
    }

    @Before("@annotation(edu.vlasenko.aspects.annotations.LogBefore)")
    public void logBeforeAdvice(JoinPoint joinPoint) {

        logger.atLevel(level).log("Called before method: {}", joinPoint.getSignature());
    }
}