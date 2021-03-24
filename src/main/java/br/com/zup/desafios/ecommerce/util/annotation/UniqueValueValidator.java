package br.com.zup.desafios.ecommerce.util.annotation;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private Class<?> clazz;
    private String field;
    private final EntityManager entityManager;

    public UniqueValueValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void initialize(UniqueValue uniqueValue) {
        clazz = uniqueValue.clazz();
        field = uniqueValue.field();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("select 1 from " + clazz.getName() + " where " + field + "=:value");
        query.setParameter("value", o);
        List<?> list = query.getResultList();

        return list.isEmpty();
    }
}
