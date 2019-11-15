$(document).ready(function () {
    if (usuarioEstaAutenticado() === false) {
        redirecionarIndexLogin();
        return;
    } else {
        if (localStorage.getItem("Role") != "ROLE_COORD_MONITORIA") {
            redirecionarMenuPrincipal();
        } else {
            carregarComboboxCursos();
            consultaTodosCronograma();

            $("#mensagens").hide();
            $("#salvar").click(function (e) {
                limpaMensagensDeValidacao();

                if (validaCronogramaMonitoria()) {
                    var cronogramaMonitoria = JSON.stringify({
                        "id": $('idCronograma').val(),
                        "curso": $('#cboCursos').val(),
                        "dataEditalInicio": $('#DtIniPublicacaoEdital').val(),
                        "dataEditalFim": $('#DtFimPublicacaoEdital').val(),

                        "dataInscricaoInicio": $('#DtIniPeriodoDeIncricao').val(),
                        "dataInscricaoFim": $('#DtFimPeriodoDeIncricao').val(),

                        "dataPeriodoAvaliacaoInicio": $('#DtIniPeriodoAvaliacao').val(),
                        "dataPeriodoAvaliacaoFim": $('#DtFimPeriodoAvaliacao').val(),

                        "dataEntregaResultadosInicio": $('#DtIniEntregaDeResultados').val(),
                        "dataEntregaResultadosFim": $('#DtFimEntregaDeResultados').val(),

                        "dataPeriodoLetivoInicio": $('#DtIniPeriodoLetivo').val(),
                        "dataPeriodoLetivoFim": $('#DtFimPeriodoLetivo').val(),

                        "dataEntregaCertificadoInicio": $('#DtIniEntregaDeCertificados').val(),
                        "dataEntregaCertificadoFim": $('#DtFimEntregaDeCertificados').val()
                    });
                    var idCronograma = $("#idCronograma").val();
                    if (idCronograma == "") {
                        $.ajax({
                            method: "POST", // TIPO DE REQUISIÇÃO
                            url: obterUrlDaAPI("/cronograma_monitoria/"), // END POINT DA API
                            headers: {
                                "Authorization": localStorage.getItem("Authorization"),
                            },
                            dataType: "text",
                            contentType: "application/json;charset=UTF-8",
                            async: true,
                            data: cronogramaMonitoria,
                            success: function (result, status, request) {
                                limpaMensagensDeValidacao();
                                if (request.status === 206) {
                                    exibaAlerta(result + "\nCode status request: " + request.status);
                                } else {
                                    exibaAlerta(result);
                                }
                            },
                            error: function (request, status, erro) {
                                if (request.status === 400) {
                                    $("#mensagens ul").append("<li>Todos os campos são obrigatórios!</li><br/>");
                                    $("#mensagens").show();
                                }
                            }
                        });

                    } else {
                        $.ajax({
                            method: "PUT", // TIPO DE REQUISIÇÃO
                            url: obterUrlDaAPI("/cronograma_monitoria/" + idCronograma), // END POINT DA API
                            headers: {
                                "Authorization": localStorage.getItem("Authorization"),
                            },
                            dataType: "text",
                            contentType: "application/json;charset=UTF-8",
                            async: true,
                            data: cronogramaMonitoria,
                            success: function (result, status, request) {
                                limpaMensagensDeValidacao();
                                if (request.status === 206) {
                                    exibaAlerta(result + "\nCode status request: " + request.status);
                                } else {
                                    exibaAlerta(result);
                                }
                            },
                            error: function (request, status, erro) {
                                if (request.status === 400) {
                                    $("#mensagens ul").append("<li>Todos os campos são obrigatórios!</li><br/>");
                                    $("#mensagens").show();
                                }
                            }
                        });
                    }
                }
            });

            $("#cboCursos").change(function () {

                var id_curso = document.getElementById("cboCursos");
                var valorIdcurso = id_curso.options[id_curso.selectedIndex].value;
                limpaCampos();
                $.ajax({
                    method: "GET", // TIPO DE REQUISIÇÃO
                    url: obterUrlDaAPI("/cronograma_monitoria/" + valorIdcurso),
                    headers: {
                        "Authorization": localStorage.getItem("Authorization"),
                    },
                    dataType: "text",
                    contentType: "application/json;charset=UTF-8",
                    async: true,
                    success: function (result, status, request) {
                        if (result != "") {
                            if (result != "null") {
                                preencheCamposDoCronogramaMonitoria(result);
                            }
                        }
                    },
                    error: function (request, status, erro) {
                        if (request.status = 500) {
                            // REDIRECIONAR TELA LOGIN - NÃO ESTÁ AUTENTICADO
                        }
                    }
                });
            });

            $('#btnconsulta').on("click", function (e) {
                var id_curso = document.getElementById("cboCursos");
                var valorIdcurso = id_curso.options[id_curso.selectedIndex].value;

                $.ajax({
                    method: "GET", // TIPO DE REQUISIÇÃO
                    url: obterUrlDaAPI("/cronograma_monitoria/" + valorIdcurso),
                    headers: {
                        "Authorization": localStorage.getItem("Authorization"),
                    },
                    dataType: "json",
                    contentType: "application/json;charset=UTF-8",
                    async: true,
                    success: function (result, status, request) {
                        if (result != null) {
                            if (valorIdcurso != "") {
                                preencheCamposTabelaPorCurso(result);
                                $("#mensagensConsulta ul").empty();
                                document.getElementById("mensagensConsulta").style.display = "none";
                            } else {
                                preencheCamposTabela(result);
                                $("#mensagensConsulta ul").empty();
                                document.getElementById("mensagensConsulta").style.display = "inline-block";
                                $("#mensagensConsulta ul").append("<li>É necessário selecionar pelo menos uma opção no campo curso!</li><br/>");
                            }
                        }
                    },
                    error: function (request, status, erro) {
                        if (request.status = 500) {
                            // REDIRECIONAR TELA LOGIN - NÃO ESTÁ AUTENTICADO
                        }
                        if (request.status = 404) {
                            $('#tabela').empty();
                            $("#mensagensConsulta ul").empty();
                            document.getElementById("mensagensConsulta").style.display = "inline-block";
                            $("#mensagensConsulta ul").append("<li>Não existe nenhum cronograma definido para o curso selecionado!</li><br/>");
                        }
                    }
                });
            });
        }
    }
});

