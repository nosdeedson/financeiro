package br.com.edson.Util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaUtil {
	
	private static EntityManagerFactory emf;
	
	static {
		emf =	Persistence.createEntityManagerFactory("financeiroPU");
	}

	public static EntityManager obterEntity() {
		
		return emf.createEntityManager();
	}
	

}
