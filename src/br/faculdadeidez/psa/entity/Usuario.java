package br.faculdadeidez.psa.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



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
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String nome, String login, String senha, int id, int ativo) {
		setAtivo(ativo);
		setId(id);
		setSenha(senha);
		setLogin(login);
		setNome(nome);
	}
	
	public Usuario(String nome, String login, String senha) {
		
		setSenha(senha);
		setLogin(login);
		setNome(nome);
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
	
}
