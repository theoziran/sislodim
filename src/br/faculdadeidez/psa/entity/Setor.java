package br.faculdadeidez.psa.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table (name="SIS_SETOR")
public class Setor implements Serializable {	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column (name="SET_CODIGO")
	private int codigo;
	@Basic @Column (name="SET_NOME") private String nome;
	
	public void Setor()
	{
		
	}
	
	public void Setor(String nome)
	{
		this.nome = nome;
	}
	
	public void Setor(int codigo, String nome)
	{
		this.codigo = codigo;
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}