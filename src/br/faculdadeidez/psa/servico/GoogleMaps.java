package br.faculdadeidez.psa.servico;

import br.faculdadeidez.psa.vo.ViaturaVO;

public class GoogleMaps {

	private String distancia = null;
	private Double distanciaReal;
	private String tempo = null;
	private ViaturaVO viatura;
	
	private String paisOrigem = null;
	private String paisDestino = null;
	
	
	private String municipioOrigem = null;
	private String municipioDestino = null;
	
	private String enderecoOrigem = null;
	private String enderecoDestino = null;
	
	private String cepOrigem = null;
	private String cepDestino = null;
	
	private String latitudeOrigem = null;
	private String latitudeDestino = null;
	
	private String longitudeOrigem = null;
	private String longitudeDestino = null;
	
	private String enderecoCompletoOrigem = null;
	private String enderecoCompletoDestino = null;
	
	private String msgErro = null;
	private String msgSucesso = null;

	public GoogleMaps() {
		
	}
	
	public Double getDistanciaReal() { 
		return distanciaReal;
	}
	
	public void setDistanciaReal(Double distanciaReal) { 
		this.distanciaReal = distanciaReal;
	}
	
	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}
	
	public String getTempo(){
		return this.tempo;
	}

	public void setTempo(String tempo){
		this.tempo = tempo;
	}
	
	public String getMsgErro() {
		return msgErro;
	}

	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}

	public String getMsgSucesso() {
		return msgSucesso;
	}

	public void setMsgSucesso(String msgSucesso) {
		this.msgSucesso = msgSucesso;
	}

	public String getMunicipioOrigem() {
		return municipioOrigem;
	}


	public void setMunicipioOrigem(String municipioOrigem) {
		this.municipioOrigem = municipioOrigem;
	}


	public String getMunicipioDestino() {
		return municipioDestino;
	}


	public void setMunicipioDestino(String municipioDestino) {
		this.municipioDestino = municipioDestino;
	}

	public String getEnderecoOrigem() {
		return enderecoOrigem;
	}


	public void setEnderecoOrigem(String enderecoOrigem) {
		this.enderecoOrigem = enderecoOrigem;
	}


	public String getEnderecoDestino() {
		return enderecoDestino;
	}


	public void setEnderecoDestino(String enderecoDestino) {
		this.enderecoDestino = enderecoDestino;
	}


	public String getLatitudeOrigem() {
		return latitudeOrigem;
	}


	public void setLatitudeOrigem(String latitudeOrigem) {
		this.latitudeOrigem = latitudeOrigem;
	}


	public String getLatitudeDestino() {
		return latitudeDestino;
	}


	public void setLatitudeDestino(String latitudeDestino) {
		this.latitudeDestino = latitudeDestino;
	}


	public String getLongitudeOrigem() {
		return longitudeOrigem;
	}


	public void setLongitudeOrigem(String longitudeOrigem) {
		this.longitudeOrigem = longitudeOrigem;
	}


	public String getLongitudeDestino() {
		return longitudeDestino;
	}


	public void setLongitudeDestino(String longitudeDestino) {
		this.longitudeDestino = longitudeDestino;
	}


	public String getEnderecoCompletoOrigem() {
		return enderecoCompletoOrigem;
	}


	public void setEnderecoCompletoOrigem(String enderecoCompletoOrigem) {
		this.enderecoCompletoOrigem = enderecoCompletoOrigem;
	}


	public String getEnderecoCompletoDestino() {
		return enderecoCompletoDestino;
	}


	public void setEnderecoCompletoDestino(String enderecoCompletoDestino) {
		this.enderecoCompletoDestino = enderecoCompletoDestino;
	}


	public String getCepOrigem() {
		return cepOrigem;
	}


	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}


	public String getCepDestino() {
		return cepDestino;
	}


	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}


	public String getPaisOrigem() {
		return paisOrigem;
	}


	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}


	public String getPaisDestino() {
		return paisDestino;
	}


	public void setPaisDestino(String paisDestino) {
		this.paisDestino = paisDestino;
	}


	public void setViatura(ViaturaVO viatura) {
		this.viatura = viatura;
	}


	public ViaturaVO getViatura() {
		return viatura;
	}
}
