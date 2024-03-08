package br.com.alura.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	//para criar uma EntityManager nós precisamos instancia-las
	//através das EntityManagerFactory's, que também são somente
	//instanciadas através de uma classe da JPA chamada "Persistence"
	private static final EntityManagerFactory FACTORY = Persistence
			.createEntityManagerFactory("loja");

	public static EntityManager getEntityManager() { 
		return FACTORY.createEntityManager();
	}
	
}
