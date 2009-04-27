package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.faculdadeidez.psa.db.dao.DAOViatura;
import br.faculdadeidez.psa.vo.EscalaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

@SuppressWarnings("serial")
@Entity 
@Table (name="SIS_ESCALA")
public class Escala implements Serializable {	
		
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column (name="ESC_CODIGO")
	private int codigo;
	@Basic @Column (name="ESC_SET_CODIGO") 
	private int setor;	
	@Basic @Temporal(value = TemporalType.DATE) @Column (name="ESC_DATA_INC") 
	private Date dataInicial;
	@Basic @Temporal(value = TemporalType.DATE) @Column (name="ESC_DATA_FIM")  
	private Date dataFinal;    
	@ManyToMany(cascade={CascadeType.ALL} )
    @JoinTable(name="SIS_ESCALA_VIATURA",
            joinColumns=
                @JoinColumn(name="ESV_ESC_CODIGO", referencedColumnName="ESC_CODIGO"),
            inverseJoinColumns=
                @JoinColumn(name="ESV_VIA_CODIGO", referencedColumnName="VIA_CODIGO")
            )	
      
    private List<Viatura> viaturas;	
	
	/*********************************************************/
	/******** Conversão do objeto Escala para o EscalaVO *******/
	/*********************************************************/
	public Escala(EscalaVO vo) {
		this.codigo = vo.getCodigo();
		this.setor = vo.getSetor();
		this.dataInicial = vo.getDataInicial();
		this.dataFinal = vo.getDataFinal();		
		this.viaturas = new DAOViatura().ConverteEntidade(vo.getViaturas());
	}
	
	public static EscalaVO VO(Escala obj){
		return new EscalaVO(obj.getCodigo(), obj.getSetor(), obj.getDataInicial(), obj.getDataFinal());
	}
	/*********************************************************/
	
	public Escala() {
		// TODO Auto-generated constructor stub
	}
	
	public Escala(int codigo)
	{
		setCodigo(codigo);
	}
	
	public Escala(int codigo, int setor, Date dataInicial, Date dataFinal) {
		setCodigo(codigo);	
		setSetor(setor);
		setDataInicial(dataInicial);
		setDataFinal(dataFinal);
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getSetor() {
		return setor;
	}

	public void setSetor(int setor) {
		this.setor = setor;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}	
	
	public List<Viatura> getViaturas() {
		return viaturas;
	}

	public void setViaturas(List<Viatura> viaturas) {
		this.viaturas = viaturas;
	}
}