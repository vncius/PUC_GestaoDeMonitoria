package com.monitoria.puc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.monitoria.puc.model.ModelCurso;

@Repository
public interface RepositoryCurso extends JpaRepository<ModelCurso, Long>{

	@Query("select u from curso u where u.id <> 0")
	List<ModelCurso> findCursosByRegra();

	@Transactional
	@Modifying
	@Query("UPDATE curso u SET u.situacao_avaliacao = ?1 where u.id = ?2")
	void atualizeSituacaoDoCurso(String situacaoCursoAvaliado, long idCurso);
}


