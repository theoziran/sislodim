$(function() {
	$(".cpf").mask("999.999.999-99");	
	$(".rg").keypress(function (e) {
	  if( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57))
	  {
	    return false;
	  }
	});
});