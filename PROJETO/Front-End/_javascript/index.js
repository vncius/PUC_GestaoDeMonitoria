$(document).ready(function(){
    $("#btnEntrar").click(function(e){
        var usuario = $('#inputMat').val(); //PEGANDO VALORES DO HTML E INSERINDO EM VARIAVEL JS
		var senha = $('#inputSenha').val(); //PEGANDO VALORES DO HTML E INSERINDO EM VARIAVEL JS;


		var dados = "{'id': '', 'login': '{0}', 'senha': '{1}', 'nome', ''}";
		dados = dados.replace("{0}", usuario);
		dados = dados.replace("{1}", senha);
		
		alert("Em desenvolvimento, matricula: "+usuario+" senha: "+senha);
		
		$.ajax({
		  method: "POST", // TIPO DE REQUISIÇÃO
		  url: "191.233.244.144/login/", // END POINT DA API
		  data: dados // JSON COM USUÁRIO E SENHA PEGA DO FORM NO HTML
		}).done(function(msg){
			alert(msg); // MENSAGEM DE RETORNO PELA API EXMP: TRUE, FALSE
		})
    });
});







