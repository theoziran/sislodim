package br.faculdadeidez.psa.apresentacao.managedbean;

import br.faculdadeidez.psa.servico.GoogleMaps;
import br.faculdadeidez.psa.vo.BairroVO;

/**
 * ManagedBean de coordenada
 * @author Samuel
 *
 */
public class CoordenadaBean extends GenericoBean{
	
	/**
	 * Responsável por armazenar o nome do bairro
	 */
	private String bairro="";
	/**
	 * Responsável por armazenar o nome da rua
	 */
	private String rua="";
	/**
	 * Responsável por armazenar o número
	 */
	private String numero;
	/**
	 * Responsável por armazenar o conteúdo do googleMaps
	 */
	private GoogleMaps gmaps;
	
	/**
	 * Método getter do atributo bairro
	 * @return String
	 */
	public String getBairro() {
		BairroVO bairroVO = getFachada().pesquisaBairroNome(this.bairro);
		return String.valueOf(bairroVO.getCodigo());
	}
	/**
	 * Método setter do atributo bairro
	 * @param bairro String
	 */
	public void setBairro(String bairro) {
		BairroVO bairroVO = getFachada().pesquisaBairro(Integer.parseInt(bairro));
		this.bairro = bairroVO.getNome();
	}
	/**
	 * Método getter do atributo rua
	 * @return String
	 */
	public String getRua() {
		return rua;
	}
	/**
	 * Método setter do atributo rua
	 * @param rua String
	 */
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	/**
	 * Método responsável por enviar os dados a serem calculados para trazer a viatura desocupada mais próxima da ocorrência
	 */
	public String calculaPontoMaisProximo(){
		StringBuffer destino= new StringBuffer();
		destino.append(this.rua);
		destino.append(",");
		destino.append(this.bairro);
		destino.append(",");
		destino.append("João Pessoa");
		this.gmaps=getFachada().calculaViaturaMaisProxima(destino.toString());
		if (this.gmaps.getMsgErro()!=null){
			if (gmaps.getMsgErro().equals("ViaturasOcupadas"))
				adicionarMensagemErro("Todas as viaturas estão indisponíveis");
			else
				adicionarMensagemErro("Endereço não encontrado");
		}else{
			adicionarMensagem("A viatura " +this.gmaps.getViatura().getCodigo()+" sairá do ponto A ("+this.gmaps.getEnderecoCompletoOrigem()+") para o ponto B ("+this.gmaps.getEnderecoCompletoDestino()+")"+" percorrendo "+this.gmaps.getDistancia()+" em "+this.gmaps.getTempo());
		}
		
		return "viaturaProxima";
	}
	/**
	 * Método setter do atributo gmaps
	 * @param gmaps GoogleMaps
	 */
	public void setGmaps(GoogleMaps gmaps) {
		this.gmaps = gmaps;
	}
	/**
	 * Método getter do atributo gmaps
	 * @return GoogleMaps
	 */
	public GoogleMaps getGmaps() {
		return gmaps;
	}
	/**
	 * Método setter do atributo numero
	 * @param numero String
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * Método getter do atributo numero
	 * @return String
	 */
	public String getNumero() {
		return numero;
	}

}
