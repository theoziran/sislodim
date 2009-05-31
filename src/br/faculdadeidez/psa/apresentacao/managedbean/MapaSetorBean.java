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

/**
 * ManagedBean de MapaSetor
 *
 */
public class MapaSetorBean extends GenericoBean {
	/**
	 * Responsável por representar a entidade Setor
	 */
	private SetorVO setor = new SetorVO();
	/**
	 * Método getter do atributo setor
	 * @return SetorVO
	 */
	public SetorVO getSetor() {
		return setor;
	}

	/**
	 * Método setter do atributo setor
	 * @param setor
	 */
	public void setSetor(SetorVO setor) {
		this.setor = setor;
	}
	
	/**
	 * Retorna uma lista de coordenadas com as viaturas desocupadas
	 * @return List<CoordenadaVO>
	 */
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
	
	/**
	 * Retorna uma lista de coordenadas com as viaturas ocupadas
	 * @return List<CoordenadaVO>
	 */
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
		
	/**
	 * 
	 * Método que retorna uma lista de SelectItem contendo os bairros de um determinado setor
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListBairros() {
		SetorVO setor2 = Fachada.getFachada().pesquisaSetor(setor.getCodigo());
		
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

	
	/**
	 * Método que mostra o mapa por setor
	 * @return String
	 */
	public String visualizarMapaPorSetor() {
		SetorVO setor = Fachada.getFachada().pesquisaSetor(this.setor.getCodigo());	
		adicionarMensagem("Visualizando viaturas no setor " + setor.getNome());		
		return "visualizarMapaSetor";
	}
	
	/**
	 * Retorna a última coordenada de uma viatura no setor
	 * @return CoordenadaVO
	 */
	public CoordenadaVO getUltimaCoordenada() {		
		return getFachada().getUltimaCoordenadaViaturaSetor(setor.getCodigo());
	}
}
