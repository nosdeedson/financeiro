package br.com.edson.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.edson.Repository.Lancamentos;
import br.com.edson.Repository.Pessoas;
import br.com.edson.financeiro.Model.Lancamento;
import br.com.edson.financeiro.Model.Pessoa;

public class CadastroPessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Pessoas pessoasBD;
	private Lancamentos l;
	
	EntityManager em;

	public CadastroPessoa(EntityManager em) {;
		this.em = em;
	}
	
	/**
	 * Regra de negócio pessoa com lançamento não pode ser excluída por estar vinculada
	 * a uma lançamento para exibir o lançamento é preciso a pessoa em questão no banco por conta do id
	 * @param pessoa
	 * @throws NegocioException
	 */
	public void excluirPessoa( Pessoa pessoa) throws NegocioException{
		pessoasBD = new Pessoas(em);
		
		pessoa = pessoasBD.porId(pessoa.getId());
		l = new Lancamentos(em);
		Long existe = l.pegaLancamentoPorPessoa(pessoa.getId());
		
		if( existe > 0)
			throw new NegocioException("Pessoa não pode ser excluída. Pois tem lançamento no sistema.");
		pessoasBD.removerPessoa(pessoa);
		
			
		
	}
	
	
	

}
