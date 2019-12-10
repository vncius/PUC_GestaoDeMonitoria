package com.monitoria.puc.controller;

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

import com.monitoria.puc.model.ModelDisciplina;
import com.monitoria.puc.model.ModelVaga;
import com.monitoria.puc.service.DisciplinaService;
import com.monitoria.puc.service.SolicitaVagaService;

@RestController
@RequestMapping(value = "/solicitaVaga")
public class ControllerSolicitaVagaDisciplina {

	@Autowired
	private SolicitaVagaService solicitaVaga;

	@Autowired
	private DisciplinaService disciplinaService;

	@PostMapping("disciplina/{id}")
	public ResponseEntity<String> save(@PathVariable(name = "id") Long id, @RequestBody ModelVaga vagaDisciplina) {
		ModelDisciplina modelDisciplina = disciplinaService.getById(id);
		vagaDisciplina.setModelDisciplina(modelDisciplina);
		ModelDisciplina disciplina = null;
		int qtdeSomada = 0;
		try {
			qtdeSomada = solicitaVaga.somaQtdeVgSolicitada(vagaDisciplina.getModelDisciplina().getId());
			if (qtdeSomada < modelDisciplina.getQtdeVgMonitoria() && modelDisciplina.getQtdeVgMonitoria() != 0) {
				try {
					ModelVaga modelVaga = solicitaVaga.getVagaUserDisciplina(vagaDisciplina.getUsuario_id(),
							vagaDisciplina.getModelDisciplina().getId());
					// Se já existir um cadastro de uma solicitação ele retorna - 203
					modelVaga.getId();
					return new ResponseEntity<String>("Você já Solicitou Vagas anteriormente tente editar!",
							HttpStatus.NON_AUTHORITATIVE_INFORMATION);
				} catch (Exception e) {
					solicitaVaga.save(vagaDisciplina);
					qtdeSomada = solicitaVaga.somaQtdeVgSolicitada(vagaDisciplina.getModelDisciplina().getId());
					disciplinaService.updateQtdeVgDisp(modelDisciplina.getQtdeVgDisponiveis() - qtdeSomada,
							vagaDisciplina.getModelDisciplina().getId());
					return new ResponseEntity<String>("Vagas solicitas com sucesso!", HttpStatus.OK);
				}

			} else {
				// Se atingir a quantidade de vagas disponibilizadas retorna 206
				return new ResponseEntity<String>("Não existe vagas suficientes para essa solicitação!",
						HttpStatus.PARTIAL_CONTENT);
			}
		} catch (Exception e) {
			solicitaVaga.save(vagaDisciplina);
			qtdeSomada = solicitaVaga.somaQtdeVgSolicitada(vagaDisciplina.getModelDisciplina().getId());
			disciplinaService.updateQtdeVgDisp(modelDisciplina.getQtdeVgDisponiveis() - qtdeSomada,
					vagaDisciplina.getModelDisciplina().getId());
			return new ResponseEntity<String>("Vagas solicitas com sucesso!", HttpStatus.OK);
		}
	}

	@GetMapping("disciplina/{idDisciplina}/usuario/{idUsuario}")
	public ResponseEntity<ModelVaga> buscaVagaUsuario(@PathVariable(name = "idUsuario") String idUsuario,
			@PathVariable(name = "idDisciplina") Long idDisciplina) {
		ModelVaga vagaBuscada = solicitaVaga.getVagaUserDisciplina(idUsuario, idDisciplina);
		try {
			if (vagaBuscada.getId() != null) {
				return ResponseEntity.status(HttpStatus.OK).body(vagaBuscada);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(vagaBuscada);
		}
		return null;
	}

	@PutMapping("disciplina/{id}")
	public ResponseEntity<String> update(@PathVariable(name = "id") Long id, @RequestBody ModelVaga vagaDisciplina) {
		ModelDisciplina modelDisciplina = disciplinaService.getById(id);
		ModelVaga vagaBuscada = solicitaVaga.getVagaUserDisciplina(vagaDisciplina.getUsuario_id(), id);
		vagaDisciplina.setId(vagaBuscada.getId());
		vagaDisciplina.setModelDisciplina(modelDisciplina);
		int qtdeSubtraida = 0;
		if (vagaDisciplina.getQtdeVgSolicitada() > vagaBuscada.getQtdeVgSolicitada()) {
			qtdeSubtraida = vagaDisciplina.getQtdeVgSolicitada() - vagaBuscada.getQtdeVgSolicitada();
			if (qtdeSubtraida <= modelDisciplina.getQtdeVgDisponiveis()) {
				solicitaVaga.save(vagaDisciplina);
				disciplinaService.updateQtdeVgDisp(modelDisciplina.getQtdeVgDisponiveis() - qtdeSubtraida, id);
				return new ResponseEntity<String>("Alteração realizada com sucesso!", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Não existe Quantidade de Vagas Disponiveis!",
						HttpStatus.PARTIAL_CONTENT);
			}
		} else {
			qtdeSubtraida = vagaBuscada.getQtdeVgSolicitada() - vagaDisciplina.getQtdeVgSolicitada();
			solicitaVaga.save(vagaDisciplina);
			disciplinaService.updateQtdeVgDisp(modelDisciplina.getQtdeVgDisponiveis() + qtdeSubtraida, id);
			return new ResponseEntity<String>("Alteração realizada com sucesso!", HttpStatus.OK);
		}
	}

}
