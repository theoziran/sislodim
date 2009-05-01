package br.faculdadeidez.psa.instalacao;

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
		 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("Configurando banco de dados...<br/>");

		Map property = new HashMap();
		property.put(EntityManagerFactoryProvider.DDL_GENERATION,
				EntityManagerFactoryProvider.DROP_AND_CREATE);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(
				"sislodim", property);

		response.getWriter().println("Atualizando schema...<br/>");
		UsuarioBusinessLogic bo = new UsuarioBusinessLogic();
		bo.logon("123", "123");

		response.getWriter().println("Criando primeiro usuário...<br/>");
		response.getWriter().println(
				bo.create(new UsuarioVO("Administrador", "admin", "123",
						"123456789", "123456", "SSP/PB", "3")));

		response.getWriter().println("Login: admin<br/>");
		response.getWriter().println("Senha: 123<br/>");
		response.getWriter().println("Nível: administrador<br/>");
		//System.out.println(getResourceFileAsString("bairros.sql"));
		
		 /* EntityManager manager= emf.createEntityManager(); Query q =
		  manager.createQuery(getResourceFileAsString("bairros.sql"));
		 q.executeUpdate();*/
		 
		response.getWriter().println("Banco de dados configurado!<br/>");

	}

	/*private String getResourceFileAsString(String resourcefilename) throws IOException {
		StringBuffer strB = new StringBuffer();
		
		 * InputStream in = getClass().getResourceAsStream(resourcefilename);
		 * try { String str; while (in.available()) { str = in.readLine();
		 * strB.append(str); } in.close(); } catch (IOException e) { } return
		 * strB.toString(); }
		 
		InputStream in = getClass().getResourceAsStream(resourcefilename);
		StringBuffer novoTexto = new StringBuffer();

		BufferedReader mysqlReader = new BufferedReader(new InputStreamReader( 
				in ));
		try {
			String linha = "";

			while (linha != null) {
				linha = mysqlReader.readLine();
				if (linha != null) {
					novoTexto.append(linha+"\n");
				}
			}
		} finally {
			mysqlReader.close();
		}
		return novoTexto.toString();
	}*/

}
