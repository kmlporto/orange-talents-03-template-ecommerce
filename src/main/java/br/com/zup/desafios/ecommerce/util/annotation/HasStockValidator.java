package br.com.zup.desafios.ecommerce.util.annotation;

import br.com.zup.desafios.ecommerce.compra.item.ItemPersist;
import br.com.zup.desafios.ecommerce.produto.Produto;
import br.com.zup.desafios.ecommerce.produto.ProdutoRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HasStockValidator implements ConstraintValidator<HasStock, ItemPersist> {

    private final ProdutoRepository produtoRepository;

    public HasStockValidator(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void initialize(HasStock constraintAnnotation) {
    }

    @Override
    public boolean isValid(ItemPersist itemPersist, ConstraintValidatorContext constraintValidatorContext) {
        Produto produto = produtoRepository.getOne(itemPersist.getIdProduto());
        if(produto.getQuantidadeDisponivel() < itemPersist.getQuantidade())
            return false;

        return true;
    }
}
