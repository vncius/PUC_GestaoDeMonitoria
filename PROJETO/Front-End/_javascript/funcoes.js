function mudaFoto(foto) {
    document.getElementById("icone").src = foto;
}

function usuarioEstaAutenticado() {
    $.ajax({
        method: "GET", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/usuario/auth/"), // END POINT DA API
        headers: {
            "Authorization": localStorage.getItem("Authorization"),
        },
        dataType: "text",
        async: true,
        success: function (result, status, request) {
            if (request.status === 200) {
                return true;
            } else {
                alert("É necessário estar logado para acessar esta página!");
                redirecionarIndexLogin();
                return false;
            }
        },
        error: function (request, status, erro) {
            if (status === "error") {
                alert("Falha ao conectar com o servidor de aplicação.");
            }
            return false;
        }
    });
}

function recuperaTokenParaRequisicao() {
    if (localStorage.getItem("Authorization") === null || localStorage.getItem("Authorization") === "") {
        alert("Usuário não autenticado ou sessão experiou, faça login novamente!");
        redirecionarIndexLogin();
    }
    return localStorage.getItem("Authorization");
}

function obterUrlDePaginas(direcionamento) {
    return "http://localhost:200/" + direcionamento;
}

function obterUrlDaAPI(direcionamento) {
    return "http://localhost:8080/apimonitoria" + direcionamento;
}

function redirecionarIndexLogin(){
    window.location = obterUrlDePaginas("");
}