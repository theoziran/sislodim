package br.faculdadeidez.psa.servico;

import br.faculdadeidez.psa.vo.ViaturaVO;
/**
 * Classe que armazena os dados retornados do serviço do google maps
 *
 */
public class GoogleMaps {

	/**
	 * Propriedade que armazena a distância entre os pontos
	 */
	private String distancia = null;
	/**
	 * Propriedade que armazena a distância entre os pontos em um tipo double
	 */
	private Double distanciaReal;
	/**
	 * Propriedade que armazena o tempo a ser percorrido
	 */
	private String tempo = null;
	/**
	 * Propriedade que armazena a viatura do percurso
	 */
	private ViaturaVO viatura;
	/**
	 * Propriedade que armazena o país de origem
	 */
	private String paisOrigem = null;
	/**
	 * Propriedade que armazena o país de destino
	 */
	private String paisDestino = null;
	
	/**
	 * Propriedade que armazena o município de origem
	 */
	private String municipioOrigem = null;
	/**
	 * Propriedade que armazena o município de destino
	 */
	private String municipioDestino = null;
	/**
	 * Propriedade que armazena o endereço de origem
	 */
	private String enderecoOrigem = null;
	/**
	 * Propriedade que armazena o endereço de destino
	 */
	private String enderecoDestino = null;
	
	/**
	 * Propriedade que armazena o cep de origem
	 */
	private String cepOrigem = null;
	/**
	 * Propriedade que armazena o cep de destino
	 */
	private String cepDestino = null;
	
	/**
	 * Propriedade que armazena a latitude de origem
	 */
	private String latitudeOrigem = null;
	/**
	 * Propriedade que armazena o latitude de destino
	 */
	private String latitudeDestino = null;
	/**
	 * Propriedade que armazena a longitude de origem
	 */
	private String longitudeOrigem = null;
	/**
	 * Propriedade que armazena a longitude de destino
	 */
	private String longitudeDestino = null;
	/**
	 * Propriedade que armazena o endereço completo da origem
	 */
	private String enderecoCompletoOrigem = null;
	/**
	 * Propriedade que armazena o endereço completo do destino
	 */
	private String enderecoCompletoDestino = null;
	/**
	 * Propriedade que armazena possível mensagem de erro
	 */
	private String msgErro = null;
	/**
	 * Propriedade que armazena possível mensagem de sucesso
	 */
	private String msgSucesso = null;

	/**
	 * Construtor default
	 */
	public GoogleMaps() {
		
	}
	
	/**
	 * Método getter do atributo distanciaReal
	 * @return Double
	 */
	public Double getDistanciaReal() { 
		return distanciaReal;
	}
	/**
	 * Método setter do atributo distanciaReal
	 * @param distanciaReal Double
	 */
	public void setDistanciaReal(Double distanciaReal) { 
		this.distanciaReal = distanciaReal;
	}
	/**
	 * Método getter do atributo distancia
	 * @return String
	 */
	public String getDistancia() {
		return distancia;
	}

