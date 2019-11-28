$(document).ready(function() {
    if (usuarioEstaAutenticado() === false) {
        //redirecionarIndexLogin();
        //return;
    } else {
        if (localStorage.getItem("Role") != "ROLE_") {
            //redirecionarMenuPrincipal();
        } else {
            //-------------------------------------------- VALIDAÇÕES PRÉ-CARREGAMENTO
            if (validacoes() === true) {
                buscarTodosOsAlunosInscritos();
            }
        }
    }
});

function buscarTodosOsAlunosInscritos() {
    // REQUISIÇÃO QUE BUSCARÁ TODOS OS ALUNOS INSCRITOS
    // SALVAR JSON NO FORMATO DE TEXTO EM LOCAL STORAGE
}

// ------------------------------------------------------- VALIDAÇÕES DE FUNCIONALIDADE
function validacoes() {
    var retorno = true;

    if (estaNoPeriodoDePublicarResultados() === false) {
        retorno = false;
    }

    return retorno;
}

function estaNoPeriodoDePublicarResultados() {
    // REQUISIÇÃO PARA VERIFICAR SE ESTÁ NO PERIODO DE PUBLICAR RESULTADOS
}
// ------------------------------------------------------------------------------------