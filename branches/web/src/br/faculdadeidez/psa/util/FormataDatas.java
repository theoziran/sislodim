package br.faculdadeidez.psa.util;

import java.util.Date;


public class FormataDatas {

	public String getDia(Date data){
		int x = data.getDate();
		String dia = null;
		
		switch(x){
		 	case 1: dia = "0"+String.valueOf(x);
		 		break;
		 	case 2: dia = "0"+String.valueOf(x);
		 		break;
		 	case 3: dia = "0"+String.valueOf(x);
		 		break;
		 	case 4: dia = "0"+String.valueOf(x);
		 		break;
		 	case 5: dia = "0"+String.valueOf(x);
		 		break;
		 	case 6: dia = "0"+String.valueOf(x);
		 		break;
		 	case 7: dia = "0"+String.valueOf(x);
		 		break;
		 	case 8: dia = "0"+String.valueOf(x);
		 		break;
		 	case 9: dia = "0"+String.valueOf(x);
		 		break;
		 	default:
		 		dia = String.valueOf(x);
		 	
		}
		return dia;
		
	}

	public String getMes(Date data){
		int x = data.getMonth();
		String mes = null;

		switch(x){
			case 0: mes = "jan";
				break;
			case 1: mes = "fev";
				break;
			case 2: mes = "mar";
				break;
			case 3: mes = "abr";
				break;
			case 4: mes = "mai";
				break;
			case 5: mes = "jun";
				break;
			case 6: mes = "jul";
				break;
			case 7: mes = "ago";
				break;
			case 8: mes = "set";
				break;
			case 9: mes = "out";
				break;
			case 10: mes = "nov";
				break;
			case 11: mes = "dez";
				break;
			}

		return mes;
		
	}

	public String getHorario(Date data) {
		int x = data.getHours();
		String horas;
		String minutos;
		
		switch(x){
	 	case 1: horas = "0"+String.valueOf(x);
	 		break;
	 	case 2: horas = "0"+String.valueOf(x);
	 		break;
	 	case 3: horas = "0"+String.valueOf(x);
	 		break;
	 	case 4: horas = "0"+String.valueOf(x);
	 		break;
	 	case 5: horas = "0"+String.valueOf(x);
	 		break;
	 	case 6: horas = "0"+String.valueOf(x);
	 		break;
	 	case 7: horas = "0"+String.valueOf(x);
	 		break;
	 	case 8: horas = "0"+String.valueOf(x);
	 		break;
	 	case 9: horas = "0"+String.valueOf(x);
	 		break;
	 	default:
	 		horas = String.valueOf(x);
	 	
		}
		
		x = data.getMinutes();
		
		switch(x){
	 	case 1: minutos = "0"+String.valueOf(x);
	 		break;
	 	case 2: minutos = "0"+String.valueOf(x);
	 		break;
	 	case 3: minutos = "0"+String.valueOf(x);
	 		break;
	 	case 4: minutos = "0"+String.valueOf(x);
	 		break;
	 	case 5: minutos = "0"+String.valueOf(x);
	 		break;
	 	case 6: minutos = "0"+String.valueOf(x);
	 		break;
	 	case 7: minutos = "0"+String.valueOf(x);
	 		break;
	 	case 8: minutos = "0"+String.valueOf(x);
	 		break;
	 	case 9: minutos = "0"+String.valueOf(x);
	 		break;
	 	default:
	 		minutos = String.valueOf(x);
		}
		return horas+":"+minutos+" Horas";
	}

}
