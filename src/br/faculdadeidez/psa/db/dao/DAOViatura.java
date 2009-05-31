package br.faculdadeidez.psa.db.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.faculdadeidez.psa.db.entity.Viatura;
import br.faculdadeidez.psa.vo.BairroVO;
import br.faculdadeidez.psa.vo.EscalaVO;
import br.faculdadeidez.psa.vo.SetorVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

/**
 * Classe que contém todos os métodos de banco de dados específicos de viatura
 * 
 */
public class DAOViatura extends DAOFactory<Viatura> {
	/**
	 * Construtor default
	 */
	public DAOViatura() {
		super();
	}

	/**
	 * Encapsulamento do método find genérico Recebe a chave do objeto e procura
	 * a chave no banco de dados retornando uma entidade
	 * 
	 * @param chave
	 *            int
	 * @return ViaturaVO
	 */
	public ViaturaVO find(String chave) {
		return Viatura.VO(super.find(Viatura.class, chave));
	}

	/**
	 * Encapsulamento do método findByField genérico Recebe o nome do campo e o
	 * valor a ser pesquisado nele
	 * 
	 * @param campo
	 *            String
	 * @param valor
	 *            String
	 * @return List<ViaturaVO>
	 */
	public List<ViaturaVO> findByField(String campo, String valor) {
		return ConvertList(super.findByField(Viatura.class, campo, valor));
	}

	/**
	 * Encapsulamento do método findAll genérico Retorna todas as entidades como
	 * um select * from
	 * 
	 * @return List<ViaturaVO>
	 */
	public List<ViaturaVO> findAll() {
		return ConvertList(super.findAll(Viatura.class));

	}

	/**
	 * Retorna uma lista de usuários ativos
	 * 
	 * @return List<ViaturaVO>
	 */
	@SuppressWarnings("unchecked")
	public List<ViaturaVO> findAllActivated() {
		String strQuery = "SELECT v FROM Viatura v WHERE v.ativo = 1";
		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);

		List<ViaturaVO> resultList = ConvertList(query.getResultList());

