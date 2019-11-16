package com.monitoria.puc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.monitoria.puc.model.ModelOrientador;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@Repository
public interface RepositoryOrientador extends CrudRepository<ModelOrientador, Long> {

}
