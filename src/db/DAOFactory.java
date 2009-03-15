package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
		manager.getTransaction().begin();
		manager.remove(obj);
		manager.getTransaction().commit();
	}

	public void update(T obj) {
		manager.getTransaction().begin();
		manager.merge(obj);
		manager.getTransaction().commit();
	}
	
	public T find(Class<T> classe, Object chave){
		return manager.find(classe, chave);
	}


}