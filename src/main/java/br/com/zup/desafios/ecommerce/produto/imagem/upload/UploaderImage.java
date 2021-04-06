package br.com.zup.desafios.ecommerce.produto.imagem.upload;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface UploaderImage {
    Set<String> envia(List<MultipartFile> files);

}
