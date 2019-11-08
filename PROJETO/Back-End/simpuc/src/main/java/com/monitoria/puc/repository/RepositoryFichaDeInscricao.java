package com.monitoria.puc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.monitoria.puc.model.ModelInscricaoMonitoria;


@Repository
public interface RepositoryFichaDeInscricao extends CrudRepository<ModelInscricaoMonitoria, Long> {

	@Query("select u from incricao_monitoria u where u.matricula = ?1")
	ModelInscricaoMonitoria findInscricaoByMatricula(String matricula);
}
