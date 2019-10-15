package com.monitoria.puc.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitoria.puc.model.ModelCronogramaGeral;
import com.monitoria.puc.repository.RepositoryCronogramaGeral;

@CrossOrigin
@RestController
@RequestMapping(value = "/cronogramaGeral")
public class ControllerCronogramaGeral {

	long cronogramaMaisRecente;
	
	@Autowired
	private RepositoryCronogramaGeral repositoryCronogramaGeral;
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<Optional<ModelCronogramaGeral>> consultaCronogramaGeral() {
		Long maiorId = repositoryCronogramaGeral.findMaxIdCronogramaGeral();
		Optional<ModelCronogramaGeral> cronograma = null;
		if (maiorId != null) {
			cronograma = repositoryCronogramaGeral.findById(maiorId);
		}
		return new ResponseEntity<Optional<ModelCronogramaGeral>>(cronograma, HttpStatus.OK);
	}
	
	@PutMapping(value = "/", produces="application/text")
	public ResponseEntity<String> atualizarCronogramaGeral(@RequestBody ModelCronogramaGeral cronogramaGeral) {
		Boolean resultado = cronogramaGeral.validaCronogramaGeral();
		if (resultado) {
			try {
				repositoryCronogramaGeral.save(cronogramaGeral);
				return new ResponseEntity<String>("Cronograma geral salvo com sucesso.", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Houve uma erro interno da API", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("Cronograma geral não válido. Existe data inicio maior que data fim.", HttpStatus.PARTIAL_CONTENT);
		}
	}
}
