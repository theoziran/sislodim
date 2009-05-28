package br.faculdadeidez.psa.db.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DAOFactory<T> {
	private static EntityManager manager;

	public DAOFactory() {
		manager = getManager();
	}

	public static EntityManager getManager() {
		if (manager == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("sislodim");
			manager = factory.createEntityManager();
		}
		return manager;
	}

	public void persist(T obj) {
		manager.getTransaction().begin();
		manager.persist(obj);
		manager.getTransaction().commit();
		
	}

	public void remove(T obj) {
		if (!manager.getTransaction().isActive())
		manager.getTransaction().begin();
		manager.remove(obj);
		manager.getTransaction().commit();
	}

	public void update(T obj) {
		manager.getTransaction().begin();
		manager.merge(obj);
		manager.getTransaction().commit();
	}
	
	T find(Class<T> classe, Object chave){
		return manager.find(classe, chave);
	}

	@SuppressWarnings("unchecked")
	List<T> findAll(Class<T> classe) {
		Query query = manager.createQuery("select a from "+classe.getSimpleName()+" a");
		List<T> result = null;
		result = query.getResultList();					
		return  result;
	}

	@SuppressWarnings("unchecked")
	List<T> findByField(Class<T> classe, String campo, String valor) {
		Query query = manager.createQuery("select a from "+classe.getSimpleName()+" a " + "where a."
				+ campo + " = \"" + valor + "\"");
		return (List<T>) query.getResultList();
	}	

	@SuppressWarnings("unchecked")
	List<T> findByField(Class<T> classe, String campo, String campo2, String valor, String valor2) {
		Query query = manager.createQuery("select a from "+classe.getSimpleName()+" a " + "where a."
				+ campo + " = \"" + valor + "\" and a."+ campo2 + " = \"" + valor2 + "\"");
		return (List<T>) query.getResultList();
	}	

}