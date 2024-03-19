package com.carros.fipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados{


    private ObjectMapper mapper = new ObjectMapper();

    //um objectMapper é um objeto que recebe um json e uma classe java
    //ela serve para transformar o json em um objeto, por exemplo removendo
    //itens do json tal quais não vamos usar
    //é importante que os nomes dos atributos sejam os mesmos dos itens
    //dentro do json, embora a anotação @jsonAlias nos prove uma forma
    //de apelidar aquele item no código.
    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
