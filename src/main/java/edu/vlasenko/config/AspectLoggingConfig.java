package edu.vlasenko.config;

import edu.vlasenko.aspects.CatchExceptionAspect;
import edu.vlasenko.aspects.ExecutionTimeAspect;
import edu.vlasenko.aspects.LogAfterReturningAspect;
import edu.vlasenko.aspects.LogBeforeAspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectLoggingConfig {

    @Bean
    @ConditionalOnProperty(name = "aspect-logging.logBefore.enabled", havingValue = "true")
    public LogBeforeAspect logBeforeAspect(@Value("${aspect-logging.logBefore.log-level}") String logLevel) {
        return new LogBeforeAspect(logLevel);
    }

    @Bean
    @ConditionalOnProperty(name = "aspect-logging.logAfterReturning.enabled", havingValue = "true")
    public LogAfterReturningAspect logAfterReturningAspect(@Value("${aspect-logging.logAfterReturning.log-level}") String logLevel) {
        return new LogAfterReturningAspect(logLevel);
    }

    @Bean
    @ConditionalOnProperty(value = "aspect-logging.executionTime.enabled", havingValue = "true")
    public ExecutionTimeAspect executionTimeAspect(@Value("${aspect-logging.executionTime.log-level}") String logLevel) {
        return new ExecutionTimeAspect(logLevel);
    }

    @Bean
    @ConditionalOnProperty(value = "aspect-logging.catchException.enabled", havingValue = "true")
    public CatchExceptionAspect catchExceptionAspect(@Value("${aspect-logging.catchException.log-level}") String logLevel) {
        return new CatchExceptionAspect(logLevel);
    }
}