package br.faculdadeidez.psa.vo;

import java.io.Serializable;

public class SetorVO implements Serializable {	
	private int codigo;
	private String nome;
	
	public SetorVO() {
		// TODO Auto-generated constructor stub
	}
	
	public SetorVO(String nome)
	{
		setNome(nome);
	}
	
	public SetorVO(int codigo)
	{
		setCodigo(codigo);
	}
	
	public SetorVO(int codigo, String nome)
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