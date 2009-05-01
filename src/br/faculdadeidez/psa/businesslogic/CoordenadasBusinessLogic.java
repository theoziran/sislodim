package br.faculdadeidez.psa.businesslogic;

import br.faculdadeidez.psa.db.dao.DAOCoordenada;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class CoordenadasBusinessLogic {
	
	
	public String create(CoordenadaVO vo){
		try {
			DAOCoordenada dCoordenada = new DAOCoordenada();
			dCoordenada.persist(vo);
			return "inserido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaInserir";
		}
	}
	
	/**
	 * Este método retorna a última coordenada enviada pela viatura durante o seu percurso
	 * @param viatura viatura a ser pesquisada
	 * @return CoordenadaVO
	 */
	public CoordenadaVO getUltimaCoordenadaViatura(ViaturaVO viatura) {
		
		CoordenadaVO coord=null;
		if (viatura.getCodigo()==null){
			coord = new CoordenadaVO();
			coordenadaDefault(coord);
			return coord;
		}	
		DAOCoordenada dCoordenada = new DAOCoordenada();
		coord = dCoordenada.getUltimaCoordenadaViatura(viatura);
		if (coord==null){
			coord = new CoordenadaVO();
			coordenadaDefault(coord);
			
		}
		return coord;
	}
	
	
	/**
	 * Transforma uma coordenada nula em uma coordenada padrão
	 * 
	 * @param coord
	 */
	private void coordenadaDefault(CoordenadaVO coord){
		
		coord.setLatitude("-7.096985");
		coord.setLongitude("-34.834374");
	}
	
}