function carregarComboboxCursos() {
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
            if (result != "") {
                if (result != "null") {
                    $.each(result, function (i) {
                        $('#cboCursos').append($("<option />").val(result[i].id).text(result[i].descricao));
                    });
                }
            }
        },
        error: function (request, status, erro) {
            if (request.status = 500) {
                // REDIRECIONAR TELA LOGIN - NÃO ESTÁ AUTENTICADO
            }
        }
    });

}

function preencheCamposDoCronogramaMonitoria(resultado) {
    $("#mensagens ul").empty();
    document.getElementById("mensagens").style.display = "none";
    var cronogramaMonitoria = JSON.parse(resultado);
    document.querySelector("#idCronograma").value = cronogramaMonitoria.id;
    document.querySelector("#DtIniPublicacaoEdital").value = cronogramaMonitoria.dataEditalInicio;
    document.querySelector("#DtFimPublicacaoEdital").value = cronogramaMonitoria.dataEditalFim;
    document.querySelector("#DtIniPeriodoDeIncricao").value = cronogramaMonitoria.dataInscricaoInicio;
    document.querySelector("#DtFimPeriodoDeIncricao").value = cronogramaMonitoria.dataInscricaoFim;
    document.querySelector("#DtIniPeriodoAvaliacao").value = cronogramaMonitoria.dataPeriodoAvaliacaoInicio;
    document.querySelector("#DtFimPeriodoAvaliacao").value = cronogramaMonitoria.dataPeriodoAvaliacaoFim;
    document.querySelector("#DtIniEntregaDeResultados").value = cronogramaMonitoria.dataEntregaResultadosInicio;
    document.querySelector("#DtFimEntregaDeResultados").value = cronogramaMonitoria.dataEntregaResultadosFim;
    document.querySelector("#DtIniPeriodoLetivo").value = cronogramaMonitoria.dataPeriodoLetivoInicio;
    document.querySelector("#DtFimPeriodoLetivo").value = cronogramaMonitoria.dataPeriodoLetivoFim;
    document.querySelector("#DtIniEntregaDeCertificados").value = cronogramaMonitoria.dataEntregaCertificadoInicio;
    document.querySelector("#DtFimEntregaDeCertificados").value = cronogramaMonitoria.dataEntregaCertificadoFim;
}

