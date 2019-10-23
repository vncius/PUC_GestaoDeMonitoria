function mudaFoto(foto) {
    document.getElementById("icone").src = foto;
}

function usuarioEstaAutenticado() {
    $.ajax({
        method: "GET", // TIPO DE REQUISIÇÃO
        url: "http://localhost:8080/apimonitoria/usuario/auth/", // END POINT DA API
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
                window.location = "http://localhost:200/";
                return false;
            }
        },
        error: function (request, status, erro) {
            alert("É necessário estar logado para acessar esta página!");
            window.location = "http://localhost:200/";
            return false;
        }
    });
}

function recuperaTokenParaRequisicao(){
    if (localStorage.getItem("Authorization") === null || localStorage.getItem("Authorization") === ""){
        alert("Usuário não autenticado ou sessão experiou, faça login novamente!");
        window.location = "http://localhost:200/";
    }
    return localStorage.getItem("Authorization");
}