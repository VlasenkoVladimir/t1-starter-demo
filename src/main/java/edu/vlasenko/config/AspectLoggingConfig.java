package edu.vlasenko.config;

import org.slf4j.event.Level;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;

@Configuration
@ConditionalOnProperty(
        prefix = "aspect-logging",
        name = {"log-level"})
@ConditionalOnBean(type = "org.springframework.stereotype.Controller")
@AutoConfigureAfter(Controller.class)
@EnableAspectJAutoProxy
public class AspectLoggingConfig {

    private final Level logLevel;

    public AspectLoggingConfig(Level logLevel) {
        this.logLevel = logLevel;
    }

    public Level getLogLevel() {
        return logLevel;
    }
}