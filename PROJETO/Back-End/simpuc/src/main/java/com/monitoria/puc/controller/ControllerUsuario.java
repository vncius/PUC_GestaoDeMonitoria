package com.monitoria.puc.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitoria.puc.model.UsuarioModel;
import com.monitoria.puc.repository.RepositoryUsuario;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@RequestMapping(value = "/usuario")
public class ControllerUsuario {

	@Autowired
	private RepositoryUsuario usuarioRepository;
	
	/*@GetMapping(value = "/cadastrar", produces = "application/json")
	public ResponseEntity cadastrar(@RequestParam(value = "nome", required = true, defaultValue="Nome não informado") String nome, 
			@RequestParam(value = "sobrenome", required = true, defaultValue="Sobrenome não informado") String sobrenome) {
		return new ResponseEntity(String.format("SEU NOME É: %s, e seu sobrenome é: %s", nome, sobrenome), HttpStatus.LOCKED);
	}*/
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<Iterable<UsuarioModel>> consultarTodosUsuarios() {
		Iterable<UsuarioModel> list = usuarioRepository.findAll();
		return ResponseEntity.ok(list); 
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Optional<UsuarioModel>> consultarPorId(@PathVariable(value = "id") Long id) {
		Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping(value = "/", produces="application/json")
	public ResponseEntity<UsuarioModel> cadastrar(@RequestBody UsuarioModel usuario) {
		UsuarioModel user = usuarioRepository.save(usuario);
		return  new ResponseEntity(user, HttpStatus.CREATED);
	}
}
