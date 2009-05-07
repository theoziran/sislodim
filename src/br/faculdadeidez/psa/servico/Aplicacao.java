package br.faculdadeidez.psa.servico;

import br.faculdadeidez.psa.businesslogic.CoordenadasBusinessLogic;
import br.faculdadeidez.psa.businesslogic.EscalaBusinessLogic;
import br.faculdadeidez.psa.businesslogic.ViaturaBusinessLogic;
import br.faculdadeidez.psa.db.dao.DAOCoordenada;
import br.faculdadeidez.psa.db.dao.DAOViatura;
import br.faculdadeidez.psa.vo.BairroVO;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;


public class Aplicacao {
	public static void main(String[] args) throws ComparacaoDistanciaException {
		System.out.println("Iniciando serviços...");
		
		// inicia o primeiro serviço
		servicoVerificaCoordenadas();
	}
	
	/*
	 * 
	 */
	private static void servicoVerificaCoordenadas() {		
		CoordenadasBusinessLogic coord = new CoordenadasBusinessLogic();
		ViaturaBusinessLogic via = new ViaturaBusinessLogic();
		EscalaBusinessLogic esc = new EscalaBusinessLogic();
		
		System.out.println("Iniciando serviço de verificação de viaturas fora de setor");
				
		for(CoordenadaVO vo : coord.listarCoordenadasNaoVerificadas()) {
			// para cada coordenada que não foi verificada, chega se a viatura saiu do seu setor
			
			RetornaEndereco re = new RetornaEndereco();
			re.setLatitude(vo.getLatitude());
			re.setLongitude(vo.getLongitude());
			
			try { 
				String bairroAtual = re.getBairro(re.PercorrerXml(re.receberXml()));
				
				if(bairroAtual != null) { 			
					boolean saiuBairro = true;
									
					// pega os bairros do setor da escala desta viatura
					for(BairroVO bairro : via.listaBairrosSetorEscalaViatura(vo.getViatura().getCodigo(), vo.getData())) {
						if(bairro.getNome().toLowerCase().equals(bairroAtual)) { 
							saiuBairro = false;
							break;
						}
					}	
					
					if(saiuBairro) {
						// modifica a coordenada					
						vo.setForaDeArea(true);
						vo.setProcessadoVerificacao(true);
						coord.update(vo);						
						System.out.println("Viatura '"+ vo.getViatura().getCodigo() +"' saiu de sua rota");
					}
				}
			}
			catch(Exception exc) { 
				vo.setProcessadoVerificacao(true);
				coord.update(vo);
				
				System.out.println("Não foi possível consultar informações sobre a coordeada "+ vo.getLatitude() +"," + vo.getLongitude() + " da viatura '"+ vo.getViatura() +"'");
			}
		}
		
		System.out.println("Terminado serviço de consulta de viaturas fora de setor");	
	}
}
