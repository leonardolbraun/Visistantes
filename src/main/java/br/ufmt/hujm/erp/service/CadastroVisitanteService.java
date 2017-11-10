package br.ufmt.hujm.erp.service;

import java.io.Serializable;
import javax.inject.Inject;
import br.ufmt.hujm.erp.model.Setor;
import br.ufmt.hujm.erp.model.Visitante;
import br.ufmt.hujm.erp.model.Visitas;
import br.ufmt.hujm.erp.repository.VisitanteDAO;


public class CadastroVisitanteService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private VisitanteDAO visitanteDAO;
	
	
	public void salvar(Visitante visitante) {
		visitanteDAO.saveOrUpdateVisitante(visitante);
	}
	
	
	public void excluir(Visitante visitante) {
		visitanteDAO.removerVisitante(visitante);
	}

	
	public void salvarSetor(Setor setor) {
		visitanteDAO.saveOrUpdate(setor);
	}
	
	
	public void excluirSetor(Setor setor) {
		visitanteDAO.removerSetor(setor);
	}
	
	
	public void salvarVisita(Visitas visitas) {
		visitanteDAO.saveOrUpdateVisita(visitas);
	}
	
	
	public void excluirVisita(Visitas visitas) {
		visitanteDAO.removerVisitas(visitas);
	}
	
	
	
}