function validaCronogramaMonitoria() {
    var retorno = true;

    var curso = $('#cboCursos').val();
    if (curso == "") {
        $("#mensagens ul").append("<li>É necessário selecionar um Curso para definição do cronograma!</li><br/>");
        retorno = false;
    }

    /*PUBLICAÇÃO DE EDITAL ---------------------------------------------------------------------------*/
    var DtIniPublicacaoEdital = $('#DtIniPublicacaoEdital').val();
    var DtFimPublicacaoEdital = $('#DtFimPublicacaoEdital').val();

    if (DtIniPublicacaoEdital.length < 8 || DtFimPublicacaoEdital.length < 8) {
        $("#mensagens ul").append("<li>Os campos do cronograma de publicação de edital é obrigatório!</li><br/>");
        retorno = false;
    } else if (DtIniPublicacaoEdital > DtFimPublicacaoEdital) {
        $("#mensagens ul").append("<li>O campo data inicio de publicação de edital não pode ser maior que o campo data fim!</li><br/>");
        retorno = false;
    }
    /*PERIODO DE INSCRICAO ---------------------------------------------------------------------------*/
    var DtIniPeriodoDeIncricao = $('#DtIniPeriodoDeIncricao').val();
    var DtFimPeriodoDeIncricao = $('#DtFimPeriodoDeIncricao').val();

    if (DtIniPeriodoDeIncricao.length < 8 || DtFimPeriodoDeIncricao.length < 8) {
        $("#mensagens ul").append("<li>Os campos do cronograma do periodo de inscrição é obrigatório!</li><br/>");
        retorno = false;
    } else if (DtIniPeriodoDeIncricao > DtFimPeriodoDeIncricao) {
        $("#mensagens ul").append("<li>O campo data inicio do periodo de inscrição não pode ser maior que o campo data fim!</li><br/>");
        retorno = false;
    }
    /*PERIODO AVALIACAO -------------------------------------------------------------------------------*/
    var DtIniPeriodoAvaliacao = $('#DtIniPeriodoAvaliacao').val();
    var DtFimPeriodoAvaliacao = $('#DtFimPeriodoAvaliacao').val();

    if (DtIniPeriodoAvaliacao.length < 8 || DtFimPeriodoAvaliacao.length < 8) {
        $("#mensagens ul").append("<li>Os campos do cronograma do periodo de avaliação é obrigatório!</li><br/>");
        retorno = false;
    } else if (DtIniPeriodoAvaliacao > DtFimPeriodoAvaliacao) {
        $("#mensagens ul").append("<li>O campo data inicio do periodo de avaliação não pode ser maior que o campo data fim!</li><br/>");
        retorno = false;
    }
    /*ENTREGA DE RESULTADOS ---------------------------------------------------------------------------*/
    var DtIniEntregaDeResultados = $('#DtIniEntregaDeResultados').val();
    var DtFimEntregaDeResultados = $('#DtFimEntregaDeResultados').val();

    if (DtIniEntregaDeResultados.length < 8 || DtFimEntregaDeResultados.length < 8) {
        $("#mensagens ul").append("<li>Os campos do cronograma de entrega de resultados é obrigatório!</li><br/>");
        retorno = false;
    } else if (DtIniEntregaDeResultados > DtFimEntregaDeResultados) {
        $("#mensagens ul").append("<li>O campo data inicio da entrega de resultados não pode ser maior que o campo data fim!</li><br/>");
        retorno = false;
    }
    /*PERIODO LETIVO -----------------------------------------------------------------------------------*/
    var DtIniPeriodoLetivo = $('#DtIniPeriodoLetivo').val();
    var DtFimPeriodoLetivo = $('#DtFimPeriodoLetivo').val();

    if (DtIniPeriodoLetivo.length < 8 || DtFimPeriodoLetivo.length < 8) {
        $("#mensagens ul").append("<li>Os campos do cronograma do periodo letivo é obrigatório!</li><br/>");
        retorno = false;
    } else if (DtIniPeriodoLetivo > DtFimPeriodoLetivo) {
        $("#mensagens ul").append("<li>O campo data inicio do periodo letivo não pode ser maior que o campo data fim!</li><br/>");
        retorno = false;
    }
    /*ENTREGA DE CERTIFICADOS ---------------------------------------------------------------------------*/
    var DtIniEntregaDeCertificados = $('#DtIniEntregaDeCertificados').val();
    var DtFimEntregaDeCertificados = $('#DtFimEntregaDeCertificados').val();

    if (DtIniEntregaDeCertificados.length < 8 || DtFimEntregaDeCertificados.length < 8) {
        $("#mensagens ul").append("<li>Os campos do cronograma da entrega de certificados é obrigatório!</li><br/>");
        retorno = false;
    } else if (DtIniEntregaDeCertificados > DtFimEntregaDeCertificados) {
        $("#mensagens ul").append("<li>O campo data inicio da entrega de certificados não pode ser maior que o campo data fim!</li>");
        retorno = false;
    }
    /*----------------------------------------------------------------------------------------------------*/
    /*VALIDAÇÃO PARA QUE DATAS NÃO ESTEJA NO INTERVALO UMA DA OUTRA */
    if (retorno === true) {
        if (DtIniPeriodoDeIncricao < DtFimPublicacaoEdital) {
            $("#mensagens ul").append("<li>Periodo de inscrição está no intervalo de outra atividade</li><br/>");
            retorno = false;
        } else if (DtIniPeriodoAvaliacao < DtFimPublicacaoEdital ||
            DtIniPeriodoAvaliacao < DtFimPublicacaoEdital) {
            $("#mensagens ul").append("<li>Periodo de avaliação está no intervalo de outra atividade</li><br/>");
            retorno = false;
        } else if (DtIniEntregaDeResultados < DtFimPublicacaoEdital ||
            DtIniEntregaDeResultados < DtFimPeriodoDeIncricao ||
            DtIniEntregaDeResultados < DtFimPeriodoAvaliacao) {
            $("#mensagens ul").append("<li>Entrega de resultados está no intervalo de outra atividade</li><br/>");
            retorno = false;
        } else if (DtIniPeriodoLetivo < DtFimPublicacaoEdital ||
            DtIniPeriodoLetivo < DtFimPeriodoDeIncricao ||
            DtIniPeriodoLetivo < DtFimPeriodoAvaliacao ||
            DtIniPeriodoLetivo < DtFimEntregaDeResultados) {
            $("#mensagens ul").append("<li>Periodo letivo está no intervalo de outra atividade</li><br/>");
            retorno = false;
        } else if (DtIniEntregaDeCertificados < DtFimPublicacaoEdital ||
            DtIniEntregaDeCertificados < DtFimPeriodoDeIncricao ||
            DtIniEntregaDeCertificados < DtFimPeriodoAvaliacao ||
            DtIniEntregaDeCertificados < DtFimEntregaDeResultados ||
            DtIniEntregaDeCertificados < DtFimPeriodoLetivo) {
            $("#mensagens ul").append("<li>Periodo letivo está no intervalo de outra atividade</li><br/>");
            retorno = false;
        }
    }
    /*----------------------------------------------------------------------------------------------------*/
    if (retorno === false) {
        $("#mensagens").show();
    }
    return retorno;
}

