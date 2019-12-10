package com.monitoria.puc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.monitoria.puc.model.ModelVaga;
import com.monitoria.puc.repository.RepositorySolicitaVagaDisciplina;

@Service
public class SolicitaVagaService {

	@Autowired
	private RepositorySolicitaVagaDisciplina repository;
	
	public ModelVaga save(ModelVaga vagadisciplina) {
		ModelVaga vagaDisciplinaCriada = repository.save(vagadisciplina);
		return vagaDisciplinaCriada;
	}
	
	public int somaQtdeVgSolicitada(Long id) {
		int qtdeSomada;
		try {
		qtdeSomada = repository.somaVagaIdDisciplina(id);
		return qtdeSomada;
		}catch(Exception e){
			return qtdeSomada = 0;	
		}
	}
	
	public ModelVaga getVagaUserDisciplina(String matricula, Long idDisciplina) {
		ModelVaga result = repository.getVagaUsuarioDisciplina(matricula, idDisciplina);
		return result;
	}
	
}
