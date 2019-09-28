$(document).ready(function(){
    $("#btnEntrar").click(function(e){
        var usuario = $('#inputMat').val(); //PEGANDO VALORES DO HTML E INSERINDO EM VARIAVEL JS
		var senha = $('#inputSenha').val(); //PEGANDO VALORES DO HTML E INSERINDO EM VARIAVEL JS;
		
		alert("Em desenvolvimento, matricula: "+usuario+" senha: "+senha);
		
		/*
		$.ajax({
		  method: "POST", // TIPO DE REQUISIÇÃO
		  url: "191.233.244.144/login", // END POINT DA API
		  data: { user: usuario, password: senha } // JSON COM USUÁRIO E SENHA PEGA DO FORM NO HTML
		}).done(function(msg){
				  $("#resultado").html(msg); // MENSAGEM DE RETORNO PELA API EXMP: TRUE, FALSE
		})*/
    });
});







