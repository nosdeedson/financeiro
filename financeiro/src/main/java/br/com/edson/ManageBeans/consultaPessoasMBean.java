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

import br.com.edson.Repository.Pessoas;
import br.com.edson.Util.JpaUtil;
import br.com.edson.financeiro.Model.Pessoa;
import br.com.edson.service.CadastroPessoa;

@ManagedBean
@ViewScoped
public class consultaPessoasMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private Pessoa pessoaSerExcluida = new Pessoa();
	
	private CadastroPessoa cadP;
	
	
	public void consultarPessoas() {
		EntityManager em = JpaUtil.obterEntity();
		this.pessoas = new Pessoas(em).todas();
		em.close();
	}
	
	public void excluir() {
		EntityManager em = JpaUtil.obterEntity();
		EntityTransaction et = em.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			et.begin();
			pessoaSerExcluida = new Pessoas(em).porId(pessoaSerExcluida.getId());
			
			this.cadP = new CadastroPessoa(em);
			this.cadP.excluirPessoa(pessoaSerExcluida);
			context.addMessage(null, new FacesMessage("Pessoa excluida com sucesso."));
			et.commit();
			this.consultarPessoas();
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}finally {
			em.close();
		}
		
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa getPessoaSerExcluida() {
		return pessoaSerExcluida;
	}

	public void setPessoaSerExcluida(Pessoa pessoaSerExcluida) {
		this.pessoaSerExcluida = pessoaSerExcluida;
	}
	
	

}
