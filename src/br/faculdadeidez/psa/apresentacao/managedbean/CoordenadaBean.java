package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.List;

import br.faculdadeidez.psa.servico.ComparacaoDistancia;
import br.faculdadeidez.psa.servico.ComparacaoDistanciaException;
import br.faculdadeidez.psa.vo.BairroVO;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class CoordenadaBean extends GenericoBean{
	
	private CoordenadaVO coordenada;
	private String bairro="";
	private String rua="";
	
	public CoordenadaVO getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(CoordenadaVO coordenada) {
		this.coordenada = coordenada;
	}
	public String getBairro() {
		BairroVO bairroVO = getFachada().pesquisaBairroNome(this.bairro);
		return String.valueOf(bairroVO.getCodigo());
	}
	public void setBairro(String bairro) {
		BairroVO bairroVO = getFachada().pesquisaBairro(Integer.parseInt(bairro));
		this.bairro = bairroVO.getNome();
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public void calculaPontoMaisProximo() throws NumberFormatException, ComparacaoDistanciaException{
		StringBuffer origem= new StringBuffer();
		StringBuffer destino= new StringBuffer();
		CoordenadaVO coordenadaTemporaria = new CoordenadaVO();
		int distancia=0;
		int distanciaTemporaria=999999999;
		origem.append(this.rua);
		origem.append(",");
		origem.append(this.bairro);
		ComparacaoDistancia comparador = new ComparacaoDistancia();
		comparador.setOrigem(origem.toString());
		List<ViaturaVO> viaturas = getFachada().pesquisarViaturasEscalaTurno();
		for (ViaturaVO viaturaVO : viaturas) {
			coordenadaTemporaria = getFachada().getUltimaCoordenadaViatura(viaturaVO);
			destino.append(coordenadaTemporaria.getLatitude());
			destino.append(",");
			destino.append(coordenadaTemporaria.getLongitude());
			comparador.setDestino(destino.toString());
			distancia = Integer.parseInt(comparador.getDistancia());
			if (distanciaTemporaria>distancia){
				distanciaTemporaria=distancia;
				this.coordenada=coordenadaTemporaria;
				destino = new StringBuffer();
			}
		}
	}

}
