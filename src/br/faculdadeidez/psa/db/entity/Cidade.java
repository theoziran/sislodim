package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.faculdadeidez.psa.vo.CidadeVO;

@SuppressWarnings("serial")
@Entity 
@Table (name="SIS_CIDADE")
public class Cidade implements Serializable {	
		
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column (name="CID_CODIGO")
	private int codigo;
	@Basic @Column (name="CID_NOME", length=80, nullable=false) 
	private String nome;
	

	
	/*********************************************************/
	/******** Conversão do objeto Cidade para o CidadeVO *******/
	/*********************************************************/
	public Cidade(CidadeVO vo) {
		this.codigo = vo.getCodigo();
		this.nome = vo.getNome();
	}
	
	public static CidadeVO VO(Cidade obj){
		return new CidadeVO(obj.getCodigo(), obj.getNome());
	}
	/*********************************************************/
	
	public Cidade() {
		 
	}
	
	public Cidade(String nome)
	{
		setNome(nome);
	}
	
	public Cidade(int codigo)
	{
		setCodigo(codigo);
	}
	
	public Cidade(int codigo, String nome)
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