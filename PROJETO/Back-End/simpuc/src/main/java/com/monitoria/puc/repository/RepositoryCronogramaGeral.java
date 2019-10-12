package com.monitoria.puc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.monitoria.puc.model.ModelCronogramaGeral;

@Repository
public interface RepositoryCronogramaGeral extends CrudRepository<ModelCronogramaGeral, Long> {
	
}
