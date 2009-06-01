package br.faculdadeidez.psa.businesslogic;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.faculdadeidez.psa.db.dao.DAOUsuario;
import br.faculdadeidez.psa.vo.MensagemValidacaoVO;
import br.faculdadeidez.psa.vo.UsuarioVO;
/**
* Classe que implementa regras de negócio referente a Entidade Usuario
* Abstrai a camada de persistencia JPA e realiza validações de negócio 
*/
public class UsuarioBusinessLogic {

	/**
	 * Método que realiza logon do usuário ao sistema 
	 * @param String login
	 * @param String senha
	 * @return String
	 */
	public String logon(String login, String senha) {
		try {
			ArrayList<Boolean> errosCamposEmBranco = new ArrayList<Boolean>();
			errosCamposEmBranco.add(Boolean.valueOf(senha == null));
			errosCamposEmBranco.add(Boolean.valueOf(login == null));
			if (errosCamposEmBranco.contains(Boolean.valueOf(true)))
				return "camposEmBranco";
			errosCamposEmBranco.add(Boolean.valueOf(senha.isEmpty()));
			errosCamposEmBranco.add(Boolean.valueOf(login.isEmpty()));
			if (errosCamposEmBranco.contains(Boolean.valueOf(true)))
				return "camposEmBranco";
			DAOUsuario dUsuario = new DAOUsuario();
			List<UsuarioVO> usuarios = dUsuario.findByField("login", login);
			if (usuarios.isEmpty())
				throw new Exception();
			for (UsuarioVO obj : usuarios) {
				if (obj.getSenha().equals(senha)) {
					if (obj.getAtivo() == true) {
						FacesContext context = FacesContext
								.getCurrentInstance();
						if (context != null) {
							HttpSession session = (HttpSession) context
									.getExternalContext().getSession(false);
							session.setAttribute("logado", true);
							session.setAttribute("currentUser", obj);
							System.out.println("Logado com sucesso");
						}
						return "logado";
					}
				}
			}
			throw new Exception();
		} catch (Exception e) {
			return "naoEncontrado";
		}
	}
	
	/**
	 * Método para deletar um objeto Usuario
	 * @param UsuarioVO vo -> objeto a ser removido do banco
	 * @return String -> indica sucesso ou falha 
	 */
	public String delete(int id) {
		try {
			DAOUsuario dUsuario = new DAOUsuario();
			UsuarioVO user = dUsuario.find(id);
			if (user == null)
				return "usuarioInexistente";
			user.setAtivo(false);
			dUsuario.update(user);
			return "removido";
		} catch (Exception e) {
			e.printStackTrace();
			return "problemaRemover";
		}
	}
	
	/**
	 * Método para atualizar um objeto Usuario
	 * @param UsuarioVo vo
	 * @return String
	 */
	public String update(UsuarioVO user) {
		try {
			if (user == null)
				return "usuarioInexistente";
			DAOUsuario dUsuario = new DAOUsuario();
			if (user.getSenha().equals("")) {
				user.setSenha(dUsuario.find(user.getId()).getSenha());
			}
			// valida os dados inseridos
			List<MensagemValidacaoVO> erros = validaDados(user);

			if (!MensagemValidacao.isValido(erros))
				return MensagemValidacao.getMensagensValidacao(erros);

			ArrayList<Boolean> errosUserInexistente = new ArrayList<Boolean>();

			UsuarioVO userFind = dUsuario.find(user.getId());
			errosUserInexistente.add(Boolean.valueOf(userFind == null));
			List<UsuarioVO> usuarios = dUsuario.findByField("login", user
					.getLogin());
			boolean loginExiste = false;
			if (!usuarios.isEmpty())
				if (usuarios.get(0).getId() != user.getId())
					loginExiste = true;
			errosUserInexistente.add(Boolean.valueOf(loginExiste));
			usuarios = dUsuario.findByField("cpf", user.getCpf());
			boolean cpfExiste = false;
			if (!usuarios.isEmpty())
				if (usuarios.get(0).getId() != user.getId())
					cpfExiste = true;
			if (cpfExiste)
				return "cpfExistente";
			boolean rgExiste = false;
			usuarios = dUsuario.existsRg(user.getRg(), user.getOrgExpeditor());
			if (!usuarios.isEmpty())
				if (usuarios.get(0).getId() != user.getId())
					rgExiste = true;
			if (rgExiste)
				return "rgExistente";
			if (!errosUserInexistente.contains(Boolean.valueOf(true))) {

				dUsuario.update(user);
				return "atualizado";
			} else {
				return "usuarioExistente";
			}
		} catch (Exception e) {
			return "problemaAtualizar";
		}
	}
	
	/**
	 * Método para criar um objeto Usuario
	 * @param UsuarioVO vo
	 * @return String
	 */
	public String create(UsuarioVO user) {

		try {
			// valida os dados inseridos
			List<MensagemValidacaoVO> erros = validaDados(user);

			if (!MensagemValidacao.isValido(erros))
				return MensagemValidacao.getMensagensValidacao(erros);

			// inserção
			DAOUsuario dUsuario = new DAOUsuario();
			if (dUsuario.findByField("login", user.getLogin()).isEmpty()) {
				if (dUsuario.findByField("cpf", user.getCpf()).isEmpty()) {
					if (dUsuario.existsRg(user.getRg(), user.getOrgExpeditor())
							.isEmpty()) {
						user.setAtivo(true);
						dUsuario.persist(user);
						return "inserido";
					} else {
						return "rgExistente";
					}
				} else {
					return "cpfExistente";
				}
			} else {
				return "usuarioExistente";
			}
		} catch (Exception e) {
			return "problemaInserir";
		}
	}
	
