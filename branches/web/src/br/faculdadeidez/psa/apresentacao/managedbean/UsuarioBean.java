package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.faculdadeidez.psa.vo.UsuarioVO;

/**
 * ManagedBean de usuário
 * 
 */
public class UsuarioBean extends GenericoBean {
	/**
	 * Representa a entidade usuário
	 */
	private UsuarioVO usuario = new UsuarioVO();
	/**
	 * Representa a lista de usuários
	 */
	private List<UsuarioVO> listaTudo;

	/**
	 * Construtor default
	 */
	public UsuarioBean() {
	}

	/**
	 * Método que efetua o logon do usuário
	 * 
	 * @return String
	 */
	public String logon() {
		String mensagem = getFachada().logon(getUsuario().getLogin(),
				getUsuario().getSenha());
		adicionaMensagemUsuario(mensagem);
		return mensagem;
	}

	/**
	 * Método que efetua o logout do usuário 
	 * É feito o processo reverso do
	 * logon, no atributo logado coloca falso no objeto do usuário na sessão
	 * coloco null e depois apago o mapeamento da sessão
	 * 
	 * @return String
	 */
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context != null) {
			HttpSession session = (HttpSession) context.getExternalContext()
					.getSession(false);
			session.setAttribute("logado", false);
			session.setAttribute("currentUser", null);
			context.getExternalContext().getSessionMap().clear();
			System.out.println("Logout efetuado com sucesso");
		}

		adicionaMensagemUsuario("Você saiu do sistema!");

		return "logout";
	}

	/**
	 * Método que envia os dados do usuário para ser deletado/desativado
	 * 
	 * @return String
	 */
	public String delete() {
		Object o = getElementoSelecionado();
		UsuarioVO usuarioDaVez = (UsuarioVO) o;
		String mensagem = getFachada().deleteUsuario(usuarioDaVez);
		adicionaMensagemUsuario(mensagem);

		// força atualização
		setListaTudo(null);

		return mensagem;
	}

	/**
	 * Método que envia os dados do usuário a ser atualizado
	 * 
	 * @return String
	 */
	public String update() {
		UsuarioVO usuarioDaVez = (UsuarioVO) getElementoSelecionado();
		String mensagem = getFachada().updateUsuario(usuarioDaVez);
		adicionaMensagemUsuario(mensagem);
		return mensagem;
	}

	/**
	 * Método que envia os dados para cadastrar o usuário
	 * 
	 * @return String
	 */
	public String create() {
		String mensagem = getFachada().createUsuario(getUsuario());
		adicionaMensagemUsuario(mensagem);
		return mensagem;
	}

	/**
	 * Método getter do atributo usuario
	 * 
	 * @return UsuarioVO
	 */
	public UsuarioVO getUsuario() {
		return usuario;
	}

	/**
	 * Método setter do atributo usuario
	 * 
	 * @param usuario
	 *            UsuarioVO
	 */
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	/**
	 * Método getter do atributo listaTudo
	 * 
	 * @return List<UsuarioVO>
	 */
	public List<UsuarioVO> getListaTudo() {
		if (listaTudo == null || canUpdate())
			setListaTudo(getFachada().listaUsuarios());
		return listaTudo;
	}

	/**
	 * Método setter do atributo listaTudo
	 * 
	 * @param listaTudo
	 *            List<UsuarioVO>
	 */
	public void setListaTudo(List<UsuarioVO> listaTudo) {
		this.listaTudo = listaTudo;
	}

	/**
	 * Método que envia os dados do usuário a ser pesquisado
	 */
	public void pesquisar() {
		if (getTermoPesquisa().isEmpty()) {
			setListaTudo(getFachada().listaUsuarios());
		} else {
			List<UsuarioVO> usuarios = getFachada().pesquisaUsuario(
					getTermoPesquisa());
			if (usuarios.isEmpty())
				adicionarMensagem("Usuário não encontrado");
			else {
				if (usuarios.size() > 1)
					adicionarMensagem("Foram encontrados " + usuarios.size()
							+ " resultados para a busca por "
							+ getTermoPesquisa());
				else
					adicionarMensagem("Foi encontrado " + usuarios.size()
							+ " resultado para a busca por "
							+ getTermoPesquisa());
				setListaTudo(usuarios);
			}
		}
	}

	/**
	 * Método que adiciona mensagens para serem mostradas ao usuário
	 * 
	 * @param mensagem
	 *            String
	 */
	private void adicionaMensagemUsuario(String mensagem) {
		if (mensagem.equals("atualizado"))
			adicionarMensagem("Atualizado com sucesso!");
		else if (mensagem.equals("usuarioInexistente")) {
			adicionarMensagem("Usuário não existe no banco de dados");
		} else if (mensagem.equals("dadoInvalido")) {
			adicionarMensagem("Estes dados não são válidos");
		} else if (mensagem.equals("cpfExistente")) {
			adicionarMensagem("Este CPF já está cadastrado!");
		} else if (mensagem.equals("rgExistente")) {
			adicionarMensagem("Este RG já está cadastrado!");
		} else if (mensagem.equals("usuarioExistente")) {
			adicionarMensagem("Este login já existe");
		} else if (mensagem.equals("problemaAtualizar")) {
			adicionarMensagem("Houve um problema ao tentar atualizar,\n contacte o administrador");
		} else if (mensagem.equals("problemaInserir")) {
			adicionarMensagem("Houve um problema ao tentar criar o usuário,\n contacte o administrador");
		} else if (mensagem.equals("inserido")) {
			adicionarMensagem("Cadastrado com sucesso!");
			setUsuario(new UsuarioVO());
		} else if (mensagem.equals("removido")) {
			adicionarMensagem("Deletado com sucesso!");
		} else if (mensagem.equals("problemaRemover")) {
			adicionarMensagem("Houve um problema ao tentar remover,\n contacte o administrador");
		} else if (mensagem.equals("logado")) {
			adicionarMensagem("Logado com sucesso");
			setUsuario(new UsuarioVO());
		} else if (mensagem.equals("camposEmBranco")) {
			adicionarMensagem("Não é permitido campos em branco");
		} else if (mensagem.equals("naoEncontrado")) {
			adicionarMensagem("Usuário ou senha inválido");
		} else {
			adicionarMensagem(mensagem);
		}
	}

}
