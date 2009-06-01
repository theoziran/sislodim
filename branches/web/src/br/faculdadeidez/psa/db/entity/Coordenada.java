package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.faculdadeidez.psa.vo.CoordenadaVO;

@SuppressWarnings("serial")
@Entity 
@Table (name="SIS_COORDENADA")
/**
 * Classe que abstrai uma linha da tabela SIS_COORDENADA
 * -Objeto relacional Coordenada
 */
public class Coordenada implements Serializable {		
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column (name="COO_CODIGO")
	/**
	 * Propriedade privada id
	 * Representa o identificador do registro na tabela
	 */
	private int id;
	
	@Basic @Column (name="COO_LOGITUDE", nullable=false, length=18)
	/**
	 * Propriedade privada longitude
	 * Representa a coluna longitude da tabela
	 */
	private String longitude;
	
	@Basic @Column (name="COO_LATITUDE", nullable=false, length=18)
	/**
	 * Propriedade privada latitude
	 * Representa a coluna latitude da tabela
	 */
	private String latitude;
	
	@ManyToOne
	@JoinColumn (name="COO_VIA_CODIGO", nullable=false)
	/**
	 * Propriedade privada viaturas
	 * Responsável por representar os relacionamentos
	 */
	private Viatura viatura;
	
	@Column (name="COO_DATA_HORA", nullable=false)
	@Temporal(value=TemporalType.TIMESTAMP)
	/**
	 * Propriedade privada data
	 * Representa a coluna data da tabela
	 */
	private Calendar data;
	
	@Column (name="COO_FORA_AREA", nullable=false)
	/**
	 * Propriedade privada foraDeArea
	 * Representa a coluna foraDeArea da tabela
	 */
	private boolean foraDeArea;
	
	@Column (name="COO_PROC_VERIFIC", nullable=false)
	/**
	 * Propriedade privada foraDeprocessadoVerificacao
	 * Representa a coluna foraDeprocessadoVerificacao da tabela
	 */
	private boolean processadoVerificacao;
	
	/**
	 * Construtor default da classe
	 */
	public Coordenada() {
		 
	}
	
	/**
	 * Sobrecarga do construtor padrão da classe
	 * @param CoordenadaVO coo
	 */
	public Coordenada(CoordenadaVO coo) {		
		setLongitude(coo.getLongitude());
		setLatitude(coo.getLatitude());
		setViatura(new Viatura(coo.getViatura()));
		setData(coo.getData());
		setForaDeArea(coo.getForaDeArea());
		setProcessadoVerificacao(coo.isProcessadoVerificacao());
		if (coo.getCodigo()!=0){
			setId(coo.getCodigo());
		}
	}
	
	/**
	 * Método para conversão de objeto Coordenada para CoordenadaVO
	 * @param Coordenada obj
	 * @return CoordenadaVO
	 */
	public static CoordenadaVO toVO(Coordenada obj) {		
		return new CoordenadaVO(obj.getLatitude(),obj.getLongitude(),Viatura.VO(obj.getViatura()),obj.getData(),obj.getForaDeArea(),obj.getId(),obj.isProcessadoVerificacao());
		 
	}	
	
	/**
	 * Método IS da propriedade privada processadoVerficacao
	 * @return boolean
	 */
	public boolean isProcessadoVerificacao() {
		return processadoVerificacao;
	}
	
	/**
	 * Método setter da propriedade privada processadoVerficacao
	 * @param boolean processadoVerificacao
	 */
	public void setProcessadoVerificacao(boolean processadoVerificacao) {
		this.processadoVerificacao = processadoVerificacao;
	}
	
	/**
	 * Método getter da propriedade pricada id
	 * @return int
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Método setter da propriedade id
	 * @param int id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Método getter da propriedade latitude
	 * @return String
	 */
	public String getLatitude() {
		return latitude;
	}
	
	/**
	 * Método setter da propriedade latitude
	 * @param String lat
	 */
	public void setLatitude(String lat) {
		this.latitude = lat;
	}
	
	/**
	 * Método getter da propriedade longitude
	 * @return String
	 */
	public String getLongitude() {
		return longitude;
	}
	
	/**
	 * Método setter da propriedade longitude
	 * @param String longit
	 */
	public void setLongitude(String longit) {
		this.longitude = longit;
	}
	
	/**
	 * Método setter da propriedade viatura
	 * @param Viatura viatura
	 */
	public void setViatura(Viatura viatura) {
		this.viatura = viatura;
	}
	
	/**
	 * Método getter da propriedade  viatura
	 * @return Viatura
	 */
	public Viatura getViatura() {
		return viatura;
	}
	
	/**
	 * Método setter da propriedade data 
	 * @param Calendar data
	 */
	public void setData(Calendar data) {
		this.data = data;
	}
	
	/**
	 * Método getter da propriedade  data
	 * @return Calendar
	 */
	public Calendar getData() {
		return data;
	}
	
	/**
	 * Método setter da propriedade foraDeArea 
	 * @param boolean foraDeArea
	 */
	public void setForaDeArea(boolean foraDeArea) {
		this.foraDeArea = foraDeArea;
	}
	
	/**
	 * Método getter da propriedade foraDeArea
	 * @return boolean
	 */
	public boolean getForaDeArea() {
		return foraDeArea;
	}

}
