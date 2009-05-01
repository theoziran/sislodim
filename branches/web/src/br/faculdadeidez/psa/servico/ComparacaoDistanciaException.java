package br.faculdadeidez.psa.servico;

public class ComparacaoDistanciaException extends Exception {

	private static final long serialVersionUID = 1L;

	private Integer code;

	public ComparacaoDistanciaException() {

	}

	public ComparacaoDistanciaException(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		String message = null;
		switch (this.code) {
		case 1:
			message = "O campo origem é obrigatório";
			break;
		case 2:
			message = "O campo destino é obrigatório";
			break;
		case 3:
			message = "Problemas na URL";
			break;
		case 4:
			message = "Problemas Entrada e Saída";
			break;
		case 5:	
			message = "Problema não identificado";
			break;			
		case 6:
			message = "Endereço não foi encontrado, ou serviço pode ter sido modificado, verifique o source";
			break;			
		}
		return message;
	}

}