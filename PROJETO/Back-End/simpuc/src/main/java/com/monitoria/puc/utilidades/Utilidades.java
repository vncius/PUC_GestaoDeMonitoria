package com.monitoria.puc.utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
public class Utilidades {

	public static boolean valorStringEstaPreenchido(String valor) {
		if (valor == null)
			return false;
		if (valor.isEmpty())
			return false;
		if (valor.length() == 0)
			return false;
		return true;
	}

	public static boolean valorInteiroEstaPreenchido(int valor) {
		if (valor <= 0)
			return false;
		return true;
	}

	public static boolean validaSeDataInicioMaiorQueDataFim(GregorianCalendar dataIni, GregorianCalendar dataFim) {
		if (dataIni.getTimeInMillis() > dataFim.getTimeInMillis())
			return true;
		return false;
	}

	public static boolean validaSeDataInicioMaiorQueDataFimDate(Date dataIni, Date dataFim) {
		if (dataIni.after(dataFim))
			return true;
		return false;
	}

	public static GregorianCalendar convertDataStringEmDate(String data) throws Exception {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(df.parse(data));
			return calendar;
		} catch (Exception e) {
			throw new Exception(
					String.format("Data com formato inválido: %s! Exception: %s", data.toString(), e.getMessage()));
		}
	}

	public static String convertDateParaString(GregorianCalendar data) throws Exception {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dataFormata = dateFormat.format(data.getTime());
			return dataFormata;
		} catch (Exception e) {
			throw new Exception(
					String.format("Data com formato inválido: %s! Exception: %s", data.toString(), e.getMessage()));
		}
	}

	public static boolean validaSeArquivoEhValido(byte[] file) {
		if (file.length > 1000) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean quantidadeDeCaracteresEhValido(String atributo, int min, int max) {
		if (atributo.length() < min || atributo.length() > max) {
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("deprecation")
	public static boolean validaSeDataEstaDentroDeUmPeriodoInformado(Date DataInicial, Date DataFinal,
																	Date periodoDataInicio, Date periodoDataFim) {
	
		periodoDataInicio.setDate(periodoDataInicio.getDate() - 1);
		periodoDataFim.setDate(periodoDataFim.getDate() + 1);
		if (DataInicial.after(periodoDataInicio) && DataFinal.before(periodoDataFim)) {
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static boolean validaSeDataAtualEstaDentroDoPeriodo(Date DataInicialPeriodo, Date DataFinalPeriodo) {
		Date dataAtual = new Date(System.currentTimeMillis());
		DataInicialPeriodo.setDate(DataInicialPeriodo.getDate() - 1);
		DataFinalPeriodo.setDate(DataFinalPeriodo.getDate() + 1);
		if (dataAtual.after(DataInicialPeriodo) && dataAtual.before(DataFinalPeriodo)) {
			return true;
		} else {
			return false;
		}
	}
}
