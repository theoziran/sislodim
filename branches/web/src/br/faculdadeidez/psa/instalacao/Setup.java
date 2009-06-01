package br.faculdadeidez.psa.instalacao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.toplink.essentials.ejb.cmp3.EntityManagerFactoryProvider;
import br.faculdadeidez.psa.businesslogic.UsuarioBusinessLogic;
import br.faculdadeidez.psa.vo.UsuarioVO;

/**
 * Classe responsavel por fazer a instalação do banco de dados e colocando o primeiro usuário
 */
@SuppressWarnings("serial")
public class Setup extends HttpServlet {

	/**
	 * Contrutor default
	 */
	public Setup() {
		super();
		 
	}

	/**
	 * Recebe os dados vindo por get
	 * Configura o banco de dados
	 * Cria o usuario admin
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Configurando banco de dados...<br/>");

		Map property = new HashMap();
		property.put(EntityManagerFactoryProvider.DDL_GENERATION,
				EntityManagerFactoryProvider.DROP_AND_CREATE);
		Persistence.createEntityManagerFactory(
                "sislodim", property);


		response.getWriter().println("Atualizando schema...<br/>");
		UsuarioBusinessLogic bo = new UsuarioBusinessLogic();
		bo.logon("123", "123");

		response.getWriter().println("Criando primeiro usuário...<br/>");
		response.getWriter().println(
				bo.create(new UsuarioVO("Administrador", "admin", "123",
						"75626335250", "123456", "SSP/PB", "3")));

		response.getWriter().println("Login: admin<br/>");
		response.getWriter().println("Senha: 123<br/>");
		response.getWriter().println("Nível: administrador<br/>");
		 
		response.getWriter().println("Banco de dados configurado!<br/>");

	}

}
