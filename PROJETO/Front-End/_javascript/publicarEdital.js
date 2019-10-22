$(document).ready(function () {
    $('#formulario').submit(function (e) {
        e.preventDefault();
        var form = document.forms[0];
        var formData = new FormData(form);

        if (returnTypeFile() === "pdf") {
            var ajaxReq = $.ajax({
                url: 'http://localhost:8080/apimonitoria/edital/',
                headers: {
                    "Authorization": localStorage.getItem("Authorization"),
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
                alert("Edital publicado com sucesso!")
            });
            ajaxReq.fail(function (jqXHR) {
                alert("Falha na publicação do Edital!");
            });
        } else {
            alert("Formato de arquivo não suportado!");
        }
    });

    function returnTypeFile() {
        var file = $("#inputGroupFile01")[0].files;
        var nameFile = file[0].name.split(".");
        return nameFile.slice(-1)[0];
    }
});