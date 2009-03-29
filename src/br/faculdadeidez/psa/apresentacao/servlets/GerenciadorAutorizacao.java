package br.faculdadeidez.psa.apresentacao.servlets;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class GerenciadorAutorizacao implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();

		// System.out.println("Página solicitada estática: "+FacesContext.
		// getCurrentInstance().getViewRoot().getViewId());
		// System.out.println("Página solicitada: "+ currentPage);

		Boolean isLoginPage = (currentPage.lastIndexOf("inicio.xhtml") > -1);
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(true);
		Boolean isLogado;
		if (session.getAttribute("logado") == null)
			isLogado = false;
		else
			isLogado = true;

		if (!isLogado && !isLoginPage) {
			NavigationHandler nh = facesContext.getApplication()
					.getNavigationHandler();
			nh.handleNavigation(facesContext, null, "loginPage");
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.INVOKE_APPLICATION;
	}
}
