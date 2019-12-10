package com.monitoria.puc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.monitoria.puc.model.ModelDisciplina;
import com.monitoria.puc.repository.RepositoryDisciplina;

@Service
public class DisponibilizaVagaService {
	
	@Autowired
	private RepositoryDisciplina repositoryDisciplina;
	
	public ModelDisciplina updateVagas(ModelDisciplina modelDisciplina) {
		ModelDisciplina updateQtdeVagaMonitoria = repositoryDisciplina.save(modelDisciplina);
		return updateQtdeVagaMonitoria;
	}
	
}
