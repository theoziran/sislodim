package br.com.idez.core.response;


public class ConsultaVeiculoResponse implements IResponse {
	private String response;
	
	public ConsultaVeiculoResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
