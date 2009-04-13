package br.faculdadeidez.psa.apresentacao.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.toplink.essentials.ejb.cmp3.EntityManagerFactoryProvider;
import br.faculdadeidez.psa.businesslogic.UsuarioBusinessLogic;
import br.faculdadeidez.psa.vo.UsuarioVO;

/**
 * Servlet implementation class Setup
 */
public class Setup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Setup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Configurando banco de dados...");
		
		Map property = new HashMap();
		property.put(EntityManagerFactoryProvider.DDL_GENERATION, EntityManagerFactoryProvider.DROP_AND_CREATE);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sislodim", property);
		
		response.getWriter().println("Atualizando schema...");
		UsuarioBusinessLogic bo = new UsuarioBusinessLogic();
		bo.logon("123", "123");
		
		response.getWriter().println("Criando primeiro usuário...");
		response.getWriter().println(bo.create(new UsuarioVO("Administrador", "admin", "123", "123456789", "123456", "SSP/PB", "3")));
		
		response.getWriter().println("login: admin");
		response.getWriter().println("senha: 123");
		response.getWriter().println("nível: administrador");
		
		response.getWriter().println("Banco de dados configurado!");
	}

}
