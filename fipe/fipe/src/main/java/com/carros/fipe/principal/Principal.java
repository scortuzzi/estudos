package com.carros.fipe.principal;

import com.carros.fipe.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {

    private final String url = "https://parallelum.com.br/fipe/api/v1/";
    Scanner scanner = new Scanner(System.in);
    ConsumoAPI consumo = new ConsumoAPI();

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
            Boolean acesso = true;

            if (option.toLowerCase().contains("carr")) {
                uri = url + "carros/marcas";
            } else if (option.toLowerCase().contains("mot")) {
                uri = url + "motos/marcas";
            } else if (option.toLowerCase().contains("camin")) {
                uri = url + "caminhoes/marcas";
            } else {
                System.out.println("opção inválida");
                acesso = false;
            }


            if (acesso) {
                var json = consumo.obterDados(uri);
                System.out.println(json);
            }

        }while(!option.equals("sair"));

    }
}
