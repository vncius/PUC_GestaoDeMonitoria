package com.monitoria.puc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.monitoria.puc.model.ModelCurso;

@Repository
public interface RepositoryCurso extends JpaRepository<ModelCurso, Long>{

}


