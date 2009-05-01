package br.faculdadeidez.psa.db.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.faculdadeidez.psa.db.entity.Viatura;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class DAOViatura extends DAOFactory<Viatura> {
	public DAOViatura() {
		super();
	}

	public ViaturaVO find(String chave) {
		return Viatura.VO(super.find(Viatura.class, chave));
	}

	public List<ViaturaVO> findByField(String campo, String valor) {
		return ConvertList(super.findByField(Viatura.class, campo, valor));
	}

	public List<ViaturaVO> findAll() {
		return ConvertList(super.findAll(Viatura.class));

	}

	public List<ViaturaVO> findAllActivated() {
		String strQuery = "SELECT v FROM Viatura v WHERE v.ativo = 1";
		EntityManager em = getManager();
		Query query = em.createQuery(strQuery);

		List<ViaturaVO> resultList = ConvertList(query.getResultList());

		return resultList;
	}

	public void update(ViaturaVO vo) {
		super.update(new Viatura(vo));
	}

	public void persist(ViaturaVO vo) {
		super.persist(new Viatura(vo));
	}

	public void remove(ViaturaVO vo) {
		Viatura viatura = super.find(Viatura.class, vo.getCodigo());
		super.remove(viatura);
	}

	/*
	 * Converte um List<Tipo1> para um List<Tipo2>
	 */
	private List<ViaturaVO> ConvertList(List<Viatura> lista) {
		List<ViaturaVO> newLista = new Vector<ViaturaVO>();
		for (Viatura set : lista)
			newLista.add(Viatura.VO(set));
		return newLista;
	}

	public List<Viatura> ConverteEntidade(List<ViaturaVO> lista) {
		List<Viatura> newLista = new Vector<Viatura>();
		for (ViaturaVO viatura : lista)
			newLista.add(super.find(Viatura.class, viatura.getCodigo()));
		return newLista;
	}

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

}
