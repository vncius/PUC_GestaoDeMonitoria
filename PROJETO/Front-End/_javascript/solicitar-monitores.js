
$(document).ready(function () {
    if (usuarioEstaAutenticado() === false) {
        redirecionarIndexLogin();
        return;
    } else {
        if (localStorage.getItem("Role") != "ROLE_PROFESSOR") {
            redirecionarMenuPrincipal();
        } else {
            var idCurso = localStorage.getItem("Course");
            buscaDisciplinas(idCurso);
            limpaCamposInconsistencias();
            $("#cboDisciplinas").change(function () {
                var idDisciplina = $("#cboDisciplinas").val();
                if (idDisciplina != "") {
                    var objetoBuscado = buscaObjeto(idDisciplina);
                    preencheVagDisponiveis(objetoBuscado.qtdeVgDisponiveis);
                } else {
                    limpaCampoVagaDisponiveis();
                }
            });
        }

    }

});

//variavel criada para armazenar as disciplinas retornadas.
var listaDisciplinas = 0;


function buscaDisciplinas(idCurso) {
    $.ajax({
        method: "GET", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/disciplina/curso/" + idCurso),
        headers: {
            "Authorization": recuperaTokenParaRequisicao(),
        },
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        async: true,
        success: function (result, status, request) {
            if (result != "" || result != null) {
                listaDisciplinas = result;
                carregaComboboxDisciplinas(result);
            }
        },
        error: function (request, status, erro) {
            if (request.status = 500) {
                // REDIRECIONAR TELA LOGIN - NÃO ESTÁ AUTENTICADO
                alert(status);
                redirecionarIndexLogin();
            }
        }
    });
}



function carregaComboboxDisciplinas(result) {
    $('#cboDisciplinas').empty();
    $('#cboDisciplinas').append($("<option />").val(result.id).text("Escolha uma disciplina"));
    $.each(result, function (i) {
        $('#cboDisciplinas').append($("<option />").val(result[i].id).text(result[i].codigoDisciplina + " - " + result[i].descricao));
    });
}

function buscaObjeto(idDisciplina) {
    for (var i = 0; i < listaDisciplinas.length; i++) {
        if (listaDisciplinas[i].id == idDisciplina) {
            return listaDisciplinas[i];
        }
    }
}

function preencheVagDisponiveis(qtde) {
    $('#nvagas').append("<input/>").val(qtde);
}


function solicitaVagaDisciplina() {
    limpaCamposInconsistencias();

    var objeto = JSON.stringify({
        "qtdeVgSolicitada": $('#qtd-vag').val(),
        "usuario_id": localStorage.getItem("Matricula")
    });

    var idDisciplina = $('#cboDisciplinas').val();

    var button = document.querySelector('#editar');

    if (idDisciplina != "") {

        if (button.disabled) {
            editar(idDisciplina, objeto);
        } else {
            salvar(idDisciplina, objeto);
        }
    }
}



function salvar(idDisciplina, objeto) {
    $.ajax({
        method: "POST", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/solicitaVaga/disciplina/" + idDisciplina),
        headers: {
            "Authorization": recuperaTokenParaRequisicao(),
        },
        dataType: "text",
        contentType: "application/json;charset=UTF-8",
        async: true,
        data: objeto,
        success: function (result, status, request) {
            if (request.status === 200) {
                exibaAlerta(result);
                limpaCamposInconsistencias();
                limpaCamposFormularioVaga();
            }
            if (request.status === 203) {
                registraInconsistencia(result);
            }
            if (request.status === 206) {
                registraInconsistencia(result);
            }
        },
        error: function (request, status, erro) {
            if (request.status === 400) {

            }
        }
    });
}

function editar(idDisciplina, objeto) {

    $.ajax({
        method: "PUT", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/solicitaVaga/disciplina/" + idDisciplina),
        headers: {
            "Authorization": recuperaTokenParaRequisicao(),
        },
        dataType: "text",
        contentType: "application/json;charset=UTF-8",
        async: true,
        data: objeto,
        success: function (result, status, request) {
            if (request.status === 200) {
                exibaAlerta(result);
                limpaCamposInconsistencias();
                limpaCamposFormularioVaga();
                $("#editar").attr("disabled", false);
            }
            if (request.status === 206) {
                registraInconsistencia(result);
            }
        },
        error: function (request, status, erro) {
            if (request.status === 400) {
                alert(status);
            }
        }
    });
}


function buscaVagaEditar() {
    $("#editar").attr("disabled", true);

    var idDisciplina = $('#cboDisciplinas').val();
    var idUsuario = localStorage.getItem("Matricula");

    if(idDisciplina != ""){

    $.ajax({
        method: "GET", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/solicitaVaga/disciplina/" + idDisciplina + "/usuario/" + idUsuario),
        headers: {
            "Authorization": recuperaTokenParaRequisicao(),
        },
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        async: true,
        success: function (result, status, request) {
            if (request.status === 200) {
                $('#cboDisciplinas').empty();
                preencheQtdeVaga(result.qtdeVgSolicitada);
                $('#cboDisciplinas').append($("<option />").val(result.modelDisciplina.id).text(result.modelDisciplina.codigoDisciplina + " - " + result.modelDisciplina.descricao));
            }
            if (request.status === 206) {
                limpaCamposInconsistencias();
                registraInconsistencia("O usuário não solicitou vagas para está disciplina!");
                $("#editar").attr("disabled", false);
            }
        },
        error: function (request, status, erro) {
            if (request.status === 206) {
                limpaCamposInconsistencias();
                registraInconsistencia("O usuário não solicitou vagas para está disciplina!");
                $("#editar").attr("disabled", false);
            }
        }
    });
    } else {
        $("#editar").attr("disabled", false);
    }
}

function botaoCancelar() {
    limpaCamposFormularioVaga();
    $("#editar").attr("disabled", false);
}

function limpaCamposFormularioVaga() {
    var idCurso = localStorage.getItem("Course");
    buscaDisciplinas(idCurso);

    document.getElementById("qtd-vag").value = null;
    document.getElementById("nvagas").value = null;
}

function limpaCampoVagaDisponiveis() {
    document.getElementById("nvagas").value = null;
}


function preencheQtdeVaga(qtde) {
    $('#qtd-vag').append("<input/>").val(qtde);
}

function validacaoQtdeVagas(evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode(key);
    //var regex = /^[0-9.,]+$/;
    var regex = /^[0-9.]+$/;
    if (!regex.test(key)) {
        theEvent.returnValue = false;
        if (theEvent.preventDefault) theEvent.preventDefault();
    }
}