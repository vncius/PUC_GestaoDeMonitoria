$(document).ready(function () {
    if(usuarioEstaAutenticado() === false) { 
        redirecionarIndexLogin(); 
        return;
    } else {
        if (validaSeUsuarioLogado() === false) {
            redirecionarIndexLogin();
        } else {
            selecioneMenu(localStorage.getItem("Role"));
        }
    }
});

function validaSeUsuarioLogado() {
    if (localStorage.getItem("Matricula") === null) { return false; }
    if (localStorage.getItem("Role") === null) { return false; }
    if (localStorage.getItem("Authorization") === null) { return false; }
    if (localStorage.getItem("Course") === null) { return false; }
    return true;
}

function selecioneMenu(role) {
    switch (role) {
        case "ROLE_ALUNO":
            $("#div_menu").load(obterUrlDePaginas("menu/menu-aluno.html"));
            break;
        case "ROLE_PROFESSOR":
            $("#div_menu").load("menu/menu-professor.html");
            break;
        case "ROLE_COORD_CAEME":
            $("#div_menu").load("menu/menu-coordenacao-caeme.html");
            break;
        case "ROLE_COORD_CURSO":
            $("#div_menu").load("menu/menu-coordenacao-curso.html");
            break;
        case "ROLE_COORD_MONITORIA":
            $("#div_menu").load("menu/menu-coordenacao-monitoria.html");
            break;
        default:
            $("#div_menu").css("width", "100%");
            $("#div_menu").css("text-align", "center");
            $("#div_menu").css("font-weight", "bolder");
            $("#div_menu").css("font-size", "50px");
            $("#div_menu").append(`<h2>Erro 404</h2>`);
            $("#div_menu").append(`<h1>Página não encontrada</h1>`);
            break;
    }
}