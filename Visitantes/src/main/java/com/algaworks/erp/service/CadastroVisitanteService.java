package com.algaworks.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.erp.model.Visitante;
import com.algaworks.erp.repository.Visitantes;
import com.algaworks.erp.util.Transacional;

public class CadastroVisitanteService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Visitantes visitantes;
	
	@Transacional
	public void salvar(Visitante visitante) {
		visitantes.guardar(visitante);
	}
	
	@Transacional
	public void excluir(Visitante visitante) {
		visitantes.remover(visitante);
	}

}