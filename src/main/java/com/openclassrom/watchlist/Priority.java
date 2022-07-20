
package com.openclassrom.watchlist;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target ( { ElementType.METHOD, ElementType.FIELD })
@Retention (RetentionPolicy.RUNTIME)
@Constraint (validatedBy= PriorityValidator.class)
public @interface Priority {

    String message() default "Please enter M,L or H for priority";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}