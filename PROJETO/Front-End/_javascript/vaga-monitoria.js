$(document).ready(function () {

    verificaPermissao();
    var idCurso = localStorage.getItem("Course");
    buscaDisciplinas(idCurso);
});

function verificaPermissao() {
    var role = recuperaRole();
    if (role != "ROLE_PROFESSOR") {
        redirecionarIndexLogin();
    }
}

//variavel criada para armazenar as disciplinas retornadas.
var listaDisciplinas;

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
            if (result != "") {
                if (result != "null") {
                    preencheCamposTabela(result);
                   listaDisciplinas = result;
                }
            }
        },
        error: function (request, status, erro) {
            if (request.status = 500) {
                // REDIRECIONAR TELA LOGIN - NÃO ESTÁ AUTENTICADO
                alert(status);
            }
        }
    });
}


function preencheCamposTabela(result) {
        $('#tabela tbody tr').remove();
    for (var i = 0; result.length > i; i++) {
        $('#tabela tbody').append('<tr id="'+result[i].id+'"><td>' + result[i].codigoDisciplina + '</td><td>' + result[i].descricao + '</td><td>' + result[i].qtdeVgMonitoria + '</td><td>' + '<a class="btn btn-warning btn-xs" data-toggle="modal" data-target="#exampleModal" onclick=javascript:buscarObjeto("' + result[i].id + '")>Disp.Vagas</a>' + '</td></tr>');
    }

}

//função criada para buscarObjeto e passar para Janela Modal
function buscarObjeto(id) {
for(var i = 0; listaDisciplinas.length; i++){
    if(listaDisciplinas[i].id == id){
        preencheModal(listaDisciplinas[i]);
    }
}

}

function preencheModal(result) {
    document.getElementById("recipient-id").value = result.id;
    document.getElementById("recipient-discip").value = result.codigoDisciplina + " - " + result.descricao;
    document.getElementById("recipient-qtdeVagas").value = result.qtdeVgMonitoria;
}


function salvarQtdeVagas() {
    var idDiscip = $('#recipient-id').val();
    var qtdeVg = $('#recipient-qtdeVagas').val();

    $.ajax({
        method: "PUT", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/disciplina/" + idDiscip + "/" + qtdeVg),
        headers: {
            "Authorization": recuperaTokenParaRequisicao(),
        },
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        success: function (result, status, request) {
            preencheCamposTabela(result);
            $('#exampleModal').modal('hide');
            listaDisciplinas = result;
        },  error: function (request, status, erro) {
            if (request.status === 400) {
                $("#mensagens ul").append("<li>Todos os campos são obrigatórios!</li><br/>");
                $("#mensagens").show();
            }
        }
    });
}

//Função para buscar qualquer campo da tabela sem requisição.
$(function () {
    $(".input-search").keyup(function () {
        var tabela = $(this).attr('alt');
        if ($(this).val() != "") {
            $("." + tabela + " tbody>tr").hide();
            $("." + tabela + " td:contains-ci('" + $(this).val() + "')").parent("tr").show();
        } else {
            $("." + tabela + " tbody>tr").show();
        }
    });
});
$.extend($.expr[":"], {
    "contains-ci": function (elem, i, match, array) {
        return (elem.textContent || elem.innerText || $(elem).text() || "").toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
    }
});

function validaNumero(evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode( key );
    //var regex = /^[0-9.,]+$/;
    var regex = /^[0-9.]+$/;
    if( !regex.test(key) ) {
       theEvent.returnValue = false;
       if(theEvent.preventDefault) theEvent.preventDefault();
    }
 }
 //






