package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity 
@Table (name="SIS_VIATURA")
public class Viatura implements Serializable {	
	
	@Id @Column (name="VIA_CODIGO")
	private String codigo;
	@Basic @Column (name="VIA_OCUPADA")
	private Boolean ocupada = false;
	
	public Viatura()
	{
		
	}
	
	public Viatura(String codigo)
	{
		setCodigo(codigo);
	}
	
	public Viatura(String codigo, Boolean ocupada)
	{
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
