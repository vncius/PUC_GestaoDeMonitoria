package com.monitoria.puc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.monitoria.puc.model.ModelOrientador;

@Repository
public interface RepositoryOrientador extends CrudRepository<ModelOrientador, Long> {

	
}
