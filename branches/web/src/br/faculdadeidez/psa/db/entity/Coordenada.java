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
public class Coordenada implements Serializable {		
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column (name="COO_CODIGO")
	private int id;
	@Basic @Column (name="COO_LOGITUDE", nullable=false, length=18) 
	private String longitude;
	@Basic @Column (name="COO_LATITUDE", nullable=false, length=18) 
	private String latitude;
	@ManyToOne
	@JoinColumn (name="COO_VIA_CODIGO", nullable=false)
	private Viatura viatura;
	@Column (name="COO_DATA_HORA", nullable=false)
	@Temporal(value=TemporalType.TIMESTAMP)
	private Calendar data;
	@Column (name="COO_FORA_AREA", nullable=false)
	private boolean foraDeArea;
	@Column (name="COO_PROC_VERIFIC", nullable=false)
	private boolean processadoVerificacao;
	
	public Coordenada() {
		 
	}
		
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
	
	public static CoordenadaVO toVO(Coordenada obj) {		
		return new CoordenadaVO(obj.getLatitude(),obj.getLongitude(),Viatura.VO(obj.getViatura()),obj.getData(),obj.getForaDeArea(),obj.getId(),obj.isProcessadoVerificacao());
		 
	}	
		
	public boolean isProcessadoVerificacao() {
		return processadoVerificacao;
	}

	public void setProcessadoVerificacao(boolean processadoVerificacao) {
		this.processadoVerificacao = processadoVerificacao;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String lat) {
		this.latitude = lat;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longit) {
		this.longitude = longit;
	}

	public void setViatura(Viatura viatura) {
		this.viatura = viatura;
	}

	public Viatura getViatura() {
		return viatura;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Calendar getData() {
		return data;
	}

	public void setForaDeArea(boolean foraDeArea) {
		this.foraDeArea = foraDeArea;
	}

	public boolean getForaDeArea() {
		return foraDeArea;
	}

}
