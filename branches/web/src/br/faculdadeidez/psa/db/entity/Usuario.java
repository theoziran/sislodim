package br.faculdadeidez.psa.db.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.faculdadeidez.psa.vo.UsuarioVO;

@SuppressWarnings("serial")
@Entity 
@Table (name="SIS_USUARIO")
public class Usuario implements Serializable {
		
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column (name="USU_CODIGO")
	private int id;
	@Basic @Column (name="USU_NOME") private String nome;
	@Basic @Column (unique=true, name="USU_LOGIN") private String login;
	@Basic @Column (name="USU_SENHA") private String senha;
	@Basic @Column (name="USU_ATIVO") private int ativo;
	@Basic @Column (unique=true, name="USU_CPF") private String cpf;
	@Basic @Column (name="USU_RG") private String rg;
	@Basic @Column (name="USU_ORGAO_EXPED") private String orgExpeditor;
	@Basic @Column (name="USU_PERMISSAO") private int tipoPermissao;	
	
	


		
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
		
	public Usuario(UsuarioVO user) {		
		setSenha(user.getSenha());
		setLogin(user.getLogin());
		setNome(user.getNome());
		setCpf(user.getCpf());
		setRg(user.getRg());
		setOrgExpeditor(user.getOrgExpeditor());
		setTipoPermissao(user.getTipoPermissao());
		setAtivo(user.getAtivo());
		if (user.getId()!=0){
			setId(user.getId());
		}
	}
	
	public UsuarioVO toVO() {		
		UsuarioVO user = new UsuarioVO();
		user.setAtivo(getAtivo());
		user.setCpf(getCpf());
		user.setId(getId());
		user.setLogin(getLogin());
		user.setNome(getNome());
		user.setOrgExpeditor(getOrgExpeditor());
		user.setRg(getRg());
		user.setSenha(getSenha());
		user.setTipoPermissao(getTipoPermissao());
		return user;
	}
	
	public int getTipoPermissao() {
		return tipoPermissao;
	}

	public void setTipoPermissao(int tipoPermissao) {
		this.tipoPermissao = tipoPermissao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgExpeditor() {
		return orgExpeditor;
	}

	public void setOrgExpeditor(String orgExpeditor) {
		this.orgExpeditor = orgExpeditor;
	}	
}
