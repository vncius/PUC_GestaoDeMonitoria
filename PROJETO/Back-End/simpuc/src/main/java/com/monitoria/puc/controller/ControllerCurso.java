package com.monitoria.puc.controller;

import java.util.List;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitoria.puc.model.ModelCurso;
import com.monitoria.puc.service.CursoService;

@RestController
@RequestMapping(value = "/curso")
public class ControllerCurso {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public ResponseEntity<List<ModelCurso>> listAll() {
		List<ModelCurso>listaDeCursos = cursoService.listAll();
		return ResponseEntity.ok(listaDeCursos);
	}

}
