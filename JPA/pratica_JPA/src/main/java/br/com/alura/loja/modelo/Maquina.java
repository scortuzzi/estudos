package br.com.alura.loja.modelo;

import javax.persistence.*;

//estes objetos são classes JPA
//elas são o reflexo de uma entidade no banco de dados
//no geral, ela representa uma tabela no banco,
//neste caso, a tabela maquinas"

@Entity
@Table(name = "maquinas")
public class Maquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String SO;
    private String fabricante;

    public Maquina(String SO, String fabricante) {
        this.SO = SO;
        this.fabricante = fabricante;
    }

    public String getSO() {
        return SO;
    }

    public void setSO(String SO) {
        this.SO = SO;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
}
