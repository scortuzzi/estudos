package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

public class TesteLooca {
    public static void main(String[] args) {

        Looca looca = new Looca();
        Memoria ram = looca.getMemoria();
        Long usoRam = ram.getEmUso();
        Long ramDis = ram.getTotal();
        System.out.println(ramDis);
        System.out.println(usoRam);

        Rede rede = looca.getRede();
        List<RedeInterface> ip = rede.getGrupoDeInterfaces().getInterfaces();

        System.out.println();
        System.out.println(ip);




    }
}
