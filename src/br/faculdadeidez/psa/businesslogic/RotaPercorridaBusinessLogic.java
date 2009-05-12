package br.faculdadeidez.psa.businesslogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOCoordenada;
import br.faculdadeidez.psa.servico.RetornaEndereco;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.RotaPercorridaVO;

public class RotaPercorridaBusinessLogic {

	@SuppressWarnings("deprecation")
	public List<RotaPercorridaVO> listar(Date dataInicio, Date dataFim, Boolean foraDeSetor){

		RotaPercorridaVO rota = null;
		List<CoordenadaVO> coordenadas = null;
		List<RotaPercorridaVO> rotas = new ArrayList<RotaPercorridaVO>();

		dataFim = new Date(dataFim.getYear(),dataFim.getMonth(),dataFim.getDate(),23,59);

			if(foraDeSetor){
				coordenadas = listarForaDoSetor();
				rota = new RotaPercorridaVO();
				for (CoordenadaVO coordenadaVO : coordenadas) {
				if ((coordenadaVO.getData().after(dataInicio))
						&& (coordenadaVO.getData().before(dataFim))){
					rota = new RotaPercorridaVO();
					rota.setBairro(getBairro(coordenadaVO.getLatitude(),coordenadaVO.getLongitude()));
					rota.setViatura(coordenadaVO.getViatura());
					rota.setData(coordenadaVO.getData());
					rotas.add(rota);

				}
			}
			}else if(!foraDeSetor){
				coordenadas = listarNoSetor();
				rota = new RotaPercorridaVO();
				for (CoordenadaVO coordenadaVO : coordenadas) {
					if ((coordenadaVO.getData().after(dataInicio))
							&& (coordenadaVO.getData().before(dataFim))){
					rota.setBairro(getBairro(coordenadaVO.getLatitude(),coordenadaVO.getLongitude()));
					rota.setViatura(coordenadaVO.getViatura());
					rota.setData(coordenadaVO.getData());
					rotas.add(rota);

					}
				}
			}
			return rotas;
			
	}	
			
	public List<CoordenadaVO> listarForaDoSetor(){
		DAOCoordenada dCoordenada = new DAOCoordenada();
		return dCoordenada.findByField("foraDeArea", "true");
	}
	
	public List<CoordenadaVO> listarNoSetor(){
		DAOCoordenada dCoordenada = new DAOCoordenada();
		return dCoordenada.findByField("foraDeArea", "false");
	}
	private String getBairro(String latitude, String longitude) {
		RetornaEndereco re = new RetornaEndereco(latitude, longitude);
		return re.getBairro(re.PercorrerXml(re.receberXml()));
	}

}
