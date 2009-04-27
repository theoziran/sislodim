package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.faculdadeidez.psa.vo.ViaturaVO;

@SuppressWarnings("serial")
@Entity 
@Table (name="SIS_VIATURA")
public class Viatura implements Serializable {	
	
	@Id @Column (name="VIA_CODIGO")
	private String codigo;
	@Basic @Column (name="VIA_OCUPADA", nullable=false)
	private Boolean ocupada = false;
	@Basic @Column (name="VIA_ATIVO", nullable=false)
	private Boolean ativo = false;	
		
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
	
	public Boolean getOcupada() {
		return ocupada;
	}
	
	public void setOcupada(Boolean ocupada) {
		this.ocupada = ocupada;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}	
}
