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
import br.com.edson.Util.JpaUtil;
import br.com.edson.financeiro.Model.Lancamento;
import br.com.edson.service.CadastroLancamentos;

@ManagedBean
@ViewScoped
public class ConsultaLancamentosMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private Lancamento lancamentoSerExcluido = new Lancamento();
	
	public void consultar() {
		EntityManager em = JpaUtil.obterEntity();
		this.lancamentos = new Lancamentos(em).todos();
		em.close();
	}
	
	public void excluir() {
		EntityManager em = JpaUtil.obterEntity();
		EntityTransaction et = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		CadastroLancamentos cad = new CadastroLancamentos( new Lancamentos(em));
		try {
			et.begin();
			cad.excluirLancamento(lancamentoSerExcluido);
			context.addMessage(null, new FacesMessage("Lançamento removido com sucusso."));
			et.commit();
			this.consultar();
		} catch (Exception e) {
			et.rollback();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_FATAL);
			context.addMessage(null, msg);
		}finally {
			em.close();
		}
	}
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Lancamento getLancamentoSerExcluido() {
		return lancamentoSerExcluido;
	}

	public void setLancamentoSerExcluido(Lancamento lancamentoSerExcluido) {
		this.lancamentoSerExcluido = lancamentoSerExcluido;
	}
	
	
	
}
