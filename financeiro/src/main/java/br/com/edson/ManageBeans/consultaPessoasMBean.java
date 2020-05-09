package br.com.edson.ManageBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.edson.Repository.Pessoas;
import br.com.edson.Util.JpaUtil;
import br.com.edson.financeiro.Model.Pessoa;

@ManagedBean
@ViewScoped
public class consultaPessoasMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	public void consultarPessoas() {
		EntityManager em = JpaUtil.obterEntity();
		this.pessoas = new Pessoas(em).todas();
		em.close();
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	

}
