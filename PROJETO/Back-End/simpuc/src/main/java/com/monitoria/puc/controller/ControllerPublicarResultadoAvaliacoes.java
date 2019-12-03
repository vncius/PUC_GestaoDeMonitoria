package com.monitoria.puc.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitoria.puc.model.ModelCronogramaGeral;
import com.monitoria.puc.model.ModelCronogramaMonitoria;
import com.monitoria.puc.model.ModelCurso;
import com.monitoria.puc.model.ModelDisciplina;
import com.monitoria.puc.model.ModelInscricaoMonitoria;
import com.monitoria.puc.repository.RepositoryCronogramaGeral;
import com.monitoria.puc.repository.RepositoryCronogramaMonitoria;
import com.monitoria.puc.repository.RepositoryCurso;
import com.monitoria.puc.repository.RepositoryDisciplina;
import com.monitoria.puc.repository.RepositoryFichaDeInscricao;
import com.monitoria.puc.utilidades.Constantes;
import com.monitoria.puc.utilidades.DTODadosAvaliacao;
import com.monitoria.puc.utilidades.DTOResultadoAvaliacoes;
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
	RepositoryFichaDeInscricao fichaInscricaoRepository;
	
	@Autowired
	RepositoryCronogramaMonitoria cronogramaDeCursoRepository;
	
	@Autowired
	RepositoryDisciplina disciplinaRepository;
	
	@Autowired
	RepositoryCurso cursoRepository;

	@PostMapping(value = "/", produces = "application/text")
	public ResponseEntity<String> save(@RequestBody DTODadosAvaliacao dadosAvaliacao) {
		avalieAlunosInscritos(dadosAvaliacao);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}

	// ----------------------------------------------------- CONSULTAS
	@GetMapping(value = "/validaPeriodoDeAvaliacao", produces = "application/text")
	public ResponseEntity<String> validaPeriodoDeAvaliacao() throws Exception {
		Long id = cronogramaGeralRepository.findMaxIdCronogramaGeral();
		ModelCronogramaGeral cronograma = cronogramaGeralRepository.findById(id).get();
		Date data_inicio = Utilidades.convertDataStringEmDate(cronograma.getPeriodoAvaliacao_dtInicio()).getTime();
		Date data_fim = Utilidades.convertDataStringEmDate(cronograma.getPeriodoAvaliacao_dtFim()).getTime();

		if (Utilidades.validaSeDataAtualEstaDentroDoPeriodo(data_inicio, data_fim)) {
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("false", HttpStatus.OK);
		}
	}

	@GetMapping(value = "/validaPeriodoDeAvaliacao/{idCurso}", produces = "application/text")
	public ResponseEntity<String> validaPeriodoDeAvaliacaoCurso(@PathVariable(value = "idCurso") long idCurso) {
		ModelCronogramaMonitoria cronograma = cronogramaDeCursoRepository.findByIdCurso(idCurso).get();
		Date data_inicio = cronograma.getDataPeriodoAvaliacaoInicio();
		Date data_fim = cronograma.getDataPeriodoAvaliacaoFim();

		if (Utilidades.validaSeDataAtualEstaDentroDoPeriodo(data_inicio, data_fim)) {
			return new ResponseEntity<String>("true", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("false", HttpStatus.OK);
		}
	}

	private void avalieAlunosInscritos(DTODadosAvaliacao dadosAvaliacao) {
		double coeficiente = dadosAvaliacao.getCoeficiente();
		List<DTOResultadoAvaliacoes> aprovadosPorNota = new ArrayList<DTOResultadoAvaliacoes>();
		
		
		dadosAvaliacao.getListaAvaliacoes().forEach(avaliacao -> {
			ModelInscricaoMonitoria inscricao = fichaInscricaoRepository.findInscricaoByMatricula(avaliacao.getMatricula());
			ModelCurso curso = cursoRepository.findById(inscricao.getId_curso()).get();
			
			curso.getDisciplinas().forEach(disciplina -> {
				if (disciplina.getId().equals(inscricao.getId_disciplina())) {
					avaliacao.setIdDisciplina(disciplina.getId());
					if (avaliacao.getNotaAluno() >= coeficiente) {
						aprovadosPorNota.add(avaliacao);
					} else {
						reproveAluno(avaliacao.getMatricula());
					}
				}
			});
		});
		
		List<DTOResultadoAvaliacoes> melhoresAprovados = separeAlunosComMaiorNota(aprovadosPorNota);
		
		melhoresAprovados.forEach(aprov -> { aproveAluno(aprov.getMatricula(), aprov.getIdDisciplina()); });
	}

	
	
	private List<DTOResultadoAvaliacoes> separeAlunosComMaiorNota(List<DTOResultadoAvaliacoes> avaliacoes1) {
		List<DTOResultadoAvaliacoes> alunosComMelhoresNotas = new ArrayList<DTOResultadoAvaliacoes>();
		List<String> disciplinas = new ArrayList<String>();
		
		// ORDENA LISTA POR ID DA DISCIPLINA
		Collections.sort(avaliacoes1, new Comparator<Object>() {  
            public int compare(Object o1, Object o2) {  
            	DTOResultadoAvaliacoes c1 = (DTOResultadoAvaliacoes) o1;  
            	DTOResultadoAvaliacoes c2 = (DTOResultadoAvaliacoes) o2;  
                return c1.getIdDisciplina().compareTo(c2.getIdDisciplina());
             }
		});
		
		// PEGA TODAS AS DISCIPLINAS QUE FORAM AVALIADAS
		avaliacoes1.forEach(avaliacao -> {
			if (!disciplinas.contains(avaliacao.getIdDisciplina().toString())) {
				disciplinas.add(avaliacao.getIdDisciplina().toString());
			}
		});
		
		//SEPARA OS MELHOERES POR DISCIPLINA DE ACORDO COM A QUANTIDADE DE VAGAS
		disciplinas.forEach(idDisciplina -> {
			List<DTOResultadoAvaliacoes> aprovadosPorDisciplina = new ArrayList<DTOResultadoAvaliacoes>();
			ModelDisciplina disciplina = disciplinaRepository.findById(Long.parseLong(idDisciplina)).get();
			int qtdVgDisciplina = disciplina.getQtdeVgMonitoria();
			
			avaliacoes1.forEach(avaliacao -> {
				if (avaliacao.getIdDisciplina().equals(Long.parseLong(idDisciplina))) {
					aprovadosPorDisciplina.add(avaliacao);
				}
			});
			
			Collections.sort(aprovadosPorDisciplina, new Comparator<Object>() {  
	            public int compare(Object o1, Object o2) {  
	            	DTOResultadoAvaliacoes c1 = (DTOResultadoAvaliacoes) o1;  
	            	DTOResultadoAvaliacoes c2 = (DTOResultadoAvaliacoes) o2;  
	                return c1.getNotaAluno() > c2.getNotaAluno() ? 1 : 
	                	c1.getNotaAluno() < c2.getNotaAluno() ? -1 : 0;
	             }
			});
			
			for (int i = 0; i < qtdVgDisciplina; i++) {
				if (i <= aprovadosPorDisciplina.size() -1 ) {
					alunosComMelhoresNotas.add(aprovadosPorDisciplina.get(i));
				}
			}
		});
		
		return alunosComMelhoresNotas;
	}

	private void reproveAluno(String matricula) {
		fichaInscricaoRepository.atualizeStatusInscricao(Constantes.SITUACAO_REPROVADA, matricula);
	}

	private void aproveAluno(String matricula, long idDisciplina) {
		fichaInscricaoRepository.atualizeStatusInscricao(Constantes.SITUACAO_APROVADA, matricula);
		disciplinaRepository.decrementeQuantidadeDeVagas(Integer.parseInt(String.valueOf(idDisciplina)));
	}
}