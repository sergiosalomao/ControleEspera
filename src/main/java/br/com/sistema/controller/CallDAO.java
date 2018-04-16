package br.com.sistema.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sistema.model.Call;



public class CallDAO implements CrudDAO<Call> {

	EntityManager manager;
	EntityTransaction transaction;
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("SCS");

	public CallDAO() {
		manager = factory.createEntityManager();
	}

	public void save(Call entity) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(entity);
		manager.flush();
		transaction.commit();

	}

	public void update(Call entity) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(entity);
		manager.flush();
		transaction.commit();
	}

	public void delete(Call entity) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.remove(entity);
		transaction.commit();
	}

	public List<Call> findAll() throws Exception {
		return (List<Call>) manager.createQuery("select obj from Call obj order by obj.id").getResultList();
		
	}


	public Call find(Call call)  {
		return manager.find(Call.class, call.getId());
		
	}
	
}
