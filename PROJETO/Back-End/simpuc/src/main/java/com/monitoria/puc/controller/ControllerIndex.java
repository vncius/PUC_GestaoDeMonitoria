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
		html.append("<h1 style='margin: auto; text-align:center;'>API RestFULL Java</h1>");
		html.append("<h2 style='margin: auto; text-align:center;'>Sistema: Monitoria PUC</h2>");
		return html.toString();
	}

}