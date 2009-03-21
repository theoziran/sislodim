package br.faculdadeidez.psa.beans;



public class SessaoBean extends GenericoBean{
	private UsuarioBean usuario;
	

	public SessaoBean() {
	}


	public UsuarioBean getUsuario() {
		return usuario;
	}


	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}
	
	
	
}
