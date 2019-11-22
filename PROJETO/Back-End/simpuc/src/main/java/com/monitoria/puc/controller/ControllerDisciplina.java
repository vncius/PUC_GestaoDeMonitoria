package com.monitoria.puc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitoria.puc.model.ModelDisciplina;
import com.monitoria.puc.service.DisciplinaService;

@RestController
@RequestMapping(value = "/disciplina")
public class ControllerDisciplina {

	@Autowired
	private DisciplinaService disciplinaService;

	@GetMapping("/curso/{id}")
	public ResponseEntity<List<ModelDisciplina>> listAllIdCurso(@PathVariable(name = "id") Long id) {
		List<ModelDisciplina> listaDeDisciplinas = disciplinaService.listAll(id);
		return ResponseEntity.ok(listaDeDisciplinas);
	}

	@PutMapping("/{id}/{qtdeVg}")
	public  ResponseEntity<List<ModelDisciplina>> update(@PathVariable(name = "id") Long id,@PathVariable(name = "qtdeVg") int qtdeVg) {
		
			int qtdeLinhaAfetadas = disciplinaService.updateVagas(id,qtdeVg);
			ModelDisciplina disciplina = disciplinaService.getById(id);
			List<ModelDisciplina> listaDeDisciplinas = disciplinaService.listAll(disciplina.getCurso().getId());
			return ResponseEntity.ok(listaDeDisciplinas);
	}
}
