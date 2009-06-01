package br.faculdadeidez.psa.vo;

import java.io.Serializable;
/**
 * Classe responsável por transportar objetoCidade
 * entre as camadas.
 */
@SuppressWarnings("serial")
public class CidadeVO implements Serializable {

	/**
	 * Propriedade privada codigo
	 */
	private int codigo;
	
	/**
	 * Propriedade privada nome
	 */
	private String nome;
	
	/**
	 * Construtor da classe
	 */
	public CidadeVO(){
		
	}
	
	/**
	 * Sobrecarga do construtor padrão
	 * @param int codigo
	 * @param String nome
	 */
	public CidadeVO(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
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
	
}