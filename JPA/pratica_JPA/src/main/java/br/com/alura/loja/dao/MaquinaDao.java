package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Maquina;

import javax.persistence.EntityManager;

public class MaquinaDao {

    //inicializa o EntityManager vazio
    private EntityManager em;

    //transoforma ele no EntityManager resgatado pelo JPAUtil
    public MaquinaDao(EntityManager em) {
        this.em = em;
    }

    //cadastra ele através do método "persist" da EntityManager
    //o método persist trás com ele a entidade JPA, que por si só
    //já mapea os atributos com suas respectivas colunas por possuirem o mesmo nome
    public void cadastrar(Maquina maquina) {
        this.em.persist(maquina);
    }


}
