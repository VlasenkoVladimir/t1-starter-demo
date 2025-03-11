package edu.vlasenko.aspects;

import edu.vlasenko.config.AspectLoggingConfig;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging before
 */
@Component
@Aspect
@ConditionalOnExpression("${aspect-logging.log-before.enable}")
public class LogBeforeAspect {

    private final Level level;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public LogBeforeAspect(AspectLoggingConfig config) {
        this.level = config.getLogLevel();
    }

    @Before("@annotation(edu.vlasenko.aspects.annotations.LogBefore)")
    public void logBeforeAdvice(JoinPoint joinPoint) {

        logger.atLevel(level).log("Called before method: {}", joinPoint.getSignature());
    }
}