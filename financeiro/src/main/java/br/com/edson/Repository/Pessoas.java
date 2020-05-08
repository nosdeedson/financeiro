package br.com.edson.Repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.edson.financeiro.Model.Pessoa;

public class Pessoas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;

	public Pessoas(EntityManager em) {
		this.em = em;
	}
	
	public List<Pessoa> todas(){
		
		TypedQuery<Pessoa> query = em.createQuery("from Pessoa", Pessoa.class);
		return query.getResultList();
	}

}
