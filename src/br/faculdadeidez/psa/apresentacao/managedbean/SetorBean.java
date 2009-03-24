package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.List;

import br.faculdadeidez.psa.vo.SetorVO;

public class SetorBean extends GenericoBean {
	private List<SetorBean> listaTudo = null;
	private SetorVO setor = new SetorVO();

	public SetorVO getSetor() {
		return setor;
	}

	public void setSetor(SetorVO setor) {
		this.setor = setor;
	}	
	
	public List<SetorVO> getListaTudo()
	{	
		return getFachada().listarSetores();
	}
		
	public String delete()
	{
		SetorVO setorDaVez = (SetorVO)getElementoSelecionado();
		return getFachada().deleteSetor(setorDaVez);
	}
	
	public String update()
	{
		SetorVO setorDaVez = (SetorVO)getElementoSelecionado();
		return getFachada().updateSetor(setorDaVez);
	}
	
	public String create()
	{
		String mensagem = getFachada().createSetor(setor);
		
		if(mensagem.equals("setorExistente")){			
			adicionarMensagem("Setor já existente");			
		}else if (mensagem.equals("problemaInserir")) {			
			adicionarMensagem("Error...");
		}
		return getFachada().createSetor(setor);
	}
}
