package br.faculdadeidez.psa.db.dao;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.faculdadeidez.psa.db.entity.Coordenada;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class DAOCoordenada extends DAOFactory<Coordenada> {
	public DAOCoordenada() {
		super();
	}

	public CoordenadaVO find(int chave) {
		return Coordenada.toVO(super.find(Coordenada.class, chave));
	}

	public List<CoordenadaVO> findByField(String campo, String valor) {
		return ConvertList(super.findByField(Coordenada.class, campo, valor));
	}

	public List<CoordenadaVO> findAll() {
		return ConvertList(super.findAll(Coordenada.class));
	}

	public void update(CoordenadaVO vo) {
		super.update(new Coordenada(vo));
	}

	public void persist(CoordenadaVO vo) {
		super.persist(new Coordenada(vo));
	}

	public void remove(CoordenadaVO vo) {
		super.remove(new Coordenada(vo));
	}

	/*
	 * Converte um List<Tipo1> para um List<Tipo2>
	 */
	private List<CoordenadaVO> ConvertList(List<Coordenada> lista) {
		List<CoordenadaVO> newLista = new Vector<CoordenadaVO>();
		for (Coordenada set : lista)
			newLista.add(Coordenada.toVO(set));
		return newLista;
	}
	
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

}
