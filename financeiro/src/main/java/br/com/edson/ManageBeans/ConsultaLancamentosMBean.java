package br.com.edson.ManageBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.edson.Repository.Lancamentos;
import br.com.edson.Util.JpaUtil;
import br.com.edson.financeiro.Model.Lancamento;

@ManagedBean
@ViewScoped
public class ConsultaLancamentosMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	
	public void consultar() {
		EntityManager em = JpaUtil.obterEntity();
		this.lancamentos = new Lancamentos(em).todos();
		em.close();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
}