function limpaMensagensDeValidacao() {
    $('#mensagens ul li, #mensagens ul br').remove();
    $("#mensagens").hide();
}

function limpaCampos() {
    document.querySelector("#idCronograma").value = null;
    document.querySelector("#DtIniPublicacaoEdital").value = null;
    document.querySelector("#DtFimPublicacaoEdital").value = null;
    document.querySelector("#DtIniPeriodoDeIncricao").value = null;
    document.querySelector("#DtFimPeriodoDeIncricao").value = null;
    document.querySelector("#DtIniPeriodoAvaliacao").value = null;
    document.querySelector("#DtFimPeriodoAvaliacao").value = null;
    document.querySelector("#DtIniEntregaDeResultados").value = null;
    document.querySelector("#DtFimEntregaDeResultados").value = null;
    document.querySelector("#DtIniPeriodoLetivo").value = null;
    document.querySelector("#DtFimPeriodoLetivo").value = null;
    document.querySelector("#DtIniEntregaDeCertificados").value = null;
    document.querySelector("#DtFimEntregaDeCertificados").value = null;
}

function consultaTodosCronograma() {
    $.ajax({
        method: "GET", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/cronograma_monitoria/"),
        headers: {
            "Authorization": localStorage.getItem("Authorization"),
        },
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        async: true,
        success: function (result, status, request) {
            if (result != "") {
                if (result != "null") {
                    preencheCamposTabela(result);
                }
            }
        },
        error: function (request, status, erro) {
            if (request.status = 500) {
                // REDIRECIONAR TELA LOGIN - NÃO ESTÁ AUTENTICADO
            }
        }
    });

}

