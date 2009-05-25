package br.faculdadeidez.psa.apresentacao.managedbean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.faculdadeidez.psa.vo.UsuarioVO;

/**
 * ManagedBean da sessão
 *
 */

public class SessaoBean extends GenericoBean{
	/**
	 * Representa o usuário da sessão
	 */
	private UsuarioBean usuario;
	/**
	 * Define a permissão do usuário como moderador
	 */
	private boolean moderador;
	/**
	 * Define a permissão do usuário como coordenador
	 */
	private boolean coordenador;
	/**
	 * Define a permissão do usuário como operador
	 */
	private boolean operador;

	/**
	 * Construtor default
	 */
	public SessaoBean() {
	}

	/**
	 * Método getter do atributo usuario
	 * @return UsuarioBean
	 */
	public UsuarioBean getUsuario() {
		if (usuario==null){
		usuario = new UsuarioBean();
		HttpSession sessao = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		UsuarioVO usuarioVo = (UsuarioVO)sessao.getAttribute("currentUser");
		usuario.setUsuario(usuarioVo);
		}
		return usuario;
	}

	/**
	 * Método setter do atributo usuario
	 * @param usuario
	 */
	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}
	/**
	 * Método que definir se o usuário está logado
	 * @return
	 */
	public boolean isLogado(){
		HttpSession sessao = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		if(sessao.getAttribute("logado")==null)
			return false;
		return true;
	}
	
	/**
	 * Método getter do atributo moderador
	 * @return boolean
	 */
	public boolean isModerador(){
		if (getUsuario().getUsuario().getTipoPermissao()==2)
			setModerador(true);
		return moderador;
	}
	/**
	 * Método getter do atributo operador
	 * @return boolean
	 */
	public boolean isOperador(){
		if (getUsuario().getUsuario().getTipoPermissao()==1)
			setOperador(true);
		return operador;
	}
	/**
	 * Método getter do atributo coordenador
	 * @return boolean
	 */
	public boolean isCoordenador(){
		if (getUsuario().getUsuario().getTipoPermissao()==3)
			setCoordenador(true);
		return coordenador;
	}
	/**
	 * Método setter do atributo coordenador
	 * @param coordenador boolean
	 */
	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}


	/**
	 * Método setter do atributo operador
	 * @param moderador boolean
	 */
	public void setModerador(boolean moderador) {
		this.moderador = moderador;
	}


	/**
	 * Método setter do atributo operador
	 * @param operador boolean
	 */
	public void setOperador(boolean operador) {
		this.operador = operador;
	}

	
}
