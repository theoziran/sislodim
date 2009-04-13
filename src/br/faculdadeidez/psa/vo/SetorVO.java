package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.List;


import br.faculdadeidez.psa.db.entity.Bairro;

@SuppressWarnings("serial")
public class SetorVO implements Serializable {
	
	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	private String codigo;
	private String nome;
	private Boolean ativo;
	private List<Bairro> bairros;
	
	public SetorVO() {
		// TODO Auto-generated constructor stub
	}
	
	public SetorVO(String nome)
	{
		setNome(nome);
	}
	
	public SetorVO(String codigo, String nome)
	{
		setCodigo(codigo);
		setNome(nome);
	}
	
	public SetorVO(String codigo, String nome, Boolean ativo)
	{
		setCodigo(codigo);
		setNome(nome);
		setAtivo(ativo);
	}
		
	public SetorVO(String nome, Boolean ativo){
		setNome(nome);
		setAtivo(ativo);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}