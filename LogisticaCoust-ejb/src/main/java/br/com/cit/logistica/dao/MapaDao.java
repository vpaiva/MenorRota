package br.com.cit.logistica.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import br.com.cit.logistica.model.Mapa;

/**
 * DAO for Mapa
 */
@Stateless
public class MapaDao extends Dao {
	@PersistenceContext
	private EntityManager em;

	@Inject
	private Event<Mapa> eventCadastro;

	public void create(Mapa entity) {
		eventCadastro.fire(entity);
	}
	
	public void save(Mapa entity) {
		em.persist(entity);
	}
	
	public void update(Mapa entity) {
		em.merge(entity);
	}

	public void deleteById(Long id) {
		Mapa entity = em.find(Mapa.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Mapa findById(Long id) {
		return em.find(Mapa.class, id);
	}

	public Optional<Mapa> findByName(String name) {
		TypedQuery<Mapa> query = em.createQuery(
				"select m from Mapa where m.nome = ?1", Mapa.class);
		query.setParameter(1, name);

		return getRegistro(query);
	}

	public List<Mapa> listAll() {
		TypedQuery<Mapa> findAllQuery = em.createQuery("SELECT m FROM Mapa m",
				Mapa.class);

		return findAllQuery.getResultList();
	}
}
