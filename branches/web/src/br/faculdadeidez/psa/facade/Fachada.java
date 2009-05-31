package br.faculdadeidez.psa.facade;

import java.util.Calendar;
import java.util.List;

import br.faculdadeidez.psa.businesslogic.BairroBusinessLogic;
import br.faculdadeidez.psa.businesslogic.CoordenadasBusinessLogic;
import br.faculdadeidez.psa.businesslogic.EscalaBusinessLogic;
import br.faculdadeidez.psa.businesslogic.RotaPercorridaBusinessLogic;
import br.faculdadeidez.psa.businesslogic.SetorBusinessLogic;
import br.faculdadeidez.psa.businesslogic.UsuarioBusinessLogic;
import br.faculdadeidez.psa.businesslogic.ViaturaBusinessLogic;
import br.faculdadeidez.psa.servico.GoogleMaps;
import br.faculdadeidez.psa.vo.BairroVO;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.EscalaVO;
import br.faculdadeidez.psa.vo.RotaPercorridaVO;
import br.faculdadeidez.psa.vo.SetorVO;
import br.faculdadeidez.psa.vo.UsuarioVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

/**
 * Classe que contém todos os métodos de acesso à regra de negócio
 *
 */
public class Fachada {

	/**
	 * Representa a instância da fachada
	 */
	private static Fachada INSTANCE;

	/**
	 * Construtor default da fachada
	 */
	private Fachada() {

	}

	/**
	 * Método que constroi a fachada caso não exista
	 * @return Fachada
	 */
	public static Fachada getFachada() {
		if (INSTANCE == null) {
			INSTANCE = new Fachada();
			return INSTANCE;
		}
		return INSTANCE;
	}

	/**
	 * Método que faz logon no sistema
	 * @param login String
	 * @param senha String
	 * @return String
	 */
	public String logon(String login, String senha) {
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.logon(login, senha);
	}

	/**
	 * Método que repassa os dados do usuário a ser deletado
	 * @param userVO UsuarioVO
	 * @return String
	 */
	public String deleteUsuario(UsuarioVO userVO) {

		UsuarioBusinessLogic logica = new UsuarioBusinessLogic();
		return logica.delete(userVO.getId());
	}

	/**
	 * Método que repassa os dados do usuário para se atualizado
	 * @param userVO UsuarioVO
	 * @return String
	 */
	public String updateUsuario(UsuarioVO userVO) {

		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.update(userVO);
	}

	/**
	 * Método que passa os dados do usuário para a regra de negócio onde é criado
	 * @param userVO UsuarioVO
	 * @return String
	 */
	public String createUsuario(UsuarioVO userVO) {
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.create(userVO);
	}

	/**
	 * Retorna  a lista de usuários
	 * @return List<UsuarioVO>
	 */
	public List<UsuarioVO> listaUsuarios() {
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.listarAtivos();

	}

	/**
	 * Método que passa o nome do usuário para ser pesquisado 
	 * @param nome String
	 * @return List<UsuarioVO>
	 */
	public List<UsuarioVO> pesquisaUsuario(String nome) {
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.pesquisar(nome);

	}

	/**
	 * Método que passa os dados do setor a ser deletado 
	 * @param vo SetorVO
	 * @return String
	 */
	public String deleteSetor(SetorVO vo) {
		return new SetorBusinessLogic().delete(vo);
	}

	/**
	 * Método que passa os dados de setor a ser atualizado
	 * @param vo SetorVO
	 * @return String
	 */
	public String updateSetor(SetorVO vo) {
		return new SetorBusinessLogic().update(vo);
	}

	/**
	 * Método que passa os dados de setor para ser validado na regra de negócio e criado
	 * @param vo SetorVO
	 * @return String
	 */
	public String createSetor(SetorVO vo) {
		return new SetorBusinessLogic().create(vo);
	}

	/**
	 * Método que retorna os setores ativos
	 * @return List<Setor>
	 */
	public List<SetorVO> listarSetores() {
		return new SetorBusinessLogic().listarAtivos();
	}

