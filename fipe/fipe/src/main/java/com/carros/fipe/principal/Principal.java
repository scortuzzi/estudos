package com.carros.fipe.principal;

import com.carros.fipe.model.Carro;
import com.carros.fipe.model.Marcas;
import com.carros.fipe.service.ConsumoAPI;
import com.carros.fipe.service.ConverteDados;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {

    private final String url = "https://parallelum.com.br/fipe/api/v1/";
    Scanner scanner = new Scanner(System.in);
    ConsumoAPI consumo = new ConsumoAPI();
    ConverteDados conversor = new ConverteDados();

    String option = null;
    public void exibirMenu() {

        do {


            System.out.println("""
                    ---Menu---
                    o que deseja consultar ?
                    .Carros
                    .Motos
                    .Caminhões""");

            option = scanner.nextLine();
            String uri = null;

            if (option.toLowerCase().contains("carr")) {
                uri = url + "carros/marcas";
            } else if (option.toLowerCase().contains("mot")) {
                uri = url + "motos/marcas";
            } else if (option.toLowerCase().contains("camin")) {
                uri = url + "caminhoes/marcas";
            } else {
                System.out.println("opção inválida");
                break;
            }

            var json = consumo.obterDados(uri);
            var marcas = conversor.obterLista(json, Carro.class);
            marcas.stream().sorted(Comparator.comparing(Carro::codigo)).forEach(System.out::println);

            System.out.println("Digite o código da marca qual deseja consultar:");
            var marca = scanner.nextLine();

            uri = uri + "/" + marca + "/modelos";

            json = consumo.obterDados(uri);
            var resposta = conversor.converterDados(json, Marcas.class);

            resposta.modelos().stream().sorted(Comparator.comparing(Carro::codigo)).forEach(System.out::println);

        }while(!option.equals("sair"));

    }
}
