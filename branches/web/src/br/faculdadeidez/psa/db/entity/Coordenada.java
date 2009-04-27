package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	@ManyToMany(cascade={CascadeType.ALL} )
    @JoinTable(name="SIS_PERCURSO",
            joinColumns=
                @JoinColumn(name="PER_COO_CODIGO", referencedColumnName="COO_CODIGO"),
            inverseJoinColumns=
                    @JoinColumn(name="PER_VIA_CODIGO", referencedColumnName="VIA_CODIGO")
            )            
    private List<Viatura> viaturas;
		
	public Coordenada() {
		// TODO Auto-generated constructor stub
	}
		
	public Coordenada(CoordenadaVO coo) {		
		setLongitude(coo.getLongitude());
		setLatitude(coo.getLatitude());
		if (coo.getCodigo()!=0){
			setId(coo.getCodigo());
		}
	}
	
	public static CoordenadaVO toVO(Coordenada obj) {		
		return new CoordenadaVO(obj.getLatitude(),obj.getLongitude());
		 
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
}
