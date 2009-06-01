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
/**
 * Classe que abstrai uma linha da tabela SIS_USUARIO
 * -Objeto relacional Usuario
 */
public class Usuario implements Serializable {
		
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY) 
	@Column (name="USU_CODIGO")
	/**
	 * Propriedade privada id
	 * Representa o identificador do registro na tabela
	 */
	private int id;
	
	@Basic @Column (name="USU_NOME", nullable=false, length=150)
	/**
	 * Propriedade privada nome
	 * Representa a coluna nome da tabela
	 */
	private String nome;
	
	@Basic @Column (unique=true, name="USU_LOGIN", nullable=false, length=30)
	/**
	 * Propriedade privada login
	 * Representa a coluna login da tabela
	 */
	private String login;
	
	@Basic @Column (name="USU_SENHA", nullable=false, length=10)
	/**
	 * Propriedade privada senha
	 * Representa a coluna senha da tabela
	 */
	private String senha;
	
	@Basic @Column (name="USU_ATIVO", nullable=false)
	/**
	 * Propriedade privada ativo
	 * Representa a coluna ativo da tabela
	 */
	private boolean ativo;
	
	@Basic @Column (unique=true, name="USU_CPF", nullable=false, length=11)
	/**
	 * Propriedade privada cpf
	 * Representa a coluna cpf da tabela
	 */
	private String cpf;
	
	@Basic @Column (name="USU_RG", nullable=false, length=10)
	/**
	 * Propriedade privada rg
	 * Representa a coluna rg da tabela
	 */
	private String rg;
	
	@Basic @Column (name="USU_ORGAO_EXPED", nullable=false, length=10)
	/**
	 * Propriedade privada orgExpeditor
	 * Representa a coluna orgExpeditor da tabela
	 */
	private String orgExpeditor;
	
	@Basic @Column (name="USU_PERFIL", nullable=false)
	/**
	 * Propriedade privada tipoPermissao
	 * Representa a coluna tipoPermissao da tabela
	 */
	private short tipoPermissao;	
	
	


	/**
	 * Construtor default da classe
	 */
	public Usuario() {
		 
	}
	
	/**
	 * Sobrecarga do construtor padrão da classe
	 * @param UsuarioVO user
	 */
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
	
	/**
	 * Método para conversão de objeto Usuario para UsuarioVO
	 * @return UsuarioVO
	 */
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
	
	/**
	 * Método getter da propriedade privada tipoPermissao
	 * @return short
	 */
	public short getTipoPermissao() {
		return tipoPermissao;
	}
	
	/**
	 * Método setter da propriedade privada tipoPermissao
	 * @param short tipoPermissao
	 */
	public void setTipoPermissao(short tipoPermissao) {
		this.tipoPermissao = tipoPermissao;
	}
	
	/**
	 * Método getter da propriedade privada id
	 * @return int 
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Método setter da propriedade privada id
	 * @param int id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Método getter da propriedade privada nome
	 * @return String
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Método setter da propriedade privada nome
	 * @param String nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Método getter da propriedade privada login
	 * @return String
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Método setter da propriedade privada login
	 * @param String login
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Método getter da propriedade privada senha
	 * @return String 
	 */
	public String getSenha() {
		return senha;
	}
	
	/**
	 * Método setter da propriedade privada senha
	 * @param String senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/**
	 * Método getter da propriedade privada ativo
	 * @return boolean
	 */
	public boolean getAtivo() {
		return ativo;
	}	
	
	/**
	 * Método setter da propriedade privada aivo
	 * @param boolean ativo
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	/**
	 * Método getter da propriedade privada cpf
	 * @return String
	 */
	public String getCpf() {
		return cpf;
	}
	
	/**
	 * Método setter da propriedade privada cof
	 * @param String cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	/**
	 * Método getter da propriedade privada rg
	 * @return String
	 */
	public String getRg() {
		return rg;
	}
	
	/**
	 * Método setter da propriedade privada rg
	 * @param String rg
	 */
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	/**
	 * Método getter da propriedade privada orgExpeditor
	 * @return String
	 */
	public String getOrgExpeditor() {
		return orgExpeditor;
	}
	
	/**
	 * Método setter da propriedade privada orgExpeditor
	 * @param String orgExpeditor
	 */
	public void setOrgExpeditor(String orgExpeditor) {
		this.orgExpeditor = orgExpeditor;
	}	
}
