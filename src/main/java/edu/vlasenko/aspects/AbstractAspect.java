package edu.vlasenko.aspects;

import org.slf4j.Logger;
import org.slf4j.event.Level;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractAspect {
    protected final Logger logger = getLogger(this.getClass());
    protected final Level level;

    public AbstractAspect(String logLevel) {
        this.level = Level.valueOf(logLevel);
    }
}