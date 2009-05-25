package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.faces.model.SelectItem;

import br.faculdadeidez.psa.vo.RotaPercorridaVO;
import br.faculdadeidez.psa.vo.SetorVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class RotaPercorridaBean extends GenericoBean {
	private RotaPercorridaVO rotaPercorrida = new RotaPercorridaVO();
	private SetorVO setorVO = new SetorVO();
	private Locale locBr = new Locale("pt","br");
	private Calendar periodoInicio =  Calendar.getInstance(locBr);
	private Calendar periodoFim =  Calendar.getInstance(locBr);
	private Boolean foraDeSetor = false;
	private List<RotaPercorridaVO> rotas;
	private String termoPesquisa;
	private String tipoDeRelatorio;
	private String saida;
	private String viatura;
	
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
	
	public String getSaida() {
		return saida;
	}
	
	public RotaPercorridaVO getRotaPercorrida() {
		return rotaPercorrida;
	}

	public void setRotaPercorrida(RotaPercorridaVO rotaPercorrida) {
		this.rotaPercorrida = rotaPercorrida;
	}

	public SetorVO getSetorVO() {
		return setorVO;
	}

	public void setSetorVO(SetorVO setorVO) {
		this.setorVO = setorVO;
	}

	public Calendar getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(Calendar periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	public Calendar getPeriodoFim() {
		return periodoFim;
	}

	public void setPeriodoFim(Calendar peridoFim) {
		this.periodoFim = peridoFim;
	}

	public Boolean getForaDeSetor() {
		return foraDeSetor;
	}

	public void setForaDeSetor(Boolean foraDeSetor) {
		this.foraDeSetor = foraDeSetor;
	}

	public List<RotaPercorridaVO> getRotas() {
		rotas = new ArrayList<RotaPercorridaVO>();
		rotas = getFachada().listarRotas(periodoInicio, periodoFim, foraDeSetor);
		return rotas;

	}

	public List<RotaPercorridaVO> getListaTudo() {
		return rotas;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}
	public String getTipoDeRelatorio() {
		if(foraDeSetor){
			tipoDeRelatorio = "Relatório de Rotas Fora do Setor";
		}
		else{
			tipoDeRelatorio = "Relatório de Rotas";
		}
		return tipoDeRelatorio;
	}
	
	public String viaturaForaDoSetor(){
		foraDeSetor = true;
		getRotas();
		return "exibirRelatorios";
		
	}

	public String viaturaNoSetor(){
		this.foraDeSetor = false;
		return "exibirRelatorios";
		
	}
	
	public String getViatura() {
		return viatura;
	}

	public void setViatura(String viatura) {
		this.viatura = viatura;
	}
	public String geraRelatorio(){
		saida = getFachada().gerarRelatorio(rotas, foraDeSetor);  
		return "exibeRelatorio"; 
		
	}
	
}
