package com.monitoria.puc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monitoria.puc.utilidades.Constantes;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@RestController
@RequestMapping(value = "/edital")
public class ControllerPublicacaoDeEdital {

	static String FILEPATH = System.getProperty("user.home") + "\\Documents\\Edital_Puc.pdf";

	@GetMapping(value = "/", produces = "application/PDF")
	public void consultarEdital(HttpServletRequest request, HttpServletResponse response) throws IOException {
		File file = new File(FILEPATH);
		if (file.exists()) {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline");
			try {
				Files.copy(file.toPath(), response.getOutputStream());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			response.setContentType("text/html");
			response.setStatus(HttpStatus.PARTIAL_CONTENT.value());
			response.getWriter().write(Constantes.MENSAGEM_EDITAL_NAO_PUBLICADO);
			response.getWriter().flush();
			response.getWriter().close();
		}
	}

	@PostMapping(value = "/")
	public ResponseEntity<String> publicarEdital(@RequestParam("file") MultipartFile file) throws IOException {
		try {
			byte[] pdf = file.getBytes();
			File file2 = new File(FILEPATH);
			OutputStream outputStream = new FileOutputStream(file2);
			outputStream.write(pdf);
			outputStream.flush();
			outputStream.close();
			return new ResponseEntity<String>("Edital publicado com sucesso", HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<String>("Houve uma erro interno da API", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
