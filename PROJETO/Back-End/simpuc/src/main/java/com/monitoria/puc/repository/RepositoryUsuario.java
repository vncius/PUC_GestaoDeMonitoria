package com.monitoria.puc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.monitoria.puc.model.UsuarioModel;

@Repository
public interface RepositoryUsuario extends CrudRepository<UsuarioModel, Long> {

	@Query("select u from UsuarioModel u where u.matricula = ?1")
	UsuarioModel findUserByLogin(String matricula);
}
