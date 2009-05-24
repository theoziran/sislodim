package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.faces.model.SelectItem;

import br.faculdadeidez.psa.facade.Fachada;
import br.faculdadeidez.psa.vo.BairroVO;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.SetorVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class MapaSetorBean extends GenericoBean {
	private SetorVO setor = new SetorVO();
	private List<CoordenadaVO> listaCoordenadas;
	
	public SetorVO getSetor() {
		return setor;
	}

	public void setSetor(SetorVO setor) {
		this.setor = setor;
	}
	
	public List<CoordenadaVO> getListaCoordenadasDesocupadas() {
		List<ViaturaVO> escalaViaturas = Fachada.getFachada().listarViaturasEscalaSetor(setor.getCodigo());
		
		List<CoordenadaVO> lstCoordenadas = new Vector<CoordenadaVO>();
		
		for(ViaturaVO viat : escalaViaturas) {
			if(!viat.getOcupada()) { 
				CoordenadaVO coord = Fachada.getFachada().getUltimaCoordenadaViatura(viat);			
				coord.setViatura(viat);
				lstCoordenadas.add(coord);
			}
		}
		
		return lstCoordenadas;
	}
	
	public List<CoordenadaVO> getListaCoordenadasOcupadas() {
		List<ViaturaVO> escalaViaturas = Fachada.getFachada().listarViaturasEscalaSetor(setor.getCodigo());
		
		List<CoordenadaVO> lstCoordenadas = new Vector<CoordenadaVO>();
		
		for(ViaturaVO viat : escalaViaturas) {
			if(viat.getOcupada()) {			
				CoordenadaVO coord = new CoordenadaVO();;			
				coord.setViatura(viat);
				lstCoordenadas.add(coord);
			}
		}
		
		return lstCoordenadas;
	}
		
	public List<SelectItem> getListBairros() {
		SetorVO setor2 = Fachada.getFachada().pesquisaSetor(setor.getCodigo());
		
		List<String> listaAtuais = new ArrayList<String>();
		List<BairroVO> bairros = setor2.getBairros();
		
		List<SelectItem> listBairros = new ArrayList<SelectItem>();
		for (BairroVO bairro : bairros) {
			SelectItem selectItem = new SelectItem();
			selectItem.setLabel(bairro.getNome());
			selectItem.setValue(String.valueOf(bairro.getCodigo()));
			listBairros.add(selectItem);
		}
		return listBairros;
	}

	public String visualizarMapaPorSetor() {
		SetorVO setor2 = Fachada.getFachada().pesquisaSetor(setor.getCodigo());	
		
		adicionarMensagem("Visualizando viaturas no setor " + setor2.getNome());		
		return "visualizarMapaSetor";
	}
	
	/**
	 * Retorna a última coordenada de uma viatura no setor
	 * 
	 * @return
	 */
	public CoordenadaVO getUltimaCoordenada() {		
		return getFachada().getUltimaCoordenadaViaturaSetor(setor.getCodigo());
	}
}
