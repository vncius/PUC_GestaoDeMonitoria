package com.monitoria.puc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.monitoria.puc.model.ModelDisciplina;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@Repository
public interface RepositoryDisciplina extends CrudRepository<ModelDisciplina, Long> {
	
	
	@Query("SELECT d FROM disciplina d WHERE fk_curso_id = ?1")
	public List<ModelDisciplina> findByIdCurso(Long id);

	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE disciplina d set d.qtdeVgMonitoria = ?2  WHERE d.id = ?1")
	public int updateVagas(Long id,int qtde);
}
