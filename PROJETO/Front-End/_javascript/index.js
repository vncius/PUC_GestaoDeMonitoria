$(document).ready(function(){
    $("#btnEntrar").click(function(e){
        var usuario = $('#inputMat').val();
		var senha = $('#inputSenha').val();

		$.ajax({
			method: "POST", // TIPO DE REQUISIÇÃO
			url: "http://191.233.244.144:8080/apimonitoria/login/", // END POINT DA API
			dataType: "text",
			contentType: "application/json;charset=UTF-8",
			async: true,
			data: JSON.stringify({
				"matricula": usuario,
				"senha": senha
			}),
			success: function (result, status, request) {
				registraTokenEmCookie(result)
				alert("Estado atual: " + status + "\nToken: "+result);
			},
			error: function (request, status, erro) {
				alert("Problema ocorrido: " + status + "\nDescição: " + erro + "\nInformações da requisição: " + request.getAllResponseHeaders());
			}
		});
	});
	
	function registraTokenEmCookie(token){
		console.log("Implementar salvar token em cookie... token: " + token);
	}
});







