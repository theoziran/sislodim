package br.faculdadeidez.psa.vo;

import java.io.Serializable;

public class ViaturaVO implements Serializable {	
	private String codigo;
	private Boolean ocupada = false;
	
	public ViaturaVO(){
		
	}
	
	public ViaturaVO(String codigo){
		setCodigo(codigo);
	}
	
	public ViaturaVO(String codigo, Boolean ocupada){
		setCodigo(codigo);
		setOcupada(ocupada);
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Boolean getOcupada() {
		return ocupada;
	}
	
	public void setOcupada(Boolean ocupada) {
		this.ocupada = ocupada;
	}	
}