	/**
	 * Método que passa o nome do setor a ser pesquisado
	 * @param nome String
	 * @return List<SetorVO>
	 */
	public List<SetorVO> pesquisaSetor(String nome) {
		SetorBusinessLogic logicaSetor = new SetorBusinessLogic();
		return logicaSetor.pesquisar(nome);
	}
	/**
	 * Método que passa o código de um setor a ser pesquisado
	 * @param codigo int
	 * @return SetorVO
	 */
	public SetorVO pesquisaSetor(int codigo) {
		SetorBusinessLogic logicaSetor = new SetorBusinessLogic();
		return logicaSetor.find(codigo);
	}



	/**
	 * Método que passa os dados de viatura a ser deletada
	 * @param vo ViaturaVO
	 * @return String
	 */
	public String deleteViatura(ViaturaVO vo) {
		return new ViaturaBusinessLogic().delete(vo);
	}

	/**
	 * Método que passa os dados para a viatura ser atualizada
	 * @param vo ViaturaVO
	 * @return String
	 */
	public String updateViatura(ViaturaVO vo) {
		return new ViaturaBusinessLogic().update(vo);
	}

	/**
	 * Método que passa os dados para a viatura ser criada
	 * @param vo ViaturaVO
	 * @return String
	 */
	public String createViatura(ViaturaVO vo) {
		return new ViaturaBusinessLogic().create(vo);
	}

	/**
	 * Método que retorna a lista de viaturas ativas
	 * @return List<ViaturaVO>
	 */
	public List<ViaturaVO> listarViaturas() {
		return new ViaturaBusinessLogic().listarAtivos();
	}

	/**
	 * Método que passa o código da viatura a ser pesquisado
	 * pesquisa parcial de codigo
	 * @param codigo String
	 * @return List<ViaturaVO>
	 */
	public List<ViaturaVO> pesquisaViaturas(String codigo) {
		ViaturaBusinessLogic logicaViatura = new ViaturaBusinessLogic();
		return logicaViatura.pesquisar(codigo);
	}

	/**
	 * Método que passa o código da viatura a ser pesquisado
	 * @param codigo String
	 * @return ViaturaVO
	 */
	public ViaturaVO pesquisaViatura(String codigo) {
		return new ViaturaBusinessLogic().find(codigo);
	}

	/**
	 * Método que retorna as viaturas escaladas
	 * @return List<ViaturaVO>
	 * 
	 */
	public List<ViaturaVO> pesquisarViaturasEscalaTurno() {
		EscalaBusinessLogic logicaEscala = new EscalaBusinessLogic();
		return logicaEscala.getViaturasEscalaTurno();
	}

	/**
	 * Método que retorna os dados da viatura mais próxima do destino pesquisado
	 * @param destino String
	 * @return GoogleMaps
	 */

	public GoogleMaps calculaViaturaMaisProxima(String destino) {
		CoordenadasBusinessLogic logicaCoordenada = new CoordenadasBusinessLogic();
		return logicaCoordenada.calculaViaturaMaisProxima(destino);
	}


	/**
	 * Método que passa os dados da escala a ser deletada
	 * @param vo EscalaVO
	 * @return String
	 */
	public String deleteEscala(EscalaVO vo) {
		return new EscalaBusinessLogic().delete(vo);
	}

	/**
	 * Método que passa os dados da escala a ser atualizada
	 * @param vo EscalaVO
	 * @return String
	 */
	public String updateEscala(EscalaVO vo) {
		return new EscalaBusinessLogic().update(vo);
	}


	/**
	 * Método que passa os dados da escala a ser criada de acordo com a regra de negócio
	 * @param vo EscalaVO
	 * @return String
	 */
	public String createEscala(EscalaVO vo) {

		return new EscalaBusinessLogic().create(vo);
	}

	/**
	 * Método que retorna a lista de escalas ativas
	 * @return List<EscalaVO>
	 */
	public List<EscalaVO> listarEscalas() {
		return new EscalaBusinessLogic().listar();
	}

