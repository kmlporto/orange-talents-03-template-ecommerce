package br.com.zup.desafios.ecommerce.produto.imagem.upload;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Component
@Profile(value = "test")
public class UploaderImageProd implements UploaderImage{
    @Override
    public Set<String> envia(List<MultipartFile> files) {
        return null;
    }
}
