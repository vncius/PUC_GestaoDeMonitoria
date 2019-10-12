package com.monitoria.puc.utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Utilidades {

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
	
	public static boolean validaSeDataInicioMaiorQueDataFim(GregorianCalendar  dataIni, GregorianCalendar dataFim) {
		if (dataIni.getTimeInMillis() > dataFim.getTimeInMillis()) return true;
		return false;
	}

	public static GregorianCalendar convertDataStringEmDate(String data) throws Exception {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(df.parse(data));
			return cal;
		} catch (Exception e) {
			throw new Exception(String.format("Data com formato inválido: %s! Exception: %s", data.toString(), e.getMessage()));
		}
	}
	
	public static String convertDateParaString(GregorianCalendar data) throws Exception {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dataFormata = dateFormat.format(data.getTime());
			return dataFormata;
		} catch (Exception e) {
			throw new Exception(String.format("Data com formato inválido: %s! Exception: %s", data.toString(), e.getMessage()));
		}
	}
}
