package com.monitoria.puc.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monitoria.puc.utilidades.Constantes;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@CrossOrigin
@RestController
@RequestMapping(value = "/downloads")
public class ControllerDownload {

	final static String FILEPATH = System.getProperty("user.home") + "\\Documents\\TermoDeCompromisso\\TermoDeCompromisso.docx";
	final static String NAMEFILE = "TermoDeCompromisso.docx";


	@GetMapping(value = "/termoDeCompromisso")
	public void consultarEdital(HttpServletRequest request, HttpServletResponse response) throws IOException {
		File file = new File(FILEPATH);
		if (file.exists()) {
			response.setContentType("application/msword");
			response.addHeader("Content-Disposition", "attachment; filename=" + NAMEFILE);
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
}
