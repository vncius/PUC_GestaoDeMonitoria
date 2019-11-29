$(document).ready(function () {
    if (usuarioEstaAutenticado() === false) {
        redirecionarIndexLogin();
        return;
    } else {
        if (localStorage.getItem("Role") != "ROLE_COORD_MONITORIA") {
            redirecionarMenuPrincipal();
            return;
        } else {
            //-------------------------------------------- VALIDAÇÕES PRÉ-CARREGAMENTO
            if (validacoes() === true) {
                buscarTodosOsAlunosInscritos();
                preencherCamposDaTable(recuperaDadosPublicacaoResultadoDaAvaliacao());
                preencherCamposComboCursos();
            }
        }
    }
});

function preencherCamposComboCursos() {
    
}

function preencherCamposDaTable(inscricoes) {
    $('table tbody tr').remove();

    inscricoes.forEach(inscricao => {
        var linha = "";
        linha = linha.concat(`<tr id="linhaAluno">`);
        linha = linha.concat(`<th>${inscricao.matricula}</th>`);
        linha = linha.concat(`<td>${inscricao.nome}</td>`);

        inscricao.cursos.disciplinas.forEach(disciplina => {
            if(disciplina.id === inscricao.id_disciplina_selecionada){
                linha = linha.concat(`<td>${disciplina.descricao}</td>`);
            }
        });
        linha = linha.concat(`<td><input type="text" id="notaAluno" class="form-control" max="10" maxlength="4" required></td>`);
        linha = linha.concat(`<td>${inscricao.statusIncricao}</td>`);
        linha = linha.concat(`</tr>`);

        inserirLinhaNaTable(linha);
    });
}

function buscarTodosOsAlunosInscritos() {
    var retorno = false;
    $.ajax({
        method: "GET", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/fichaInscricao/"),
        headers: {
            "Authorization": recuperaTokenParaRequisicao(),
        },
        dataType: "JSON", // SE JSON JÁ CONVERTE O RESULT EM OBJETO
        async: false,
        success: function (result, status, request) {
            registraDadosPublicacaoResultadoDaAvaliacao(result);
        },
        error: function (request, status, erro) {
            if (request.status === 500) {
                exibaAlerta(status);
            } else if (request.status === 404) {
                registraInconsistencia(request.responseText);
            } else {
                exibaAlerta("Houve uma falha na requisição!");
            }
        }
    });
    return retorno;
}

function inserirLinhaNaTable(linha) {
    $('table tbody').append(linha);
}

// ------------------------------------------------------- VALIDAÇÕES DE FUNCIONALIDADE
function validacoes() {
    var retorno = true;

    if (estaNoPeriodoDePublicarResultados() === false) {
        retorno = false;
    }

    return retorno;
}

function estaNoPeriodoDePublicarResultados() {
    // REQUISIÇÃO PARA VERIFICAR SE ESTÁ NO PERIODO DE PUBLICAR RESULTADOS
}
// ------------------------------------------------------------------------------------

/* ------------------------------------ DADOS LOCALSTORAGE --------------------------------------*/

function recuperaDadosPublicacaoResultadoDaAvaliacao() {
    return JSON.parse(localStorage.getItem("dadosPublicacaoResultado"));
}

function registraDadosPublicacaoResultadoDaAvaliacao(dadosPublicacaoResultado) {
    localStorage.removeItem("dadosPublicacaoResultado");
    localStorage.setItem("dadosPublicacaoResultado", JSON.stringify(dadosPublicacaoResultado));
}


function removDadosPublicacaoResultadoDaAvaliacao() {
    localStorage.removeItem("dadosPublicacaoResultado");
}

function recarregarPagina() {
    $(location).attr('href', obterUrlDePaginas("publicar-resultado-avaliacoes.html"));
}