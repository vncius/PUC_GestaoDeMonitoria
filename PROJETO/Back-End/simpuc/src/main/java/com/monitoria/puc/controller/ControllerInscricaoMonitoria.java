package com.monitoria.puc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.monitoria.puc.repository.RepositoryFichaDeInscricao;
import com.monitoria.puc.repository.RepositoryUsuario;
import com.monitoria.puc.service.CursoService;
import com.monitoria.puc.utilidades.Constantes;
import com.monitoria.puc.utilidades.DTOFichaDeInscricao;
import com.monitoria.puc.utilidades.Utilidades;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@CrossOrigin
@RestController
@RequestMapping(value = "/fichaInscricao")
public class ControllerInscricaoMonitoria {

	final static String FILEPATH = System.getProperty("user.home") + "\\Documents\\InscricoesMonitoria";
	final static String NAMEFILE = "ANEXO_INSCRICAO.pdf";
	final static String NAMEFILERETORNO = "ANEXO_ALUNO_";
	
	@Autowired
	private RepositoryCronogramaMonitoria repositoryCronogramaMonitoria;

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private RepositoryUsuario usuarioRepository;

	@Autowired
	private RepositoryFichaDeInscricao fichaDeInscricaoRepository;

	@PutMapping(value = "/")
	public ResponseEntity<String> cadastrar_atualizar(ModelInscricaoMonitoria inscricaoMonitoria, MultipartFile file)
			throws IOException {
		String retorno = validaSeCursoEmPeriodoDeInscricao(inscricaoMonitoria.getMatricula());
				 
		if (retorno == Constantes.PERIODO_INSCRICAO){
			inscricaoMonitoria.setStatusIncricao(Constantes.SITUACAO_CADASTRADA);

			if (inscricaoMonitoria.validaInscricao() && file.getBytes().length > 0) {
				String matricula = inscricaoMonitoria.getMatricula();
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
					String mensagem = "";
					if (inscricaoMonitoria.getId() > 0) {
						mensagem = String.format("Inscrição do aluno(a) %s foi atualizado!", inscricaoMonitoria.getNome());
					} else {
						mensagem = String.format("Inscrição do aluno(a) %s foi registrada!", inscricaoMonitoria.getNome());
					}
					return new ResponseEntity<String>(mensagem, HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<String>("Houve uma erro interno da API", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<String>("Dados obigatórios não preenchidos.", HttpStatus.PARTIAL_CONTENT);
			}
		} else if (retorno != Constantes.PERIODO_INSCRICAO && retorno != Constantes.SEM_CRONOGRAMA) {
			return new ResponseEntity<String>("O curso não está no periodo de inscrição.", HttpStatus.PARTIAL_CONTENT);
		} else {
			return new ResponseEntity<String>("O curso ainda não possui cronograma de monitoria!", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/EstaNoCronograma/{matricula}", produces = "application/json")
	public ResponseEntity<String> cursoEstaEmPeriodoDeInscricao(@PathVariable(value = "matricula") String matricula) {
		ModelUsuario usuario = usuarioRepository.findUserByLogin(matricula);
		long id_curso = usuario.getCurso().getId();
		
		ModelCronogramaMonitoria cronograma = repositoryCronogramaMonitoria.findCronogramaByIdCurso(id_curso);
		
		if (cronograma != null) {
			if (Utilidades.validaSeDataAtualEstaDentroDoPeriodo(cronograma.getDataInscricaoInicio(), cronograma.getDataInscricaoFim())) {
				return new ResponseEntity<String>("true", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(
						String.format("Não é possivel alterar ou cancelar a inscrição para o curso de %s, o curso não está no periodo de inscrição.", 
								usuario.getCurso().getDescricao()),
						HttpStatus.PARTIAL_CONTENT);
			}
		} else {
			return new ResponseEntity<String>(String.format("O curso %s ainda não possui cronograma de monitoria!",
					usuario.getCurso().getDescricao()), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/{id}", produces = "application/text")
	public ResponseEntity<String> cancelarInscricao(@PathVariable(value = "id") Long id) {
		ModelInscricaoMonitoria inscricao = fichaDeInscricaoRepository.findById(id).get();
		inscricao.setStatusIncricao(Constantes.SITUACAO_CANCELADA);
		try {
			fichaDeInscricaoRepository.save(inscricao);
			return new ResponseEntity<String>("Inscrição cancelada com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve uma erro interno da API", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private static String geraCaminhoDiretorioParaAnexos(String matricula) {
		return FILEPATH + "\\" + matricula + "\\";
	}

	private static String geraNomeDoArquivoECaminhoDoAnexo(String matricula) {
		return FILEPATH + "\\" + matricula + "\\" + NAMEFILE;
	}
	
	private String validaSeCursoEmPeriodoDeInscricao(String matricula) {
		ModelUsuario usuario = usuarioRepository.findUserByLogin(matricula);
		long id_curso = usuario.getCurso().getId();
		ModelCronogramaMonitoria cronograma = repositoryCronogramaMonitoria.findCronogramaByIdCurso(id_curso);

		if (cronograma != null) {
			if (Utilidades.validaSeDataAtualEstaDentroDoPeriodo(cronograma.getDataInscricaoInicio(), cronograma.getDataInscricaoFim())) {
				return Constantes.PERIODO_INSCRICAO;
			} else {
				return Constantes.PERIODO_NENHUM;
			}
		} else {
			return Constantes.SEM_CRONOGRAMA;
		}
	}
	
	// ----------------------------------------------------- CONSULTAS
	
	@GetMapping(value = "/downloadAnexo/{matricula}", produces = "application/PDF")
	public void consultarEdital(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "matricula") String matricula) throws IOException {
		File file = new File(geraNomeDoArquivoECaminhoDoAnexo(matricula));
		if (file.exists()) {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=" + NAMEFILERETORNO + matricula + ".pdf");
			try {
				Files.copy(file.toPath(), response.getOutputStream());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			response.setContentType("text/html");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.getWriter().write(Constantes.MENSAGEM_ALUNO_SEM_ANEXO);
			response.getWriter().flush();
			response.getWriter().close();
		}
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<DTOFichaDeInscricao>> consultaPorTodosFiltros() {
		List<DTOFichaDeInscricao> listaInscricoesDTO = new ArrayList<DTOFichaDeInscricao>();
		
		try {
			List<ModelInscricaoMonitoria> listaInscricoesModel = fichaDeInscricaoRepository.findAll();
			listaInscricoesModel.forEach(inscricaoModel -> { 
				listaInscricoesDTO.add(inscricaoModel.converteModelEmDTOParaConsulta(cursoService.listAll()));
			});
			return new ResponseEntity<List<DTOFichaDeInscricao>>(listaInscricoesDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<DTOFichaDeInscricao>>(listaInscricoesDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
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
					fichaDeInscricao.converteModelEmDTOParaConsulta(cursoService.listAll()), HttpStatus.OK);
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
}
