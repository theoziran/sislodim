package br.faculdadeidez.psa.apresentacao.managedbean;

import br.faculdadeidez.psa.servico.ComparacaoDistanciaException;
import br.faculdadeidez.psa.servico.GoogleMaps;
import br.faculdadeidez.psa.vo.BairroVO;

public class CoordenadaBean extends GenericoBean{
	
	private String bairro="";
	private String rua="";
	private String nomeBairro;
	private String numero;
	private GoogleMaps gmaps;
	
	
	public String getBairro() {
		BairroVO bairroVO = getFachada().pesquisaBairroNome(this.bairro);
		return String.valueOf(bairroVO.getCodigo());
	}
	public void setBairro(String bairro) {
		BairroVO bairroVO = getFachada().pesquisaBairro(Integer.parseInt(bairro));
		this.bairro = bairroVO.getNome();
		this.nomeBairro = bairroVO.getNome();
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public void calculaPontoMaisProximo() throws NumberFormatException, ComparacaoDistanciaException{
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
			adicionarMensagem("A viatura " +this.gmaps.getViatura().getCodigo()+" sairá do ponto A ("+this.gmaps.getEnderecoCompletoOrigem()+") para o ponto B ("+this.gmaps.getEnderecoCompletoDestino()+")");
		}
	}
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}
	public String getNomeBairro() {
		return nomeBairro;
	}
	public void setGmaps(GoogleMaps gmaps) {
		this.gmaps = gmaps;
	}
	public GoogleMaps getGmaps() {
		return gmaps;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNumero() {
		return numero;
	}

}
