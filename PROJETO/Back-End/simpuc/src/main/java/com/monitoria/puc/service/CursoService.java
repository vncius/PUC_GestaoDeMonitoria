package com.monitoria.puc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitoria.puc.exception.NotFoundException;
import com.monitoria.puc.model.ModelCurso;
import com.monitoria.puc.repository.RepositoryCurso;

@Service
public class CursoService {
	
	@Autowired
	private RepositoryCurso repositoryCurso;
	
	public List<ModelCurso> listAll(){
		List<ModelCurso> listaDeCursos = repositoryCurso.findCursosByRegra();
		return listaDeCursos;
	}
	
	public ModelCurso findById(Long id) {
		Optional<ModelCurso> result = repositoryCurso.findById(id);
		return result.orElseThrow(()-> new NotFoundException("Não existe curso com o id: = " + id));
	}

}
