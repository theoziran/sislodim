package br.com.idez.core.request;

public class ConsultaCondutorRequest implements IRequest {
	private String identificacaoCNH;
	
	public ConsultaCondutorRequest(String identificacaoCNH) {
		this.identificacaoCNH = identificacaoCNH;
	}
		
	public String getIdentificacaoCNH() {
		return identificacaoCNH;
	}

	public void setIdentificacaoCNH(String identificacaoCNH) {
		this.identificacaoCNH = identificacaoCNH;
	}

	public String getRequest() {
		return "/Consulta?command=condutor&cnh=" + this.identificacaoCNH;
	}
}
