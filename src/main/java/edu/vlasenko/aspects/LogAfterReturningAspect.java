package edu.vlasenko.aspects;

import edu.vlasenko.config.AspectLoggingConfig;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging after returning
 */
@Component
@Aspect
@ConditionalOnExpression("${aspect-logging.log-after-returning.enable}")
public class LogAfterReturningAspect {

    private final Level level;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public LogAfterReturningAspect(AspectLoggingConfig config) {
        this.level = config.getLogLevel();
    }

    @AfterReturning(
            pointcut = "@annotation(edu.vlasenko.aspects.annotations.LogAfterReturning)",
            returning = "result")
    public void logAfterReturningAdvice(Object result) {

        logger.atLevel(level).log("The result is: {}", result.toString());
    }
}