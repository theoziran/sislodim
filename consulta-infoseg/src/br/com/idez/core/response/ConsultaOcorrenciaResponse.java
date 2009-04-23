package br.com.idez.core.response;


public class ConsultaOcorrenciaResponse implements IResponse {
	private String response;
	
	public ConsultaOcorrenciaResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
