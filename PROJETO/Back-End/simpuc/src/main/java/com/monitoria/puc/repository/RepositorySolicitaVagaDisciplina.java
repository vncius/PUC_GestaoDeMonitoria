package com.monitoria.puc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.monitoria.puc.model.ModelVaga;


@Repository
public interface RepositorySolicitaVagaDisciplina extends JpaRepository<ModelVaga,Long>{

	
	@Query(value = "SELECT SUM(qtde_vg_solicitada) FROM vaga where id_disciplina_fk = ?1", nativeQuery = true)
	public int  somaVagaIdDisciplina(Long id);
	
	
	@Query(value = "SELECT * FROM vaga where usuario_id = ?1 AND id_disciplina_fk = ?2",nativeQuery = true)
	public ModelVaga getVagaUsuarioDisciplina(String usuario,Long id);
}
