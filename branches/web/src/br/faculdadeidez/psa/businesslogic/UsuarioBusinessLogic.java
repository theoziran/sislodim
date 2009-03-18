package br.faculdadeidez.psa.businesslogic;

import java.util.List;

import br.faculdadeidez.psa.db.DAOUsuario;
import br.faculdadeidez.psa.entity.Usuario;

public class UsuarioBusinessLogic {

	public String logar(String nome, String senha) {
		try {
			DAOUsuario dUsuario = new DAOUsuario();
			List<Usuario> usuarios = dUsuario.findByField("nome", nome);
			if (usuarios.isEmpty())
				throw new Exception();
			for (Usuario obj : usuarios) {
				if (obj.getSenha().equals(senha))
					return "logado";
			}
			throw new Exception();
		} catch (Exception e) {
			System.out.println("Usuário não existe ou senha incorreta");
			return "naoEncontrado";
		}
	}

	public String excluir(Usuario user){
		try {
			DAOUsuario dUsuario = new DAOUsuario();
			dUsuario.remove(user);
			return "removido";
		} catch (Exception e) {
			// TODO: handle exception
			return "problemaRemover";
		}
	}
	
	
}
