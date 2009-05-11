package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CoordenadaVO implements Serializable {
	private int codigo;
	private String latitude;
	private String longitude;
	private ViaturaVO viatura= new ViaturaVO() ;
	private Date data;
	private boolean foraDeArea;
	private boolean processadoVerificacao;
	
	public CoordenadaVO(){
		
	}
	
	public CoordenadaVO(String lat,String longit, ViaturaVO viatura, Date data, boolean foraDeArea, int id, boolean processadoVerificacao) {
		this.latitude = lat;
		this.longitude = longit;
		this.viatura = viatura;
		this.data = data;
		this.foraDeArea = foraDeArea;
		this.codigo = id;
		this.processadoVerificacao = processadoVerificacao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	
	public boolean isProcessadoVerificacao() {
		return processadoVerificacao;
	}

	public void setProcessadoVerificacao(boolean processadoVerificacao) {
		this.processadoVerificacao = processadoVerificacao;
	}

	public void setLongitude(String lat) {
		this.longitude = lat;
	}

	public void setViatura(ViaturaVO viatura) {
		this.viatura = viatura;
	}

	public ViaturaVO getViatura() {
		return viatura;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getData() {
		return data;
	}

	public void setForaDeArea(boolean foraDeArea) {
		this.foraDeArea = foraDeArea;
	}

	public boolean getForaDeArea() {
		return foraDeArea;
	}

}
