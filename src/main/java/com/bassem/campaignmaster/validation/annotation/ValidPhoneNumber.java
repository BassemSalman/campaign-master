package com.bassem.campaignmaster.validation.annotation;

import com.bassem.campaignmaster.validation.PhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Constraint(validatedBy = PhoneNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumber {
    String message() default "Invalid phoneNumber format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}