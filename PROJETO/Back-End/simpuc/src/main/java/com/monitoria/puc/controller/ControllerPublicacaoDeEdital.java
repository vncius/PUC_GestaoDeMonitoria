package com.monitoria.puc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/edital")
public class ControllerPublicacaoDeEdital {


	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<Byte[]> consultarEdital() {
		Byte[] pdf = null;
		return new ResponseEntity<Byte[]>(pdf, HttpStatus.OK);
	}

	@PostMapping(value = "/", produces = "application/text")
	public ResponseEntity<String> publicarEdital(@RequestBody Byte[] pdfEdital) {
		try {
			String FILEPATH = System.getProperty("user.home") + "\\Documents";
			File file = new File(FILEPATH);
			OutputStream in = new FileOutputStream(file);
			//in.write(pdfEdital.length);
			in.flush();
			in.close();

			return new ResponseEntity<String>("Edital publicado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve uma erro interno da API", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
