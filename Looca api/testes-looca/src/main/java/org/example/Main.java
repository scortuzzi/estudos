package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.sistema.Sistema;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Looca looca = new Looca();
        Memoria memoria = looca.getMemoria();
        System.out.println(memoria);

    }
}