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
	
	/**
	 * adiciona um lançamento no banco
	 * @param l
	 */
	public void adicionar(Lancamento lancamento) {
		this.em.persist(lancamento);
	}
	/**
	 * metodo que atualiza e edita
	 * @param l
	 */
	public void adicionarEditar(Lancamento lancamento) {
		em.merge(lancamento);
	}
	
	/**
	 * pegas as descrições existentes no banco
	 * @param descricao
	 * @return
	 */
	public List<String> pegaDescricao(String descricao){
		String consulta = " select distinct descricao from Lancamento "
				+ " where upper(descricao) like upper(:descricao)";
		TypedQuery<String> query = this.em.createQuery(consulta, String.class);
		query.setParameter("descricao", "%"+descricao+"%");
		return query.getResultList();
	}
	
	/**
	 * busca um laçamento especifico no banco pela id
	 * @param id
	 * @return
	 */
	public Lancamento buscaLancametnoPorId(Long id) {
		return em.find(Lancamento.class, id);
	}
	
	/**
	 * remove um lançamento recebido
	 * @param lancamento
	 */
	public void remover(Lancamento lancamento) {
		em.remove(lancamento);
	}
	
	/**
	 * busca todos os lançamentos no banco
	 * @return
	 */
	public List<Lancamento>  todos(){
		TypedQuery<Lancamento> query = em.createQuery("from Lancamento", Lancamento.class);
		return query.getResultList();
	}
	
	
}
