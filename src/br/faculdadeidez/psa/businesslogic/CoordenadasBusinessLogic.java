package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOCoordenada;
import br.faculdadeidez.psa.servico.GoogleMaps;
import br.faculdadeidez.psa.servico.GoogleMapsDistance;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class CoordenadasBusinessLogic {

	private GoogleMaps gmaps = null;
	
	public String create(CoordenadaVO vo) {
		try {
			DAOCoordenada dCoordenada = new DAOCoordenada();
			dCoordenada.persist(vo);
			return "inserido";
		} catch (Exception e) {

			return "problemaInserir";
		}
	}

	public String update(CoordenadaVO vo) {
		try {
			DAOCoordenada dCoordenada = new DAOCoordenada();
			CoordenadaVO coord = dCoordenada.find(vo.getCodigo());
			if (coord == null)
				return "escalaInexistente";
			
			dCoordenada.update(vo);
			return "atualizado";
		} catch (Exception e) {			 
			return "problemaAtualizar";
		}
	}
	
	/**
	 * Este método retorna a última coordenada enviada pela viatura durante o
	 * seu percurso
	 * 
	 * @param viatura
	 *            viatura a ser pesquisada
	 * @return CoordenadaVO
	 */
	public CoordenadaVO getUltimaCoordenadaViatura(ViaturaVO viatura) {

		CoordenadaVO coord = null;
		if (viatura.getCodigo() == null) {
			coord = new CoordenadaVO();
			coordenadaDefault(coord);
			return coord;
		}
		DAOCoordenada dCoordenada = new DAOCoordenada();
		coord = dCoordenada.getUltimaCoordenadaViatura(viatura);
		if (coord == null) {
			coord = new CoordenadaVO();
			coordenadaDefault(coord);
			
		}
		return coord;
	}
	
	/**
	 * Retorna uma CoordenadaVO por código
	 * 
	 * @param codigo
	 * @return
	 */
	public CoordenadaVO find(int codigo) { 
		DAOCoordenada dao = new DAOCoordenada();
		return dao.find(codigo);
	}

	/**
	 * Transforma uma coordenada nula em uma coordenada padrão
	 * 
	 * @param coord
	 */
	private void coordenadaDefault(CoordenadaVO coord) {

		coord.setLatitude("-7.096985");
		coord.setLongitude("-34.834374");
		coord.getViatura().setCodigo("padrão do sistema");
	}
	
	/**
	 * Retorna uma lista das coordenadas que possuem viaturas que não foram verificadas se 
	 * alguma saiu do seu setor
	 * 	  
	 * @return
	 */
	public List<CoordenadaVO> listarCoordenadasNaoVerificadas(){
		DAOCoordenada dCoord = new DAOCoordenada();
		List<CoordenadaVO> retorno = dCoord.findByField("processadoVerificacao", "0");
		return retorno;
	}

	/**
	 * Retorna a distancia em quilometros (Km) entre dois pontos
	 * 
	 * @param origem
	 *            ponto de origem
	 * @param destino
	 *            ponto de destino
	 * @return double
	 */
	private GoogleMaps getDistancia(String origem, String destino) {
		GoogleMapsDistance distance = new GoogleMapsDistance(origem,destino);
		GoogleMaps gmaps = distance.retornaDistancia();
		
		return gmaps;
	
		/*try {
			distancia = comp.getDistancia().replaceAll(",", ".");
			distancia = distancia.replaceAll("[^0-9,]*", "");
		} catch (ComparacaoDistanciaException e) {
			distancia = "0";
		}*/
		
	}
	
	
	public  GoogleMaps getGmaps(){
		return gmaps;
	}

	public  void setGmaps(GoogleMaps gmapsE) {
		gmaps = gmapsE;
	}
	public GoogleMaps calculaViaturaMaisProxima(String destino){
		StringBuffer origem= new StringBuffer();
		CoordenadaVO coordenadaTemporaria = new CoordenadaVO();
		GoogleMaps gmaps;
		GoogleMaps retorno= null;
		double distancia;
		double distanciaTemporaria=999999999;
		ViaturaBusinessLogic logicaViatura = new ViaturaBusinessLogic();
		List<ViaturaVO> viaturas = logicaViatura.getViaturasDesocupadas();
		if (viaturas.isEmpty()){
			retorno=new GoogleMaps();
			retorno.setMsgErro("ViaturasOcupadas");
			return retorno;
		}
		for (ViaturaVO viaturaVO : viaturas) {
			coordenadaTemporaria = getUltimaCoordenadaViatura(viaturaVO);
			origem.append(coordenadaTemporaria.getLatitude());
			origem.append(",");
			origem.append(coordenadaTemporaria.getLongitude());
			gmaps = getDistancia(origem.toString(),destino.toString());
			try {
				distancia = Double.parseDouble( gmaps.getDistancia());
			} catch (Exception e) {
				distancia = 999999999;
			}
			if (distanciaTemporaria>distancia){
				distanciaTemporaria=distancia;
				retorno = gmaps;
				retorno.setViatura(viaturaVO);
				origem = new StringBuffer();
			}
		}
		if (retorno==null){
			retorno=new GoogleMaps();
			retorno.setMsgErro("Erro");
		}
		return retorno;
	}
}
