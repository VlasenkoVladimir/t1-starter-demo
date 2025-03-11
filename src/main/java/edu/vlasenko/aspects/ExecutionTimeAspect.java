package edu.vlasenko.aspects;

import edu.vlasenko.config.AspectLoggingConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * Aspect for calculating execution time
 */
@Component
@Aspect
@ConditionalOnExpression("${aspect-logging.execution-time.enable}")
public class ExecutionTimeAspect {

    private final Level level;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ExecutionTimeAspect(AspectLoggingConfig config) {
        this.level = config.getLogLevel();
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