package br.faculdadeidez.psa.tests;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.faculdadeidez.psa.apresentacao.servlets.MIDServlet;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;

public class testEnviaDados extends TestCase{
	/**
	 * Test case - TC 6.1 - Informação enviada com sucesso
	 **/
/*	public void testaEnviaDados() throws ServletException, IOException{
		MIDServlet servlet = new MIDServlet();
		
		HttpServletRequest requestMock = requestMock();
		servlet.doGet(requestMock, responseMock());
		verify(requestMock);
	}*/

	private HttpServletResponse responseMock() {
		HttpServletResponse responseMock = createMock(HttpServletResponse.class);
		replay(responseMock);
		return responseMock ;
	}

	private HttpServletRequest requestMock() {
		HttpServletRequest requestMock = createMock(HttpServletRequest.class);
		expect(requestMock.getParameter("codVtr")).andReturn("1234");
		expect(requestMock.getParameter("latitude")).andReturn("11.123");
		expect(requestMock.getParameter("longitude")).andReturn("8.923");
		expect(requestMock.getRequestDispatcher("sucesso.jsp")).andReturn(requestDispatcher());
		return requestMock;
	}

	private RequestDispatcher requestDispatcher() {
		RequestDispatcher dispatcherMock = createNiceMock(RequestDispatcher.class);
		replay(dispatcherMock);
		return dispatcherMock;
	}

}