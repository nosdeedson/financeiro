package br.com.edson.financeiro.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MAX;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MIN;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table ( name = "lancamento")
public class Lancamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private Date dataVencimento;
	private Date dataPagamento;
	private TipoLancamentoEnum tipo;
	private Pessoa pessoa;
	
	public Lancamento() {
		super();
	}
	
	public Lancamento(Long id, String descricao, BigDecimal valor, Date dataVencimento, Date dataPagamento,
			TipoLancamentoEnum tipo, Pessoa pessoa) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.tipo = tipo;
		this.pessoa = pessoa;
	}
	
	@Id
	@GeneratedValue
	@Column (name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty
	@Size (max = 100)
	@Column (name = "descricao", length = 100, nullable = false)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@DecimalMin ("0")
	@DecimalMax ("9999999")
	@Column (name = "valor", precision = 10, scale = 2, nullable = false)
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@NotNull
	@Column (name = "data_vencimento", nullable = false)
	@Temporal (TemporalType.DATE)
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	@Column (name = "dataPagamento" , nullable = true)
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	@NotNull
	@Column (name = "tipo")
	@Enumerated (EnumType.STRING)
	public TipoLancamentoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamentoEnum tipo) {
		this.tipo = tipo;
	}
	
	@ManyToOne
	@JoinColumn (name = "id_pessoa")
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
