$(document).ready(function () {
    if (usuarioEstaAutenticado() === false) {
        redirecionarIndexLogin();
        return;
    } else {
        if (localStorage.getItem("Role") != "ROLE_ALUNO" && localStorage.getItem("Role") != "ROLE_ADMIN") {
            redirecionarMenuPrincipal();
        } else {
            //-------------------------------------------- VALIDAÇÕES PRÉ-CARREGAMENTO
            var cursoEstaNoPeriodo = verificaSeCursoEstaNoPeriodoDeInscricao();

            if (cursoEstaNoPeriodo) {

                var dadosInscricao = buscaDadosInscricao();

                if (dadosInscricao != null && dadosInscricao != "null") {
                    registraDadosPreCadastradosInscricao(dadosInscricao);
                    preencheFormulario(dadosInscricao);
                    habilitaCamposNecessariosInicializacao();
                    carregaElementos();
                } else {
                    registraInconsistencia("Não foi possivel inicializar o formulário de inscrição!");
                    desabilitaTodosOsCamposDoForm();
                }
            }

        }
    }
});

function carregaElementos() { // --------------------------------------- CARREGA FUNÇÕES DE PROCESSAMENTO
    $('#formulario').submit(function (e) {
        e.preventDefault();
        var dadosIncricao = recuperaDadosPreCadastradosInscricao();

        if (dadosIncricao.statusIncricao != "CANCELADA") {
            dadosIncricao.statusIncricao = "PENDENTE";
            registraDadosPreCadastradosInscricao(dadosIncricao);
            salvarInscricao();
            recarregarPagina();
        } else {
            dadosIncricao.statusIncricao = "PENDENTE";
            limpaCamposInconsistencias();
            $("#salvar").text("Salvar");
            habilitaTodosCamposDoFormulario();
            registraInconsistencia("Anexe os documentos obrigatórios para reativar inscrição!");
            registraDadosPreCadastradosInscricao(dadosIncricao);
        }
    });

    $("#disciplina").change(function () {
        $('#orientador option').remove();
        var id_disciplina_selecionada = parseInt($('select[name="disciplina"] option:selected').val());
        var dadosIncricao = recuperaDadosPreCadastradosInscricao();

        dadosIncricao.cursos.disciplinas.forEach(disciplina => {
            if (disciplina.id === id_disciplina_selecionada) {
                limpaCamposInconsistencias();
                preencheComboOrientadores(disciplina.orientadores);
            }
        });
    });

    $("#cancelar").on("click", function () {
        if (confirm('Confirmar cancelamento de inscrição?')) {
            var dadosIncricao = recuperaDadosPreCadastradosInscricao();
            if (cancelarInscricaoDeAluno(dadosIncricao.id)) {
                recarregarPagina();
            }
        }
    });

    $("#editar").on("click", function () {
        habilitaTodosCamposDoFormulario();
        $("#cancelar").removeAttr('disabled');
        $("#salvar").removeAttr('disabled');
        $("#salvar").val("Salvar");
    });

    $("#imprimir").on("click", function () {
        imprimir();
    });
}

// ----------------------------- FUNÇÕES

function habilitaCamposNecessariosInicializacao() {
    var preInscricao = recuperaDadosPreCadastradosInscricao();

    if (preInscricao.statusIncricao === "PENDENTE") {
        desabilitaTodosOsCamposDoForm();
        $("#cancelar").attr('disabled', 'disabled');;
        $("#salvar").attr('disabled', 'disabled');
    } else if (preInscricao.statusIncricao === "CANCELADA") {
        desabilitaTodosOsCamposDoForm();
        desabilitaButtons(false, true);
        registraInconsistencia("Campos desabilitados pois a inscrição se encontra cancelada!");
    } else if (preInscricao.statusIncricao === null || preInscricao.statusIncricao === "null") {
        habilitaTodosCamposDoFormulario();
        $("#cancelar").attr('disabled', 'disabled');;
        $("#editar").attr('disabled', 'disabled');
        $("#imprimir").attr('disabled', 'disabled');
    }
}


function imprimir() {
    executaParametrizacaoParaImprimir();
}

