package com.monitoria.puc.utilidades;

import java.util.GregorianCalendar;

public class Validacoes {

	public static boolean valorStringEstaPreenchido(String valor) {
		if (valor == null) return false;
		if (valor.isEmpty()) return false;
		if (valor.length() == 0) return false;
		return true;
	}
	
	public static boolean valorInteiroEstaPreenchido(int valor) {
		if (valor <= 0) return false;
		return true;
	}
	
	@SuppressWarnings("static-access")
	public static boolean validaSeDataInicioMaiorQueDataFim(GregorianCalendar  dataIni, GregorianCalendar dataFim) {
		if (dataIni.DATE > dataFim.DATE) return true;
		return false;
	}
}
