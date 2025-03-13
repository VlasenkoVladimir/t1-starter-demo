package edu.vlasenko.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Aspect for calculating execution time
 */
@Aspect
public class ExecutionTimeAspect extends AbstractAspect {

    public ExecutionTimeAspect(String logLevel) {
        super(logLevel);
    }

    @Around("@annotation(edu.vlasenko.aspects.annotations.ExecutionTime)")
    public Object calculateExecutionTimeInMills(ProceedingJoinPoint proceedingJoinPoint) {

        Object proceeded = null;
        long start = System.currentTimeMillis();

        try {
            proceeded = proceedingJoinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;
            logger.atLevel(level)
                    .log("Method {} execution time is: {} millis", proceedingJoinPoint.getSignature().getName(), executionTime);
        } catch (Throwable throwable) {
            logger.atLevel(level)
                    .log("Can't calculate execution time because exception gained: {}", throwable.getMessage());
        }

        return proceeded;
    }
}