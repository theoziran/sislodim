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

public class Fachada {

	private static Fachada INSTANCE;

	private Fachada() {

	}

	public static Fachada getFachada() {
		if (INSTANCE == null) {
			INSTANCE = new Fachada();
			return INSTANCE;
		}
		return INSTANCE;
	}

	/*
	 * M�todos para os Usu�rios
	 */

	public String logon(String login, String senha) {
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.logon(login, senha);
	}

	public String deleteUsuario(UsuarioVO userVO) {

		UsuarioBusinessLogic logica = new UsuarioBusinessLogic();
		return logica.delete(userVO.getId());
	}

	public String updateUsuario(UsuarioVO userVO) {

		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.update(userVO);
	}

	public String createUsuario(UsuarioVO userVO) {
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.create(userVO);
	}

	public List<UsuarioVO> listaUsuarios() {
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.listarAtivos();

	}

	public List<UsuarioVO> pesquisaUsuario(String nome) {
		UsuarioBusinessLogic logicaUsuario = new UsuarioBusinessLogic();
		return logicaUsuario.pesquisar(nome);

	}

	/*
	 * M�todos para os Setores
	 */

	public String deleteSetor(SetorVO vo) {
		return new SetorBusinessLogic().delete(vo);
	}

	public String updateSetor(SetorVO vo) {
		return new SetorBusinessLogic().update(vo);
	}

	public String createSetor(SetorVO vo) {
		return new SetorBusinessLogic().create(vo);
	}

	public List<SetorVO> listarSetores() {
		return new SetorBusinessLogic().listarAtivos();
	}

	public List<SetorVO> pesquisaSetor(String nome) {
		SetorBusinessLogic logicaSetor = new SetorBusinessLogic();
		return logicaSetor.pesquisar(nome);
	}
	
	public SetorVO pesquisaSetor(int codigo) {
		SetorBusinessLogic logicaSetor = new SetorBusinessLogic();
		return logicaSetor.find(codigo);
	}

	/*
	 * M�todos para as Viaturas
	 */

	public String deleteViatura(ViaturaVO vo) {
		return new ViaturaBusinessLogic().delete(vo);
	}

	public String updateViatura(ViaturaVO vo) {
		return new ViaturaBusinessLogic().update(vo);
	}

	public String createViatura(ViaturaVO vo) {
		return new ViaturaBusinessLogic().create(vo);
	}

	public List<ViaturaVO> listarViaturas() {
		return new ViaturaBusinessLogic().listarAtivos();
	}

	public List<ViaturaVO> pesquisaViaturas(String codigo) {
		ViaturaBusinessLogic logicaViatura = new ViaturaBusinessLogic();
		return logicaViatura.pesquisar(codigo);
	}

	public ViaturaVO pesquisaViatura(String codigo) {
		return new ViaturaBusinessLogic().find(codigo);
	}

	public List<ViaturaVO> pesquisarViaturasEscalaTurno() {
		EscalaBusinessLogic logicaEscala = new EscalaBusinessLogic();
		return logicaEscala.getViaturasEscalaTurno();
	}

	

	public GoogleMaps calculaViaturaMaisProxima(String destino) {
		CoordenadasBusinessLogic logicaCoordenada = new CoordenadasBusinessLogic();
		return logicaCoordenada.calculaViaturaMaisProxima(destino);
	}

	/*
	 * M�todos para as Escalas
	 */

	public String deleteEscala(EscalaVO vo) {
		return new EscalaBusinessLogic().delete(vo);
	}

	public String updateEscala(EscalaVO vo) {
		return new EscalaBusinessLogic().update(vo);
	}


	public String createEscala(EscalaVO vo) {

		return new EscalaBusinessLogic().create(vo);
	}

	public List<EscalaVO> listarEscalas() {
		return new EscalaBusinessLogic().listar();
	}

	public List<EscalaVO> pesquisaEscala(int codigo) {
		EscalaBusinessLogic logicaEscala = new EscalaBusinessLogic();
		return logicaEscala.pesquisar(codigo);
	}

	public List<ViaturaVO> listarViaturasEscala(EscalaVO escala) {
		EscalaBusinessLogic logicaEscala = new EscalaBusinessLogic();
		return logicaEscala.listarViaturasEscala(escala);
	}
	
	/**
	 * Retorna a lista de todas as viaturas da escala ativa do setor passado
	 * 
	 * @param escala
	 * @return
	 */
	public List<ViaturaVO> listarViaturasEscalaSetor(int setor) {
		EscalaBusinessLogic logicaEscala = new EscalaBusinessLogic();
		return logicaEscala.listarViaturasEscalaSetor(setor);
	}

	/**
	 * In�cio da parte dos bairros
	 * 
	 * @return lista de bairros cadastrados
	 */
	public List<BairroVO> listarBairros() {
		BairroBusinessLogic logicaBairro = new BairroBusinessLogic();
		return logicaBairro.listar();
	}

	public BairroVO pesquisaBairro(int chave) {
		return new BairroBusinessLogic().find(chave);
	}

	public CoordenadaVO getUltimaCoordenadaViatura(ViaturaVO viatura) {
		CoordenadasBusinessLogic logicaCoordenada = new CoordenadasBusinessLogic();
		return logicaCoordenada.getUltimaCoordenadaViatura(viatura);
	}
	
	public CoordenadaVO getUltimaCoordenadaViaturaSetor(int setor) {
		CoordenadasBusinessLogic logicaCoordenada = new CoordenadasBusinessLogic();
		return logicaCoordenada.getUltimaCoordenadaViaturaSetor(setor);
	}

	public BairroVO pesquisaBairroNome(String nome) {
		if (nome.isEmpty())
			return new BairroVO();
		BairroBusinessLogic logicaBairro = new BairroBusinessLogic();
		return logicaBairro.findNome(nome);
	}

	/*
	 * M�todos para Relat�rio de Rotas Percorridas
	 */
	public List<RotaPercorridaVO> listarRotas(Calendar dataInicio, Calendar dataFim, Boolean foraDeSetor) {
		return new RotaPercorridaBusinessLogic().listar(dataInicio, dataFim, foraDeSetor);
	}
	
	public String gerarRelatorio(List<RotaPercorridaVO> rotas, boolean foraDeArea){
		String caminhoDoPDF = null;
		RotaPercorridaBusinessLogic rota = new RotaPercorridaBusinessLogic();
		
		caminhoDoPDF = rota.geraRelatorio(rotas, foraDeArea);
		
		return caminhoDoPDF;
		
		
	}

}
