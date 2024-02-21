package br.com.v8tech.calculadora.controller;

import br.com.v8tech.calculadora.domain.Calculo;
import br.com.v8tech.calculadora.domain.DadosCalculo;
import br.com.v8tech.calculadora.domain.DadosResultado;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculo")
public class CalculadoraController {


    @PostMapping
    public ResponseEntity realizarCalculo(@RequestBody @Valid DadosCalculo dto) throws Exception {

        try {
            Calculo calculo = new Calculo(dto);
            DadosResultado resultado = new DadosResultado(calculo.getUuid(), calculo.getNumero01(), calculo.getOperador(), calculo.getNumero02(), calculo.getResultado());
            return ResponseEntity.ok().body(resultado);

        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }



    }

}
