package br.com.sistema.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sistema.model.Company;

public class CompanyDAO implements CrudDAO<Company> {

	EntityManager manager;
	EntityTransaction transaction;
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("SCS");

	public CompanyDAO() {
		manager = factory.createEntityManager();
	}

	public void save(Company entity) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(entity);
		manager.flush();
		transaction.commit();

	}

	public void update(Company entity) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(entity);
		manager.flush();
		transaction.commit();
	}

	public void delete(Company entity) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.remove(entity);
		transaction.commit();
	}

	public List<Company> findAll() throws Exception {
		return (List<Company>) manager.createQuery("select obj from Company obj order by obj.id").getResultList();
		
	}


	public Company find(Company company)  {
		return manager.find(Company.class, company.getId());
		
	}
	
}
