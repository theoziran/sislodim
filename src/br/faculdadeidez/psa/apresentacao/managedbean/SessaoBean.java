package br.faculdadeidez.psa.apresentacao.managedbean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.faculdadeidez.psa.vo.UsuarioVO;



public class SessaoBean extends GenericoBean{
	private UsuarioBean usuario;
	private boolean moderador;
	private boolean coordenador;
	private boolean operador;

	public SessaoBean() {
	}

	public UsuarioBean getUsuarioBean() {
		if (usuario==null){
		usuario = new UsuarioBean();
		HttpSession sessao = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		UsuarioVO usuarioVo = (UsuarioVO)sessao.getAttribute("currentUser");
		usuario.setUsuario(usuarioVo);
		}
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}
	
	public boolean isLogado(){
		HttpSession sessao = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		if(sessao.getAttribute("logado")==null)
			return false;
		return true;
	}
	
	public boolean isModerador(){
		if (getUsuarioBean().getUsuario().getTipoPermissao()==3)
			setModerador(true);
		return moderador;
	}
	
	public boolean isOperador(){
		if (getUsuarioBean().getUsuario().getTipoPermissao()==1)
			setOperador(true);
		return operador;
	}
	
	public boolean isCoordenador(){
		if (getUsuarioBean().getUsuario().getTipoPermissao()==2)
			setCoordenador(true);
		return coordenador;
	}
	
	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}


	public void setModerador(boolean moderador) {
		this.moderador = moderador;
	}


	public void setOperador(boolean operador) {
		this.operador = operador;
	}

	
}
