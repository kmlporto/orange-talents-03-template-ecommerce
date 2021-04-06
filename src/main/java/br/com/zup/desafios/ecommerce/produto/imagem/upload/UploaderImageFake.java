package br.com.zup.desafios.ecommerce.produto.imagem.upload;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UploaderImageFake implements UploaderImage {
    private final static String PATH_UPLOADER = "http://bucket.io/";

    public Set<String> envia(List<MultipartFile> files){
        return files.stream().map(f -> PATH_UPLOADER + f.getOriginalFilename()).collect(Collectors.toSet());
    }

}
