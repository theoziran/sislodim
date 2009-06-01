package br.faculdadeidez.psa.businesslogic;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOEscala;
import br.faculdadeidez.psa.db.dao.DAOSetor;
import br.faculdadeidez.psa.db.dao.DAOViatura;
import br.faculdadeidez.psa.vo.EscalaVO;
import br.faculdadeidez.psa.vo.SetorVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

/**
* Classe que implementa regras de negócio referente a Entidade Escala
* Abstrai a camada de persistencia JPA e realiza validações de negócio 
*/
public class EscalaBusinessLogic {
	/**
	 * Método para deletar um objeto Escala
	 * @param EscalaVO vo -> objeto a ser removido do banco
	 * @return String -> indica sucesso ou falha 
	 */
	public String delete(EscalaVO vo) {
		try {
			DAOEscala dEscala = new DAOEscala();
			EscalaVO esc = dEscala.find(vo.getCodigo());
			if (esc == null)
				return "escalaInexistente";
			vo.setAtivo(false);
			dEscala.update(vo);
			return "removido";
		} catch (Exception e) {
			 
			return "problemaRemover";
		}
	}
	
	/**
	 * Método para atualizar um objeto Escala
	 * @param EscalaVo vo
	 * @return String
	 */
	public String update(EscalaVO vo) {
		try {
			String validacoes = validacoes(vo);
			if(validacoes != null) return validacoes;
			
			DAOEscala dEscala = new DAOEscala();
			EscalaVO esc = dEscala.find(vo.getCodigo());
			if (esc == null)
				return "escalaInexistente";
			
			dEscala.update(vo);
			return "atualizado";
		} catch (Exception e) {
			 
			return "problemaAtualizar";
		}
	}
	
	/**
	 * Método para criar um objeto Escala
	 * @param EscalaVO vo
	 * @return String
	 */
	public String create(EscalaVO escala) {
		try {			
			String validacoes = validacoes(escala);
			if(validacoes != null) return validacoes;
			
			DAOEscala daoEscala = new DAOEscala();
			if (daoEscala.findByField("codigo",
					String.valueOf(escala.getCodigo())).isEmpty()) {
				DAOSetor daoSetor = new DAOSetor();
				SetorVO setor = daoSetor.find(escala.getSetor().getCodigo());
				escala.setSetor(setor);
				escala.setAtivo(true);
				daoEscala.persist(escala);
				return "inserido";
			} else
				return "escalaExistente";
		} catch (Exception e) {
			 
			return "problemaInserir";
		}
	}
	
	/**
	 * Método de validação;
	 * -Certifica que não haverá uma mesma viatura com escalas diferentes;
	 * -Garante que o periodo final da escala seja maior que o perido inicial
	 * 		Ex:  se (dataInicial > dataFinal) retorna periodo de escala inválido
	 * @param EscalaVO escala
	 * @return String
	 */
	private String validacoes(EscalaVO escala) {
		// verificação de datas
		if(escala.getDataFinal().before(escala.getDataInicial())) {
			return "datafim_ant_dataini";
		} else if(escala.getDataInicial().before(new Date(Calendar.ZONE_OFFSET))) { 
			return "dataini_ant_dataatual";
		} else {
			String noutraEscala = new DAOEscala().verificaViaturasNoutrasEscalasComMesmoDia(escala);
			
			if(noutraEscala != null)			
				return "mesma_viatura_escalas_diferentes|" + noutraEscala;
			else
				return null;
		}
	}
	
	/**
	 * Método para listar todos os Escala
	 * @return List<EscalaVO>
	 */
	public List<EscalaVO> listar() {
		DAOEscala dEscala = new DAOEscala();
		return dEscala.findAllActivated();
	}

	/**
	 * Método para buscar um objeto Escala id
	 * @param int chave 
	 * @return EscalaVO
	 */
	public List<EscalaVO> pesquisar(int valor) {
		DAOEscala dEscala = new DAOEscala();
		List<EscalaVO> retorno = dEscala.findByField("codigo", String
				.valueOf(valor));
		return retorno;
	}
	
	/**
	 * Método que retorna viaturas referente a escala
	 * @param EscalaVO escala 
	 * @return List<ViaturaVO>
	 */
	public List<ViaturaVO> listarViaturasEscala(EscalaVO escala){
		DAOViatura dViatura = new DAOViatura();
		List<ViaturaVO> retorno = dViatura.findViaturasEscala(escala);
		return retorno;
	}
	
	/**
	 * Método que retorna viaturas referente ao setor
	 * @param int setor -> codigo do setor
	 * @return List<ViaturaVO>
	 */
	public List<ViaturaVO> listarViaturasEscalaSetor(int setor){
		DAOViatura dViatura = new DAOViatura();
		List<ViaturaVO> retorno = dViatura.findViaturasEscalaSetor(setor);
		return retorno;
	}
	
	/**
	 * Este método deve retornar as viaturas ativas no horário em que o operador
	 * trabalha sendo dividido por turno. O método deve recuperar a hora do
	 * sistema e definir em qual turno a consulta foi efetuada, a partir disso
	 * buscar no banco as viaturas que estão escaladas naquele turno.
	 * 
	 * @return List<ViaturaVO>
	 */
	public List<ViaturaVO> getViaturasEscalaTurno() {
		DAOViatura dViatura = new DAOViatura();
		List<ViaturaVO> retorno = dViatura.findViaturasEscalaAtivas();
		return retorno;
	}

}
