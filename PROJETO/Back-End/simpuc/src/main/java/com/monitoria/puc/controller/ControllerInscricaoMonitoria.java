package com.monitoria.puc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monitoria.puc.model.ModelInscricaoMonitoria;
import com.monitoria.puc.model.ModelUsuario;
import com.monitoria.puc.repository.RepositoryCurso;
import com.monitoria.puc.repository.RepositoryFichaDeInscricao;
import com.monitoria.puc.repository.RepositoryUsuario;
import com.monitoria.puc.utilidades.DTOFichaDeInscricao;
import com.monitoria.puc.utilidades.Utilidades;

@CrossOrigin
@RestController
@RequestMapping(value = "/fichaInscricao")
public class ControllerInscricaoMonitoria {

	final static String FILEPATH = System.getProperty("user.home") + "\\Documents\\InscricoesMonitoria";
	final static String NAMEFILE = "ANEXO_INSCRICAO.pdf";

	@Autowired
	private RepositoryUsuario usuarioRepository;

	@Autowired
	private RepositoryCurso cursoRepository;

	@Autowired
	private RepositoryFichaDeInscricao fichaDeInscricaoRepository;

	@GetMapping(value = "/{matricula}", produces = "application/json")
	public ResponseEntity<DTOFichaDeInscricao> consultarPorMatricula(
			@PathVariable(value = "matricula") String matricula) {
		ModelInscricaoMonitoria fichaDeInscricao = fichaDeInscricaoRepository.findInscricaoByMatricula(matricula);

		if (fichaDeInscricao == null) {
			ModelUsuario usuario = usuarioRepository.findUserByLogin(matricula);
			DTOFichaDeInscricao dtoIncricao = new DTOFichaDeInscricao();
			dtoIncricao.InicializeParaNovoCadastro(usuario.getCurso(), matricula, usuario.getNome());
			return new ResponseEntity<DTOFichaDeInscricao>(dtoIncricao, HttpStatus.OK);
		} else {
			return new ResponseEntity<DTOFichaDeInscricao>(
					fichaDeInscricao.converteModelEmDTOParaConsulta(cursoRepository), HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/anexos/{matricula}", produces = "application/json")
	public ResponseEntity<byte[]> consultarAnexo(@PathVariable(value = "matricula") String matricula) {
		byte[] editalEmBytes = null;
		try {
			File file = new File(geraNomeDoArquivoECaminhoDoAnexo(matricula));
			if (!file.exists()) {
				return new ResponseEntity<byte[]>(editalEmBytes, HttpStatus.PARTIAL_CONTENT); // 206
			}

			int tamanhoArquivo = Integer.parseInt(String.valueOf(file.length()));
			editalEmBytes = new byte[tamanhoArquivo];
			InputStream inputStream = new FileInputStream(file);
			inputStream.read(editalEmBytes);
			inputStream.close();
			return new ResponseEntity<byte[]>(editalEmBytes, HttpStatus.OK); // 200
		} catch (IOException e) {
			return new ResponseEntity<byte[]>(editalEmBytes, HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}

	@PutMapping(value = "/")
	public ResponseEntity<String> cadastrar_atualizar(ModelInscricaoMonitoria inscricaoMonitoria, MultipartFile file)
			throws IOException {

		if (inscricaoMonitoria.validaInscricao()) {
			String matricula = inscricaoMonitoria.getMatricula();
			String nome = inscricaoMonitoria.getNome();
			try {
				fichaDeInscricaoRepository.save(inscricaoMonitoria);
				if (Utilidades.validaSeArquivoEhValido(file.getBytes())) {
					byte[] pdf = file.getBytes();
					File file2 = new File(geraCaminhoDiretorioParaAnexos(matricula));
					if (!file2.exists()) {
						file2.mkdirs();
					}
					file2 = new File(geraNomeDoArquivoECaminhoDoAnexo(matricula));
					OutputStream outputStream = new FileOutputStream(file2);
					outputStream.write(pdf);
					outputStream.flush();
					outputStream.close();
				}
				return new ResponseEntity<String>(
						String.format("Inscrição do aluno %s foi atualizado!", inscricaoMonitoria.getNome()),
						HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Houve uma erro interno da API", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("Dados obigatórios não preenchidos.", HttpStatus.PARTIAL_CONTENT);
		}
	}

	public static String geraCaminhoDiretorioParaAnexos(String matricula) {
		return FILEPATH + "\\" + matricula + "\\";
	}

	public static String geraNomeDoArquivoECaminhoDoAnexo(String matricula) {
		return FILEPATH + "\\" + matricula + "\\" + NAMEFILE;
	}
}