	/**
	 * Método que pesquisa nas escalas cadastradas o codigo passado
	 * @param codigo int
	 * @return List<EscalaVO>
	 */
	public List<EscalaVO> pesquisaEscala(int codigo) {
		EscalaBusinessLogic logicaEscala = new EscalaBusinessLogic();
		return logicaEscala.pesquisar(codigo);
	}

	/**
	 * Método que passa os dados da escala a ser pesquisada para extrair as viaturas
	 * @param escala EscalaVO
	 * @return List<ViaturaVO>
	 */
	public List<ViaturaVO> listarViaturasEscala(EscalaVO escala) {
		EscalaBusinessLogic logicaEscala = new EscalaBusinessLogic();
		return logicaEscala.listarViaturasEscala(escala);
	}
	
	/**
	 * Método que retorna as viaturas escaladas de um determinado setor
	 * @param setor int
	 * @return List<ViaturaVO>
	 */
	public List<ViaturaVO> listarViaturasEscalaSetor(int setor) {
		EscalaBusinessLogic logicaEscala = new EscalaBusinessLogic();
		return logicaEscala.listarViaturasEscalaSetor(setor);
	}

	/**
	 * Método que retorna a lista de bairros cadastrados
	 * @return List<BairroVO>
	 */
	public List<BairroVO> listarBairros() {
		BairroBusinessLogic logicaBairro = new BairroBusinessLogic();
		return logicaBairro.listar();
	}

	/**
	 * Método que passa o identificador do bairro a ser pesquisado
	 * @param chave int
	 * @return BairroVO
	 */
	public BairroVO pesquisaBairro(int chave) {
		return new BairroBusinessLogic().find(chave);
	}

	/**
	 * Método que passa os dados da viatura para pesquisar a ultima coordenaada enviada por ela
	 * @param viatura ViaturaVO
	 * @return
	 */
	public CoordenadaVO getUltimaCoordenadaViatura(ViaturaVO viatura) {
		CoordenadasBusinessLogic logicaCoordenada = new CoordenadasBusinessLogic();
		return logicaCoordenada.getUltimaCoordenadaViatura(viatura);
	}
	
	/**
	 * Método que passa os dados do setor para ser pesquisado a ultima coordenada enviada por uma viatura
	 * naquele setor
	 * @param setor int
	 * @return CoordenadaVO
	 */
	public CoordenadaVO getUltimaCoordenadaViaturaSetor(int setor) {
		CoordenadasBusinessLogic logicaCoordenada = new CoordenadasBusinessLogic();
		return logicaCoordenada.getUltimaCoordenadaViaturaSetor(setor);
	}

	/**
	 * Método que passa os dados de um bairro para pesquisar pelo nome
	 * @param nome String
	 * @return BairroVO
	 */
	public BairroVO pesquisaBairroNome(String nome) {
		if (nome.isEmpty())
			return new BairroVO();
		BairroBusinessLogic logicaBairro = new BairroBusinessLogic();
		return logicaBairro.findNome(nome);
	}

	/**
	 * Método que passa os dados das rotas a serem pesquisadas
	 * @param dataInicio Calendar
	 * @param dataFim Calendar
	 * @param foraDeSetor boolean
	 * @param viatura String
	 * @return List<RotaPercorridaVO>
	 */
	public List<RotaPercorridaVO> listarRotas(Calendar dataInicio, Calendar dataFim, Boolean foraDeSetor, String viatura) {
		return new RotaPercorridaBusinessLogic().listar(dataInicio, dataFim, foraDeSetor, viatura);
	}
	
	/**
	 * Método que passa os dados das rotas para ser gerado o relatório
	 * @param rotas List<RotaPercorridaVO>
	 * @param foraDeArea boolean
	 * @return String
	 */
	public String gerarRelatorio(List<RotaPercorridaVO> rotas, boolean foraDeArea){
		String caminhoDoPDF = null;
		RotaPercorridaBusinessLogic rota = new RotaPercorridaBusinessLogic();
		
		caminhoDoPDF = rota.geraRelatorio(rotas, foraDeArea);
		
		return caminhoDoPDF;
		
		
	}

}
