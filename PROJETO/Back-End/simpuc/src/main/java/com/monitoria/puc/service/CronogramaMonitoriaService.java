package com.monitoria.puc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitoria.puc.model.CronogramaMonitoria;
import com.monitoria.puc.repository.RepositoryCronogramaMonitoria;

@Service
public class CronogramaMonitoriaService {

	@Autowired
	private RepositoryCronogramaMonitoria repositoryCronogramaMonitoria;

	public CronogramaMonitoria save(CronogramaMonitoria cronogramaMonitoria) {
		CronogramaMonitoria cronogramaCriado = repositoryCronogramaMonitoria.save(cronogramaMonitoria);
		return cronogramaCriado;
	}

}
