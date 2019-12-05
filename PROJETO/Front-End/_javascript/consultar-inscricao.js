$(document).ready(function () {
    if (usuarioEstaAutenticado() === false) {
        redirecionarIndexLogin();
        return;
    } else {
        if (localStorage.getItem("Role") != "ROLE_COORD_CAEME" &&
            localStorage.getItem("Role") != "ROLE_ADMIN" &&
            localStorage.getItem("Role") != "ROLE_COORD_CURSO" &&
            localStorage.getItem("Role") != "ROLE_PROFESSOR" &&
            localStorage.getItem("Role") != "ROLE_COORD_MONITORIA") {
            redirecionarMenuPrincipal();
        } else {
            buscarTodasAsInscricoes();

            $("#buscar").on("click", function () {
                filtrarInscricoes();
            });
        }
    }
});

function buscarTodasAsInscricoes() {
    $.ajax({
        method: "GET", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/fichaInscricao/"),
        headers: {
            "Authorization": recuperaTokenParaRequisicao(),
        },
        dataType: "JSON", // SE JSON JÁ CONVERTE O RESULT EM OBJETO
        async: true,
        success: function (result, status, request) {
            if (result.length > 0) {
                registrarInscricoesEmLocalStorage(result);
                preencherTableDeInscricoes(recuperaInscricoesEmLocalStorage());
                preencherCamposBusca(recuperaInscricoesEmLocalStorage());
            }
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
}

function filtrarInscricoes() {
    var filtrarPorCurso = false;
    var filtrarPorStatus = false;
    var filtrarPorMatricula = false;

    var cursoSelecionado = $('select[name="curso"] option:selected').val();
    var statusSelecionado = $('select[name="status"] option:selected').val();
    var matriculaInformada = $('input[name="matricula"]').val();

    if (cursoSelecionado != "Selecione") { filtrarPorCurso = true; }
    if (statusSelecionado != "Selecione") { filtrarPorStatus = true; }
    if (matriculaInformada != "") { filtrarPorMatricula = true; }

    var listaComResultados = [];
    if (filtrarPorCurso != false || filtrarPorStatus != false || filtrarPorMatricula != false) {
        listaComResultados = buscarInscricoesPorFiltro(filtrarPorCurso, filtrarPorStatus, filtrarPorMatricula);

        if (listaComResultados.length > 0) {
            preencherTableDeInscricoes(listaComResultados);
        } else {
            preencherTableDeInscricoes(recuperaInscricoesEmLocalStorage());
            exibaAlerta("A pesquisa não retornou nenhum resultado!");
        }
    } else {
        preencherTableDeInscricoes(recuperaInscricoesEmLocalStorage());
    }

    // RETORNA CAMPOS AO ESTADO ORIGINAL
    $('select[name="curso"]').val("Selecione");
    $('select[name="status"]').val("Selecione");
    $('input[name="matricula"]').val("");
}

function buscarInscricoesPorFiltro(filtrarPorCurso, filtrarPorStatus, filtrarPorMatricula) {
    var listTodasInscricoes = recuperaInscricoesEmLocalStorage();
    var listaFiltrada = [];

    if (listTodasInscricoes.length > 0) {
        if (filtrarPorCurso === true) {
            var cursoSelecionado = $('select[name="curso"] option:selected').val();
            var listInscricoesComFiltroCurso = [];
            listTodasInscricoes.find(inscricao => {
                if (inscricao.cursos.descricao === cursoSelecionado) {
                    listInscricoesComFiltroCurso.push(inscricao)
                }
            });
            listaFiltrada = listInscricoesComFiltroCurso;
        }

        if (filtrarPorStatus === true) {
            var statusSelecionado = $('select[name="status"] option:selected').val();
            var listInscricoesComFiltroStatus = [];
            listTodasInscricoes.find(inscricao => {
                if (inscricao.statusIncricao === statusSelecionado) {
                    listInscricoesComFiltroStatus.push(inscricao)
                }
            });
            listaFiltrada = listInscricoesComFiltroStatus;
        }

        if (filtrarPorMatricula === true) {
            var matriculaInformada = $('input[name="matricula"]').val();
            var listInscricoesComFiltroMatricula = [];
            listTodasInscricoes.find(inscricao => {
                if (inscricao.matricula === matriculaInformada) {
                    listInscricoesComFiltroMatricula.push(inscricao)
                }
            });
            listaFiltrada = listInscricoesComFiltroMatricula;
        }
    }

    return listaFiltrada;
}

// ------------------------------------------------------------------ FUNÇÕES AUXILIARES
function preencherTableDeInscricoes(inscricoes) {
    $('.table tbody tr').remove();

    
    var linha = "";
    inscricoes.forEach(inscricao => {
        var linkDownload = obterUrlDaAPI('/fichaInscricao/downloadAnexo/');
        linkDownload = linkDownload + inscricao.matricula;

        linha = linha.concat("<tr>");
        linha = linha.concat(`<td>${inscricao.id}</td>`);
        linha = linha.concat(`<td>${inscricao.matricula}</td>`);
        linha = linha.concat(`<td>${inscricao.nome}</td>`);
        linha = linha.concat(`<td>${inscricao.cursos.descricao}</td>`);
        linha = linha.concat(`<td><a href="${linkDownload}">Download</a></td>`);
        linha = linha.concat(`<td>${inscricao.statusIncricao}</td>`);
        linha = linha.concat("</tr>");
        inserirLinhaTable(linha);
        linha = "";
    });
}

function preencherCamposBusca(inscricoes) {
    $('#curso option').remove();
    inserirItemNaComboCurso(`<option selected value="Selecione">Selecione</option>`);
    var cursosJaInseridos = [];
    var cursoInserido = false;

    inscricoes.forEach(inscricao => {
        cursosJaInseridos.forEach(curso => {
            if (curso === inscricao.cursos.descricao) {
                cursoInserido = true;
            }
        });

        if (cursoInserido === false) {
            inserirItemNaComboCurso(`<option>${inscricao.cursos.descricao}</option>`);
            cursosJaInseridos.push(inscricao.cursos.descricao);
        }
        cursoInserido = false;
    });
}

function inserirItemNaComboCurso(item) {
    $("#curso").append(item);
}

function inserirLinhaTable(linhaInscricao) {
    $(".table tbody").append(linhaInscricao);
}

function registrarInscricoesEmLocalStorage(inscricoes) {
    localStorage.setItem("listInscricoes", JSON.stringify(inscricoes));
}

function recuperaInscricoesEmLocalStorage() {
    return JSON.parse(localStorage.getItem("listInscricoes"));
}