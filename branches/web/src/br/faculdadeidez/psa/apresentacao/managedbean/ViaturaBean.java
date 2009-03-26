package br.faculdadeidez.psa.apresentacao.managedbean;

import java.util.List;

import br.faculdadeidez.psa.vo.ViaturaVO;

public class ViaturaBean extends GenericoBean {
	private List<ViaturaBean> listaTudo = null;
	private ViaturaVO viatura =  new ViaturaVO();
		
	public ViaturaVO getViatura() {
		return viatura;
	}
	
	public void setViatura(ViaturaVO viatura) {
		this.viatura = viatura;
	}
	
	public List<ViaturaVO> getListaTudo()
	{	
		return getFachada().listarViaturas();
	}
		
	public String delete()
	{
		ViaturaVO viaturaDaVez = (ViaturaVO)getElementoSelecionado();
		return getFachada().deleteViatura(viaturaDaVez);
	}
	
	public String update()
	{
		ViaturaVO viaturaDaVez = (ViaturaVO)getElementoSelecionado();
		return getFachada().updateViatura(viaturaDaVez);
	}
	
	public String create()
	{
		String mensagem = getFachada().createViatura(viatura);
		
		if(mensagem.equals("viaturaExistente")){			
			adicionarMensagem("Viatura já existe");			
		}else if (mensagem.equals("problemaInserir")) {			
			adicionarMensagem("Error...");			
		}
		return mensagem;
	}
}
