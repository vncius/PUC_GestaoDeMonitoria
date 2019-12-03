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
                preencherCamposComboCursos();
                
                $('#btnBuscar').on("click", function (e) {
                    pesquisarPorFiltros();
                });

                $("#curso").change(function () {
                    pesquisarPorFiltros();
                });
    
                $('#formulario').submit(function (e) {
                    if (questione('Confirmar avaliação!')){
                        if (cursoSelecionadoEstaNoCronograma() === false){
                            exibaAlerta("Curso selecionado não está no periodo de avaliação!");
                            return;
                        }
    
                        e.preventDefault();
        
                        var objetoJSON = preenchaFormulario();

                        $.ajax({
                            method: "POST", // TIPO DE REQUISIÇÃO
                            url: obterUrlDaAPI("/publicarResultadoAvaliacoes/"),
                            headers: {
                                "Authorization": recuperaTokenParaRequisicao(),
                            },
                            contentType: "application/json;charset=UTF-8",
                            dataType: "text", //RETORNO SE JSON JÁ CONVERTE O RESULT EM OBJETO
                            async: false,
                            data: JSON.stringify(objetoJSON),
                            success: function (result, status, request) {
                                if (request.status === 200){
                                    removeInscricoesLocal();
                                    removerCursosLocal();
                                    buscarTodosOsAlunosInscritos();
                                    preencherCamposComboCursos();
                                    pesquisarPorFiltros();
                                    exibaAlerta(result);
                                } else {
                                    exibaAlerta("Provavelmente a avaliação não foi salva. Atualize a página e confirme!");
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
                });
            }
        }
    }
});

function preenchaFormulario() {
    var objeto = new Object();
    var lista = [];

    objeto.coeficiente = $('#coeficiente').val();

    $('tbody tr').each(function (el) {
        var resultadoAvaliacao = new Object();
        resultadoAvaliacao.status = $(this).find('#statusInscricao').text().trim();

        if (resultadoAvaliacao.status != "APROVADA" && resultadoAvaliacao.status != "REPROVADA"){
            resultadoAvaliacao.matricula = $(this).find('#matricula').text().trim();
            resultadoAvaliacao.notaAluno = $(this).find('#notaAluno').val();
            resultadoAvaliacao.idDisciplina = 0;
            lista.push(resultadoAvaliacao);
        }
    });

    objeto.listaAvaliacoes = lista;
    objeto.idCurso = parseInt($('#curso').val());
    return objeto;
}

function pesquisarPorFiltros() {
    var idCurso = parseInt($('#curso').val());
    
    var matricula = $('#matricula').val();
    var inscricoesFiltradasPorCurso = [];
    var inscricoesFiltradasPorMatricula = [];

    if (idCurso === 0) {
        exibaAlerta("Selecione um curso para o filtro!");
        return;
    }

    RecuperaInscricoesLocal().forEach(inscricao => {
        if (idCurso === inscricao.cursos.id && inscricao.statusIncricao != "CANCELADA") {
            inscricoesFiltradasPorCurso.push(inscricao);
        }
    });

    if (matricula != "") {
        if (inscricoesFiltradasPorCurso.length > 0) {
            inscricoesFiltradasPorCurso.forEach(inscricao => {
                if (inscricao.matricula === matricula && inscricao.statusIncricao != "CANCELADA") {
                    inscricoesFiltradasPorMatricula.push(inscricao);
                }
            });
        }
    }

    if (inscricoesFiltradasPorMatricula.length > 0 || matricula != "") {
        preencherCamposDaTable(inscricoesFiltradasPorMatricula);
    } else {
        preencherCamposDaTable(inscricoesFiltradasPorCurso);
    }

    if (inscricoesFiltradasPorCurso.length > 0){
        if (inscricoesFiltradasPorMatricula.length > 0 || matricula === ""){
            $('#div-coeficiente').show();
            $('#div-btn-avalia').show();
        } else {
            $('#div-coeficiente').hide();
            $('#div-btn-avalia').hide();
        }
    } else {
        $('#div-coeficiente').hide();
        $('#div-btn-avalia').hide();
    }

    cursoSelecionadoJaFoiAvaliado(idCurso);
}

function preencherCamposComboCursos() {
    $('select option').remove();
    $.ajax({
        method: "GET",
        url: obterUrlDaAPI("/curso/"),
        headers: {
            "Authorization": localStorage.getItem("Authorization"),
        },
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        async: true,
        success: function (result, status, request) {
            $('#curso').append(`<option selected value="0">Selecione</option>`);
            if (result != "") {
                if (result != "null") {
                    RegistraCursosLocal(result);
                    result.forEach(curso => {
                        var descCurso = curso.descricao;
                        var idCurso = curso.id;
                        $('#curso').append(`<option value="${idCurso}">${descCurso}</option>`);
                    });
                }
            }
        },
        error: function (request, status, erro) {
            exibaAlerta("Ocorreu um erro ao buscar os cursos!");
        }
    });
}

