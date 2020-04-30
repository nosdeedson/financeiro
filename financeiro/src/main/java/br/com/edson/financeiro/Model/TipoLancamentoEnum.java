package br.com.edson.financeiro.Model;

public enum TipoLancamentoEnum {
	RECEITA ("Receita"), DESPESA ("Despesa");
	
	private String descricao;

	TipoLancamentoEnum(String descricao) {
		// TODO Auto-generated constructor stub
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
