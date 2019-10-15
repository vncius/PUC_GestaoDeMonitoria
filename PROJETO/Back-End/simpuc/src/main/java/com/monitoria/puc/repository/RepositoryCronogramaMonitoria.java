package com.monitoria.puc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monitoria.puc.model.CronogramaMonitoria;

@Repository
public interface RepositoryCronogramaMonitoria extends JpaRepository<CronogramaMonitoria, Long> {

	
}
