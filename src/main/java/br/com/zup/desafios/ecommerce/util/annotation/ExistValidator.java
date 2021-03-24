package br.com.zup.desafios.ecommerce.util.annotation;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public class ExistValidator implements ConstraintValidator<Exist, Object> {
    private final EntityManager entityManager;
    private Class<?> clazz;
    private String field;

    public ExistValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(Exist constraintAnnotation) {
        this.clazz = constraintAnnotation.clazz();
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(Objects.isNull(value))
            return true;
        Query query = entityManager.createQuery("select 1 from " + clazz.getName() + " where " + field + "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();

        return !list.isEmpty();
    }
}
