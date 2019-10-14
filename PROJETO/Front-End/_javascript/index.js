$(document).ready(function(){
    $("#btnEntrar").click(function(e){
        var usuario = $('#inputMat').val();
		var senha = $('#inputSenha').val();

		$.ajax({
			method: "POST", // TIPO DE REQUISIÇÃO
			url: "http://localhost:8080/apimonitoria/login", // END POINT DA API
			dataType: "JSON",
			contentType: "application/json;charset=UTF-8",
			async: true,
			data: JSON.stringify({
				"matricula": usuario,
				"senha": senha
			}),
			success: function (result, status, request) {
				registraTokenEmLocalStorage(result.Authorization)
				//redirecionar para menu				
			},
			error: function (request, status, erro) {
				if(request.status = 403){
					alert("Usuário e/ou senha inválidos");
				} else {
					console.log("Ocorreu um erro na requisição para o servidor!");
				}
			}
		});
	});
	
	function registraTokenEmLocalStorage(token){
		localStorage.setItem("Authorization", token);
		//localStorage.getItem("Authorization");  FORMA DE PEGAR O TOKEN PARA PRÓXIMAS REQUISIÇÕES
	}
});