		return resultList;
	}

	/**
	 * Encapsulamento do método update genérico Atualiza a entidade no banco de
	 * dados
	 * 
	 * @param vo
	 *            ViaturaVO
	 */
	public void update(ViaturaVO vo) {
		super.update(new Viatura(vo));
	}

	/**
	 * Encapsulamento do método persist genérico Insere a entidade no banco de
	 * dados
	 * 
	 * @param vo
	 *            ViaturaVO
	 */
	public void persist(ViaturaVO vo) {
		super.persist(new Viatura(vo));
	}

	/**
	 * Encapsulamento do método remove genérico Remove a linha do banco de dados
	 * 
	 * @param vo
	 *            ViaturaVO
	 */
	public void remove(ViaturaVO vo) {
		Viatura viatura = super.find(Viatura.class, vo.getCodigo());
		super.remove(viatura);
	}

	/**
	 * Método que converte uma lista de entidades em Value Object
	 * 
	 * @param lista
	 *            List<Viatura>
	 * @return List<ViaturaVO>
	 */
	private List<ViaturaVO> ConvertList(List<Viatura> lista) {
		List<ViaturaVO> newLista = new Vector<ViaturaVO>();
		for (Viatura set : lista)
			newLista.add(Viatura.VO(set));
		return newLista;
	}

	/**
	 * Método que converte uma lista de Value Object de bairro em entidades
	 * Bairro
	 * 
	 * @param lista
	 *            List<ViaturaVO>
	 * @return List<Viatura>
	 */
	public List<Viatura> ConverteEntidade(List<ViaturaVO> lista) {
		List<Viatura> newLista = new Vector<Viatura>();
		for (ViaturaVO viatura : lista)
			newLista.add(super.find(Viatura.class, viatura.getCodigo()));
		return newLista;
	}

	/**
	 * Retorna todas as viaturas de uma determinada escala
	 * 
	 * @param escala
	 *            EscalaVO
	 * @return List<ViaturaVO>
	 */
	@SuppressWarnings("unchecked")
	public List<ViaturaVO> findViaturasEscala(EscalaVO escala) {

		Date data = new Date(System.currentTimeMillis());
		String formato = "dd/MM/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		try {
			data = formatter.parse(formatter.format(data));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			data = null;
		}
		System.out.println(data);
		String strQuery = "SELECT v FROM Viatura v JOIN v.escalas sv"
				+ "WHERE sv.codigo = :codigo ";
		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);
		query.setParameter("codigo", escala.getCodigo());

		List<ViaturaVO> resultList = ConvertList(query.getResultList());

		return resultList;
	}

	/**
	 * Retorna todas as viaturas de um setor
	 * 
	 * @param setor
	 *            int
	 * @return List<ViaturaVO>
	 */
	@SuppressWarnings("unchecked")
	public List<ViaturaVO> findViaturasEscalaSetor(int setor) {

		Date data = new Date(System.currentTimeMillis());
		String formato = "dd/MM/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		try {
			data = formatter.parse(formatter.format(data));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			data = null;
		}
		System.out.println(data);

		String strQuery = "SELECT v FROM Viatura v JOIN v.escalas sv JOIN sv.setor s "
				+ "WHERE :dataInicio BETWEEN sv.dataInicial AND sv.dataFinal and s.codigo = "
				+ setor;
		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);
		query.setParameter("dataInicio", new Date());

		List<ViaturaVO> resultList = ConvertList(query.getResultList());

		return resultList;
	}

	/**
	 * Retorna a lista de viaturas ativas em uma escala
	 * 
	 * @return List<ViaturaVO>
	 */
	@SuppressWarnings("unchecked")
	public List<ViaturaVO> findViaturasEscalaAtivas() {

		Date data = new Date(System.currentTimeMillis());
		String formato = "dd/MM/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		try {
			data = formatter.parse(formatter.format(data));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			data = null;
		}
		System.out.println(data);
		String strQuery = "SELECT v FROM Viatura v " + "JOIN v.escalas sv "
				+ "WHERE sv.dataInicial = :dataInicio AND v.ativo = 1";
		// +"sv.dataFim = :dataFim";
		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);
		query.setParameter("dataInicio", data);
		// query.setParameter("dataFinal", data);

		List<ViaturaVO> resultList = ConvertList(query.getResultList());

		return resultList;
	}

	/**
	 * Retorna as escalas de uma determinada viatura
	 * 
	 * @param viatura
	 *            String
	 * @return List<EscalaVO>
	 */
	@SuppressWarnings("unchecked")
	public List<EscalaVO> findViaturasEscalaAtivas(String viatura) {

		Date data = new Date(System.currentTimeMillis());
		String formato = "dd/MM/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		try {
			data = formatter.parse(formatter.format(data));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			data = null;
		}
		System.out.println(data);
		String strQuery = "SELECT sv FROM Viatura v "
				+ "JOIN v.escalas sv "
				+ "WHERE sv.dataInicial = :dataInicio AND v.ativo = 1 AND v.codigo = '"
				+ viatura + "'";
		// +"sv.dataFim = :dataFim";
		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);
		query.setParameter("dataInicio", data);
		// query.setParameter("dataFinal", data);

		List<EscalaVO> resultList = new DAOEscala().ConvertList(query
				.getResultList());

		return resultList;
	}

	/**
	 * Retorna uma lista de todos os bairros das escalas atuais da viatura
	 * 
	 * @param viatura
	 *            String
	 * @param dataPesquisa
	 *            Calendar
	 * @return List<BairroVO>
	 */
	@SuppressWarnings("unchecked")
	public List<BairroVO> findViaturasEscalasBairros(String viatura,
			Calendar dataPesquisa) {
		Date data = new Date(System.currentTimeMillis());
		String formato = "dd/MM/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		try {
			data = formatter.parse(formatter.format(data));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			data = null;
		}
		String strQuery = "SELECT v FROM Viatura v JOIN v.escalas sv "
				+ "WHERE :dataInicio BETWEEN sv.dataInicial AND sv.dataFinal AND v.codigo = '"
				+ viatura + "'";
		// +"sv.dataFim = :dataFim";
		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);
		query.setParameter("dataInicio", dataPesquisa);

		List<ViaturaVO> viaturas = ConvertList(query.getResultList());

		for (ViaturaVO viat : viaturas) {
			for (EscalaVO escala : findViaturasEscalaAtivas(viat.getCodigo())) {
				SetorVO setor = new DAOSetor().find(escala.getSetor()
						.getCodigo());
				return setor.getBairros();
			}
		}

		return null;
	}

	/**
	 * Método que pesquisa por parte de codigo de uma viatura
	 * @param codigo String
	 * @return List<ViaturaVO>
	 */
	@SuppressWarnings("unchecked")
	public List<ViaturaVO> pesquisaViaturas(String codigo) {

		String strQuery = "SELECT  v FROM Viatura v WHERE v.codigo like '%"
				+ codigo + "%'";
		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);

		List<ViaturaVO> resultList = ConvertList(query.getResultList());

		return resultList;
	}
}
