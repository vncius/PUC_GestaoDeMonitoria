package com.monitoria.puc.controller;


import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitoria.puc.model.ModelCronogramaGeral;

@CrossOrigin
@RestController
@RequestMapping(value = "/inscricaoMonitoria")
public class ControllerIncricaoMonitoria {

	long cronogramaMaisRecente;
	
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<Optional<ModelCronogramaGeral>> consultarInscricaoMonitoria() {
		return null;
	}
	
	@PutMapping(value = "/", produces="application/text")
	public ResponseEntity<String> atualizaInscricaoMonitoria() {
		return null;
	}
}
