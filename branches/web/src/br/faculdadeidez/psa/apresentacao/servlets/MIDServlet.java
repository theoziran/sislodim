package br.faculdadeidez.psa.apresentacao.servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import br.faculdadeidez.psa.businesslogic.CoordenadasBusinessLogic;
import br.faculdadeidez.psa.db.entity.Coordenada;
import br.faculdadeidez.psa.vo.CoordenadaVO;

/**
 * Servlet implementation class MIDServlet
 */
public class MIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MIDServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String codVtr = (String) request.getAttribute("codVtr");
		String latitude = (String) request.getAttribute("altitude");
		String longitude = (String) request.getAttribute("latitude");
		
		//pegar o bussnesslogic
		CoordenadaVO coo = new CoordenadaVO();
		
		//setar os valores
		coo.setLatitude(latitude);
		coo.setLongitude(longitude);
		//salvar no banco
		CoordenadasBusinessLogic cooBL = new CoordenadasBusinessLogic();
		cooBL.create(coo);

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

}
