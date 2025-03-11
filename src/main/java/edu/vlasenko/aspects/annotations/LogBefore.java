package edu.vlasenko.aspects.annotations;

import edu.vlasenko.aspects.LogBeforeAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for {@link LogBeforeAspect}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogBefore {
}