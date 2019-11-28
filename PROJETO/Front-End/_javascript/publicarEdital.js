$(document).ready(function () {
    if (usuarioEstaAutenticado() === false) {
        redirecionarIndexLogin();
        return;
    } else {
        if (localStorage.getItem("Role") != "ROLE_COORD_MONITORIA" && localStorage.getItem("Role") != "ROLE_ADMIN") {
            redirecionarMenuPrincipal();
        } else {
            $('#formulario').submit(function (e) {
                e.preventDefault();
                var form = document.forms[0];
                var formData = new FormData(form);

                if (returnTypeFile() === "pdf") {
                    var ajaxReq = $.ajax({
                        url: obterUrlDaAPI("/edital/"),
                        headers: {
                            "Authorization": recuperaTokenParaRequisicao(),
                        },
                        type: 'POST',
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
                        exibaAlerta("Edital publicado com sucesso!")
                    });
                    ajaxReq.fail(function (jqXHR) {
                        exibaAlerta("Falha na publicação do Edital!");
                    });
                } else {
                    exibaAlerta("Formato de arquivo não suportado!");
                }
            });

            function returnTypeFile() {
                var file = $("#inputGroupFile01")[0].files;
                var nameFile = file[0].name.split(".");
                return nameFile.slice(-1)[0];
            }
        }
    }
});