package br.faculdadeidez.psa.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.faculdadeidez.psa.db.entity.Coordenada;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;
/**
 * Classe que contém todos os métodos específicos de Coordenada
 *
 */

public class DAOCoordenada extends DAOFactory<Coordenada> {
	/**
	 * Construtor default
	 */
	public DAOCoordenada() {
		super();
	}

	/**
	 * Encapsulamento do método find genérico
	 * Recebe a chave do objeto e procura a chave no banco de dados retornando uma entidade
	 * @param chave int
	 * @return CoordenadaVO
	 */
	public CoordenadaVO find(int chave) {
		return Coordenada.toVO(super.find(Coordenada.class, chave));
	}

	/**
	 * Encapsulamento do método findByField genérico
	 * Recebe o nome do campo e o valor a ser pesquisado nele
	 * @param campo String
	 * @param valor String
	 * @return List<CoordenadaVO>
	 */
	public List<CoordenadaVO> findByField(String campo, String valor) {
		return ConvertList(super.findByField(Coordenada.class, campo, valor));
	}
	/**
	 * Encapsulamento do método findAll genérico
	 * Retorna todas as entidades como um select * from
	 * @return List<CoordenadaVO>
	 */
	public List<CoordenadaVO> findAll() {
		return ConvertList(super.findAll(Coordenada.class));
	}
	/**
	 * Encapsulamento do método update genérico
	 * Atualiza a entidade no banco de dados
	 * @param vo CoordenadaVO
	 */
	public void update(CoordenadaVO vo) {
		super.update(new Coordenada(vo));
	}

	/**
	 * Encapsulamento do método persist genérico
	 * Insere a entidade no banco de dados
	 * @param vo CoordenadaVO
	 */
	public void persist(CoordenadaVO vo) {
		super.persist(new Coordenada(vo));
	}

	/**
	 * Encapsulamento do método remove genérico
	 * Remove a linha do banco de dados
	 * @param vo CoordenadaVO
	 */
	public void remove(CoordenadaVO vo) {
		super.remove(new Coordenada(vo));
	}

	/**
	 * Método que converte uma lista de entidades em Value Object
	 * @param lista List<Coordenada>
	 * @return List<CoordenadaVO>
	 */
	private List<CoordenadaVO> ConvertList(List<Coordenada> lista) {
		List<CoordenadaVO> newLista = new Vector<CoordenadaVO>();
		for (Coordenada set : lista)
			newLista.add(Coordenada.toVO(set));
		return newLista;
	}
	/**
	 * Método que retorna a última coordenada enviada por uma viatura
	 * @param viatura ViaturaVO
	 * @return CoordenadaVO
	 */
	@SuppressWarnings("unchecked")
	public CoordenadaVO getUltimaCoordenadaViatura(ViaturaVO viatura) {
		String strQuery = "SELECT c FROM Coordenada c WHERE c.id = (SELECT MAX(cid.id) FROM Coordenada cid WHERE cid.viatura.codigo = '"
				+ viatura.getCodigo() + "')";

		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);

		List<Coordenada> resultList = query.getResultList();
		if (resultList.isEmpty())
			return null;
		Coordenada c = resultList.get(0);
		CoordenadaVO cVO = Coordenada.toVO(c);

		return cVO;
	}

	/**
	 * Retorna a ultima coordenada enviada por uma viatura em um determinado setor
	 * @param setor int
	 * @return CoordenadaVO
	 */
	@SuppressWarnings("unchecked")
	public CoordenadaVO getUltimaCoordenadaViaturaSetor(int setor) {
		String strQuery = "SELECT c FROM Coordenada c WHERE c.id = " +
				"(SELECT MAX(cid.id) FROM Coordenada cid " +
				"JOIN cid.viatura v " +
				"JOIN v.escalas e " +
				"JOIN e.setor s " +
				"WHERE s.codigo = '"
				+ setor + "')";

		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);

		List<Coordenada> resultList = query.getResultList();
		if (resultList.isEmpty())
			return null;
		Coordenada c = resultList.get(0);
		CoordenadaVO cVO = Coordenada.toVO(c);

		return cVO;
	}
	
	/**
	 * Retorna as coordenadas enviadas por determinada viatura
	 * @param viatura String
	 * @param foraDeArea Boolean
	 * @return List<CoordenadaVO>
	 */
	@SuppressWarnings("unchecked")
	public List<CoordenadaVO> getCoordenadasPorViatura(String viatura, Boolean foraDeArea) {
		List<CoordenadaVO> listaDeCoordenadas = new ArrayList<CoordenadaVO>();
		
		String strQuery = "SELECT c FROM Coordenada c " +
				"WHERE c.viatura.codigo = '"
				+ viatura + "' and c.foraDeArea = '"+foraDeArea+"'";

		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);

		List<Coordenada> resultList = query.getResultList();

		if (resultList.isEmpty()){
			return null;
		}
	
		listaDeCoordenadas = ConvertList(resultList);
		
		return listaDeCoordenadas;
			
	}

	
}
