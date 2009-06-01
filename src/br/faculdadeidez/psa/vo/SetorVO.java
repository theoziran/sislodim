package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por transportar objetoSetorVO
 * entre as camadas.
 */
@SuppressWarnings("serial")
public class SetorVO implements Serializable {
	
	/**
	 * Propriedade privada codigo 
	 */
	private int codigo;
	/**
	 * Propriedade privada nome
	 */
	private String nome;
	
	/**
	 * Propriedade privada ativa
	 */
	private boolean ativo;
	
	/**
	 * Responsável por referenciar os Objeto Bairro relacionado com o Setor no banco
	 */
	private List<BairroVO> bairros;
	
	/**
	 * Construtor da classe
	 */
	public SetorVO() {
		 
	}
	
	/**
	 * Sobrecarga do construtor padrão
	 * @param String nome
	 */
	public SetorVO(String nome)
	{
		setNome(nome);
	}
	
	/**
	 * Sobrecarga do construtor padrão
	 * @param int codigo
	 * @param String nome
	 */
	public SetorVO(int codigo, String nome)
	{
		setCodigo(codigo);
		setNome(nome);
	}
	
	/**
	 * Sobrecarga do construtor padrão
	 * @param int codigo
	 * @param String nome
	 * @param boolean ativo
	 */
	public SetorVO(int codigo, String nome, boolean ativo)
	{
		setCodigo(codigo);
		setNome(nome);
		setAtivo(ativo);
	}
	
	/**
	 * Sobrecarga do construtor padrão
	 * @param String nome
	 * @param boolean ativo
	 */
	public SetorVO(String nome, boolean ativo){
		setNome(nome);
		setAtivo(ativo);
	}
	
	/**
	 * Sobrecarga do construtor padrão
	 * @param nome
	 * @param ativo
	 * @param bairros
	 */
	public SetorVO(String nome, boolean ativo, List<BairroVO> bairros)
	{
		setNome(nome);
		setAtivo(ativo);
		setBairros(bairros);
	}
	
	/**
	 * Sobrecarga do construtor padrão
	 * @param int codigo
	 * @param String nome
	 * @param boolean ativo
	 * @param List<BairroVO> bairros
	 */
	public SetorVO(int codigo, String nome, boolean ativo, List<BairroVO> bairros)
	{
		setCodigo(codigo);
		setNome(nome);
		setAtivo(ativo);
		setBairros(bairros);
	}
	
	/**
	 * Método getter da propriedade bairros
	 * @return List<BairroVO> bairros
	 */
	public List<BairroVO> getBairros() {
		return bairros;
	}
	
	/**
	 * Método setter da propriedade bairros
	 * @param List<BairroVO) bairros
	 */
	public void setBairros(List<BairroVO> bairros) {
		this.bairros = bairros;
	}
	
	/**
	 * Método getter da propriedade codigo
	 * @return int codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Método setter da propriedade codigo
	 * @param int codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Método getter da propriedade nome
	 * @return String nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Método setter da propriedade nome
	 * @param String nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Método getter da propriedade ativo
	 * @return boolean ativo
	 */
	public boolean getAtivo() {
		return ativo;
	}
	
	/**
	 * Método setter da propriedade ativo
	 * @param boolean ativo
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}