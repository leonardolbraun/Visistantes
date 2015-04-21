package com.algaworks.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.erp.model.Visitante;

public class Visitantes implements Serializable {

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

}