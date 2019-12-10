package com.monitoria.puc.utilidades;

import java.util.List;

public class DTODadosAvaliacao {
	
	private long idCurso;
	private double coeficiente;
	private List<DTOResultadoAvaliacoes> listaAvaliacoes;
	
	public double getCoeficiente() {
		return coeficiente;
	}
	public void setCoeficiente(double coeficiente) {
		this.coeficiente = coeficiente;
	}
	public List<DTOResultadoAvaliacoes> getListaAvaliacoes() {
		return listaAvaliacoes;
	}
	public void setListaAvaliacoes(List<DTOResultadoAvaliacoes> listaAvaliacoes) {
		this.listaAvaliacoes = listaAvaliacoes;
	}
	public long getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(long idCurso) {
		this.idCurso = idCurso;
	}
}
