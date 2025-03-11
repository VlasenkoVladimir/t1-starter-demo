package edu.vlasenko.aspects;

import edu.vlasenko.config.AspectLoggingConfig;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * Aspect for catching thrown Exceptions
 */
@Component
@Aspect
@ConditionalOnExpression("${aspect-logging.catch-exception.enable}")
public class CatchExceptionAspect {

    private final Level level;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CatchExceptionAspect(AspectLoggingConfig config) {
        this.level = config.getLogLevel();
    }

    @AfterThrowing(
            pointcut = "@annotation(edu.vlasenko.aspects.annotations.CatchException)",
            throwing = "exception")
    public void logAfterReturningAdvice(JoinPoint joinPoint, Exception exception) {

        logger.atLevel(level)
                .log("Exception in: {}, Exception message is: {}", joinPoint.getSignature(), exception.getMessage());
    }
}