package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.List;

import br.faculdadeidez.psa.db.entity.Setor;

public class CidadeVO implements Serializable {
	private int codigo;
	private String nome;
	
	public CidadeVO(){
		
	}
	
	public CidadeVO(int codigo, String nome) {
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