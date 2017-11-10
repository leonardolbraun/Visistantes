package br.ufmt.hujm.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;

import br.ufmt.hujm.erp.model.Setor;
import br.ufmt.hujm.erp.model.Visitante;
import br.ufmt.hujm.erp.model.Visitas;

public class VisitanteDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	/*protected Session getSession(){		
		return manager.unwrap(Session.class);
	}*/
	
	public Visitante porId(Long id) {
		return manager.find(Visitante.class, id);
	}
	
	public List<Visitante> allVisitantes() {
		String queryString = "SELECT v FROM Visitante v";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public void saveOrUpdateVisitante(Visitante visitante) {
		try{
			manager.getTransaction().begin();
			if(visitante.getId() == null){
				manager.persist(visitante);			
			}else{
				manager.merge(visitante);
			}
			manager.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
		
	}
	
	public void removerVisitante(Visitante visitante) {
		visitante = porId(visitante.getId());
		try{
			manager.getTransaction().begin();
			manager.remove(visitante);
			manager.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
		
	}

	public Setor setorPorId(Long id) {
		return manager.find(Setor.class, id);
	}
	
	public List<Setor> allSetores() {
		String queryString = "SELECT s FROM Setor s";
		Query query = manager.createQuery(queryString);		
		return query.getResultList();
	}
	
	public void saveOrUpdate(Setor setor) {
		try{
			manager.getTransaction().begin();
			if(setor.getId() == null){
				manager.persist(setor);			
			}else{
				manager.merge(setor);
			}
			manager.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
		
	}
	
	public void removerSetor(Setor setor) {
		setor = setorPorId(setor.getId());
		try{
			manager.getTransaction().begin();
			manager.remove(setor);
			manager.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
		
	}
	
	public Visitas visitasPorId(Long id) {
		return manager.find(Visitas.class, id);
	}
	
	public List<Visitas> allVisitas() {
		String queryString = "SELECT v FROM Visitas v";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public void saveOrUpdateVisita(Visitas visitas) {
		try{
			manager.getTransaction().begin();
			if(visitas.getId() == null){
				manager.persist(visitas);			
			}else{
				manager.merge(visitas);
			}
			manager.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
	}
	
	public void removerVisitas(Visitas visitas) {
		visitas = visitasPorId(visitas.getId());
		try{
			manager.getTransaction().begin();
			manager.remove(visitas);
			manager.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}		
	}
}