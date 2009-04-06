package br.faculdadeidez.psa.businesslogic;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.faculdadeidez.psa.db.dao.DAOUsuario;
import br.faculdadeidez.psa.vo.UsuarioVO;

public class UsuarioBusinessLogic {

	public String logon(String login, String senha) {
		try {
			ArrayList<Boolean> errosCamposEmBranco = new ArrayList<Boolean>();
			errosCamposEmBranco.add(Boolean.valueOf(senha==null));
			errosCamposEmBranco.add(Boolean.valueOf(login==null));
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
					if (obj.getAtivo() == 1) {
						FacesContext context = FacesContext
								.getCurrentInstance();
						if (context != null){
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

	public String delete(int id) {
		try {
			DAOUsuario dUsuario = new DAOUsuario();
			UsuarioVO user = dUsuario.find(id);
			if (user == null)
				return "usuarioInexistente";
			user.setAtivo(0);
			dUsuario.update(user);
			return "removido";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "problemaRemover";
		}
	}

	public String update(UsuarioVO user) {
		try {
			if (user == null)
				return "usuarioInexistente";
			DAOUsuario dUsuario = new DAOUsuario();
			if (user.getSenha().equals("")) {
				user.setSenha(dUsuario.find(user.getId()).getSenha());
			}
			// valida os dados inseridos
			List<Boolean> erros = validaDados(user);
			if (erros.contains(Boolean.valueOf(true)))
				return "dadoInvalido";
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
			// TODO: handle exception
			return "problemaAtualizar";
		}
	}

	public String create(UsuarioVO user) {

		try {
			// valida os dados inseridos
			List<Boolean> erros = validaDados(user);
			if (erros.contains(Boolean.valueOf(true)))
				return "dadoInvalido";
			DAOUsuario dUsuario = new DAOUsuario();
			if (dUsuario.findByField("login", user.getLogin()).isEmpty()) {
				if (dUsuario.findByField("cpf", user.getCpf()).isEmpty()) {
					if (dUsuario.existsRg(user.getRg(), user.getOrgExpeditor())
							.isEmpty()) {
						user.setAtivo(1);
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
			// TODO: handle exception
			return "problemaInserir";
		}
	}

	public List<UsuarioVO> listar() {
		DAOUsuario dUsuario = new DAOUsuario();
		List<UsuarioVO> usuarios = dUsuario.findAll();
		return usuarios;

	}
	
	public List<UsuarioVO> listarAtivos(){
		DAOUsuario dUsuario = new DAOUsuario();
		return dUsuario.findAllActived();
	}

	public List<UsuarioVO> pesquisar(String valor) {
		DAOUsuario dUsuario = new DAOUsuario();
		List<UsuarioVO> retorno = dUsuario.findByField("nome", valor);
		retorno.addAll(dUsuario.findByField("login", valor));
		retorno.addAll(dUsuario.findByField("cpf", valor));
		retorno.addAll(dUsuario.findByField("rg", valor));
		return retorno;
	}

	private List<Boolean> validaDados(UsuarioVO user){
		ArrayList<Boolean> erros = new ArrayList<Boolean>();
		erros.add(Boolean.valueOf(user.getCpf().isEmpty()));
		erros.add(Boolean.valueOf(user.getLogin().isEmpty()));
		erros.add(Boolean.valueOf(user.getNome().isEmpty()));
		erros.add(Boolean.valueOf(user.getRg().isEmpty()));
		erros.add(Boolean.valueOf(user.getOrgExpeditor().isEmpty()));
		erros.add(Boolean.valueOf(user.getSenha().isEmpty()));
		erros.add(Boolean.valueOf(user.getLogin().matches("^[0-9]*$")));
		erros.add(Boolean.valueOf(user.getNome().matches("^[0-9]*$")));
		erros.add(Boolean.valueOf(!user.getNome().matches(
				"^[a-zA-Z¡¬√¿«… Õ”‘’⁄‹·‚„‡ÁÈÍÌÛÙı˙¸]*$")));
		return erros;
	}
	
	
}
