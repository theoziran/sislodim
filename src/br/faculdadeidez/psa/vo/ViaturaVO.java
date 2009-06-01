package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por transportar objetoSetorVO
 * entre as camadas.
 */
@SuppressWarnings("serial")
public class ViaturaVO implements Serializable {
	
	/**
	 * Propriedade privada codigo
	 */
	private String codigo;
	/**
	 * Propriedade privada ocupado
	 */
	private boolean ocupada;
	
	/**
	 * Propriedade privada ativo
	 */
	private boolean ativo;
	
	/**
	 * Responsável por referenciar os Objeto Escala relacionado com  Viatura no banco
	 */
	private List<EscalaVO> escalas;
	
	/**
	 * Responsável por referenciar os Objeto Coordenada relacionado com Viatura no banco
	 */
	private List<CoordenadaVO> coordenadas;
	
	/**
	 * Construtor da classe
	 */
	public ViaturaVO(){
		super();
		this.ativo = false;
		this.ocupada = false;
	}
	
	/**
	 * Sobrecarga do construtor padrão
	 * @param String codigo
	 */
	public ViaturaVO(String codigo){
		setCodigo(codigo);
	}
	
	/**
	 * Sobrecarga do construtor padrão
	 * @param String codigo
	 * @param boolean ocupada
	 * @param boolean ativo
	 */
	public ViaturaVO(String codigo, boolean ocupada, boolean ativo){
		setCodigo(codigo);
		setOcupada(ocupada);
		setAtivo(ativo);
	}
	
	/**
	 * Método getter da propriedade codigo
	 * @return String codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Método setter da propriedade codigo
	 * @param int codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Método getter da propriedade ocupada
	 * @return boolean ocupada
	 */
	public boolean getOcupada() {
		return ocupada;
	}
	
	/**
	 * Método setter da propriedade ocupada
	 * @param boolean ocupada
	 */
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
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

	/**
	 * Método getter da propriedade escalas
	 * @return List<EscalaVO> escalas
	 */
	public List<EscalaVO> getEscalas() {
		return escalas;
	}
	
	/**
	 * Método setter da propriedade escalas
	 * @param List<EscalaVO> escalas
	 */
	public void setEscalas(List<EscalaVO> escalas) {
		this.escalas = escalas;
	}
	
	/**
	 * Método setter da propriedade coordenadas
	 * @param List<CoordenadaVO> coordenadas
	 */
	public void setCoordenadas(List<CoordenadaVO> coordenadas) {
		this.coordenadas = coordenadas;
	}
	
	/**
	 * Método getter da propriedade coordenada
	 * @return List<CoordenadaVO> 
	 */
	public List<CoordenadaVO> getCoordenadas() {
		return coordenadas;
	}
}
