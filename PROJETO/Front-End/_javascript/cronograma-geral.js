$(document).ready(function () {
    if (usuarioEstaAutenticado() === false) {
        redirecionarIndexLogin();
        return;
    } else {
        carregarCronograma()
        $("#salvar").click(function (e) {
            limpaCamposInconsistencias()

            if (validaCronogramaGeral()) {
                var cronogramaGeral = JSON.stringify({
                    "publicacaoEdital_dtInicio": $('#DtIniPublicacaoEdital').val(),
                    "publicacaoEdital_dtFim": $('#DtFimPublicacaoEdital').val(),

                    "periodoInscricao_dtInicio": $('#DtIniPeriodoDeIncricao').val(),
                    "periodoInscricao_dtFim": $('#DtFimPeriodoDeIncricao').val(),

                    "periodoAvaliacao_dtInicio": $('#DtIniPeriodoAvaliacao').val(),
                    "periodoAvaliacao_dtFim": $('#DtFimPeriodoAvaliacao').val(),

                    "entregaDosResultados_dtInicio": $('#DtIniEntregaDeResultados').val(),
                    "entregaDosResultados_dtFim": $('#DtFimEntregaDeResultados').val(),

                    "periodoLetivo_dtInicio": $('#DtIniPeriodoLetivo').val(),
                    "periodoLetivo_dtFim": $('#DtFimPeriodoLetivo').val(),

                    "entregaDosCertificados_dtInicio": $('#DtIniEntregaDeCertificados').val(),
                    "entregaDosCertificados_dtFim": $('#DtFimEntregaDeCertificados').val()
                });

                $.ajax({
                    method: "PUT", // TIPO DE REQUISIÇÃO
                    url: obterUrlDaAPI("/cronogramaGeral/"),
                    headers: {
                        "Authorization": recuperaTokenParaRequisicao(),
                    },
                    dataType: "text",
                    contentType: "application/json;charset=UTF-8",
                    async: true,
                    data: cronogramaGeral,
                    success: function (result, status, request) {
                        limpaCamposInconsistencias()
                        if (request.status === 206) {
                            alert(result + "\nCode status request: " + request.status);
                        } else {
                            alert(result);
                        }
                    },
                    error: function (request, status, erro) {
                        if (request.status === 500) {
                            alert(status);
                        } else {
                            alert("Houve uma falha na requisição!");
                        }
                    }
                });
            }
        });

    }
});

function carregarCronograma() {
    $.ajax({
        method: "GET", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/cronogramaGeral/"), // END POINT DA API
        headers: {
            "Authorization": recuperaTokenParaRequisicao(),
        },
        dataType: "text",
        contentType: "application/json;charset=UTF-8",
        async: true,
        success: function (result, status, request) {
            if (result != "") {
                if (result != "null") {
                    preencheCamposDoCronogramaGeral(result);
                }
            }
        },
        error: function (request, status, erro) {
            if (request.status === 500) {
                alert(status);
            }
        }
    });
}

function preencheCamposDoCronogramaGeral(resultado) {
    var crononogramaGeral = JSON.parse(resultado);

    $('#DtIniPublicacaoEdital').val(crononogramaGeral.publicacaoEdital_dtInicio),
        $('#DtFimPublicacaoEdital').val(crononogramaGeral.publicacaoEdital_dtFim),

        $('#DtIniPeriodoDeIncricao').val(crononogramaGeral.periodoInscricao_dtInicio),
        $('#DtFimPeriodoDeIncricao').val(crononogramaGeral.periodoInscricao_dtFim),

        $('#DtIniPeriodoAvaliacao').val(crononogramaGeral.periodoAvaliacao_dtInicio),
        $('#DtFimPeriodoAvaliacao').val(crononogramaGeral.periodoAvaliacao_dtFim),

        $('#DtIniEntregaDeResultados').val(crononogramaGeral.entregaDosResultados_dtInicio),
        $('#DtFimEntregaDeResultados').val(crononogramaGeral.entregaDosResultados_dtFim),

        $('#DtIniPeriodoLetivo').val(crononogramaGeral.periodoLetivo_dtInicio),
        $('#DtFimPeriodoLetivo').val(crononogramaGeral.periodoLetivo_dtFim),

        $('#DtIniEntregaDeCertificados').val(crononogramaGeral.entregaDosCertificados_dtInicio),
        $('#DtFimEntregaDeCertificados').val(crononogramaGeral.entregaDosCertificados_dtFim)
}

function validaCronogramaGeral() {
    var retorno = true;
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
