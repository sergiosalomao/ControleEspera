package br.com.sistema.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sistema.model.Service;

public class ServiceDAO implements CrudDAO<Service> {

	EntityManager manager;
	EntityTransaction transaction;
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("SCS");

	public ServiceDAO() {
		manager = factory.createEntityManager();
	}

	public void save(Service entity) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(entity);
		manager.flush();
		transaction.commit();

	}

	public void update(Service entity) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(entity);
		manager.flush();
		transaction.commit();
	}

	public void delete(Service entity) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.remove(entity);
		transaction.commit();
	}

	public List<Service> findAll() throws Exception {
		return (List<Service>) manager.createQuery("select obj from Service obj order by obj.id").getResultList();
		
	}


	public Service find(Service service)  {
		return manager.find(Service.class, service.getId());
		
	}
	
}
