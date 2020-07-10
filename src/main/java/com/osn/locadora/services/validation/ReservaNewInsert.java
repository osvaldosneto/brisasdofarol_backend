package com.osn.locadora.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ReservaNewInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface ReservaNewInsert {
	String message() default "Erro de validação da reserva";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
