package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.Date;
import java.util.List;

import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.SetorVO;

public class RotaPercorridaBean extends GenericoBean {
	private SetorVO setorVO = new SetorVO();
	private Date periodoInicio = new Date();
	private Date peridoFim = new Date();
	private Boolean foraDeSetor = false;

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

	public Date getPeridoFim() {
		return peridoFim;
	}

	public void setPeridoFim(Date peridoFim) {
		this.peridoFim = peridoFim;
	}

	public Boolean getForaDeSetor() {
		return foraDeSetor;
	}

	public void setForaDeSetor(Boolean foraDeSetor) {
		this.foraDeSetor = foraDeSetor;
	}

	public List<CoordenadaVO> getRotas() {
		List<CoordenadaVO> rotas = getFachada().listaRotas();
		for (CoordenadaVO coordenadaVO : rotas) {
			if ((coordenadaVO.getData().compareTo(peridoFim)>=0) && (coordenadaVO.getData().compareTo(periodoInicio)<0)){
				rotas.remove(coordenadaVO);
			}
		}
		return rotas;
	}
}
