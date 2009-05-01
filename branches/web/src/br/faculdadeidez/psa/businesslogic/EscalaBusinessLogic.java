package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOEscala;
import br.faculdadeidez.psa.db.dao.DAOSetor;
import br.faculdadeidez.psa.db.dao.DAOViatura;
import br.faculdadeidez.psa.vo.EscalaVO;
import br.faculdadeidez.psa.vo.SetorVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class EscalaBusinessLogic {
	public String delete(EscalaVO vo) {
		try {
			DAOEscala dEscala = new DAOEscala();
			EscalaVO esc = dEscala.find(vo.getCodigo());
			if (esc == null)
				return "escalaInexistente";
			dEscala.remove(esc);
			return "removido";
		} catch (Exception e) {
			 
			return "problemaRemover";
		}
	}

	public String update(EscalaVO vo) {
		try {
			DAOEscala dEscala = new DAOEscala();
			dEscala.update(vo);
			return "atualizado";
		} catch (Exception e) {
			 
			return "problemaAtualizar";
		}
	}

	public String create(EscalaVO escala) {
		try {
			DAOEscala daoEscala = new DAOEscala();
			if (daoEscala.findByField("codigo",
					String.valueOf(escala.getCodigo())).isEmpty()) {
				DAOSetor daoSetor = new DAOSetor();
				SetorVO setor = daoSetor.find(escala.getSetor().getCodigo());
				escala.setSetor(setor);
				
				daoEscala.persist(escala);
				return "inserido";
			} else
				return "escalaExistente";
		} catch (Exception e) {
			 
			return "problemaInserir";
		}
	}

	public List<EscalaVO> listar() {
		DAOEscala dEscala = new DAOEscala();
		return dEscala.findAll();
	}

	public List<EscalaVO> pesquisar(int valor) {
		DAOEscala dEscala = new DAOEscala();
		List<EscalaVO> retorno = dEscala.findByField("codigo", String
				.valueOf(valor));
		return retorno;
	}
	
	/**
	 * Este método deve retornar as viaturas ativas no horário em que o operador
	 * trabalha sendo dividido por turno. O método deve recuperar a hora do
	 * sistema e definir em qual turno a consulta foi efetuada, a partir disso
	 * buscar no banco as viaturas que estão escaladas naquele turno.
	 * 
	 * @return
	 */
	public List<ViaturaVO> getViaturasEscalaTurno() {
		DAOViatura dViatura = new DAOViatura();
		List<ViaturaVO> retorno = dViatura.findViaturasEscalaAtivas();
		return retorno;
	}

}
