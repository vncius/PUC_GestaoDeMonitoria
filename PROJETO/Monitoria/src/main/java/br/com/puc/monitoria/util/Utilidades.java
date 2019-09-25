package br.com.puc.monitoria.util;

public class Utilidades {

	public static String primeiraLetraMaiuscula(String entrada) {
		entrada.trim();
		String [] listPalavras = entrada.split(" ");
		String saida = "";
		
		for(int i = 0; i < listPalavras.length; i++) {
			String palavra = listPalavras[i];
			saida += palavra.substring(0, 1).toUpperCase();
			saida += palavra.substring(1, palavra.length()).toLowerCase();
			if(i < listPalavras.length - 1) {
				saida += " ";
			}
		}
		return saida;
	}
	
	public static String letrasMinusculas(String entrada) {
		return entrada.toLowerCase().trim();
	}
	
	public static String lestrasMaiusculas(String entrada) {
		return entrada.toUpperCase().trim();
	}
}
