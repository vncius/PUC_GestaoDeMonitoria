package com.monitoria.puc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.monitoria.puc.model.ModelCronogramaMonitoria;
import com.monitoria.puc.model.ModelInscricaoMonitoria;
import com.monitoria.puc.model.ModelUsuario;
import com.monitoria.puc.repository.RepositoryCronogramaMonitoria;
import com.monitoria.puc.repository.RepositoryCurso;
import com.monitoria.puc.repository.RepositoryFichaDeInscricao;
import com.monitoria.puc.repository.RepositoryUsuario;
import com.monitoria.puc.utilidades.Constantes;
import com.monitoria.puc.utilidades.DTOFichaDeInscricao;
import com.monitoria.puc.utilidades.Utilidades;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 28/11/2019*/
@CrossOrigin
@RestController
@RequestMapping(value = "/publicarResultadoAvaliacoes")
public class ControllerPublicarResultadoAvaliacoes {
	
	@Autowired
	private RepositoryCronogramaMonitoria repositoryCronogramaMonitoria;

	@Autowired
	private RepositoryUsuario usuarioRepository;

	@Autowired
	private RepositoryCurso cursoRepository;

	@Autowired
	private RepositoryFichaDeInscricao fichaDeInscricaoRepository;

	private boolean validaSeEstaNoPeriodoDeAvaliacao() {
		return true;
	}
	
	// ----------------------------------------------------- CONSULTAS
}
