package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOBairro;
import br.faculdadeidez.psa.db.dao.DAOCoordenada;

import br.faculdadeidez.psa.vo.BairroVO;
import br.faculdadeidez.psa.vo.CoordenadaVO;

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
	

	public List<BairroVO> listar(){
		DAOBairro dBairro = new DAOBairro();
		return dBairro.findAll();		
	}
}
