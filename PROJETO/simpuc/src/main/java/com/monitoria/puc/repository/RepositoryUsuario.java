package com.monitoria.puc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.monitoria.puc.model.UsuarioModel;

@Repository
public interface RepositoryUsuario extends CrudRepository<UsuarioModel, Long>{

}
