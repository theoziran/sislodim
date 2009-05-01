package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;
import java.util.Date;

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
	@Basic @Column (name="COO_LOGITUDE", nullable=false) 
	private String longitude;
	@Basic @Column (name="COO_LATITUDE", nullable=false) 
	private String latitude;
	@ManyToOne
	@JoinColumn (name="COO_VIA_CODIGO")
	private Viatura viatura;
	@Column (name="COO_DATA_HORA", nullable=false)
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date data;
	
	public Coordenada() {
		 
	}
		
	public Coordenada(CoordenadaVO coo) {		
		setLongitude(coo.getLongitude());
		setLatitude(coo.getLatitude());
		setViatura(new Viatura(coo.getViatura()));
		setData(coo.getData());
		if (coo.getCodigo()!=0){
			setId(coo.getCodigo());
		}
	}
	
	public static CoordenadaVO toVO(Coordenada obj) {		
		return new CoordenadaVO(obj.getLatitude(),obj.getLongitude(),Viatura.VO(obj.getViatura()));
		 
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

	public void setData(Date data) {
		this.data = data;
	}

	public Date getData() {
		return data;
	}

}
