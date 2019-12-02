package com.monitoria.puc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monitoria.puc.model.ModelCronogramaGeral;
import com.monitoria.puc.model.ModelCronogramaMonitoria;
import com.monitoria.puc.repository.RepositoryCronogramaGeral;
import com.monitoria.puc.repository.RepositoryCronogramaMonitoria;
import com.monitoria.puc.utilidades.DTODadosAvaliacao;
import com.monitoria.puc.utilidades.Utilidades;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 28/11/2019*/
@CrossOrigin
@RestController
@RequestMapping(value = "/publicarResultadoAvaliacoes")
public class ControllerPublicarResultadoAvaliacoes {

	@Autowired
	RepositoryCronogramaGeral cronogramaGeralRepository;
	
	@Autowired
	RepositoryCronogramaMonitoria cronogramaDeCursoRepository;
	
	@PostMapping(value = "/", produces = "application/text")
	public ResponseEntity<String> save(@RequestBody DTODadosAvaliacao dadosAvaliacao) {
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}

	 //----------------------------------------------------- CONSULTAS
	@GetMapping(value = "/validaPeriodoDeAvaliacao", produces = "application/text")
	public ResponseEntity<String> validaPeriodoDeAvaliacao() throws Exception {
		Long id = cronogramaGeralRepository.findMaxIdCronogramaGeral();
		ModelCronogramaGeral cronograma = cronogramaGeralRepository.findById(id).get();
		Date data_inicio = Utilidades.convertDataStringEmDate(cronograma.getPeriodoAvaliacao_dtInicio()).getTime();
		Date data_fim = Utilidades.convertDataStringEmDate(cronograma.getPeriodoAvaliacao_dtFim()).getTime();
		
		if(Utilidades.validaSeDataAtualEstaDentroDoPeriodo(data_inicio, data_fim)) {
			return new ResponseEntity<String>("true", HttpStatus.OK);
		}	else {
			return new ResponseEntity<String>("false", HttpStatus.OK);
		}	
	}
	
	@GetMapping(value = "/validaPeriodoDeAvaliacao/{idCurso}", produces = "application/text")
	public ResponseEntity<String> validaPeriodoDeAvaliacaoCurso(@PathVariable(value = "idCurso") long idCurso) {
		ModelCronogramaMonitoria cronograma = cronogramaDeCursoRepository.findByIdCurso(idCurso).get();
		Date data_inicio = cronograma.getDataPeriodoAvaliacaoInicio();
		Date data_fim = cronograma.getDataPeriodoAvaliacaoFim();
		
		if(Utilidades.validaSeDataAtualEstaDentroDoPeriodo(data_inicio, data_fim)) {
			return new ResponseEntity<String>("true", HttpStatus.OK);
		}	else {
			return new ResponseEntity<String>("false", HttpStatus.OK);
		}
	}
}