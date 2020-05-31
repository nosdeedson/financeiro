package br.com.edson.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.edson.Repository.Lancamentos;
import br.com.edson.Util.JpaUtil;
import br.com.edson.financeiro.Model.Lancamento;

@FacesConverter (forClass = Lancamento.class)
public class LancamentoConverter  implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		EntityManager em = JpaUtil.obterEntity();
		Lancamento retorno= null;
		try {
			if( value != null && !"".equals(value)) {
				retorno = new Lancamentos(em).buscaLancametnoPorId(new Long(value));
			}
			return retorno;
		} finally {
			em.close();
		}
	
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if( value != null) {
			Lancamento lancamento = (Lancamento) value;
			return lancamento.getId() == null ? null: lancamento.getId().toString();
		}
		return null;
	}

}
