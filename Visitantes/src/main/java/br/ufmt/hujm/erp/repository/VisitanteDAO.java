package br.ufmt.hujm.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.ufmt.hujm.erp.model.Setor;
import br.ufmt.hujm.erp.model.Visitante;

public class VisitanteDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Visitante porId(Long id) {
		return manager.find(Visitante.class, id);
	}
	
	public List<Visitante> todas() {
		return manager.createQuery("from Visitante", Visitante.class).getResultList();
	}
	
	public Visitante guardar(Visitante visitante) {
		return manager.merge(visitante);
	}
	
	public void remover(Visitante visitante) {
		visitante = porId(visitante.getId());
		manager.remove(visitante);
	}

	public Setor setorPorId(Long id) {
		return manager.find(Setor.class, id);
	}
	
	public List<Setor> todosSetores() {
		return manager.createQuery("from Setor", Setor.class).getResultList();
	}
	
	public Setor guardarSetor(Setor setor) {
		return manager.merge(setor);
	}
	
	public void removerSetor(Setor setor) {
		setor = setorPorId(setor.getId());
		manager.remove(setor);
	}
}