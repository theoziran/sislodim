package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.faculdadeidez.psa.vo.RotaPercorridaVO;
import br.faculdadeidez.psa.vo.SetorVO;

public class RotaPercorridaBean extends GenericoBean {
	private RotaPercorridaVO rotaPercorrida = new RotaPercorridaVO();
	private SetorVO setorVO = new SetorVO();
	private Date periodoInicio = new Date();
	private Date periodoFim = new Date();
	private Boolean foraDeSetor = false;
	private List<RotaPercorridaVO> rotas;
	private List<RotaPercorridaVO> listaTudo;
	private String termoPesquisa;
	private String tipoDeRelatorio;

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

	public Date getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(Date periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	public Date getPeriodoFim() {
		return periodoFim;
	}

	public void setPeriodoFim(Date peridoFim) {
		this.periodoFim = peridoFim;
	}

	public Boolean getForaDeSetor() {
		return foraDeSetor;
	}

	public void setForaDeSetor(Boolean foraDeSetor) {
		this.foraDeSetor = foraDeSetor;
	}

	public List<RotaPercorridaVO> getRotas() {
		List<RotaPercorridaVO> rotas = new ArrayList<RotaPercorridaVO>();
		rotas = getFachada().listarRotas(periodoInicio, periodoFim, foraDeSetor);
		return rotas;

	}

	public void setListaTudo(List<RotaPercorridaVO> listaTudo) {
		this.listaTudo = listaTudo;
	}

	public List<RotaPercorridaVO> getListaTudo() {
		return getRotas();
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
		return "exibirRelatorios";
		
	}

	public String viaturaNoSetor(){
		this.foraDeSetor = false;
		return "exibirRelatorios";
		
	}
}
