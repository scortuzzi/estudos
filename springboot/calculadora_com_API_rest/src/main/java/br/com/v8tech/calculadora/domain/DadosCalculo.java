package br.com.v8tech.calculadora.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCalculo(

        @NotNull
        Double numero01,
        @NotNull
        Character operador,
        @NotNull
        Double numero02) {
}
