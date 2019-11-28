package com.monitoria.puc.controller;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitoria.puc.model.ModelCronogramaMonitoria;
import com.monitoria.puc.model.ModelDisciplina;
import com.monitoria.puc.service.CronogramaMonitoriaService;
import com.monitoria.puc.service.DisciplinaService;
import com.monitoria.puc.service.DisponibilizaVagaService;

@RestController
@RequestMapping(value = "/disponibilizaVaga")
public class ControllerDisponibilizaVaga {

	@Autowired
	private DisciplinaService disciplinaService;

	@Autowired
	private CronogramaMonitoriaService cronogramaMonitoriaService;

	@Autowired
	private DisponibilizaVagaService disponibilizaVagaService;

	@GetMapping("/curso/{id}")
	public ResponseEntity<List<ModelDisciplina>> listAllIdCurso(@PathVariable(name = "id") Long id) {
		List<ModelDisciplina> listaDeDisciplinas = disciplinaService.listAll(id);
		return ResponseEntity.ok(listaDeDisciplinas);
	}

	@PutMapping()
	public ResponseEntity<List<ModelDisciplina>> update(@RequestBody ModelDisciplina disciplina) {
		Date dataAtual = new Date();
		ModelCronogramaMonitoria cronogramaBuscado;
		ModelDisciplina disciplinaBuscada = disciplinaService.getById(disciplina.getId());
		List<ModelDisciplina> listaDisciplinas = null;
		disciplinaBuscada.setQtdeVgMonitoria(disciplina.getQtdeVgMonitoria());
		cronogramaBuscado = cronogramaMonitoriaService.getById(disciplinaBuscada.getCurso().getId());
		if (dataAtual.before(cronogramaBuscado.getDataEditalInicio())) {
			ModelDisciplina disciplinaUpdate = disponibilizaVagaService.updateVagas(disciplinaBuscada);
			listaDisciplinas = disciplinaService.listAll(disciplinaBuscada.getCurso().getId());
			return ResponseEntity.ok(listaDisciplinas);
		}
		listaDisciplinas = disciplinaService.listAll(disciplinaBuscada.getCurso().getId());
		return ResponseEntity.status(HttpStatus.FOUND).body(listaDisciplinas);
	}

	@GetMapping("/dtaAtualMenorDtaEditalInicio/{id}")
	public ResponseEntity<String> verificaSeDataAtualMenorDataEditalInicio(@PathVariable(name = "id") Long id) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		Date dataAtual = new Date();
		try {
			ModelCronogramaMonitoria cronogramaBuscado = cronogramaMonitoriaService.getById(id);
			if (dataAtual.before(cronogramaBuscado.getDataEditalInicio())) {
				return new ResponseEntity<String>("true", HttpStatus.OK);
			} else if (dataAtual.after(cronogramaBuscado.getDataEditalInicio())) {
				return new ResponseEntity<String>("false", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Não existe cronograma para este curso",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Período de disponibilização de vagas encerrado!",
				HttpStatus.PARTIAL_CONTENT);

	}
}
