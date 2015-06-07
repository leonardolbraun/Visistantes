package br.ufmt.hujm.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.ufmt.hujm.erp.model.Setor;
import br.ufmt.hujm.erp.model.Visitante;
import br.ufmt.hujm.erp.repository.VisitanteDAO;
import br.ufmt.hujm.erp.util.Transacional;

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

	@Transacional
	public void salvarSetor(Setor setor) {
		visitanteDAO.guardarSetor(setor);
	}
	
	@Transacional
	public void excluirSetor(Setor setor) {
		visitanteDAO.removerSetor(setor);
	}
	
}