package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.List;

import br.faculdadeidez.psa.db.entity.Setor;

public class BairroVO implements Serializable {
	private int codigo;
	private String nome;
	private List<Setor> setores;
	
	public BairroVO(){
		
	}
	
	public List<Setor> getSetores() {
		return setores;
	}
	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
	public BairroVO(int codigo, String nome) {
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
