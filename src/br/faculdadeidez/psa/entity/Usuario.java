package br.faculdadeidez.psa.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



@SuppressWarnings("serial")
@Entity (name="SIS_USUARIO")
@NamedQueries( {
		@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
})
public class Usuario implements Serializable {
		
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) private int id;
	@Basic private String nome;
	@Basic @Column (unique=true) private String login;
	@Basic private String senha;
	
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
	
}
