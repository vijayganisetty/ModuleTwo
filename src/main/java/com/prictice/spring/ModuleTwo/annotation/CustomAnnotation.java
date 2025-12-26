package com.prictice.spring.ModuleTwo.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(
        validatedBy = {EmployeeValidator.class}
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomAnnotation {

    String message() default "min value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
