package br.com.edson.Repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.edson.financeiro.Model.Pessoa;

public class Pessoas implements Serializable {
	
	/*
	 * classe que faz operações no bancoS
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em;

	public Pessoas(EntityManager em) {
		this.em = em;
	}
	/*
	 * acha uma pessoa especifia pelo id
	 */
	public Pessoa porId(Long id) {
		return this.em.find(Pessoa.class, id);
	}
	/*
	 * retorna todas as pessoas
	 */
	public List<Pessoa> todas(){
		
		TypedQuery<Pessoa> query = em.createQuery("from Pessoa", Pessoa.class);
		return query.getResultList();
	}

}
