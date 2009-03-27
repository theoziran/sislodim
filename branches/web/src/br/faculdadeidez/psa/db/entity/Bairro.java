package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.faculdadeidez.psa.vo.BairroVO;

@SuppressWarnings("serial")
@Entity 
@Table (name="SIS_BAIRRO")
public class Bairro implements Serializable {	
		
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column (name="BAI_CODIGO")
	private int codigo;
	@Basic @Column (name="BAI_NOME") 
	private String nome;
	@ManyToMany(mappedBy="bairros")
	private List<Setor> setores;
	
	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
	
	
	/*********************************************************/
	/******** Conversão do objeto Bairro para o BairroVO *******/
	/*********************************************************/
	public Bairro(BairroVO vo) {
		this.codigo = vo.getCodigo();
		this.nome = vo.getNome();
	}
	
	public static BairroVO VO(Bairro obj){
		return new BairroVO(obj.getCodigo(), obj.getNome());
	}
	/*********************************************************/
	
	public Bairro() {
		// TODO Auto-generated constructor stub
	}
	
	public Bairro(String nome)
	{
		setNome(nome);
	}
	
	public Bairro(int codigo)
	{
		setCodigo(codigo);
	}
	
	public Bairro(int codigo, String nome)
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