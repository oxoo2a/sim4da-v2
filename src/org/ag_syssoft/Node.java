package org.ag_syssoft;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // Can be used on classes
public @interface Node {
    String name() default "";
    int n_instances() default 1;
}