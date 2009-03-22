package br.faculdadeidez.psa.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity 
@Table (name="SIS_SETOR")
public class Setor implements Serializable {	
		
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column (name="SET_CODIGO")
	private int codigo;
	@Basic @Column (name="SET_NOME") 
	private String nome;
	
	public Setor() {
		// TODO Auto-generated constructor stub
	}
	
	public Setor(String nome)
	{
		setNome(nome);
	}
	
	public Setor(int codigo)
	{
		setCodigo(codigo);
	}
	
	public Setor(int codigo, String nome)
	{
		setCodigo(codigo);
		setNome(nome);
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