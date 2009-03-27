package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.List;


import br.faculdadeidez.psa.db.entity.Bairro;

public class SetorVO implements Serializable {
	
	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	private int codigo;
	private String nome;
	private List<Bairro> bairros;
	
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