package com.monitoria.puc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.monitoria.puc.model.ModelCronogramaGeral;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@Repository
public interface RepositoryCronogramaGeral extends CrudRepository<ModelCronogramaGeral, Long> {
	
	@Query("select max(c.id) from cronograma_geral c")
	Long findMaxIdCronogramaGeral();
}
