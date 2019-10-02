package com.monitoria.puc.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitoria.puc.model.UsuarioModel;
import com.monitoria.puc.repository.RepositoryUsuario;

@RestController
@RequestMapping(value = "/usuario")
public class ControllerUsuario {

	@Autowired
	private RepositoryUsuario usuarioRepository;
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<Iterable<UsuarioModel>> consultarTodosUsuarios() {
		Iterable<UsuarioModel> list = usuarioRepository.findAll();
		return new ResponseEntity<Iterable<UsuarioModel>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Optional<UsuarioModel>> consultarPorId(@PathVariable(value = "id") Long id) {
		Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
		return new ResponseEntity<Optional<UsuarioModel>>(usuario, HttpStatus.OK);
	}
	
	@PostMapping(value = "/", produces="application/json")
	public ResponseEntity<String> cadastrar(@RequestBody UsuarioModel usuario) {
		usuario.setId(null); // CASO OBJ VENHA COM ID PREENCHIDO, ELE É ZERADO PARA VALIDAR QUE END-POINT É PARA CADASTRO
		Boolean resultado = usuario.validaSeCamposObrigatoriosPreenchidos();
		if (resultado) {
			try {
				usuarioRepository.save(usuario);
				return new ResponseEntity<String>("Usuário cadastrado.", HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<String>("Houve uma erro interno da API", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("Dados obigatórios não preenchidos.", HttpStatus.PARTIAL_CONTENT);
		}
	}

	@PutMapping(value = "/", produces="application/json")
	public ResponseEntity<String> atualizar(@RequestBody UsuarioModel usuario) {
		Boolean resultado = usuario.validaSeCamposObrigatoriosPreenchidos();
		if (resultado) {
			try {
				usuarioRepository.save(usuario);
				return new ResponseEntity<String>("Usuário atualizado.", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Houve uma erro interno da API", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("Dados obigatórios não preenchidos.", HttpStatus.PARTIAL_CONTENT);
		}
	}
}
