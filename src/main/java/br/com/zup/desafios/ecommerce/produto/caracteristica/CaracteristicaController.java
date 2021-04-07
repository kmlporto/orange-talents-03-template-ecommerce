package br.com.zup.desafios.ecommerce.produto.caracteristica;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.zup.desafios.ecommerce.util.Path.CARACTERISTICAS;
import static br.com.zup.desafios.ecommerce.util.Path.PRODUTOS;
import static br.com.zup.desafios.ecommerce.util.Path.V1;

@RestController
@RequestMapping(V1 + PRODUTOS + CARACTERISTICAS)
public class CaracteristicaController {

    private final CaracteristicaRepository caracteristicaRepository;

    public CaracteristicaController(CaracteristicaRepository caracteristicaRepository) {
        this.caracteristicaRepository = caracteristicaRepository;
    }

    @PostMapping
    public ResponseEntity<CaracteristicaResponse> cadastra(@RequestBody @Valid CaracteristicaPersist caracteristicaPersist){
        Caracteristica caracteristica = caracteristicaRepository.save(caracteristicaPersist.convert());

        return ResponseEntity.ok(CaracteristicaResponse.convert(caracteristica));
    }
}
