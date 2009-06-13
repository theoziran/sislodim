package br.faculdadeidez.psa.businesslogic;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.faculdadeidez.psa.db.dao.DAOCoordenada;
import br.faculdadeidez.psa.servico.RetornaEndereco;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.RotaPercorridaVO;

/**
* Classe que implementa regras de negócio referente a Entidade RotaPercorrida
* Abstrai a camada de persistencia JPA e realiza validações de negócio 
*/
public class RotaPercorridaBusinessLogic {
	
	/**
	 * Método para listar rotas percorridas
	 * @param Calendar dataInicio
	 * @param Calendar dataFim
	 * @param Boolean foraDeSetor
	 * @param String viatura
	 * @return List<RotaPercorridaVO>
	 */
	public List<RotaPercorridaVO> listar(Calendar dataInicio, Calendar dataFim, Boolean foraDeSetor, String viatura){
		Locale locBr = new Locale("pt","br");
		DateFormat dtFormatData = DateFormat.getDateInstance(DateFormat.LONG, locBr);
		DateFormat dtFormatHorario = new SimpleDateFormat("hh:MM:ss");
		RotaPercorridaVO rota = null;
		List<CoordenadaVO> coordenadas = null;
		List<RotaPercorridaVO> rotas = new ArrayList<RotaPercorridaVO>();

		dataFim.set(dataFim.get(Calendar.YEAR),dataFim.get(Calendar.MONTH),dataFim.get(Calendar.DAY_OF_MONTH),23,59);

		if(viatura.equals("todas")){
			if(foraDeSetor){
				coordenadas = listarForaDoSetor();
			}else if(!foraDeSetor){
				coordenadas = listarNoSetor();
			}
		}else{
			DAOCoordenada dCoordenada = new DAOCoordenada();

			if(dCoordenada.findByField("viatura.codigo", viatura)!=null){
				coordenadas = listarPorViaturas(viatura, foraDeSetor);
			}

		}
		
		int i = 0; 
		
			for (CoordenadaVO coordenadaVO : coordenadas) {
				if ((coordenadaVO.getData().after(dataInicio))
						&& (coordenadaVO.getData().before(dataFim))){
					rota = new RotaPercorridaVO();
					String horario = dtFormatHorario.format(coordenadaVO.getData().getTime()); 
					rota.setViatura(coordenadaVO.getViatura());
					rota.setData(dtFormatData.format(coordenadaVO.getData().getTime()));
					rota.setBairro(getBairro(coordenadaVO.getLatitude(),coordenadaVO.getLongitude()));
					rota.setHorario(horario);
					rotas.add(rota);
					
					if(i == 20)
						break;

					i++;
				}
			}

			return rotas;
			
	}	
	
	private String getBairro(String latitude, String longitude) {
        try {           
	                RetornaEndereco re = new RetornaEndereco(latitude, longitude);
	                return re.getBairro(re.PercorrerXml(re.receberXml()));
	        }
	        catch (Exception ex) { 
	                return "";
	        }
	}
	
	/**
	 * Método para listar coordenadas percorridas por uma viatura
	 * @param String viatura
	 * @param boolen foraDeArea
	 * @return List<CoordenadaVO>
	 */
	private List<CoordenadaVO> listarPorViaturas(String viatura, boolean foraDeArea) {
		DAOCoordenada dCoordenada = new DAOCoordenada();
		return dCoordenada.getCoordenadasPorViatura(viatura, foraDeArea);
	}
	
	/**
	 * Método para listar coordenadas fora do setor
	 * @return List<CoordenadaVO>
	 */
	public List<CoordenadaVO> listarForaDoSetor(){
		DAOCoordenada dCoordenada = new DAOCoordenada();
		return dCoordenada.findByField("foraDeArea", "true");
	}
	
	/**
	 * Método para listar coordenadas dentro do setor
	 * @return List<CoordenadaVO>
	 */
	public List<CoordenadaVO> listarNoSetor(){
		DAOCoordenada dCoordenada = new DAOCoordenada();
		return dCoordenada.findByField("foraDeArea", "false");
	}
	
	

	/**
	 * Método que retornar o caminho real do diretorio  
	 * @param String diretorio
	 * @return String
	 */
	private String getDiretorioReal(String diretorio) { 
		HttpSession session = (HttpSession) 
		FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
		return session.getServletContext().getRealPath(diretorio); 
	} 
	
	/**
	 * Método que retorna o caminho do contexto atual
	 * @return String
	 */
	private String getContextPath() { 
		HttpSession session = (HttpSession) 
		FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
		return session.getServletContext().getContextPath(); 
	} 
	
	/**
	 * Método para preencher o pdf
	 * @param JasperPrint print
	 * @return String
	 * @throws JRException Exceção de run time ao prencher o relatório
	 */
	private String preenchePdf(JasperPrint print) throws JRException { 
		// pega o caminho completo do PDF desde a raiz 
		String saida = getDiretorioReal("/report/Relatorio.pdf"); 

		// Exportar para PDF 
		JasperExportManager.exportReportToPdfFile(print, saida); 
		
		//saida que será utilizada pelo bean para exibir o relatorio
		saida = getContextPath() + "/report/Relatorio.pdf";

		return saida;
		
	}

	/**
	 * Método para gerar relatório das rotas percorridas
	 * @param List<RotaPercorridaVO> rotas
	 * @param boolean foraDeArea
	 * @return String
	 */
	public String geraRelatorio(List<RotaPercorridaVO> rotas, Boolean foraDeArea){
		String jasper = getDiretorioReal("/report/Relatorio.jasper"); 
		
		String logotipo = getDiretorioReal("/images/spoor.jpg"); 
		String subTitulo = "Relatório com as rotas das viaturas";

		String caminhoDoPDF = null;

		if(foraDeArea){
			subTitulo = "Relatório com as rotas das viaturas que estiveram fora de área";
			
		}
		
		try { 
			Map<String, Serializable> map = new HashMap<String, Serializable>();
			map.put("imagem", logotipo);
			map.put("subTitulo", subTitulo);
			//adição do vetor de rotas no data source
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(rotas);
			JasperPrint print = JasperFillManager.fillReport(jasper, map, ds);
			// gera o pdf 
			caminhoDoPDF = preenchePdf(print); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 

		return caminhoDoPDF; 

	}
	
}
