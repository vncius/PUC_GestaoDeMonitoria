package com.monitoria.puc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitoria.puc.model.ModelUsuario;
import com.monitoria.puc.repository.RepositoryUsuario;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@CrossOrigin
@RestController
@RequestMapping(value = "/usuario")
public class ControllerUsuario {

	@Autowired
	private RepositoryUsuario usuarioRepository;

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<Iterable<ModelUsuario>> consultarTodosUsuarios() {
		Iterable<ModelUsuario> list = usuarioRepository.findAll();
		list.forEach(x -> {
			x.setSenha("**********");
		});
		return new ResponseEntity<Iterable<ModelUsuario>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/{matricula}", produces = "application/json")
	public ResponseEntity<ModelUsuario> consultarMatricula(@PathVariable(value = "matricula") String matricula) {
		ModelUsuario usuario = usuarioRepository.findUserByLogin(matricula);
		usuario.setSenha("**********");
		return new ResponseEntity<ModelUsuario>(usuario, HttpStatus.OK);
	}

	@PostMapping(value = "/", produces = "application/text")
	public ResponseEntity<String> cadastrar(@RequestBody ModelUsuario usuario) {
		Boolean resultado = usuario.validaSeCamposObrigatoriosPreenchidos();
		if (resultado) {
			try {
				usuarioRepository.save(usuario);
				return new ResponseEntity<String>(
						String.format("Usuário de matricula %s cadastrado.", usuario.getMatricula()),
						HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<String>("Houve uma erro interno da API", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("Dados obigatórios não preenchidos.", HttpStatus.PARTIAL_CONTENT);
		}
	}

	@PutMapping(value = "/", produces = "application/text")
	public ResponseEntity<String> atualizar(@RequestBody ModelUsuario usuario) {
		Boolean resultado = usuario.validaSeCamposObrigatoriosPreenchidos();
		if (resultado) {
			try {
				usuarioRepository.save(usuario);
				return new ResponseEntity<String>(
						String.format("Usuário de matricula %s foi atualizado", usuario.getMatricula()), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Houve uma erro interno da API", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("Dados obigatórios não preenchidos.", HttpStatus.PARTIAL_CONTENT);
		}
	}

	@DeleteMapping(value = "/{id}", produces = "application/text")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
		try {
			usuarioRepository.deleteById(id);
			return new ResponseEntity<String>(String.format("Usuário com id %s deletado", id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve uma erro interno da API", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/auth", produces = "application/json")
	public ResponseEntity<Boolean> usuarioEstaAutenticado() {
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

}
