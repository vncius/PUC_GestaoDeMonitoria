// ------------------------------- UTILIDADES -------------------------------
function mudaFoto(foto) {
    document.getElementById("icone").src = foto;
}

function usuarioEstaAutenticado() {
    var retorno = true;
    if (localStorage.getItem("Authorization") === null || localStorage.getItem("Authorization") === "") {
        retorno = false;
    } else {
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
                retorno = false;
            }
        });
    }
    if (retorno === false) { exibaAlerta("É necessário estar autênticado para acessar esta página!"); }
    return retorno;
}

// ------------------------------- FUNÇOES DE RECUPERAÇÕES DE INFORMAÇÕES GRAVADAS LOCAL -------------------------------
function recuperaMatriculaAluno() {
    return localStorage.getItem("Matricula");
}

function recuperaTokenParaRequisicao() {
    if (localStorage.getItem("Authorization") === null || localStorage.getItem("Authorization") === "") {
        exibaAlerta("Usuário não autenticado ou sessão experiou, faça login novamente!");
        redirecionarIndexLogin();
    }
    return localStorage.getItem("Authorization");
}

// ------------------------------- FUNCOES PARA OBTER URL'S DA API E DO CLIENT -------------------------------
function obterUrlDaAPI(direcionamento) {
    return "http://localhost:8080/apimonitoria" + direcionamento;
}

function obterUrlDePaginas(direcionamento) {
    return `http://localhost:200/${direcionamento}`;
}

function redirecionarIndexLogin() {
    $(location).attr('href', obterUrlDePaginas(""));
}

function redirecionarMenuPrincipal() {
    $(location).attr('href', obterUrlDePaginas("menu-principal.html"));
}

function redirecionamentoDePagina(url) {
    $(location).attr('href', obterUrlDePaginas(url));
}

// ------------------------------- FUNÇOES DE INCONSISTENCIAS -------------------------------
function registraInconsistencia(Mensagem) {
    $("#mensagens ul").append(`<li style="margin-left: 30px;">${Mensagem}</li>`);
    $("#mensagens").show();
    window.location.href = "#mensagens";
}

function limpaCamposInconsistencias() {
    $('#mensagens ul li, #mensagens ul br').remove();
    ocultaCamposInconsistencias();
}

function ocultaCamposInconsistencias() {
    $("#mensagens").hide();
}

// ------------------------------- FUNÇOES DE MENU -------------------------------

function preencheCamposMenu() {
    $(".nome").html(localStorage.getItem("Nome"));
    $(".matricula").html(localStorage.getItem("Matricula"));
}

function logout() {
    localStorage.clear();
    $(location).attr('href', obterUrlDaAPI("/logout"));
    redirecionarIndexLogin();
}

// ------------------------------- FUNÇÕES DE MENSAGENS -------------------------------
function exibaAlerta(mensagem) {
    alert(mensagem);
}

function questione(mensagem) {
    var retorno = confirm(mensagem);
    return retorno;
}


// -------------------------------- FUNÇÃO DE DOWNLOAD
function realizarDownload(url) {
    var fileURL = obterUrlDaAPI(url);

    // for non-IE
    if (!window.ActiveXObject) {
        var save = document.createElement('a');
        save.href = fileURL;
        save.target = '_blank';
        save.download = "arquivo" || 'unknown';

        var evt = new MouseEvent('click', {
            'view': window,
            'bubbles': true,
            'cancelable': false
        });
        save.dispatchEvent(evt);

        (window.URL || window.webkitURL).revokeObjectURL(save.href);
    }

    // for IE < 11
    else if (!!window.ActiveXObject && document.execCommand) {
        var _window = window.open(fileURL, '_blank');
        _window.document.close();
        _window.document.execCommand('SaveAs', true, fileName || fileURL)
        _window.close();
    }
}