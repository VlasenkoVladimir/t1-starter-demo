package edu.vlasenko.aspects.annotations;

import edu.vlasenko.aspects.ExecutionTimeAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for {@link ExecutionTimeAspect}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExecutionTime {
}