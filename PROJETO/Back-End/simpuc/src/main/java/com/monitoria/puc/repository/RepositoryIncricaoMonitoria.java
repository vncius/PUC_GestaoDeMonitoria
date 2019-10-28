package com.monitoria.puc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.monitoria.puc.model.ModelInscricaoMonitoria;

@Repository
public interface RepositoryIncricaoMonitoria extends CrudRepository<ModelInscricaoMonitoria, Long> {
	
}
