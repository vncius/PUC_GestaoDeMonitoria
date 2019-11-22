package com.monitoria.puc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@RestController
@RequestMapping(value = "/edital")
public class ControllerPublicacaoDeEdital {

	static String FILEPATH = System.getProperty("user.home") + "\\Documents\\Edital_Puc.pdf";

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<byte[]> consultarEdital() {
		byte[] editalEmBytes = null;
		try {
			File file = new File(FILEPATH);
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
