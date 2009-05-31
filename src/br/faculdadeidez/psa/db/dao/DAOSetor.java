package br.faculdadeidez.psa.db.dao;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.faculdadeidez.psa.db.entity.Setor;
import br.faculdadeidez.psa.vo.SetorVO;

/**
 * Classe que contém todos os métodos específicos de Setor
 * 
 */
public class DAOSetor extends DAOFactory<Setor> {
	/**
	 * Construtor default
	 */
	public DAOSetor() {
		super();
	}

	/**
	 * Encapsulamento do método find genérico Recebe a chave do objeto e procura
	 * a chave no banco de dados retornando uma entidade
	 * 
	 * @param chave
	 *            int
	 * @return SetorVO
	 */
	public SetorVO find(int chave) {
		return Setor.VO(super.find(Setor.class, chave));
	}

	/**
	 * Encapsulamento do método findByField genérico Recebe o nome do campo e o
	 * valor a ser pesquisado nele
	 * 
	 * @param campo
	 *            String
	 * @param valor
	 *            String
	 * @return List<SetorVO>
	 */
	public List<SetorVO> findByField(String campo, String valor) {
		return ConvertList(super.findByField(Setor.class, campo, valor));
	}

	/**
	 * Procura os setores que tenham parte de um nome
	 * 
	 * @param nomeSetor
	 *            String
	 * @return List<SetorVO>
	 */
	@SuppressWarnings("unchecked")
	public List<SetorVO> findByName(String nomeSetor) {
		String strQuery = "SELECT  s FROM Setor s WHERE s.nome like '%"
				+ nomeSetor + "%'";
		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);

		List<SetorVO> resultList = ConvertList(query.getResultList());

		return resultList;
	}

	/**
	 * Encapsulamento do método findAll genérico Retorna todas as entidades como
	 * um select * from
	 * 
	 * @return List<SetorVO>
	 */
	public List<SetorVO> findAll() {
		return ConvertList(super.findAll(Setor.class));
	}

	/**
	 * Método que retorna todas os setores ativos
	 * 
	 * @return List<SetorVO>
	 */
	@SuppressWarnings("unchecked")
	public List<SetorVO> findAllActivated() {
		String strQuery = "SELECT  s FROM Setor s WHERE s.ativo = 1";
		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);

		List<SetorVO> resultList = ConvertList(query.getResultList());

		return resultList;
	}

	/**
	 * Encapsulamento do método update genérico Atualiza a entidade no banco de
	 * dados
	 * 
	 * @param vo
	 *            SetorVO
	 */
	public void update(SetorVO vo) {
		super.update(new Setor(vo));
	}

	/**
	 * Encapsulamento do método persist genérico Insere a entidade no banco de
	 * dados
	 * 
	 * @param vo
	 *            SetorVO
	 */
	public void persist(SetorVO vo) {
		super.persist(new Setor(vo));
	}

	/**
	 * Encapsulamento do método remove genérico Remove a linha do banco de dados
	 * 
	 * @param vo
	 *            SetorVO
	 */
	public void remove(SetorVO vo) {
		Setor setor = super.find(Setor.class, vo.getCodigo());
		super.remove(setor);
	}

	/**
	 * Método que converte uma lista de entidades em Value Object
	 * 
	 * @param lista
	 *            List<Setor>
	 * @return List<SetorVO>
	 */
	private List<SetorVO> ConvertList(List<Setor> lista) {
		List<SetorVO> newLista = new Vector<SetorVO>();
		for (Setor set : lista)
			newLista.add(Setor.VO(set));
		return newLista;
	}

}
