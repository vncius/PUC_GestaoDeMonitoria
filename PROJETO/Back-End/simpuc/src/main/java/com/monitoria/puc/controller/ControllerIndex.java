package com.monitoria.puc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@SuppressWarnings("rawtypes")
@RestController
public class ControllerIndex {
	
	@GetMapping(value = "/", produces = "text/html")
	public ResponseEntity init() {
		return new ResponseEntity<>(criaEstruturaHtml(), HttpStatus.OK);
	}
	
	public String criaEstruturaHtml() {
		StringBuilder html = new StringBuilder();
		html.append("<h1>API RestFULL Java</h1>");
		html.append("<h2>Sistema: Monitoria PUC</h2>");
		
		html.append("<table style='border: 1px solid black'>");
		html.append("<tr><th>Funcionalidade</th><th>End-Point</th><th>Type</th><th>Parametros</th></tr>");
		
		html.append(criaTable());
		html.append("</table>");
		return html.toString();
	}
	
	public String criaTable() {
		StringBuilder html = new StringBuilder();
		html.append(criaLinha("Usuário", "http://191.233.244.144:8080/usuario/", "GET", "3"));
		html.append(criaLinha("Autenticação", "http://191.233.244.144:8080/usuario/", "GET", "3"));	
		return html.toString();
	}
	
	public String criaLinha(String funcionalidade, String endPoint, String type, String parametros) {
		StringBuilder html = new StringBuilder();
		html.append(String.format("<tr><th>%s</th><th>%s</th><th>%s</th><th>%s</th></tr>", funcionalidade, endPoint, type, parametros));
		return html.toString();
	}
	
}