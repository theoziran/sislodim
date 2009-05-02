package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ViaturaVO implements Serializable {	
	private String codigo;
	private boolean ocupada = false;
	private boolean ativo = false;
	private List<EscalaVO> escalas;
	private List<CoordenadaVO> coordenadas;
	
	public ViaturaVO(){
		
	}
	
	public ViaturaVO(String codigo){
		setCodigo(codigo);
	}
	
	public ViaturaVO(String codigo, boolean ocupada, boolean ativo){
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
	
	public boolean getOcupada() {
		return ocupada;
	}
	
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}
	
	public boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<EscalaVO> getEscalas() {
		return escalas;
	}

	public void setEscalas(List<EscalaVO> escalas) {
		this.escalas = escalas;
	}

	public void setCoordenadas(List<CoordenadaVO> coordenadas) {
		this.coordenadas = coordenadas;
	}

	public List<CoordenadaVO> getCoordenadas() {
		return coordenadas;
	}
}
