package br.com.idez.core.request;

public class ConsultaOcorrenciaRequest implements IRequest {
	private String placa;
	
	public ConsultaOcorrenciaRequest(String placa) {
		this.placa = placa;
	}
		
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRequest() {
		return "/Consulta?command=ocorrencia&placa=" + this.placa;
	}
}
