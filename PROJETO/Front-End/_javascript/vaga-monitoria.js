$(document).ready(function () {
    if (usuarioEstaAutenticado() === false) {
        redirecionarIndexLogin();
        return;
    } else {
        if (localStorage.getItem("Role") != "ROLE_COORD_CURSO") {
            redirecionarMenuPrincipal();
        } else {
            var idCurso = localStorage.getItem("Course");
            
                $.ajax({
                    method: "GET", // TIPO DE REQUISIÇÃO
                    url: obterUrlDaAPI("/disponibilizaVaga/dtaAtualMenorDtaEditalInicio/" + idCurso),
                    headers: {
                        "Authorization": recuperaTokenParaRequisicao(),
                    },
                    dataType: "json",
                    contentType: "application/json;charset=UTF-8",
                    async: true,
                    success: function (result, status, request) {
                        if (result == true) {
                        habilitaBotao();
                        }else{
                        desabilitaBotao();
                        $("#mensagens ul").append('<li  class="alert alert-danger" role="alert">Período de disponibilização de vagas encerrado!!</li>');
                        $("#mensagens").show();
                        }
                        buscaDisciplinas(idCurso);
                    },
                    error: function (request, status, erro) {
                        if (request.status == 500) {
                            $("#mensagens ul").append('<li  class="alert alert-danger" role="alert">Não foi definido cronograma para o curso!</li>');
                            $("#mensagens").show();
                        }
                    }
                });
            }
               
        
            }
});


function verificaPermissao() {
    var role = recuperaRole();
    if (role != "ROLE_COORD_CURSO") {
        redirecionarIndexLogin();
    }
}


var disab = "disabled";

function desabilitaBotao(){
 disab = "disabled";
}
function habilitaBotao(){
disab = "enabled";
}


//variavel criada para armazenar as disciplinas retornadas.
var listaDisciplinas = 0;

function buscaDisciplinas(idCurso) {
    $.ajax({
        method: "GET", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/disponibilizaVaga/curso/" + idCurso),
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
        $('#tabela tbody').append('<tr><td>' + result[i].codigoDisciplina + '</td><td>' + result[i].descricao + '</td><td>' + result[i].qtdeVgMonitoria + '</td><td>' + '<button '+disab+' class="btn btn-warning btn-xs" data-toggle="modal" data-target="#exampleModal" onclick=preencheModal("'+result[i].id+'")>Disp.Vagas</<button>' + '</td></tr>');
    }

}

//função criada para buscarObjeto e passar para Janela Modal
function preencheModal(id) {
for(var i = 0; listaDisciplinas.length > i; i++){
    if(listaDisciplinas[i].id == id){
        $("#mensagens ul").empty();
        document.getElementById("recipient-id").value = listaDisciplinas[i].id;
        document.getElementById("recipient-discip").value = listaDisciplinas[i].codigoDisciplina + " - " + listaDisciplinas[i].descricao;
        document.getElementById("recipient-qtdeVagas").value = listaDisciplinas[i].qtdeVgMonitoria;
    }
}

}


function salvarQtdeVagas() {

    var disciplina =  JSON.stringify({
        "id": $('#recipient-id').val(),
        "descricao": $('#recipient-discip').val(),
        "qtdeVgMonitoria": $('#recipient-qtdeVagas').val()
    });

    $.ajax({
        method: "PUT", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/disponibilizaVaga/"),
        headers: {
            "Authorization": recuperaTokenParaRequisicao(),
        },
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        data: disciplina,
        success: function (result, status, request) {
            preencheCamposTabela(result);
            $('#exampleModal').modal('hide');
            listaDisciplinas = result;
            $("#mensagens ul").append('<li class="alert alert-success" role="alert">Disponibilização de vagas Registrado com Sucesso!!</li>');
                $("#mensagens").show();
        },  error: function (request, status, erro) {
            if (request.status == 302) {
                $("#mensagens ul").empty();
                $("#mensagens ul").append('<li class="alert alert-danger" role="alert" >Período de disponibilização de vagas encerrado!!</li>');
                $("#mensagens").show();
                $('#exampleModal').modal('hide');
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






