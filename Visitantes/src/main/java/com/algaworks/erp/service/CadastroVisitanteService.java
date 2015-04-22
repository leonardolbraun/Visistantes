package com.algaworks.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.erp.model.Visitante;
import com.algaworks.erp.repository.VisitanteDAO;
import com.algaworks.erp.util.Transacional;

public class CadastroVisitanteService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private VisitanteDAO visitanteDAO;
	
	@Transacional
	public void salvar(Visitante visitante) {
		visitanteDAO.guardar(visitante);
	}
	
	@Transacional
	public void excluir(Visitante visitante) {
		visitanteDAO.remover(visitante);
	}

}