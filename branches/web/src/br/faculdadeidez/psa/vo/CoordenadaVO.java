package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Classe responsável por transportar objetoCoordenada
 * entre as camadas.
 */
@SuppressWarnings("serial")
public class CoordenadaVO implements Serializable {
	/**
	 * Propriedade privada codigo
	 */
	private int codigo;
	
	/**
	 * Propriedade privada latitude
	 */
	private String latitude;
	
	/**
	 * Propriedade privada longitude
	 */
	private String longitude;
	
	/**
	 * Responsável por referenciar uma Objeto Viatura no banco
	 */
	private ViaturaVO viatura= new ViaturaVO() ;
	
	/**
	 * Propriedade privada locale
	 * Responsável por definir o padrão do Calendar
	 */
	private Locale locBr = new Locale("pt","br"); 
	/**
	 * Propriedade privada data
	 */
	private Calendar data = Calendar.getInstance(locBr);
	
	/**
	 * Propriedade privada foradeArea
	 */
	private boolean foraDeArea;
	
	/**
	 * Propriedade privada processoVerificacao
	 */
	private boolean processadoVerificacao;
	
	/**
	 * Construtor da classe
	 */
	public CoordenadaVO(){
		
	}
	
	/**
	 * Sobrecarga do construtor padrão
	 * @param String lat
	 * @param String longit
	 * @param ViaturaVO viatura
	 * @param Calendar data
	 * @param boolean foraDeArea
	 * @param int id
	 * @param processadoVerificacao
	 */
	public CoordenadaVO(String lat,String longit, ViaturaVO viatura, Calendar data, boolean foraDeArea, int id, boolean processadoVerificacao) {
		this.latitude = lat;
		this.longitude = longit;
		this.viatura = viatura;
		this.data = data;
		this.foraDeArea = foraDeArea;
		this.codigo = id;
		this.processadoVerificacao = processadoVerificacao;
	}
	
	/**
	 * Método getter da propriedade 
	 * @return int codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Método setter da propridade codigo
	 * @param int codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Método getter da propriedade latitude
	 * @return String latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	
	/**
	 * Método setter da propridade lat
	 * @param String lat
	 */
	public void setLatitude(String lat) {
		this.latitude = lat;
	}
	
	/**
	 * Método getter da propriedade longitude
	 * @return String longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	
	/**
	 * Método is(boolean) da propriedade processadoVerificacao 
	 * @return boolean processadoVerificacao
	 */
	public boolean isProcessadoVerificacao() {
		return processadoVerificacao;
	}
	
	/**
	 * Método setter da propridade processadoVerifacao
	 * @param processadoVerificacao
	 */
	public void setProcessadoVerificacao(boolean processadoVerificacao) {
		this.processadoVerificacao = processadoVerificacao;
	}

	/**
	 * Método setter da propridade lat
	 * @param String lat
	 */
	public void setLongitude(String lat) {
		this.longitude = lat;
	}
	
	/**
	 * Método setter da propridade viatura
	 * @param Objetc<ViaturaVo> viatura
	 */
	public void setViatura(ViaturaVO viatura) {
		this.viatura = viatura;
	}
	
	
	public ViaturaVO getViatura() {
		return viatura;
	}
	
	/**
	 * Método setter da propridade data
	 * @param Date data
	 */
	public void setData(Date data) {
		this.data.setTime(data);
		//	this.data.set(data.getYear(), data.getMonth(), data.getDate(), data.getHours(),data.getMinutes());
	}
	
	/**
	 * Método getter da propridade data
	 * @return Date data
	 */
	public Calendar getData() {
		return data;
	}
	
	/**
	 * Método setter da propridade foraDeArea
	 * @param boolean foraDeArea
	 */
	public void setForaDeArea(boolean foraDeArea) {
		this.foraDeArea = foraDeArea;
	}
	
	/**
	 * Método getter da propridade foraDeArea
	 * @return boolean foraDeArea
	 */
	public boolean getForaDeArea() {
		return foraDeArea;
	}

}
