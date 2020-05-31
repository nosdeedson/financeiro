package br.com.edson.ManageBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.edson.Repository.Pessoas;
import br.com.edson.Util.JpaUtil;
import br.com.edson.financeiro.Model.Pessoa;

@ManagedBean
@ViewScoped
public class CadastroPessoasMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa = new Pessoa();
	
	public void salvarPessoa() {
		EntityManager em = JpaUtil.obterEntity();
		EntityTransaction et = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			Pessoas p = new Pessoas(em);
			p.editarAdicionarPessoa(pessoa);
			this.pessoa = new Pessoa();
			context.addMessage(null, new FacesMessage(" Registro realizado com sucesso."));
			et.commit();
		} catch (Exception e) {
			et.rollback();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	

}
