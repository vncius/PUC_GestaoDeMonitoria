package com.monitoria.puc.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.monitoria.puc.exception.NotFoundException;
import com.monitoria.puc.model.ModelDisciplina;
import com.monitoria.puc.repository.RepositoryDisciplina;

@Service
public class DisciplinaService {

	@Autowired
	private RepositoryDisciplina repositoryDisciplina;
	
	public int updateVagas(Long idDisciplina, int qtdeVagas) {
		int updateQtdeVagaMonitoria = repositoryDisciplina.updateVagas(idDisciplina, qtdeVagas);
		return updateQtdeVagaMonitoria;
	}
	
	public List<ModelDisciplina> listAll(Long id){
		List<ModelDisciplina> listaDeDisciplinas = repositoryDisciplina.findByIdCurso(id);
		return listaDeDisciplinas;
	}

	public ModelDisciplina getById(Long id) {
		Optional<ModelDisciplina> result = repositoryDisciplina.findById(id);
		return result.orElseThrow(()-> new NotFoundException("Não existe disciplina com o id: = " + id));
	}
	
}
