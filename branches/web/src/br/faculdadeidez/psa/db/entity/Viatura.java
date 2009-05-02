package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.faculdadeidez.psa.vo.ViaturaVO;

@SuppressWarnings("serial")
@Entity 
@Table (name="SIS_VIATURA")
public class Viatura implements Serializable {	
	
	@Id @Column (name="VIA_CODIGO", length=4)
	private String codigo;
	@Basic @Column (name="VIA_OCUPADA", nullable=false)
	private boolean ocupada = false;
	@Basic @Column (name="VIA_ATIVO", nullable=false)
	private boolean ativo = false;
	
	@OneToMany(mappedBy = "viatura")  
	private List<Coordenada> coordenadas;
	@ManyToMany(mappedBy = "viaturas") 
	private List<Escala> escalas;
		
	/*********************************************************/
	/******** Conversão do objeto Setor para o SetorVO *******/
	/*********************************************************/
	public Viatura(ViaturaVO vo) {
		this.codigo = vo.getCodigo();
		this.ocupada = vo.getOcupada();
		this.ativo = vo.getAtivo();
	}
	
	public static ViaturaVO VO(Viatura obj){
		return new ViaturaVO(obj.getCodigo(), obj.getOcupada(), obj.getAtivo());
	}
	/*********************************************************/
	
	public Viatura()
	{
		
	}
	
	public Viatura(String codigo)
	{
		setCodigo(codigo);
	}
	
	public Viatura(String codigo, Boolean ocupada)
	{
		setCodigo(codigo);
		setOcupada(ocupada);
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public boolean getOcupada() {
		return ocupada;
	}
	
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}
	
	public boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setCoordenadas(List<Coordenada> coordenadas) {
		this.coordenadas = coordenadas;
	}

	public List<Coordenada> getCoordenadas() {
		return coordenadas;
	}

	public void setEscalas(List<Escala> escalas) {
		this.escalas = escalas;
	}

	public List<Escala> getEscalas() {
		return escalas;
	}

	
}
