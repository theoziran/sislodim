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
import br.faculdadeidez.psa.vo.CidadeVO;


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
	 * Responsável por representar um relacionamento
	 */
	private List<Setor> setores;
	
	
	/**
	 * Propriedade privada cidade
	 * Representa um relacionamento com a tabela de cidades
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="BAI_CIDADE", nullable=false, updatable=false)
	private Cidade cidade;
	
	/**
	 * Método getter da propriedade setores
	 * @return List<Setor>
	 */
	public List<Setor> getSetores() {
		return setores;
	}
	/**
	 * Método setter da propriedade setores
	 * @param List<Setor> setores
	 */
	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
	
	

	/**
	 * Método de conversão do objeto BairroVO para Bairro
	 * @param BairroVO vo
	 */
	public Bairro(BairroVO vo) {
		this.codigo = vo.getCodigo();
		this.nome = vo.getNome();
	}
	
	/**
	 * Método de conversão do objeto Bairro para BairroVO
	 * @param Bairro obj
	 * @return BairroVO
	 */
	public static BairroVO VO(Bairro obj){
		return new BairroVO(obj.getCodigo(), obj.getNome(),obj.getCidade());
	}
	
	/**
	 * Construtor default da classe
	 */
	public Bairro() {
		 
	}
	
	/**
	 * Sobrecarga do construtor padrão da classe
	 * @param String nome 
	 */
	public Bairro(String nome)
	{
		setNome(nome);
	}
	
	/**
	 * Sobrecarga do construtor padrão da classe
	 * @param int codigo
	 */
	public Bairro(int codigo)
	{
		setCodigo(codigo);
	}
	
	/**
	 * Sobrecarga do construtor padrão da classe
	 * @param int codigo
	 * @param String nome
	 */
	public Bairro(int codigo, String nome)
	{
		setCodigo(codigo);
		setNome(nome);
	}
	
	/**
	 * Método getter da propriedade private cidade
	 * @return CidadeVO
	 */
	public CidadeVO getCidade() {
		return Cidade.VO(this.cidade);
	}
	
	/**
	 * Método setter da propriedade private cidade
	 * @param cidade
	 */
	public void setCidade(CidadeVO cidade) {
		this.cidade = new Cidade(cidade);
	}	
	
	/**
	 * Método getter da propriedade privada codigo
	 * @return int 
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Método setter da propriedade privada codigo
	 * @param int codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Método getter da propriedade privada nome
	 * @return String
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Método setter da propriedade privada nome
	 * @param String nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}