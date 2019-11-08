$(document).ready(function () {
    $('#formulario').submit(function (e) {
        e.preventDefault();
        var form = document.forms[0];
        var formData = new FormData(form);

        limpaMensagensDeValidacao();

        if (validaInscricao()) {
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
                xhr: function () {
                    var xhr = $.ajaxSettings.xhr();
                    return xhr;
                },
                beforeSend: function (xhr) {

                }
            });
            ajaxReq.done(function (msg) {
                alert("Inscrição realizada com sucesso!")
            });
            ajaxReq.fail(function (jqXHR) {
                alert("Falha ao registrar inscrição!");
            });
        }
    });

    function validaInscricao() {
        var retorno = true;
        var nome = $('input[name="nome"]').val();
        var matricula = $('input[name="matricula"]').val();
        var curso = $('input[name="curso"]').val();
        var disciplina = $('input[name="disciplina"]').val();
        var orientador = $('input[name="orientador"]').val();
        var telefone = $('input[name="telefone"]').val();
        var email = $('input[name="email"]').val();
        var segunda = $('input[name="segunda"]').val();
        var terca = $('input[name="terca"]').val();
        var quarta = $('input[name="quarta"]').val();
        var quinta = $('input[name="quinta"]').val();
        var sexta = $('input[name="sexta"]').val();
        var sabado = $('input[name="sabado"]').val();
        var jaFoiBolsista = $('input:radio[name=jaFoiBolsista]:checked').val();  // S OU N
        var possuiBolsa = $('input:radio[name=possuiBolsa]:checked').val();      // S OU N
        var filePDF = $('#filePDF')[0].files;
        

        if (nome.length === "") {
            $("#mensagens ul").append("<li>O campo nome é obrigatório!</li><br/>");
            retorno = false;
        } else if (DtIniPublicacaoEdital > DtFimPublicacaoEdital) {
            $("#mensagens ul").append("<li>O campo data inicio de publicação de edital não pode ser maior que o campo data fim!</li><br/>");
            retorno = false;
        }

        if (!(returnTypeFile() === "pdf")) {
            retorno = false;
        }

        return retorno;
    }

    function returnTypeFile() {
        var file = $("#exampleFormControlFile1")[0].files;
        var nameFile = file[0].name.split(".");
        return nameFile.slice(-1)[0];
    }

    function limpaMensagensDeValidacao() {
        $('#mensagens ul li, #mensagens ul br').remove();
        $("#mensagens").hide();
    }
});