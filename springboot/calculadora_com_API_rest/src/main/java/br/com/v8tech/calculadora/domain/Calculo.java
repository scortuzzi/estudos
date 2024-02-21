package br.com.v8tech.calculadora.domain;

import lombok.Getter;

import java.util.UUID;

public class Calculo {

    @Getter
    private Double numero01;
    @Getter
    private Double numero02;
    @Getter
    private Character operador;
    @Getter
    private Double resultado;
    @Getter
    private String uuid;

    public Calculo(DadosCalculo dados) throws Exception {

        UUID uuidRandomico = UUID.randomUUID();
        this.uuid = uuidRandomico.toString();

        this.numero01 = dados.numero01();
        this.numero02 = dados.numero02();
        this.operador = dados.operador();

        switch (this.operador.charValue()) {

            case '+':
                this.resultado = this.numero01 + this.numero02;
                break;

            case '-':
                this.resultado = this.numero01 - this.numero02;
                break;

            case '*':
                this.resultado = this.numero01 * this.numero02;
                break;

            case '/':
                this.resultado = this.numero01 / this.numero02;
                break;

            case '^':
                this.resultado = this.numero01;
                for (int i = 1; i < this.numero02; i++) {
                    this.resultado = this.resultado * numero01;
                }
                break;

            default:
                throw new RuntimeException("operador inválido!");
        }

    }
}
