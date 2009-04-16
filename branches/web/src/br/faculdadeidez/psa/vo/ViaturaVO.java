package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ViaturaVO implements Serializable {	
	private String codigo;
	private Boolean ocupada = false;
	private Boolean ativo = false;
	private List<EscalaVO> escalas;
	
	
	public ViaturaVO(){
		
	}
	
	public ViaturaVO(String codigo){
		setCodigo(codigo);
	}
	
	public ViaturaVO(String codigo, Boolean ocupada, Boolean ativo){
		setCodigo(codigo);
		setOcupada(ocupada);
		setAtivo(ativo);
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
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<EscalaVO> getEscalas() {
		return escalas;
	}

	public void setEscalas(List<EscalaVO> escalas) {
		this.escalas = escalas;
	}
}
