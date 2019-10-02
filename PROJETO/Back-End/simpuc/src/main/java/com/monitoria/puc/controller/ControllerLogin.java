package com.monitoria.puc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitoria.puc.model.UsuarioModel;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@RequestMapping(value = "/login")
public class ControllerLogin {
	
	@PostMapping(value = "/", produces="text/plain") //PRODUCES = TIPO DE RETORNO DO END-POINT
	public ResponseEntity<String> autenticacao(@RequestBody UsuarioModel usuario) {
		
		/*IMPLEMENTAR METODO DE AUTENTICAÇÃO E SESSION*/
		return  new ResponseEntity("123456789", HttpStatus.OK);
	}
	
	public static boolean validaUsuarioAutenticado(String codigo) {
		
		/*IMPLEMENTAR METODO DE VERIFICAR SE USUARIO ESTA AUTENTICADO*/
		return codigo.equals("123456") ? true : false;
	}
}
