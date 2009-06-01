package br.faculdadeidez.psa.vo;


/**
 * Classe responsável por transportar objetoRotasPercorrida
 * entre as camadas.
 */
public class RotaPercorridaVO {
	
	/**
	 * Propriedade privada eSetor
	 */
	private boolean eSetor;
	
	/**
	 * Propriedade privada bairro
	 */
	private String bairro;
	
	/**
	 * Responsável por referenciar um Objeto Viatura no banco
	 */
	private ViaturaVO viatura;
	
	/**
	 * Propriedade privada data
	 */
	private String data;
	
	/**
	 * Propriedade privada horario
	 */
	private String horario;
	
	/**
	 * Métdo setter da propriedade privada bairro
	 * @param bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	/**
	 * Método getter da propriedade viatura
	 * @return ViaturaVO viatura
	 */
	public ViaturaVO getViatura() {
		return viatura;
	}
	
	/**
	 * Método setter da propriedade viatura
	 * @param ViaturaVO viatura
	 */
	public void setViatura(ViaturaVO viatura) {
		this.viatura = viatura;
	}
	
	/**
	 * Método getter da propriedade data
	 * @return Date data
	 */
	public void setData(String string) {
		this.data = string;
	}

	/**
	 * Método IS da propriedade privada eSetor
	 * @return
	 */
	public boolean isESetor() {
		return eSetor;
	}

	/**
	 * Método setter da propriedade setor
	 * @param boolean setor
	 */
	public void setESetor(boolean setor) {
		eSetor = setor;
	}
	
	/**
	 * Método getter da propriedade horario
	 * @return String 
	 */
	public String getHorario() {
		return horario;
	}
	
	/**
	 * Retorna codigo da viatura
	 * @return String
	 */
	public String getCodViatura(){
		return viatura.getCodigo();
	}
	
	/**
	 * Método getter da propriedade privada data
	 * @return String
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * Método setter da propriedade privada horario
	 * @param String horario 
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}

}
