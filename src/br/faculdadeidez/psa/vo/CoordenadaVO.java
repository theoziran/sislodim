package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.List;
import br.faculdadeidez.psa.db.entity.Setor;

public class CoordenadaVO implements Serializable {
	private int codigo;
	private String latitude;
	private String longitude;
//	private List<Setor> setores;
	
//	public List<Setor> getSetores() {
//		return setores;
//	}
//	public void setSetores(List<Setor> setores) {
//		this.setores = setores;
//	}

	public CoordenadaVO(){
		
	}
	
	public CoordenadaVO(String lat,String longit) {
		this.latitude = lat;
		this.longitude = longit;
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

}
