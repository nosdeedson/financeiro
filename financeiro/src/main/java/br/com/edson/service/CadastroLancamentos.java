package br.com.edson.service;

import java.io.Serializable;
import java.util.Date;

import br.com.edson.Repository.Lancamentos;
import br.com.edson.financeiro.Model.Lancamento;

public class CadastroLancamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Lancamentos lancamentos;
	
	public CadastroLancamentos(Lancamentos lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	/*
	 * RN 01 não permite data de pagamento maior que hoje.
	 * @param lancamento
	 * @throw NegocioException
	 */

	public void salvar( Lancamento lancamento) throws NegocioException {
		if( lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date()))
			throw new NegocioException("Data de pagamento não pode ser maior que hoje.");
		this.lancamentos.adicionarEditar(lancamento);;
	}
	
	/**
	 * não será deletado lançamento já pago
	 * @param lancamento
	 * @throws NegocioException
	 */
	public void excluirLancamento(Lancamento lancamento) throws NegocioException {
		lancamento = this.lancamentos.buscaLancametnoPorId(lancamento.getId());
		if( lancamento.getDataPagamento() != null)
			throw new NegocioException("Você não pode deletar um lançamento já pago.");
		this.lancamentos.remover(lancamento);
	}
}
