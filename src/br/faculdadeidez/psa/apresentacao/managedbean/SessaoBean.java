package br.faculdadeidez.psa.apresentacao.managedbean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.faculdadeidez.psa.vo.UsuarioVO;



public class SessaoBean extends GenericoBean{
	private UsuarioBean usuario;
	

	public SessaoBean() {
	}


	public UsuarioBean getUsuario() {
		HttpSession sessao = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		UsuarioVO usuarioVo = (UsuarioVO)sessao.getAttribute("currentUser");
		usuario.setUsuario(usuarioVo);
		return usuario;
	}


	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}
	
	
	
}
