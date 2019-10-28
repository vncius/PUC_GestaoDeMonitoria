package com.monitoria.puc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitoria.puc.exception.NotFoundException;
import com.monitoria.puc.model.ModelCronogramaMonitoria;
import com.monitoria.puc.repository.RepositoryCronogramaMonitoria;

@Service
public class CronogramaMonitoriaService {

	@Autowired
	private RepositoryCronogramaMonitoria repositoryCronogramaMonitoria;

	public ModelCronogramaMonitoria save(ModelCronogramaMonitoria cronogramaMonitoria) {
		ModelCronogramaMonitoria cronogramaCriado = repositoryCronogramaMonitoria.save(cronogramaMonitoria);
		return cronogramaCriado;
	}
	
	public ModelCronogramaMonitoria update(ModelCronogramaMonitoria cronogramaMonitoria) {
		ModelCronogramaMonitoria updatedCronogramaMonitoria = repositoryCronogramaMonitoria.save(cronogramaMonitoria);
		return updatedCronogramaMonitoria;
	}
	
	public ModelCronogramaMonitoria getById(Long id) {
		Optional<ModelCronogramaMonitoria> result = repositoryCronogramaMonitoria.findByIdCurso(id);
		return result.orElseThrow(()-> new NotFoundException("Não existe cronograma para o curso de id = " + id));
	}
	
	public List<ModelCronogramaMonitoria>listAll(){
		List<ModelCronogramaMonitoria> listaCronograma = repositoryCronogramaMonitoria.findAll();
		return listaCronograma;
	}

}