	/**
	 * Método setter do atributo distancia
	 * @param distancia String
	 */
	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}
	/**
	 * Método getter do atributo tempo
	 * @return String
	 */
	public String getTempo(){
		return this.tempo;
	}
	/**
	 * Método setter do atributo tempo
	 * @param tempo String
	 */
	public void setTempo(String tempo){
		this.tempo = tempo;
	}
	/**
	 * Método getter do atributo msgErro
	 * @return String
	 */
	public String getMsgErro() {
		return msgErro;
	}
	/**
	 * Método setter do atributo msgErro
	 * @param msgErro String
	 */
	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}
	/**
	 * Método getter do atributo msgSucesso
	 * @return String
	 */
	public String getMsgSucesso() {
		return msgSucesso;
	}
	/**
	 * Método setter do atributo msgSucesso
	 * @param msgSucesso String
	 */
	public void setMsgSucesso(String msgSucesso) {
		this.msgSucesso = msgSucesso;
	}
	/**
	 * Método getter do atributo municipioOrigem
	 * @return String
	 */
	public String getMunicipioOrigem() {
		return municipioOrigem;
	}

	/**
	 * Método setter do atributo municipioOrigem
	 * @param municipioOrigem String
	 */
	public void setMunicipioOrigem(String municipioOrigem) {
		this.municipioOrigem = municipioOrigem;
	}

	/**
	 * Método getter do atributo municipioDestino
	 * @return String
	 */
	public String getMunicipioDestino() {
		return municipioDestino;
	}

	/**
	 * Método setter do atributo municipioDestino
	 * @param municipioDestino String
	 */
	public void setMunicipioDestino(String municipioDestino) {
		this.municipioDestino = municipioDestino;
	}
	/**
	 * Método getter do atributo enderecoOrigem
	 * @return String
	 */
	public String getEnderecoOrigem() {
		return enderecoOrigem;
	}

	/**
	 * Método setter do atributo enderecoOrigem
	 * @param enderecoOrigem String
	 */
	public void setEnderecoOrigem(String enderecoOrigem) {
		this.enderecoOrigem = enderecoOrigem;
	}

	/**
	 * Método getter do atributo enderecoDestino
	 * @return String
	 */
	public String getEnderecoDestino() {
		return enderecoDestino;
	}

	/**
	 * Método setter do atributo enderecoDestino
	 * @param enderecoDestino String
	 */
	public void setEnderecoDestino(String enderecoDestino) {
		this.enderecoDestino = enderecoDestino;
	}

	/**
	 * Método getter do atributo latitudeOrigem
	 * @return String
	 */
	public String getLatitudeOrigem() {
		return latitudeOrigem;
	}

	/**
	 * Método setter do atributo latitudeOrigem
	 * @param latitudeOrigem String
	 */
	public void setLatitudeOrigem(String latitudeOrigem) {
		this.latitudeOrigem = latitudeOrigem;
	}

	/**
	 * Método getter do atributo latitudeDestino
	 * @return String
	 */
	public String getLatitudeDestino() {
		return latitudeDestino;
	}

	/**
	 * Método setter do atributo latitudeDestino
	 * @param latitudeDestino String
	 */
	public void setLatitudeDestino(String latitudeDestino) {
		this.latitudeDestino = latitudeDestino;
	}

	/**
	 * Método getter do atributo longitudeOrigem
	 * @return String
	 */
	public String getLongitudeOrigem() {
		return longitudeOrigem;
	}

	/**
	 * Método setter do atributo longitudeOrigem
	 * @param longitudeOrigem String
	 */
	public void setLongitudeOrigem(String longitudeOrigem) {
		this.longitudeOrigem = longitudeOrigem;
	}

	/**
	 * Método getter do atributo longitudeDestino
	 * @return String
	 */
	public String getLongitudeDestino() {
		return longitudeDestino;
	}

	/**
	 * Método setter do atributo longitudeDestino
	 * @param longitudeDestino String
	 */
	public void setLongitudeDestino(String longitudeDestino) {
		this.longitudeDestino = longitudeDestino;
	}

	/**
	 * Método getter do atributo enderecoCompletoOrigem
	 * @return String
	 */
	public String getEnderecoCompletoOrigem() {
		return enderecoCompletoOrigem;
	}

	/**
	 * Método setter do atributo enderecoCompletoOrigem
	 * @param enderecoCompletoOrigem String
	 */
	public void setEnderecoCompletoOrigem(String enderecoCompletoOrigem) {
		this.enderecoCompletoOrigem = enderecoCompletoOrigem;
	}

	/**
	 * Método getter do atributo enderecoCompletoDestino
	 * @return String
	 */
	public String getEnderecoCompletoDestino() {
		return enderecoCompletoDestino;
	}

	/**
	 * Método setter do atributo enderecoCompletoDestino
	 * @param enderecoCompletoDestino String
	 */
	public void setEnderecoCompletoDestino(String enderecoCompletoDestino) {
		this.enderecoCompletoDestino = enderecoCompletoDestino;
	}

	/**
	 * Método getter do atributo cepOrigem
	 * @return String
	 */
	public String getCepOrigem() {
		return cepOrigem;
	}

	/**
	 * Método setter do atributo cepOrigem
	 * @param cepOrigem String
	 */
	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	/**
	 * Método getter do atributo cepDestino
	 * @return String
	 */
	public String getCepDestino() {
		return cepDestino;
	}

	/**
	 * Método setter do atributo cepDestino
	 * @param cepDestino String
	 */
	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}

	/**
	 * Método getter do atributo paisOrigem
	 * @return String
	 */
	public String getPaisOrigem() {
		return paisOrigem;
	}

	/**
	 * Método setter do atributo paisOrigem
	 * @param paisOrigem String
	 */
	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	/**
	 * Método getter do atributo paisDestino
	 * @return String
	 */
	public String getPaisDestino() {
		return paisDestino;
	}

	/**
	 * Método setter do atributo paisDestino
	 * @param paisDestino String
	 */
	public void setPaisDestino(String paisDestino) {
		this.paisDestino = paisDestino;
	}


	/**
	 * Método setter do atributo viatura
	 * @param viatura ViaturaVO
	 */
	public void setViatura(ViaturaVO viatura) {
		this.viatura = viatura;
	}


	/**
	 * Método getter do atributo viatura
	 * @return ViaturaVO
	 */
	public ViaturaVO getViatura() {
		return viatura;
	}
}
