package br.com.zup.desafios.ecommerce.util.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = HasStockValidator.class)
@Target({ ElementType.TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface HasStock {
    String message() default "Produto não possui estoque";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
