package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;

//este tipo de classe serve para executar os processos dentro do banco de dados
//sem a necessidade de poluir o código e criar redundâncias de dificil manutenção

//o passo a passo que acontece aqui é:

/*
* 	1. Criar uma EntityManager não iniciada
*
* 	2. Iniciar a EntityManager através do construtor
*  	   isso significa que este método só é instanciado
* 	   junto dos parâmetros de uma EntityManager
* 	   que provavelmente será criada na classe
* 	   "JPAUtil"
*
* 	3. Persistir a categoria na tabela dentro do banco
*
*
*
*  */

public class CategoriaDao {
	
	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}

}







