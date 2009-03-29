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
			DAOUsuario dUsuario = new DAOUsuario();
			List<UsuarioVO> usuarios = dUsuario.findByField("login", login);
			if (usuarios.isEmpty())
				throw new Exception();
			for (UsuarioVO obj : usuarios) {
				if (obj.getSenha().equals(senha)) {
					if (obj.getAtivo() == 1) {
						FacesContext context = FacesContext
								.getCurrentInstance();
						HttpSession session = (HttpSession) context
								.getExternalContext().getSession(false);
						session.setAttribute("logado", true);
						session.setAttribute("currentUser", obj);
						System.out.println("Logado com sucesso");
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
			if (user==null)
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
			DAOUsuario dUsuario = new DAOUsuario();
			UsuarioVO userFind = dUsuario.find(user.getId());
			if (userFind==null)
				return "usuarioInexistente";
			if (user.getSenha().equals("")) {
				user.setSenha(dUsuario.find(user.getId()).getSenha());
			}
			dUsuario.update(user);
			return "atualizado";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaAtualizar";
		}
	}

	public String create(UsuarioVO user) {
	

		try {
			//valida os dados inseridos
			ArrayList<Boolean> erros = new ArrayList<Boolean>();
			erros.add(Boolean.valueOf(user.getCpf().isEmpty()));
			erros.add(Boolean.valueOf(user.getLogin().isEmpty()));
			erros.add(Boolean.valueOf(user.getNome().isEmpty()));
			erros.add(Boolean.valueOf(user.getRg().isEmpty()));
			erros.add(Boolean.valueOf(user.getOrgExpeditor().isEmpty()));
			erros.add(Boolean.valueOf(user.getSenha().isEmpty()));
			erros.add(Boolean.valueOf(user.getLogin().matches("^[0-9]*$")));
			erros.add(Boolean.valueOf(user.getNome().matches("^[0-9]*$")));
			erros.add(Boolean.valueOf(!user.getNome().matches("^[a-zA-Z¡¬√¿«… Õ”‘’⁄‹·‚„‡ÁÈÍÌÛÙı˙¸]*$")));
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

	public List<UsuarioVO> pesquisar(String valor){
		DAOUsuario dUsuario = new DAOUsuario();
		List<UsuarioVO> retorno = dUsuario.findByField("nome", valor);
		retorno.addAll(dUsuario.findByField("login", valor));
		retorno.addAll(dUsuario.findByField("cpf", valor));
		retorno.addAll(dUsuario.findByField("rg", valor));
		return retorno;
	}

}
