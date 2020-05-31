package br.com.edson.conexaoBD;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.edson.Repository.Lancamentos;
import br.com.edson.Repository.Pessoas;
import br.com.edson.Util.JpaUtil;
import br.com.edson.financeiro.Model.Lancamento;
import br.com.edson.financeiro.Model.Pessoa;
import br.com.edson.financeiro.Model.TipoLancamentoEnum;

public class CriarLancamentos {
	
	private static EntityManager em;
	private static EntityTransaction et;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			em = JpaUtil.obterEntity();
			et = em.getTransaction();
			
			et.begin();
			
			Calendar dataVencimento1 = Calendar.getInstance();
			dataVencimento1.set(2013, 10, 1, 0, 0, 0);
			
			Calendar dataVencimento2 = Calendar.getInstance();
			dataVencimento2.set(2013, 12, 10, 0, 0, 0);
			
			Pessoa cliente = new Pessoa();
			cliente.setNome("Juca Chaves");
			Pessoa fornecedor = new Pessoa();
			fornecedor.setNome("Bar do Tião");
			
			Lancamento lancamento1 = new Lancamento();
			lancamento1.setDescricao("Venda de Pamonha");
			lancamento1.setPessoa(cliente);
			lancamento1.setDataVencimento(dataVencimento1.getTime());
			lancamento1.setDataPagamento(dataVencimento1.getTime());
			lancamento1.setValor(new BigDecimal(103_000));
			lancamento1.setTipo(TipoLancamentoEnum.RECEITA);
			
			Lancamento lancamento2 = new Lancamento();
			lancamento2.setDescricao("Venda de Pastel de Milho");
			lancamento2.setPessoa(cliente);
			lancamento2.setDataVencimento(dataVencimento1.getTime());
			lancamento2.setDataPagamento(dataVencimento1.getTime());
			lancamento2.setValor(new BigDecimal(15_000));
			lancamento2.setTipo(TipoLancamentoEnum.RECEITA);
			
			Lancamento lancamento3 = new Lancamento();
			lancamento3.setDescricao("Pinga do garrafão");
			lancamento3.setPessoa(fornecedor);
			lancamento3.setDataVencimento(dataVencimento2.getTime());		
			lancamento3.setValor(new BigDecimal(68_000));
			lancamento3.setTipo(TipoLancamentoEnum.DESPESA);
			
			em.persist(cliente);
			em.persist(fornecedor);
			em.persist(lancamento1);
			em.persist(lancamento2);
			em.persist(lancamento3);
			
			et.commit();
			em = JpaUtil.obterEntity();
			Lancamentos l = new Lancamentos(em);
			List<Lancamento> lancamentos = l.todos();
			
			Pessoas p = new Pessoas(em);
			List<Pessoa> pessoas =  p.todas();
			
			for ( Lancamento lancamento : lancamentos) {
				System.out.println(lancamento.toString());
			}
			
			for( Pessoa pessoa : pessoas) {
				System.out.println(pessoa.toString());
			}
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "falha");
		}finally {
			em.close();	
		}
	}

}