function preencherCamposDaTable(inscricoes) {
    $('table tbody tr').remove();

    inscricoes.forEach(inscricao => {
        var linha = "";
        linha = linha.concat(`<tr id="linhaAluno">`);
        linha = linha.concat(`<th id="matricula">${inscricao.matricula}</th>`);
        linha = linha.concat(`<td>${inscricao.nome}</td>`);

        inscricao.cursos.disciplinas.forEach(disciplina => {
            if (disciplina.id === inscricao.id_disciplina_selecionada) {
                linha = linha.concat(`<td>${disciplina.descricao}</td>`);
            }
        });

        if (inscricao.cursos.situacao_avaliacao === "AVALIADO") {
            linha = linha.concat(`<td><input type="number" value="${inscricao.nota_avaliacao}" id="notaAluno" class="form-control" min="0" max="100" required></td>`);
        } else {
            linha = linha.concat(`<td><input type="number" id="notaAluno" class="form-control" min="0" max="100" required></td>`);
        }

        if (inscricao.nota_coeficiente != 0 && inscricao.statusIncricao != "PENDENTE") {
            $('#coeficiente').val(inscricao.nota_coeficiente);
        }

        linha = linha.concat(`<td id="statusInscricao">${inscricao.statusIncricao}</td>`);
        linha = linha.concat(`</tr>`);

        inserirLinhaNaTable(linha);
    });

    $('tbody tr').each(function (el) {
        $(this).find('#notaAluno').mask('000');
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
            RegistraInscricoesLocal(result);
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
        exibaAlerta("Não é possivel publicar os resultados, a data atual não está no perido de avaliação. Verifique o cronograma geral!");
        redirecionarMenuPrincipal();
        retorno = false;
    }
    return retorno;
}

function cursoSelecionadoJaFoiAvaliado(idCurso) {
    var cursos = RecuperaCursosLocal();
    cursos.forEach(curso => {
        if (curso.id === idCurso){
            if (curso.situacao_avaliacao === "AVALIADO") {
                $('#coeficiente').prop("disabled", true);
                $('#div-btn-avalia').hide();
                $('tbody tr').each(function (el) {
                    $(this).find('#notaAluno').prop("disabled", true);
                });
            }
        }
    });
}

function cursoSelecionadoEstaNoCronograma() {
    var retorno = false;
    var idCurso = $('#curso').val();
    $.ajax({
        method: "GET",
        url: obterUrlDaAPI(`/publicarResultadoAvaliacoes/validaPeriodoDeAvaliacao/${idCurso}`),
        headers: {
            "Authorization": localStorage.getItem("Authorization"),
        },
        dataType: "text",
        async: false,
        success: function (result, status, request) {
            if (result === "true"){
                retorno = true;
            }
        },
        error: function (request, status, erro) {
            exibaAlerta("Ocorreu um erro ao verificar se está no periodo de avaliação!");
            return;
        }
    });
    return retorno;
}

function estaNoPeriodoDePublicarResultados() {
    var retorno = false;
    $.ajax({
        method: "GET",
        url: obterUrlDaAPI("/publicarResultadoAvaliacoes/validaPeriodoDeAvaliacao/"),
        headers: {
            "Authorization": localStorage.getItem("Authorization"),
        },
        dataType: "text",
        async: false,
        success: function (result, status, request) {
            if (result === "true"){
                retorno = true;
            }
        },
        error: function (request, status, erro) {
            exibaAlerta("Ocorreu um erro ao verificar se está no periodo de avaliação!");
            retorno = false;
        }
    });
    return retorno;
}
// ------------------------------------------------------------------------------------
/* ------------------------------------ DADOS LOCALSTORAGE --------------------------------------*/

function RecuperaInscricoesLocal() {
    return JSON.parse(localStorage.getItem("dadosPublicacaoResultado"));
}

function RecuperaCursosLocal() {
    return JSON.parse(localStorage.getItem("CursosLocal"));
}

function RegistraCursosLocal(cursos) {
    localStorage.removeItem("CursosLocal");
    localStorage.setItem("CursosLocal", JSON.stringify(cursos));
}

function RegistraInscricoesLocal(dadosPublicacaoResultado) {
    localStorage.removeItem("dadosPublicacaoResultado");
    localStorage.setItem("dadosPublicacaoResultado", JSON.stringify(dadosPublicacaoResultado));
}

function removerCursosLocal() {
    localStorage.removeItem("CursosLocal");
}

function removeInscricoesLocal() {
    localStorage.removeItem("dadosPublicacaoResultado");
}

function recarregarPagina() {
    $(location).attr('href', obterUrlDePaginas("publicar-resultado-avaliacoes.html"));
}