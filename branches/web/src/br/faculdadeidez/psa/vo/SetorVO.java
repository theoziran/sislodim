package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class SetorVO implements Serializable {
	private int codigo;
	private String nome;
	private Boolean ativo;
	private List<BairroVO> bairros;
	
	public SetorVO() {
		 
	}
	
	public SetorVO(String nome)
	{
		setNome(nome);
	}
	
	public SetorVO(int codigo, String nome)
	{
		setCodigo(codigo);
		setNome(nome);
	}
	
	public SetorVO(int codigo, String nome, Boolean ativo)
	{
		setCodigo(codigo);
		setNome(nome);
		setAtivo(ativo);
	}
		
	public SetorVO(String nome, Boolean ativo){
		setNome(nome);
		setAtivo(ativo);
	}
	
	public SetorVO(String nome, Boolean ativo, List<BairroVO> bairros)
	{
		setNome(nome);
		setAtivo(ativo);
		setBairros(bairros);
	}
	
	public SetorVO(int codigo, String nome, Boolean ativo, List<BairroVO> bairros)
	{
		setCodigo(codigo);
		setNome(nome);
		setAtivo(ativo);
		setBairros(bairros);
	}

	public List<BairroVO> getBairros() {
		return bairros;
	}

	public void setBairros(List<BairroVO> bairros) {
		this.bairros = bairros;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}