package br.faculdadeidez.psa.beans;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.faculdadeidez.psa.businesslogic.SetorBusinessLogic;
import br.faculdadeidez.psa.entity.Setor;

public class SetorBean extends GenericoBean {
	private List<SetorBean> listaTudo = null;
	
	private int codigo;
	private String nome;
	
	public SetorBean() {
		
	}
	
	public SetorBean(int codigo, String nome) {
		setCodigo(codigo);
		setNome(nome);
	}
		
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public List<SetorBean> getListaTudo()
	{	
		return getFachada().listarSetores();
	}
		
	public String delete()
	{
		SetorBean setorDaVez = (SetorBean)getElementoSelecionado();
		return getFachada().deleteSetor(setorDaVez);
	}
	
	public String update()
	{
		SetorBean setorDaVez = (SetorBean)getElementoSelecionado();
		return getFachada().updateSetor(setorDaVez);
	}
	
	public String create()
	{
		String mensagem = getFachada().createSetor(this);
		
		if(mensagem.equals("setorExistente")){
			
			adicionarMensagem("Setor já existente");
			
		}else if (mensagem.equals("problemaInserir")) {
			
			adicionarMensagem("Error...");
		}
		return getFachada().createSetor(this);
	}
}