	/**
	 * Método para listar todos os Usuario
	 * @return List<UsuarioVO>
	 */
	public List<UsuarioVO> listar() {
		DAOUsuario dUsuario = new DAOUsuario();
		List<UsuarioVO> usuarios = dUsuario.findAll();
		return usuarios;

	}
	
	/**
	 * Método para listar todos os Usuario ativos
	 * @return List<UsuarioVO>
	 */
	public List<UsuarioVO> listarAtivos() {
		DAOUsuario dUsuario = new DAOUsuario();
		return dUsuario.findAllActived();
	}

	/**
	 * Método para listar Usuario por valor
	 * @param String valor
	 * @return List<UsuarioVO>
	 */
	public List<UsuarioVO> pesquisar(String valor) {
		DAOUsuario dUsuario = new DAOUsuario();
		List<UsuarioVO> retorno = dUsuario.findByField("nome", valor);
		retorno.addAll(dUsuario.findByField("login", valor));
		retorno.addAll(dUsuario.findByField("cpf", valor));
		retorno.addAll(dUsuario.findByField("rg", valor));
		return retorno;
	}
	
	/**
	 * Método de validação;
	 * -Garante que os campos obrigatórios foram preenchidos
	 * @param UsuarioVO user
	 * @return List<MensagemValidacaoVO>
	 */
	private List<MensagemValidacaoVO> validaDados(UsuarioVO user) {
		ArrayList<MensagemValidacaoVO> erros = new ArrayList<MensagemValidacaoVO>();

		erros.add(new MensagemValidacaoVO("Nome", "O nome é obrigatório",
				Boolean.valueOf(user.getNome().isEmpty())));
		erros.add(new MensagemValidacaoVO("RG", "O RG é obrigatório", Boolean
				.valueOf(user.getRg().isEmpty())));
		erros.add(new MensagemValidacaoVO("Órgão Expeditor",
				"O Órgão Expedidor é obrigatório", Boolean.valueOf(user
						.getOrgExpeditor().isEmpty())));
		erros.add(new MensagemValidacaoVO("Senha", "A senha é obrigatória",
				Boolean.valueOf(user.getSenha().isEmpty())));
		erros.add(new MensagemValidacaoVO("CPF", "O CPF é obrigatório", Boolean
				.valueOf(user.getCpf().isEmpty())));
		erros.add(new MensagemValidacaoVO("Login", "O login é obrigatório",
				Boolean.valueOf(user.getLogin().isEmpty())));
		erros.add(new MensagemValidacaoVO("Login",
				"O login não pode conter só números", Boolean.valueOf(user
						.getLogin().matches("^[0-9]*$"))));
		erros.add(new MensagemValidacaoVO("CPF", "O CPF digitado é inválido",
				!validaCpf(user.getCpf())));
		erros.add(new MensagemValidacaoVO("Nome",
				"O nome não pode conter só números", Boolean.valueOf(user
						.getNome().matches("^[0-9]*$"))));
		erros.add(new MensagemValidacaoVO("Nome",
				"O nome contém caracteres inválidos", Boolean.valueOf(!user
						.getNome().matches(
								"^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü\\s]*$"))));
		return erros;
	}
	
	/**
	 * Método para validar CPF
	 * @param Stringcpf
	 * @return boolean
	 */
	private boolean validaCpf(String cpf) {

		if (cpf == null)
			return false;

		String n = cpf.replaceAll("[^0-9]*", "");
		boolean isCpf = n.length() == 11;

		if (!isCpf)
			return false;

		if (n.equals("00000000000") || n.equals("11111111111")
				|| n.equals("22222222222") || n.equals("33333333333")
				|| n.equals("44444444444") || n.equals("55555555555")
				|| n.equals("66666666666") || n.equals("77777777777")
				|| n.equals("88888888888") || n.equals("99999999999")
				|| n.equals("12345678909")) {
			return false;
		}

		int i;
		int j;
		int digit;
		int coeficient;
		int sum;
		int[] foundDv = { 0, 0 };
		int dv1 = Integer.parseInt(String.valueOf(n.charAt(n.length() - 2)));
		int dv2 = Integer.parseInt(String.valueOf(n.charAt(n.length() - 1)));

		for (j = 0; j < 2; j++) {
			sum = 0;
			coeficient = 2;
			for (i = n.length() - 3 + j; i >= 0; i--) {
				digit = Integer.parseInt(String.valueOf(n.charAt(i)));
				sum += digit * coeficient;
				coeficient++;
			}
			foundDv[j] = 11 - sum % 11;
			if (foundDv[j] >= 10)
				foundDv[j] = 0;
		}
		return dv1 == foundDv[0] && dv2 == foundDv[1];
	}

}
