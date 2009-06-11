package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.faculdadeidez.psa.vo.BairroVO;


@SuppressWarnings("serial")
@Entity 
@Table (name="SIS_BAIRRO")
/**
 * Classe que abstrai uma linha da tabela SIS_BAIRRO
 * -Objeto relacional Bairro
 */
public class Bairro implements Serializable {	
		
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column (name="BAI_CODIGO")
	/**
	 * Propriedade privada codigo
	 * Representa o identificador do registro na tabela
	 */
	private int codigo;
	
	@Basic @Column (name="BAI_NOME", length=80, nullable=false)
	/**
	 * Propriedade privada nome
	 * Representa a coluna nome da tabela
	 */
	private String nome;
	
	@ManyToMany(mappedBy="bairros")
	/**
	 * Propriedade privada setores
	 * Respons�vel por representar um relacionamento
	 */
	private List<Setor> setores;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="BAR_CIDADE", unique=true, nullable=false, updatable=false)
	private Cidade cidade;
	
	/**
	 * M�todo getter da propriedade setores
	 * @return List<Setor>
	 */
	public List<Setor> getSetores() {
		return setores;
	}
	/**
	 * M�todo setter da propriedade setores
	 * @param List<Setor> setores
	 */
	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
	
	

	/**
	 * M�todo de concers�o do objeto BairroVO para Bairro
	 * @param BairroVO vo
	 */
	public Bairro(BairroVO vo) {
		this.codigo = vo.getCodigo();
		this.nome = vo.getNome();
	}
	
	/**
	 * M�todo de convers�o do objeto Bairro para BairroVO
	 * @param Bairro obj
	 * @return BairroVO
	 */
	public static BairroVO VO(Bairro obj){
		return new BairroVO(obj.getCodigo(), obj.getNome());
	}
	
	/**
	 * Construtor default da classe
	 */
	public Bairro() {
		 
	}
	
	/**
	 * Sobrecarga do construtor padr�o da classe
	 * @param String nome 
	 */
	public Bairro(String nome)
	{
		setNome(nome);
	}
	
	/**
	 * Sobrecarga do construtor padr�o da classe
	 * @param int codigo
	 */
	public Bairro(int codigo)
	{
		setCodigo(codigo);
	}
	
	/**
	 * Sobrecarga do construtor padr�o da classe
	 * @param int codigo
	 * @param String nome
	 */
	public Bairro(int codigo, String nome)
	{
		setCodigo(codigo);
		setNome(nome);
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}	
	
	/**
	 * M�todo getter da propriedade privada codigo
	 * @return int 
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * M�todo setter da propriedade privada codigo
	 * @param int codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * M�todo getter da propriedade privada nome
	 * @return String
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * M�todo setter da propriedade privada nome
	 * @param String nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}