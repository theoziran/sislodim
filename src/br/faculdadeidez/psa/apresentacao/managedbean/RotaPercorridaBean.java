package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.faces.model.SelectItem;

import br.faculdadeidez.psa.vo.RotaPercorridaVO;
import br.faculdadeidez.psa.vo.SetorVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

/**
 * ManagedBean de rotas percorridas
 * 
 */
public class RotaPercorridaBean extends GenericoBean {
	/**
	 * Responsável por armazenar o conteúdo de uma rota
	 */
	private RotaPercorridaVO rotaPercorrida = new RotaPercorridaVO();
	/**
	 * Responsável por representar a entidade Setor
	 */
	private SetorVO setorVO = new SetorVO();
	/**
	 * Responsável por definir a localidade do tempo
	 */
	private Locale locBr = new Locale("pt", "br");
	/**
	 * Responsável por armazenar a data inicial da rota
	 */
	private Calendar periodoInicio = Calendar.getInstance(locBr);
	/**
	 * Responsável por armazenar a data final da rota
	 */
	private Calendar periodoFim = Calendar.getInstance(locBr);

	/**
	 * Define se a viatura saiu do setor
	 */
	private Boolean foraDeSetor = false;
	/**
	 * Armazena o tipo de relatório a ser gerado
	 */
	private String tipoDeRelatorio;
	/**
	 * Armazena as rotas percorridas
	 */
	private List<RotaPercorridaVO> rotas;
	/**
	 * Responsável pelo caminho do relatório
	 */
	private String saida;
	/**
	 * Responsável pela viatura do filtro
	 */
	private String viatura;

	/**
	 * Método que retorna uma lista de SelectItem contendo a lista de viaturas do relatório
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListaViaturas() {
		List<SelectItem> listaViaturas = new ArrayList<SelectItem>();
		SelectItem itemDefault = new SelectItem();
		itemDefault.setLabel("todas");
		itemDefault.setValue("todas");
		listaViaturas.add(itemDefault);

		for (ViaturaVO viatura : getFachada().listarViaturas()) {
			SelectItem selectItem = new SelectItem();
			selectItem.setLabel(viatura.getCodigo());
			selectItem.setValue(viatura.getCodigo());
			listaViaturas.add(selectItem);
		}
		return listaViaturas;
	}

	/**
	 * Método getter do atributo saida
	 * @return String
	 */
	public String getSaida() {
		return saida;
	}
	

	/**
	 * Método getter do atributo rotaPercorrida
	 * @return RotaPercorridaVO
	 */
	public RotaPercorridaVO getRotaPercorrida() {
		return rotaPercorrida;
	}

	/**
	 * Método setter do atributo rotaPercorrida
	 * 
	 * @param rotaPercorrida
	 *            RotaPercorridaVO
	 */
	public void setRotaPercorrida(RotaPercorridaVO rotaPercorrida) {
		this.rotaPercorrida = rotaPercorrida;
	}

	/**
	 * Método getter do atributo setorVO
	 * 
	 * @return SetorVO
	 */
	public SetorVO getSetorVO() {
		return setorVO;
	}

	/**
	 * Método setter do atributo setorVO
	 * 
	 * @param setorVO
	 *            SetorVO
	 */
	public void setSetorVO(SetorVO setorVO) {
		this.setorVO = setorVO;
	}

	/**
	 * Método getter do atributo periodoInicio
	 * 
	 * @return Date
	 */
	public Calendar getPeriodoInicio() {
		return periodoInicio;
	}

	/**
	 * Método setter do atributo periodoInicio
	 * 
	 * @param periodoInicio
	 *            Date
	 */
	public void setPeriodoInicio(Calendar periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	/**
	 * Método getter do atributo periodoFim
	 * 
	 * @return Calendar
	 */
	public Calendar getPeriodoFim() {
		return periodoFim;
	}

	/**
	 * Método setter do atributo periodoFim
	 * 
	 * @param peridoFim
	 *            Calendar
	 */
	public void setPeriodoFim(Calendar peridoFim) {
		this.periodoFim = peridoFim;
	}

	/**
	 * Método getter do atributo foraDeSetor
	 * 
	 * @return Boolean
	 */
	public Boolean getForaDeSetor() {
		return foraDeSetor;
	}

	/**
	 * Método setter do atributo foraDeSetor
	 * 
	 * @param foraDeSetor
	 *            Boolean
	 */
	public void setForaDeSetor(Boolean foraDeSetor) {
		this.foraDeSetor = foraDeSetor;
	}

	/**
	 * Método que retorna as rotas de um periodo
	 * 
	 * @return List<RotaPercorridaVO>
	 */
	public List<RotaPercorridaVO> getRotas() {
		rotas = new ArrayList<RotaPercorridaVO>();
		rotas = getFachada()
				.listarRotas(periodoInicio, periodoFim, foraDeSetor);
		return rotas;

	}

	/**
	 * Método que retorna as rotas de um periodo
	 * 
	 * @return List<RotaPercorridaVO>
	 */
	public List<RotaPercorridaVO> getListaTudo() {
		return rotas;
	}

	/**
	 * Método getter do atributo tipoDeRelatorio
	 * 
	 * @return String
	 */
	public String getTipoDeRelatorio() {
		if (foraDeSetor) {
			tipoDeRelatorio = "Relatório de Rotas Fora do Setor";
		} else {
			tipoDeRelatorio = "Relatório de Rotas";
		}
		return tipoDeRelatorio;
	}

	/**
	 * Método que define parâmetro fora do setor
	 * 
	 * @return String
	 */
	public String viaturaForaDoSetor() {
		foraDeSetor = true;
		getRotas();
		return "exibirRelatorios";

	}

	/**
	 * Método que define parâmetro fora do setor
	 * 
	 * @return String
	 */
	public String viaturaNoSetor() {
		this.foraDeSetor = false;
		return "exibirRelatorios";

	}
	/**
	 * Método getter do atributo viatura
	 * @return String
	 */
	public String getViatura() {
		return viatura;
	}

	/**
	 * Método setter do atributo viatura
	 * @param viatura String
	 */
	public void setViatura(String viatura) {
		this.viatura = viatura;
	}

	/**
	 * Método responsável por gerar no relatório
	 * @return String
	 */
	public String geraRelatorio() {
		saida = getFachada().gerarRelatorio(rotas, foraDeSetor);
		return "exibeRelatorio";

	}

}
