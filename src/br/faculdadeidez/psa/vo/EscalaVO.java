package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.faculdadeidez.psa.db.entity.Setor;

@SuppressWarnings("serial")
public class EscalaVO implements Serializable {	
	private int codigo;
	private Setor setor;
	private Date dataInicial;
	private Date dataFinal;
	List<ViaturaVO> viaturas;
	
	public EscalaVO(){
		
	}
	
	public EscalaVO(int codigo){
		setCodigo(codigo);
	}
	
	public EscalaVO(int codigo, Setor setor, Date dataInicial, Date dataFinal) {
		setCodigo(codigo);	
		setSetor(setor);
		setDataInicial(dataInicial);
		setDataFinal(dataFinal);
	}
	
	public List<ViaturaVO> getViaturas() {
		return viaturas;
	}

	public void setViaturas(List<ViaturaVO> viaturas) {
		this.viaturas = viaturas;
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
}
