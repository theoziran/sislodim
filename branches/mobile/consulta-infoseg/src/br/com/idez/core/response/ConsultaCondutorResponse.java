package br.com.idez.core.response;


public class ConsultaCondutorResponse implements IResponse {
	private String response;
	
	public ConsultaCondutorResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
