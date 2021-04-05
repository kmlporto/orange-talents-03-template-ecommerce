package br.com.zup.desafios.ecommerce.util.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ListUniqueValidator implements ConstraintValidator<ListUnique, List<Long>> {
    @Override
    public void initialize(ListUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<Long> objectList, ConstraintValidatorContext context) {
        Set t = new TreeSet(objectList);

        return t.size()==objectList.size();
    }
}
