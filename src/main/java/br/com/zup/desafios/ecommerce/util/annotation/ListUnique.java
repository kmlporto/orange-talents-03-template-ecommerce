package br.com.zup.desafios.ecommerce.util.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ListUniqueValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ListUnique {
    String message() default "Valores não devem ser repetidos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
