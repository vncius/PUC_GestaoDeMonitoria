$(document).ready(function(){
	limparLocalStorage();

    $("#btnEntrar").click(function(e){
        var matricula = $('#inputMat').val();
		var senha = $('#inputSenha').val();

		$.ajax({
			method: "POST", // TIPO DE REQUISIÇÃO
			url: "http://localhost:8080/apimonitoria/login", // END POINT DA API
			dataType: "JSON",
			contentType: "application/json;charset=UTF-8",
			async: true,
			data: JSON.stringify({
				"matricula": matricula,
				"senha": senha
			}),
			success: function (result, status, request) {
				registraTokenEmLocalStorage(result.Authorization, matricula)				
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

	function limparLocalStorage() {
		localStorage.setItem("Matricula", "null");
		localStorage.setItem("Role", "null");
		localStorage.setItem("Authorization", "null");
	}
	
	function registraTokenEmLocalStorage(token, matricula){
		$.ajax({
			method: "GET", // TIPO DE REQUISIÇÃO
			url: "http://localhost:8080/apimonitoria/usuario/"+matricula, // END POINT DA API
			headers: {
				"Authorization": token,
		   },
			dataType: "JSON",
			contentType: "application/json;charset=UTF-8",
			async: true,
			success: function (result, status, request) {
				localStorage.setItem("Matricula", matricula)
				localStorage.setItem("Role", result.authorities[0].authority)
				localStorage.setItem("Authorization", token);
				alert(localStorage.getItem("Matricula"));
				alert(localStorage.getItem("Role"));
				alert(localStorage.getItem("Authorization"));
				/*REDIRECIONAR PARA O MENU PRINCIPAL*/
			},
			error: function (request, status, erro) {
				alert("Falha ao consultar dados na API!");
			}
		});
	}
});







