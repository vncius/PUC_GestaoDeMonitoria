package com.monitoria.puc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monitoria.puc.model.ModelDisciplina;
import com.monitoria.puc.repository.RepositoryDisciplina;
import com.monitoria.puc.repository.RepositoryDisponibilizaVaga;

@Repository
public class DisponibilizaVagaService {
	
	@Autowired
	private RepositoryDisciplina repositoryDisciplina;
	
	public ModelDisciplina updateVagas(ModelDisciplina modelDisciplina) {
		ModelDisciplina updateQtdeVagaMonitoria = repositoryDisciplina.save(modelDisciplina);
		return updateQtdeVagaMonitoria;
	}
	
	

}
