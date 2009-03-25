package br.faculdadeidez.psa.apresentacao.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FiltroSeguranca implements Filter {

	// Pagina de login
	private static final String SIGNON_PAGE_URI = "/Sislodim/inicio.st";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		if (!this.authorize((HttpServletRequest) req)) {
			response.sendRedirect(SIGNON_PAGE_URI);
		} else {
			// Desativa o cache do browser
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			// Processa request e response
			chain.doFilter(req, res);
		}
	}

	public void destroy() {
	}

	// Metodo que verifica o bean em sessao se esta logado.
	private boolean authorize(HttpServletRequest req) {
		boolean retorno = false;
		HttpSession session = req.getSession(false);
		if (session != null) {
			Object login = session.getAttribute("userLogin");
			if (login != null) {
				retorno = true;
			}
		}
		return retorno;
	}

}
