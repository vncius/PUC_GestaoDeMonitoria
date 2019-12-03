package com.monitoria.puc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.monitoria.puc.model.ModelInscricaoMonitoria;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@Repository
public interface RepositoryFichaDeInscricao extends JpaRepository<ModelInscricaoMonitoria, Long> {

	@Query("select u from incricao_monitoria u where u.matricula = ?1")
	ModelInscricaoMonitoria findInscricaoByMatricula(String matricula);

	@Transactional
	@Modifying
	@Query("UPDATE incricao_monitoria im SET im.statusIncricao = ?1 WHERE im.matricula = ?2")
	void atualizeStatusInscricao(String situacaoAprovada, String matricula);
}
