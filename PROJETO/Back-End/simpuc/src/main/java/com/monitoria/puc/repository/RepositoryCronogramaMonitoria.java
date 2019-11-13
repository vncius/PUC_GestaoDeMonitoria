package com.monitoria.puc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.monitoria.puc.model.ModelCronogramaMonitoria;

@Repository
public interface RepositoryCronogramaMonitoria extends JpaRepository<ModelCronogramaMonitoria, Long> {

	@Query("SELECT cm FROM cronogramaMonitoria cm WHERE curso_id = ?1")
	public Optional<ModelCronogramaMonitoria> findByIdCurso(Long id);
	
	@Query("SELECT cm FROM cronogramaMonitoria cm WHERE curso_id = ?1")
	public ModelCronogramaMonitoria findCronogramaByIdCurso(Long id);
}
