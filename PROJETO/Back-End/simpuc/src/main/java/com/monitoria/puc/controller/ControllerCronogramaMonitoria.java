package com.monitoria.puc.controller;

import java.util.List;

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

import com.monitoria.puc.model.ModelCronogramaGeral;
import com.monitoria.puc.model.ModelCronogramaMonitoria;
import com.monitoria.puc.repository.RepositoryCronogramaGeral;
import com.monitoria.puc.service.CronogramaMonitoriaService;

@RestController
@RequestMapping(value = "/cronograma_monitoria")
public class ControllerCronogramaMonitoria {

	@Autowired
	private CronogramaMonitoriaService cronogramaMonitoriaService;

	@Autowired
	private RepositoryCronogramaGeral cronogramaGeralRepository;

	@PostMapping
	public ResponseEntity<String> save(@RequestBody ModelCronogramaMonitoria cronogramaMonitoria) throws Exception {
		Long idCronogramaGeral = cronogramaGeralRepository.findMaxIdCronogramaGeral();
		Boolean resultado = false;
		if (idCronogramaGeral != null) {
			ModelCronogramaGeral cronogramaGeral = cronogramaGeralRepository.findById(idCronogramaGeral).get();
			resultado = cronogramaMonitoria.validaCronogramaMonitoria(cronogramaGeral);
		}

		if (resultado) {
			try {

				cronogramaMonitoriaService.save(cronogramaMonitoria);
				return new ResponseEntity<String>("Cronograma monitoria salvo com sucesso.", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Houve uma erro interno da API", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>(
					"Cronograma de curso não é válido. Verifique se as datas estão dentro do cronograma geral!",
					HttpStatus.PARTIAL_CONTENT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable(name = "id") Long id,
			@RequestBody ModelCronogramaMonitoria cronogramaMonitoria) throws Exception {
		Long idCronogramaGeral = cronogramaGeralRepository.findMaxIdCronogramaGeral();
		Boolean resultado = false;
		if (idCronogramaGeral != null) {
			ModelCronogramaGeral cronogramaGeral = cronogramaGeralRepository.findById(idCronogramaGeral).get();
			resultado = cronogramaMonitoria.validaCronogramaMonitoria(cronogramaGeral);
		}

		if (resultado) {
			try {
				cronogramaMonitoria.setId(id);
				cronogramaMonitoriaService.save(cronogramaMonitoria);
				return new ResponseEntity<String>("Cronograma monitoria atualizado com sucesso.", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Houve uma erro interno da API", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>(
					"Cronograma de curso não é válido. Verifique se as datas estão dentro do cronograma geral!",
					HttpStatus.PARTIAL_CONTENT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ModelCronogramaMonitoria> getById(@PathVariable(name = "id") Long id) {
		ModelCronogramaMonitoria cronogramaMonitoria = cronogramaMonitoriaService.getById(id);
		return ResponseEntity.ok(cronogramaMonitoria);
	}

	@GetMapping
	public ResponseEntity<List<ModelCronogramaMonitoria>> getAll() {
		List<ModelCronogramaMonitoria> listaCronogramaMonitoria = cronogramaMonitoriaService.listAll();
		return ResponseEntity.ok(listaCronogramaMonitoria);
	}
}
