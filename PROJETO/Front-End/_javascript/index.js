$(document).ready(function () {
	if (usuarioJaEstaLogado()) {
		redirecionarMenuPrincipal();
	} else {
		$("#btnEntrar").click(function (e) {
			limparLocalStorage();
			var matricula = $('#inputMat').val();
			var senha = $('#inputSenha').val();
	
			$.ajax({
				method: "POST", // TIPO DE REQUISIÇÃO
				url: obterUrlDaAPI("/login"), // END POINT DA API
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
					if (request.status === 403) {
						alert("Usuário e/ou senha inválidos");
					} else {
						alert("Falha ao consultar dados na API!");
					}
				}
			});
		});
	}
});

function usuarioJaEstaLogado() {
	var retorno = false;

	if (localStorage.getItem("Authorization") != null &&
		localStorage.getItem("Matricula") != null &&
		localStorage.getItem("Role") != null &&
		localStorage.getItem("Nome") != null &&
		localStorage.getItem("Course") != null) {

		$.ajax({
			method: "GET", // TIPO DE REQUISIÇÃO
			url: obterUrlDaAPI("/usuario/auth/"), // END POINT DA API
			headers: {
				"Authorization": localStorage.getItem("Authorization"),
			},
			dataType: "text",
			async: false,
			success: function (result, status, request) {
				if (result === "true") {
					retorno = true;
				}
			}, 
			error: function (request, status, erro) {
				limparLocalStorage();
			}
		});
	}
	return retorno;
}

function limparLocalStorage() {
	localStorage.clear();
}

function registraTokenEmLocalStorage(token, matricula) {
	token = token.replace("Bearer", "");
	$.ajax({
		method: "GET", // TIPO DE REQUISIÇÃO
		url: obterUrlDaAPI("/usuario/" + matricula),
		headers: {
			"Authorization": token,
		},
		dataType: "JSON",
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function (result, status, request) {
			localStorage.setItem("Matricula", matricula);
			localStorage.setItem("Role", result.authorities[0].authority);
			localStorage.setItem("Authorization", "Bearer " + token);
			localStorage.setItem("Course", result.curso.id);
			localStorage.setItem("Nome", result.nome);
			redirecionamentoDePagina("menu-principal.html");
		},
		error: function (request, status, erro) {
			alert("Falha ao consultar dados na API!");
			$(location).attr('href', obterUrlDePaginas("/inscricao.html"));
		}
	});
}
