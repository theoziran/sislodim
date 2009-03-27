package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.faculdadeidez.psa.vo.SetorVO;

@SuppressWarnings("serial")
@Entity 
@Table (name="SIS_SETOR")
public class Setor implements Serializable {	
		
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column (name="SET_CODIGO")
	private int codigo;
	@Basic @Column (name="SET_NOME") 
	private String nome;
    @ManyToMany(cascade={CascadeType.ALL} )
    @JoinTable(name="SIS_BAIRRO_SETOR",
            joinColumns=
                @JoinColumn(name="BAS_SET_CODIGO", referencedColumnName="SET_CODIGO"),
            inverseJoinColumns=
                @JoinColumn(name="BAI_CODIGO", referencedColumnName="BAI_CODIGO")
            )	
      
    private List<Bairro> bairros; 
	
	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	/*********************************************************/
	/******** Conversão do objeto Setor para o SetorVO *******/
	/*********************************************************/
	public Setor(SetorVO vo) {
		this.codigo = vo.getCodigo();
		this.nome = vo.getNome();
	}
	
	public static SetorVO VO(Setor obj){
		return new SetorVO(obj.getCodigo(), obj.getNome());
	}
	/*********************************************************/
	
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