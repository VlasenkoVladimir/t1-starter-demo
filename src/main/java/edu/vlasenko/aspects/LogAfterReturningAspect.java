package edu.vlasenko.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * Aspect for logging after returning
 */
@Aspect
public class LogAfterReturningAspect extends AbstractAspect {

    public LogAfterReturningAspect(String logLevel) {
        super(logLevel);
    }

    @AfterReturning(
            pointcut = "@annotation(edu.vlasenko.aspects.annotations.LogAfterReturning)",
            returning = "result")
    public void logAfterReturningAdvice(Object result) {

        logger.atLevel(level).log("The result is: {}", result.toString());
    }
}