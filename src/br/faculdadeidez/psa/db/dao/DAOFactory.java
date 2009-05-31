package br.faculdadeidez.psa.db.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Classe responsável por fazer todas as operações genéricas com o banco de
 * dados
 * 
 * @param <T>
 *            Classe a ser usada na operação
 */
public class DAOFactory<T> {

	/**
	 * Gerencia as entidades a serem persistidas no banco de dados
	 */
	private static EntityManager manager;

	/**
	 * Construtor default
	 */
	public DAOFactory() {
		manager = getManager();
	}

	/**
	 * Cria um EntityManager caso não esteja criado
	 * 
	 * @return EntityManager
	 */
	public static EntityManager getManager() {
		if (manager == null) {
			EntityManagerFactory factory = Persistence
					.createEntityManagerFactory("sislodim");
			manager = factory.createEntityManager();
		}
		return manager;
	}

	/**
	 * Persiste o objeto no banco de dados
	 * 
	 * @param obj
	 *            Objeto da classe/entidade a ser persistida
	 */
	public void persist(T obj) {
		manager.getTransaction().begin();
		manager.persist(obj);
		manager.getTransaction().commit();

	}

	/**
	 * Remove o objeto do banco de dados
	 * 
	 * @param obj
	 *            Objeto da classe/entidade a ser removida do banco
	 */
	public void remove(T obj) {
		if (!manager.getTransaction().isActive())
			manager.getTransaction().begin();
		manager.remove(obj);
		manager.getTransaction().commit();
	}

	/**
	 * Atualiza o objeto no banco de dados
	 * 
	 * @param obj
	 *            Objeto da classe/entidade a ser atualizada no banco
	 */
	public void update(T obj) {
		manager.getTransaction().begin();
		manager.merge(obj);
		manager.getTransaction().commit();
	}

	/**
	 * Procura um objeto pela sua chave no banco de dados
	 * 
	 * @param classe
	 *            Classe/entidade a ser pesquisada
	 * @param chave
	 *            Identificador da entidade
	 * @return Objeto da entidade pesquisada
	 */
	T find(Class<T> classe, Object chave) {
		return manager.find(classe, chave);
	}

	/**
	 * retorna todos os objetos de uma determinada entidade
	 * 
	 * @param classe
	 *            Classe/entidade a ser pesquisada
	 * @return List da entidade pesquisada
	 */
	@SuppressWarnings("unchecked")
	List<T> findAll(Class<T> classe) {
		Query query = manager.createQuery("select a from "
				+ classe.getSimpleName() + " a");
		List<T> result = null;
		result = query.getResultList();
		return result;
	}

	/**
	 * Procura por campo e valor uma entidade no banco
	 * 
	 * @param classe
	 *            Classe/entidade a ser pesquisada
	 * @param campo
	 *            Campo no banco de dados a ser procurado
	 * @param valor
	 *            Valor a ser procurado no campo passado como argumento
	 * @return List da entidade pesquisada
	 */
	@SuppressWarnings("unchecked")
	List<T> findByField(Class<T> classe, String campo, String valor) {
		Query query = manager.createQuery("select a from "
				+ classe.getSimpleName() + " a " + "where a." + campo + " = \""
				+ valor + "\"");
		return (List<T>) query.getResultList();
	}

}