package com.Bai1.identity_service.validator;

@java.lang.annotation.Target({java.lang.annotation.ElementType.FIELD})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@jakarta.validation.Constraint(validatedBy = {DobValidator.class})
public @interface DobConstraint {
    java.lang.String message() default "Invalid date of birth";

    int min();

    java.lang.Class<?>[] groups() default {};

    java.lang.Class<? extends jakarta.validation.Payload>[] payload() default {};
}
