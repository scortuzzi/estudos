package com.carros.fipe.service;

import java.util.List;

public interface IConverteDados {

    <T> T converterDados (String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);
}
