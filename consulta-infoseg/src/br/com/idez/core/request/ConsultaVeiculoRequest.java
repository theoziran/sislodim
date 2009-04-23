package br.com.idez.core.request;

public class ConsultaVeiculoRequest implements IRequest {
	private String placa;
	
	public ConsultaVeiculoRequest(String placa) {
		this.placa = placa;
	}
		
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRequest() {
		return "/Consulta?command=veiculo&placa=" + this.placa;
	}
}
