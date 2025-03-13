package edu.vlasenko.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 * Aspect for catching thrown Exceptions
 */
@Aspect
public class CatchExceptionAspect extends AbstractAspect {

    public CatchExceptionAspect(String logLevel) {
        super(logLevel);
    }

    @AfterThrowing(
            pointcut = "@annotation(edu.vlasenko.aspects.annotations.CatchException)",
            throwing = "exception")
    public void logAfterReturningAdvice(JoinPoint joinPoint, Exception exception) {

        logger.atLevel(level)
                .log("Exception in: {}, Exception message is: {}", joinPoint.getSignature(), exception.getMessage());
    }
}