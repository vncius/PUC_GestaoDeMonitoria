package com.monitoria.puc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.monitoria.puc.model.ModelCurso;

@Repository
public interface RepositoryCurso extends JpaRepository<ModelCurso, Long>{

	@Query("select u from curso u where u.id <> 0")
	List<ModelCurso> findCursosByRegra();
}


