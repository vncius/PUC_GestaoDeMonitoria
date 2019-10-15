package com.monitoria.puc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitoria.puc.model.CronogramaMonitoria;
import com.monitoria.puc.model.Curso;
import com.monitoria.puc.service.CronogramaMonitoriaService;

@RestController
@RequestMapping(value = "/cronograma_monitoria")
public class ControllerCronogramaMonitoria {

	@Autowired
	private CronogramaMonitoriaService cronogramaMonitoriaService;
	
	@PostMapping
	public ResponseEntity<CronogramaMonitoria> save(@RequestBody CronogramaMonitoria cronogramaMonitoria) {
		CronogramaMonitoria cronogramaCriado = cronogramaMonitoriaService.save(cronogramaMonitoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(cronogramaCriado);
	}
	
}
