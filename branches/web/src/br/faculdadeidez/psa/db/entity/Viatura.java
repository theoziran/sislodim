package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

import br.faculdadeidez.psa.vo.ViaturaVO;

@SuppressWarnings("serial")
@Entity 
@Table (name="SIS_VIATURA")
public class Viatura implements Serializable {	
	
	@Id @Column (name="VIA_CODIGO")
	private String codigo;
	@Basic @Column (name="VIA_OCUPADA", nullable=false)
	private Boolean ocupada = false;
	
	/*********************************************************/
	/******** Convers�o do objeto Setor para o SetorVO *******/
	/*********************************************************/
	public Viatura(ViaturaVO vo) {
		this.codigo = vo.getCodigo();
		this.ocupada = vo.getOcupada();
	}
	
	public static ViaturaVO VO(Viatura obj){
		return new ViaturaVO(obj.getCodigo(), obj.getOcupada());
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
}
