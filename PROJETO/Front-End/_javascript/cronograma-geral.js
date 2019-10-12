$(document).ready(function(){
    $("#salvar").click(function(e){

        if(validaCronogramaGeral()){
            var cronogramaGeral = JSON.stringify({
                "publicacaoEdital_dtInicio": $('#DtIniPublicacaoEdital').val(),
                "publicacaoEdital_dtFim": $('#DtFimPublicacaoEdital').val(),
                
                "periodoInscricao_dtInicio": $('#DtIniPeriodoDeIncricao').val(),
                "periodoInscricao_dtFim": $('#DtFimPeriodoDeIncricao').val(),
                
                "periodoAvaliacao_dtInicio": $('#DtIniPeriodoAvaliacao').val(),
                "periodoAvaliacao_dtFim": $('#DtFimPeriodoAvaliacao').val(),
                
                "entregaDosResultados_dtInicio": $('#DtIniEntragaDeResultados').val(),
                "entregaDosResultados_dtFim": $('#DtFimEntregaDeResultadoss').val(),
                
                "periodoLetivo_dtInicio": $('#DtIniPeriodoLetivo').val(),
                "periodoLetivo_dtFim": $('#DtFimPeriodoLetivo').val(),
                
                "entregaDosCertificados_dtInicio": $('#DtIniEntregaDeCertificados').val(),
                "entregaDosCertificados_dtFim": $('#DtFimEntregaDeCertificados').val()
            })

            $.ajax({
                method: "PUT", // TIPO DE REQUISIÇÃO
                url: "http://localhost:8080/apimonitoria/cronogramaGeral/", // END POINT DA API
                headers: {
                    "Authorization": localStorage.getItem("Authorization"),
               },
                dataType: "text",
                contentType: "application/json;charset=UTF-8",
                async: true,
                data: cronogramaGeral,
                success: function (result, status, request) {
                    if(request.status = 206){
                        alert(result+"\nCode status request: "+request.status);
                    } else {
                        alert(result);
                    }
                },
                error: function (request, status, erro) {
                    if(request.status = 400){
                        alert("Todos os campos devem ser preenchidos! \nCode status request: "+request.status);
                    }
                }
            });
        }
	});
	
	function validaCronogramaGeral(){
		var DtIniPublicacaoEdital = $('#DtIniPublicacaoEdital').val();
		var DtFimPublicacaoEdital = $('#DtFimPublicacaoEdital').val();
		
		var DtIniPeriodoDeIncricao = $('#DtIniPeriodoDeIncricao').val();
		var DtFimPeriodoDeIncricao = $('#DtFimPeriodoDeIncricao').val();
		
		var DtIniPeriodoAvaliacao = $('#DtIniPeriodoAvaliacao').val();
		var DtFimPeriodoAvaliacao = $('#DtFimPeriodoAvaliacao').val();
		
		var DtIniEntragaDeResultados = $('#DtIniEntragaDeResultados').val();
		var DtFimEntregaDeResultadoss = $('#DtFimEntregaDeResultadoss').val();
		
		var DtIniPeriodoLetivo = $('#DtIniPeriodoLetivo').val();
		var DtFimPeriodoLetivo = $('#DtFimPeriodoLetivo').val();

		var DtIniEntregaDeCertificados = $('#DtIniEntregaDeCertificados').val();
        var DtFimEntregaDeCertificados = $('#DtFimEntregaDeCertificados').val();
        
        return true
	}
});