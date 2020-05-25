package br.com.edson.Repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.edson.financeiro.Model.Lancamento;

public class Lancamentos implements Serializable{
/*
 * classe que faz operações do banco
 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em;

	public Lancamentos(EntityManager em) {
		this.em = em;
	}
	
	/*
	 * adiciona lacamento
	 */
	public void adicionar(Lancamento l) {
		this.em.persist(l);
	}
	/*
	 * retorna todos os lançamentos
	 */
	public List<Lancamento>  todos(){
		TypedQuery<Lancamento> query = em.createQuery("from Lancamento", Lancamento.class);
		return query.getResultList();
	}
	
	
}