function preencheCamposTabela(result) {
    $('#tabela').empty();
    for (var i = 0; result.length > i; i++) {
        //Adicionando registros retornados na tabela
        $('#tabela').append('<tr><td>' + result[i].id + '</td><td>' + formataData(result[i].dataEditalInicio) + ' - ' + formataData(result[i].dataEditalFim) + '</td><td>' + formataData(result[i].dataInscricaoInicio) + ' - ' + formataData(result[i].dataInscricaoFim) + '</td><td>' + formataData(result[i].dataPeriodoAvaliacaoInicio) + ' - ' + formataData(result[i].dataPeriodoAvaliacaoFim) + '</td><td>' + formataData(result[i].dataEntregaResultadosInicio) + ' - ' + formataData(result[i].dataEntregaResultadosFim) + '</td><td>' + formataData(result[i].dataPeriodoLetivoInicio) + ' - ' + formataData(result[i].dataPeriodoLetivoFim) + '</td></tr>');
    }
}

function preencheCamposTabelaPorCurso(result) {
    //Adicionando registros retornados na tabela
    $('#tabela').empty();
    $('#tabela').append('<tr><td>' + result.id + '</td><td>' + formataData(result.dataEditalInicio) + ' - ' + formataData(result.dataEditalFim) + '</td><td>' + formataData(result.dataInscricaoInicio) + ' - ' + formataData(result.dataInscricaoFim) + '</td><td>' + formataData(result.dataPeriodoAvaliacaoInicio) + ' - ' + formataData(result.dataPeriodoAvaliacaoFim) + '</td><td>' + formataData(result.dataEntregaResultadosInicio) + ' - ' + formataData(result.dataEntregaResultadosFim) + '</td><td>' + formataData(result.dataPeriodoLetivoInicio) + ' - ' + formataData(result.dataPeriodoLetivoFim) + '</td></tr>');

}

function formataData(datarec) {
    var data = moment(datarec, "YYYY-MM-DD");
    //Feito isso basta definir o formato de saída:
    return data.format("DD/MM/YYYY");
}
