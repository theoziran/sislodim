package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CoordenadaVO implements Serializable {
	private int codigo;
	private String latitude;
	private String longitude;
	private ViaturaVO viatura;
	private Date data;

	public CoordenadaVO(){
		
	}
	
	public CoordenadaVO(String lat,String longit, ViaturaVO viatura) {
		this.latitude = lat;
		this.longitude = longit;
		this.viatura = viatura;
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

}
