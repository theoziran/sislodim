package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
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
	@ManyToOne
	@JoinColumn (name="ESC_SET_CODIGO", nullable=false) 
	private Setor setor;	
	@Basic @Temporal(value = TemporalType.TIMESTAMP) @Column (name="ESC_DATA_INC", nullable=false) 
	private Date dataInicial;
	@Basic @Temporal(value = TemporalType.TIMESTAMP) @Column (name="ESC_DATA_FIM", nullable=false)  
	private Date dataFinal;   
	@Basic @Column (name="ESC_ATIVO", nullable=false)  
	private boolean ativo; 
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
		this.setor = new Setor(vo.getSetor());
		this.dataInicial = vo.getDataInicial();
		this.dataFinal = vo.getDataFinal();		
		this.viaturas = new DAOViatura().ConverteEntidade(vo.getViaturas());
	}
	
	public static EscalaVO VO(Escala obj){
		return new EscalaVO(obj.getCodigo(), Setor.VO(obj.getSetor()), obj.getDataInicial(), obj.getDataFinal(),getViaturasVO(obj.getViaturas()),obj.getAtivo());
	}
	/*********************************************************/
	
	public Escala() {
		 
	}
	
	public Escala(int codigo)
	{
		setCodigo(codigo);
	}
	
	public Escala(int codigo, Setor setor, Date dataInicial, Date dataFinal, boolean ativo, List<Viatura> viaturas) {
		setCodigo(codigo);	
		setSetor(setor);
		setDataInicial(dataInicial);
		setDataFinal(dataFinal);
		setAtivo(ativo);
		setViaturas(viaturas);
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
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

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean getAtivo() {
		return ativo;
	}
	
	private static List<ViaturaVO> getViaturasVO(List<Viatura> viaturas){
		List<ViaturaVO> viaturasVO = new ArrayList<ViaturaVO>();
		for (Viatura viatura : viaturas) {
			viaturasVO.add( Viatura.VO(viatura));
		}
		return viaturasVO;
		
	}
}