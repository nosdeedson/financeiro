package br.com.edson.ManageBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.edson.Repository.Lancamentos;
import br.com.edson.Repository.Pessoas;
import br.com.edson.Util.JpaUtil;
import br.com.edson.financeiro.Model.Lancamento;
import br.com.edson.financeiro.Model.Pessoa;
import br.com.edson.financeiro.Model.TipoLancamentoEnum;
import br.com.edson.service.CadastroLancamentos;
import br.com.edson.service.NegocioException;


@ManagedBean
@ViewScoped
public class CadastroLancamentosMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	public void preparaCadastros() {
		EntityManager em = JpaUtil.obterEntity();
		try {
			this.pessoas = new Pessoas(em).todas();
		} finally {
			em.close();
		}
		
	}
	
	public void salvar() {
		
		EntityManager em = JpaUtil.obterEntity();
		EntityTransaction et = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			CadastroLancamentos cadastro = new CadastroLancamentos(new Lancamentos(em));
			cadastro.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso."));
			et.commit();
		} catch ( NegocioException e) {
			et.rollback();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}finally {
			em.close();
		}
	}
	
	/**
	 * @author edson
	 * @return
	 */
	public TipoLancamentoEnum[] tiposLancamento() {
		return TipoLancamentoEnum.values();
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}	
	

}