function salvarInscricao() {
    var formData = criaFormularioParaSalvar();

    var ajaxReq = $.ajax({
        url: obterUrlDaAPI("/fichaInscricao/"),
        headers: {
            "Authorization": recuperaTokenParaRequisicao(),
        },
        type: 'PUT',
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        async: false,
        xhr: function () {
            var xhr = $.ajaxSettings.xhr();
            return xhr;
        }
    });
    ajaxReq.done(function (msg) {
        exibaAlerta(msg);
        removeDadosPreCadastradosInscricao(); // REMOVE JSON DE PREINSCRICAO DO LOCALSTORAGE 
    });
    ajaxReq.fail(function (jqXHR) {
        exibaAlerta("Falha ao registrar inscrição!");
    });
}

function verificaSeCursoEstaNoPeriodoDeInscricao() {
    var retorno = false;
    $.ajax({
        method: "GET", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/fichaInscricao/EstaNoCronograma/" + recuperaMatriculaAluno()),
        headers: {
            "Authorization": recuperaTokenParaRequisicao(),
        },
        dataType: "text", // SE JSON JÁ CONVERTE O RESULT EM OBJETO
        async: false,
        success: function (result, status, request) {
            if (result === "true" && request.status === 200) {
                retorno = true;
            } else if (result != "true" && request.status === 206) {
                registraInconsistencia(result);
                desabilitaTodosOsCamposDoForm();
                desabilitaButtons(true, false);
                retorno = false;
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
            desabilitaTodosOsCamposDoForm();
            desabilitaButtons(true, false);
            retorno = false;
        }
    });
    return retorno;
}

function cancelarInscricaoDeAluno(idInscricao) {
    var retorno = false;
    $.ajax({
        method: "DELETE", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/fichaInscricao/" + idInscricao),
        headers: {
            "Authorization": recuperaTokenParaRequisicao(),
        },
        dataType: "text", // JSON JÁ CONVERTE O RESULT EM OBJETO
        async: false,
        success: function (result, status, request) {
            exibaAlerta(result);
            retorno = true;
        },
        error: function (request, status, erro) {
            if (request.status === 500) {
                exibaAlerta(status);
            } else {
                exibaAlerta("Houve uma falha na requisição!");
            }
            retorno = false;
        }
    });
    return retorno;
}

function criaFormularioParaSalvar() {
    var form = document.forms[0];
    var formData = new FormData(form);

    var dadosInscricao = recuperaDadosPreCadastradosInscricao();
    var id_disciplina_selecionada = $('select[name="disciplina"] option:selected').val();
    var id_orientador_selecionado = $('select[name="orientador"] option:selected').val();

    // ADICIONANDO INFORMAÇÕES COMPLEMENTARES AO FORM
    if (dadosInscricao.id != null) {
        formData.append("id", dadosInscricao.id);
    }

    formData.append("id_curso", dadosInscricao.cursos.id);
    formData.append("id_disciplina", id_disciplina_selecionada);
    formData.append("id_orientador", id_orientador_selecionado);

    if ($('input:radio[name=InputJaFoiBolsista]:checked').val() === "S") {
        formData.append("jaPossuiuBolsa", "true");
    } else {
        formData.append("jaPossuiuBolsa", "false");
    }

    if ($('input:radio[name=InputPossuiBolsa]:checked').val() === "S") {
        formData.append("possuiBolsa", "true");
    } else {
        formData.append("possuiBolsa", "false");
    }

    // REMOVENDO ITENS DO FORMULÁRIO
    formData.delete("curso");
    formData.delete("disciplina");
    formData.delete("orientador");
    formData.delete("InputJaFoiBolsista");
    formData.delete("InputPossuiBolsa");
    return formData;
}

function preencheFormulario() {
    var dadosForm = recuperaDadosPreCadastradosInscricao();

    var matricula = dadosForm.matricula;
    var telefone = dadosForm.telefone;
    var possuiBolsa = dadosForm.possuiBolsa;
    var jaPossuiuBolsa = dadosForm.jaPossuiuBolsa;
    var nome = dadosForm.nome;
    var email = dadosForm.email;
    var carga_horaria_segunda = dadosForm.carga_horaria_segunda;
    var carga_horaria_terca = dadosForm.carga_horaria_terca;
    var carga_horaria_quarta = dadosForm.carga_horaria_quarta;
    var carga_horaria_quinta = dadosForm.carga_horaria_quinta;
    var carga_horaria_sexta = dadosForm.carga_horaria_sexta;
    var carga_horaria_sabado = dadosForm.carga_horaria_sabado;
    var descricaoCurso = dadosForm.cursos.descricao;
    var disciplinas = dadosForm.cursos.disciplinas;

    $('input[name="nome"]').val(nome);
    $('input[name="matricula"]').val(matricula);
    $('input[name="curso"]').val(descricaoCurso);
    preencheComboDisciplinas(disciplinas);
    $('input[name="telefone"]').val(telefone);
    $('input[name="email"]').val(email);
    $('input[name="carga_horaria_segunda"]').val(carga_horaria_segunda);
    $('input[name="carga_horaria_terca"]').val(carga_horaria_terca);
    $('input[name="carga_horaria_quarta"]').val(carga_horaria_quarta);
    $('input[name="carga_horaria_quinta"]').val(carga_horaria_quinta);
    $('input[name="carga_horaria_sexta"]').val(carga_horaria_sexta);
    $('input[name="carga_horaria_sabado"]').val(carga_horaria_sabado);

    if (possuiBolsa) {
        $('#InputPossuiBolsaS').prop('checked', true);
        $('#InputPossuiBolsaN').prop('checked', false);
    } else {
        $('#InputPossuiBolsaS').prop('checked', false);
        $('#InputPossuiBolsaN').prop('checked', true);
    }

    if (jaPossuiuBolsa) {
        $('#InputJaFoiBolsistaS').prop('checked', true);
        $('#InputJaFoiBolsistaN').prop('checked', false);
    } else {
        $('#InputJaFoiBolsistaS').prop('checked', false);
        $('#InputJaFoiBolsistaN').prop('checked', true);
    }
}

function preencheComboDisciplinas(disciplinas) {
    var existeDisciplinaSelecionada = false;

    if (disciplinas.length > 0) {
        disciplinas.forEach(disciplina => {
            if (disciplina.ehSelecionado != true) {
                $('#disciplina').append(`<option value='${disciplina.id}'>${disciplina.descricao}</option>`);
            } else {
                existeDisciplinaSelecionada = true;
                $('#disciplina').append(`<option selected value='${disciplina.id}'>${disciplina.descricao}</option>`);
                preencheComboOrientadores(disciplina.orientadores);
            }
        });

        if (!existeDisciplinaSelecionada) {
            $('#orientador option').remove();
            $("#disciplina").prepend(`<option selected value="0">Selecione</option>`);
            $("#orientador").prepend(`<option selected value="0">Selecione uma disciplina</option>`);
            $('#orientador').attr('disabled', 'disabled');
        }
    } else {
        desabilitaTodosOsCamposDoForm();
        registraInconsistencia("Não há disciplinas disponiveis para este curso!")
    }
}

function preencheComboOrientadores(orientadores) {
    $('#orientador option').remove();
    var existeOrientadorSelecionado = false;

    if (orientadores.length > 0) {
        orientadores.forEach(orientador => {
            if (orientador.ehSelecionado != true) {
                $('#orientador').append(`<option value='${orientador.id}'>${orientador.descricao}</option>`);
            } else {
                existeOrientadorSelecionado = true;
                $('#orientador').append(`<option selected value='${orientador.id}'>${orientador.descricao}</option>`);
            }
        });
        if (!existeOrientadorSelecionado) {
            $("#orientador").prepend(`<option selected value="0">Selecione</option>`);
        }
        $('#orientador').removeAttr('disabled');
    } else {
        $('#orientador').attr('disabled', 'enabled');
        registraInconsistencia("Não há orientadores para esta disciplina!");
    }
}

function buscaDadosInscricao() {
    var retorno = null;
    $.ajax({
        method: "GET", // TIPO DE REQUISIÇÃO
        url: obterUrlDaAPI("/fichaInscricao/" + recuperaMatriculaAluno()),
        headers: {
            "Authorization": recuperaTokenParaRequisicao(),
        },
        dataType: "JSON", // JSON JÁ CONVERTE O RESULT EM OBJETO
        async: false,
        success: function (result, status, request) {
            if (result != "") {
                if (result != "null") {
                    retorno = result;
                }
            }
        },
        error: function (request, status, erro) {
            if (request.status === 500) {
                exibaAlerta(status);
            } else {
                exibaAlerta("Houve uma falha na requisição!");
            }
            desabilitaTodosOsCamposDoForm();
            retorno = null;
        }
    });
    return retorno;
}

/* ------------------------------------ DADOS LOCALSTORAGE --------------------------------------*/

function recuperaDadosPreCadastradosInscricao() {
    return JSON.parse(localStorage.getItem("dadosInscricao"));
}

function registraDadosPreCadastradosInscricao(dadosInscricao) {
    localStorage.removeItem("dadosInscricao");
    localStorage.setItem("dadosInscricao", JSON.stringify(dadosInscricao));
}


function removeDadosPreCadastradosInscricao() {
    localStorage.removeItem("dadosInscricao");
}

function recarregarPagina() {
    $(location).attr('href', obterUrlDePaginas("inscricao.html"));
}

/* ------------------------------------ HABILITA/DESABILITA CAMPOS --------------------------------------*/

function desabilitaTodosOsCamposDoForm() {
    $('select[name=disciplina]').attr('disabled', 'disabled');
    $('select[name=orientador]').attr('disabled', 'disabled');
    $('#telefone').attr('disabled', 'disabled');
    $('#exampleInputEmail1').attr('disabled', 'disabled');
    $('input[name=carga_horaria_segunda]').attr('disabled', 'disabled');
    $('input[name=carga_horaria_terca]').attr('disabled', 'disabled');
    $('input[name=carga_horaria_quarta]').attr('disabled', 'disabled');
    $('input[name=carga_horaria_quinta]').attr('disabled', 'disabled');
    $('input[name=carga_horaria_sexta]').attr('disabled', 'disabled');
    $('input[name=carga_horaria_sabado]').attr('disabled', 'disabled');
    $('input[name=InputJaFoiBolsista]').attr('disabled', 'disabled');
    $('input[name=InputPossuiBolsa]').attr('disabled', 'disabled');
    $('input[name=file]').attr('disabled', 'disabled');
}

function habilitaTodosCamposDoFormulario() {
    $('select[name=disciplina]').removeAttr('disabled');
    $('select[name=orientador]').removeAttr('disabled');
    $('#telefone').removeAttr('disabled');
    $('#exampleInputEmail1').removeAttr('disabled');
    $('input[name=carga_horaria_segunda]').removeAttr('disabled');
    $('input[name=carga_horaria_terca]').removeAttr('disabled');
    $('input[name=carga_horaria_quarta]').removeAttr('disabled');
    $('input[name=carga_horaria_quinta]').removeAttr('disabled');
    $('input[name=carga_horaria_sexta]').removeAttr('disabled');
    $('input[name=carga_horaria_sabado]').removeAttr('disabled');
    $('input[name=InputJaFoiBolsista]').removeAttr('disabled');
    $('input[name=InputPossuiBolsa]').removeAttr('disabled');
    $('input[name=file]').removeAttr('disabled');
}

function desabilitaButtons(ehForaDoPeriodo, ehInscricaoCancelada) {
    $("#cancelar").attr('disabled', 'disabled');;
    $("#editar").attr('disabled', 'disabled');
    $("#imprimir").attr('disabled', 'disabled');

    if (ehForaDoPeriodo) {
        $("#salvar").attr('disabled', 'disabled');
    } else if (ehInscricaoCancelada) {
        $("#salvar").text("Reativar");
    }
}

function desabilitaButtonsForaDoPeriodo() {
    $("#cancelar").attr('disabled', 'disabled');;
    $("#editar").attr('disabled', 'disabled');
    $("#imprimir").attr('disabled', 'disabled');
    $("#salvar").attr('disabled', 'disabled');
}

function desabilitaButtonsInscricaoCancelada() {
    $("#cancelar").attr('disabled', 'disabled');
    $("#editar").attr('disabled', 'disabled');
    $("#imprimir").attr('disabled', 'disabled');
    $("#salvar").text("Reativar");
}

function executaParametrizacaoParaImprimir() {
    $("#cancelar").hide();
    $("#editar").hide();
    $("#salvar").hide();
    $("#imprimir").hide();
    $("#div_documentos").hide();

    var inscricao = recuperaDadosPreCadastradosInscricao();
    $("#div_impresao").show();
    var status = inscricao.statusIncricao;
    $("#div_impresao").css("font-family", "Arial");
    $("#div_impresao").css("font-weight", "bold");
    $("#div_impresao").css("color", "white");
    $("#div_impresao").css("text-align", "center");
    $("#div_impresao").css("font-size", "35px");
    $("#div_impresao").append(`<p>STATUS: ${status}</p>`);

    window.print();

    $("#div_impresao p").remove();
    $("#div_impresao").hide();

    $("#cancelar").show();
    $("#editar").show();
    $("#salvar").show();
    $("#imprimir").show();
    $("#div_documentos").show();
    $("#").show();
    $("#").show();
}






