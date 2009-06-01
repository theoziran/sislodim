package br.faculdadeidez.psa.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Classe responsável por transportar objetoEscala
 * entre as camadas.
 */
@SuppressWarnings("serial")
public class EscalaVO implements Serializable {
	
	/**
	 * Propriedade privada codigo
	 */
	private int codigo;
	
	/**
	 * Responsável por referenciar uma Objeto Setor no banco
	 */
	private SetorVO setor = new SetorVO();
	
	/**
	 * Propriedade privada dataInicial
	 * @deprecated
	 */
	private Date dataInicial;
	
	/**
	 * Propriedade privada dataFinal
	 * @deprecated
	 */
	private Date dataFinal;
	
	/**
	 * Propriedade privada ativo
	 */
	private boolean ativo;
	
	/**
	 * Responsável por referenciar os Objeto Viatura no banco 
	 */
	private List<ViaturaVO> viaturas;
	
	/**
	 * Construtor da classe
	 */
	public EscalaVO(){
		
	}
	
	
	/**
	 * Sobrecarga do contrutor padrão
	 * @param int codigo
	 */
	public EscalaVO(int codigo){
		setCodigo(codigo);
	}
	
	/**
	 * Sobrecarga do contrutor padrão
	 * @param int codigo
	 * @param Setor setor
	 * @param Date dataInicial
	 * @param Date dataFinal
	 * @param List<ViaturaVO> viaturas
	 * @param boolean ativo
	 */
	public EscalaVO(int codigo, SetorVO setor, Date dataInicial, Date dataFinal, List<ViaturaVO> viaturas, boolean ativo) {
		setCodigo(codigo);	
		setSetor(setor);
		setDataInicial(dataInicial);
		setDataFinal(dataFinal);
		setViaturas(viaturas);
		setAtivo(ativo);
	}
	
	/**
	 * Retorna uma lista de viaturas da escala
	 * @return List<ViaturaVo> viaturas
	 */
	public List<ViaturaVO> getViaturas() {
		return viaturas;
	}
	
	/**
	 * Método setter da propriedade viaturas
	 * @param List<ViaturaVo> viaturas
	 */
	public void setViaturas(List<ViaturaVO> viaturas) {
		this.viaturas = viaturas;
	}
	
	/**
	 * Método getter da propriedade codigo
	 * @return int codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Método setter da propriedade codigo
	 * @param int codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Método getter da propriedade setor
	 * @return SetorVo setor
	 */
	public SetorVO getSetor() {
		return setor;
	}
	
	/**
	 * Método setter da propriedade setor
	 * @param SetorVO setor
	 */
	public void setSetor(SetorVO setor) {
		this.setor = setor;
	}
	
	/**
	 * Método getter da propriedade dataInicial
	 * @return Date dataInicial
	 */
	public Date getDataInicial() {
		return dataInicial;
	}
	
	/**
	 * Método setter da propriedade dataInicial
	 * @param Date dataInicial
	 */
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	/**
	 * Método getter da propriedade dataFinal
	 * @return Date dataFinal
	 */
	public Date getDataFinal() {
		return dataFinal;
	}
	
	/**
	 * Método setter da propriedade dataFinal
	 * @param Date dataFinal
	 */
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	/**
	 * Método setter da propriedade ativo
	 * @param boolean ativo
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	/**
	 * Método getter da propriedade ativo
	 * @return boolean ativo
	 */
	public boolean getAtivo() {
		return ativo;
	}	
}
