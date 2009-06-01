package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOCoordenada;
import br.faculdadeidez.psa.servico.GoogleMaps;
import br.faculdadeidez.psa.servico.GoogleMapsDistance;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;
/**
 * Classe que implementa regras de negócio referente a Entidade Coordenadas
 * Abstrai a camada de persistencia JPA e realiza validações de negócio 
 */
public class CoordenadasBusinessLogic {
	
	/**
	 * Propriedade privada gmaps
	 * Responsável por armazenar endereço completo da coordenada
	 */
	private GoogleMaps gmaps = null;
	
	
	/**
	 * Método para criar um objeto Coordenada
	 * @param CoordenadaVO vo -> objeto a ser removido do banco
	 * @return String -> indica sucesso ou falha
	 */
	public String create(CoordenadaVO vo) {
		try {
			DAOCoordenada dCoordenada = new DAOCoordenada();
			dCoordenada.persist(vo);
			return "inserido";
		} catch (Exception e) {

			return "problemaInserir";
		}
	}
	
	/**
	 * Método para atualizar um objeto Coordenada
	 * @param Coordenada vo
	 * @return String
	 */
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
	 * @param ViaturaVO -> viatura a ser pesquisada
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
	 * Retorna a última coordenada de uma viatura que faz parte da escala deste setor
	 * 
	 * @param int setor
	 * @return CoordenadaVO
	 */
	public CoordenadaVO getUltimaCoordenadaViaturaSetor(int setor) {

		CoordenadaVO coord = null;
			
		DAOCoordenada dCoordenada = new DAOCoordenada();
		coord = dCoordenada.getUltimaCoordenadaViaturaSetor(setor);
		if (coord == null) {
			coord = new CoordenadaVO();
			coordenadaDefault(coord);			
		}
		
		return coord;
	}
	
	/**
	 * Retorna uma CoordenadaVO por código
	 * 
	 * @param int codigo
	 * @return CoordenadaVO
	 */
	public CoordenadaVO find(int codigo) { 
		DAOCoordenada dao = new DAOCoordenada();
		return dao.find(codigo);
	}

	/**
	 * Transforma uma coordenada nula em uma coordenada padrão
	 * 
	 * @param CoordenadaVO coord
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
	 * @return List<CoordenadaVO>
	 */
	public List<CoordenadaVO> listarCoordenadasNaoVerificadas(){
		DAOCoordenada dCoord = new DAOCoordenada();
		List<CoordenadaVO> retorno = dCoord.findByField("processadoVerificacao", "0");
		return retorno;
	}

	/**
	 * Retorna a distancia em quilometros (Km) entre dois pontos
	 * 
	 * @param String origem -> ponto de origem
	 * @param String destino -> ponto de destino
	 * @return GoogleMaps
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
	
	/**
	 * Método getter da propriedade privada gmaps
	 * @return GoogleMaps
	 */
	public  GoogleMaps getGmaps(){
		return gmaps;
	}
	
	/**
	 * Método setter da propriedade privada gmpas
	 * @param GoogleMaps gmapsE
	 */
	public  void setGmaps(GoogleMaps gmapsE) {
		gmaps = gmapsE;
	}
	
	/**
	 * Método para calcular a viatura mais proxima
	 * @param String destino -> local da ocorrência
	 * @return GoogleMaps -> endereço completo a ser mostrado no mapa
	 */
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
				distancia = gmaps.getDistanciaReal();
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
