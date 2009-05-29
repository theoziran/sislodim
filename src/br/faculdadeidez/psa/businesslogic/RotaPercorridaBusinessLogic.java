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

public class RotaPercorridaBusinessLogic {

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
			for (CoordenadaVO coordenadaVO : coordenadas) {
				if ((coordenadaVO.getData().after(dataInicio))
						&& (coordenadaVO.getData().before(dataFim))){
					rota = new RotaPercorridaVO();
					String horario = dtFormatHorario.format(coordenadaVO.getData().getTime()); 
					rota.setBairro(getBairro(coordenadaVO.getLatitude(),coordenadaVO.getLongitude()));
					rota.setViatura(coordenadaVO.getViatura());
					rota.setData(dtFormatData.format(coordenadaVO.getData().getTime()));
					rota.setHorario(horario);
					rotas.add(rota);

				}
			}

			return rotas;
			
	}	
			
	private List<CoordenadaVO> listarPorViaturas(String viatura, boolean foraDeArea) {
		DAOCoordenada dCoordenada = new DAOCoordenada();
		return dCoordenada.getCoordenadasPorViatura(viatura, foraDeArea);
	}

	public List<CoordenadaVO> listarForaDoSetor(){
		DAOCoordenada dCoordenada = new DAOCoordenada();
		return dCoordenada.findByField("foraDeArea", "true");
	}
	
	public List<CoordenadaVO> listarNoSetor(){
		DAOCoordenada dCoordenada = new DAOCoordenada();
		return dCoordenada.findByField("foraDeArea", "false");
	}

	public List<CoordenadaVO> listarPorViatura(String viatura, Boolean foraDeArea){
		DAOCoordenada dCoordenada = new DAOCoordenada();
		return dCoordenada.getCoordenadasPorViatura(viatura, foraDeArea);
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

	private String getDiretorioReal(String diretorio) { 
		HttpSession session = (HttpSession) 
		FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
		return session.getServletContext().getRealPath(diretorio); 
	} 
	
	private String getContextPath() { 
		HttpSession session = (HttpSession) 
		FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
		return session.getServletContext().getContextPath(); 
	} 
	
	private String preenchePdf(JasperPrint print) throws JRException { 
		// pega o caminho completo do PDF desde a raiz 
		String saida = getDiretorioReal("/relatorio/Relatorio.pdf"); 

		// Exportar para PDF 
		JasperExportManager.exportReportToPdfFile(print, saida); 
		
		//saida que será utilizada pelo bean para exibir o relatorio
		saida = getContextPath() + "/relatorio/Relatorio.pdf";

		return saida;
		
	}


	public String geraRelatorio(List<RotaPercorridaVO> rotas, Boolean foraDeArea){
		String jasper = getDiretorioReal("/relatorio/Relatorio.jasper"); 
		String caminhoDoPDF = null;
		
		String subTitulo = "Relatório com as rotas das viaturas";

		if(foraDeArea){
			subTitulo = "Relatório com as rotas das viaturas que estiveram fora de área";
			
		}
		
		try { 
			Map<String, Serializable> map = new HashMap<String, Serializable>();
			map.put("relatorio", jasper);
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
