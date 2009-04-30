package br.faculdadeidez.psa.businesslogic;

import java.util.ArrayList;
import java.util.List;

import br.faculdadeidez.psa.db.dao.DAOViatura;
import br.faculdadeidez.psa.servico.ComparacaoDistancia;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.MensagemValidacaoVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

public class ViaturaBusinessLogic {
	public String delete(ViaturaVO vo) {
		try {
			DAOViatura dViatura = new DAOViatura();
			if (dViatura.findByField("codigo", vo.getCodigo()).isEmpty())
				return "viaturaInexistente";
			ViaturaVO via = dViatura.find(vo.getCodigo());
			via.setAtivo(false);
			dViatura.update(via);
			return "removido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaRemover";
		}
	}

	public String update(ViaturaVO vo) {
		try {
			// valida os dados inseridos
			List<MensagemValidacaoVO> erros = validaDados(vo);

			if (!MensagemValidacao.isValido(erros))
				return MensagemValidacao.getMensagensValidacao(erros);

			DAOViatura dViatura = new DAOViatura();
			if (dViatura.findByField("codigo", vo.getCodigo()).isEmpty()
					&& vo.getCodigo() != null)
				return "viaturaInexistente";
			dViatura.update(vo);
			if (vo.getAtivo())
				return "atualizado";
			else
				return "atualizadoDeletado";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaAtualizar";
		}
	}

	public String create(ViaturaVO vo) {
		try {
			// valida os dados inseridos
			List<MensagemValidacaoVO> erros = validaDados(vo);

			if (!MensagemValidacao.isValido(erros))
				return MensagemValidacao.getMensagensValidacao(erros);

			DAOViatura dViatura = new DAOViatura();
			if (dViatura.findByField("codigo", vo.getCodigo()).isEmpty()) {
				dViatura.persist(vo);
				return "inserido";
			} else
				return "viaturaExistente";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaInserir";
		}
	}

	public List<ViaturaVO> listar() {
		DAOViatura dViatura = new DAOViatura();
		return dViatura.findAll();
	}
	
	public List<ViaturaVO> listarAtivos() {
		DAOViatura dViatura = new DAOViatura();
		return dViatura.findAllActivated();
	}

	public List<ViaturaVO> pesquisar(String valor) {

		DAOViatura dViatura = new DAOViatura();
		List<ViaturaVO> retorno = dViatura.findByField("codigo", valor);
		return retorno;
	}

	private List<MensagemValidacaoVO> validaDados(ViaturaVO vo) {
		ArrayList<MensagemValidacaoVO> erros = new ArrayList<MensagemValidacaoVO>();

		erros.add(new MensagemValidacaoVO("Código", "O código é obrigatório",
				Boolean.valueOf(vo.getCodigo().isEmpty())));
		erros.add(new MensagemValidacaoVO("Código",
				"O código deve ser menor que 4 dígitos", Boolean.valueOf(vo
						.getCodigo().length() > 4)));
		erros.add(new MensagemValidacaoVO("Código",
				"O código deve ser apenas dígitos", Boolean.valueOf(!vo
						.getCodigo().matches("^[0-9]*$"))));
		return erros;
	}
	
	
	public ViaturaVO find(String chave){

		return new DAOViatura().find(chave);
	}

	
	
	// TODO ajustar o método
	public CoordenadaVO getUltimaCoordenadaViatura(ViaturaVO viatura) {
		CoordenadaVO coord = viatura.getCoordenadas().get(viatura.getCoordenadas().size()-1);
		return coord;
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

	/**
	 * Descrição: Este método deve retornar a viatura mais próxima da coordenada
	 * passada, deve ser utilizado o Service criado por Theo para fazer os
	 * cálculos.
	 * 
	 * @param viaturasDesocupadas
	 * @param coordenadaDestino
	 * @return ViaturaVO
	 */
	public ViaturaVO getViaturaProxima(List<ViaturaVO> viaturasDesocupadas,
			CoordenadaVO coordenadasDestino) {

		ComparacaoDistancia c = new ComparacaoDistancia();
		ViaturaVO viaturaMaisProxima = null;
		double menorDistancia;

		c.setDestino("João Pessoa, PB");

		for (ViaturaVO vtr : viaturasDesocupadas) {
			// c.setOrigem();
			if (viaturaMaisProxima == null) {
				viaturaMaisProxima = vtr;
			} else {

			}
		}

		return null;
	}

	public ViaturaVO getViaturaProxima(CoordenadaVO coordenadasDestino) {
		return getViaturaProxima(getViaturasDesocupadas(), coordenadasDestino);
	}

	/**
	 * Sobrecarga do método getViaturasDesocupadas. Este método por padrão
	 * retorna as viaturas desocupadas de acordo com turno e escala
	 * [getViaturasEscalasTurno()]
	 * 
	 * @return
	 */
	public List<ViaturaVO> getViaturasDesocupadas() {
		List<ViaturaVO> viaturas = getViaturasEscalaTurno();
		return getViaturasDesocupadas(viaturas);
	}

	/**
	 * Retorna as viaturas desocupadas de acordo com a lista de viaturas
	 * passadas como parametro
	 * 
	 * @param viaturas
	 * @return
	 */
	public List<ViaturaVO> getViaturasDesocupadas(List<ViaturaVO> viaturas) {
		List<ViaturaVO> viaturasDesocupadas = new ArrayList<ViaturaVO>();

		for (ViaturaVO vtr : viaturas) {
			if (!vtr.getOcupada()) {
				viaturasDesocupadas.add(vtr);
			}
		}

		return viaturasDesocupadas;
	}

}
