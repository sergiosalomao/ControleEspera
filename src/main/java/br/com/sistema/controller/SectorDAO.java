package br.com.sistema.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sistema.model.Sector;



public class SectorDAO implements CrudDAO<Sector> {

	EntityManager manager;
	EntityTransaction transaction;
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("SCS");

	public SectorDAO() {
		manager = factory.createEntityManager();
	}

	public void save(Sector entity) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(entity);
		manager.flush();
		transaction.commit();

	}

	public void update(Sector entity) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(entity);
		manager.flush();
		transaction.commit();
	}

	public void delete(Sector entity) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.remove(entity);
		transaction.commit();
	}

	public List<Sector> findAll() throws Exception {
		return (List<Sector>) manager.createQuery("select obj from Sector obj order by obj.id").getResultList();
		
	}


	public Sector find(Sector sector)  {
		return manager.find(Sector.class, sector.getId());
		
	}
	
}
