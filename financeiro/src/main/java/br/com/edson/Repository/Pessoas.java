package br.com.edson.Repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.edson.financeiro.Model.Pessoa;

public class Pessoas implements Serializable {
	
	/**
	 * classe que faz operações no bancoS
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em;

	public Pessoas(EntityManager em) {
		this.em = em;
	}
	
	/**
	 * adiciona uma pessoa
	 * @param p
	 */
	public void adicionaPessoa( Pessoa p) {		
		em.persist(p);
	}
	
	public void editarAdicionarPessoa(Pessoa pessoa) {
		this.em.merge(pessoa);
	}
	
	/**
	 * busca pessoa especifica pelo id
	 * @param id
	 * @return
	 */
	public Pessoa porId(Long id) {
		return this.em.find(Pessoa.class, id);
	}
	
	/**
	 * remove uma pessoa
	 * @param pessoa
	 */
	public void removerPessoa(Pessoa pessoa) {
		
		em.remove(pessoa);
	}
	
	/**
	 * busca todas as pessoas
	 * @return
	 */
	public List<Pessoa> todas(){
		
		TypedQuery<Pessoa> query = em.createQuery("from Pessoa", Pessoa.class);
		return query.getResultList();
	}

}
