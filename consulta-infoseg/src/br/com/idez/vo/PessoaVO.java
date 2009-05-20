package br.com.idez.vo;

import java.util.Date;

public class PessoaVO {
	private String nome;
	private String cpf;
	private String rg;
	private String orgaoExped;
	private String dataNasc;
	private String naturalidade;
	private String nomePai;
	private String nomeMae;
	private CNHVO cnh;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public String getOrgaoExped() {
		return orgaoExped;
	}

	public void setOrgaoExped(String orgaoExped) {
		this.orgaoExped = orgaoExped;
	}

	public String getDataNasc() {
		return dataNasc;
	}
	
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	public String getNaturalidade() {
		return naturalidade;
	}
	
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	
	public String getNomePai() {
		return nomePai;
	}
	
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	
	public String getNomeMae() {
		return nomeMae;
	}
	
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public CNHVO getCnh() {
		return cnh;
	}

	public void setCnh(CNHVO cnh) {
		this.cnh = cnh;
	}
}