package br.faculdadeidez.psa.apresentacao.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.faculdadeidez.psa.businesslogic.CoordenadasBusinessLogic;
import br.faculdadeidez.psa.businesslogic.ViaturaBusinessLogic;
import br.faculdadeidez.psa.vo.CoordenadaVO;
import br.faculdadeidez.psa.vo.ViaturaVO;

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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String codVtr = (String) req.getParameter("codVtr");
		String latitude = (String) req.getParameter("latitude");
		String longitude = (String) req.getParameter("longitude");
		
		CoordenadaVO coo = new CoordenadaVO();
		
		//setar os valores
		coo.setLatitude(latitude);
		coo.setLongitude(longitude);
		ViaturaBusinessLogic viaturaBL = new ViaturaBusinessLogic();
		ViaturaVO viatura = viaturaBL.find(codVtr);
		coo.setViatura(viatura);
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
