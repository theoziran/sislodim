package br.faculdadeidez.psa.beans;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.faculdadeidez.psa.businesslogic.SetorBusinessLogic;
import br.faculdadeidez.psa.entity.Setor;

public class ViaturaBean extends GenericoBean {
	private List<ViaturaBean> listaTudo = null;
	
	private String codigo;
	private Boolean ocupada;
	
	public ViaturaBean() {
		
	}
	
	public ViaturaBean(String codigo, Boolean ocupada)
	{
		setCodigo(codigo);
		setOcupada(ocupada);
	}
		
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Boolean getOcupada() {
		return ocupada;
	}
	
	public void setOcupada(Boolean ocupada) {
		this.ocupada = ocupada;
	}	
	
	public List<ViaturaBean> getListaTudo()
	{	
		return getFachada().listarViaturas();
	}
		
	public String delete()
	{
		ViaturaBean viaturaDaVez = (ViaturaBean)getElementoSelecionado();
		return getFachada().deleteViatura(viaturaDaVez);
	}
	
	public String update()
	{
		ViaturaBean viaturaDaVez = (ViaturaBean)getElementoSelecionado();
		return getFachada().updateViatura(viaturaDaVez);
	}
	
	public String create()
	{
		String mensagem = getFachada().createViatura(this);
		
		if(mensagem.equals("viaturaExistente")){
			
			adicionarMensagem("Viatura já existe");
			
		}else if (mensagem.equals("problemaInserir")) {
			
			adicionarMensagem("Error...");
			
		}
		return mensagem;
	}
}
