package com.monitoria.puc.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.monitoria.puc.utilidades.Validacoes;

@Entity
public class ModelCronogramaGeral implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private GregorianCalendar publicacaoEdital_dtInicio;
	private GregorianCalendar publicacaoEdital_dtFim = null;
	
	private GregorianCalendar periodoInscricao_dtInicio;
	private GregorianCalendar periodoInscricao_dtFim;
	
	private GregorianCalendar periodoAvaliacao_dtInicio;
	private GregorianCalendar periodoAvaliacao_dtFim;
	
	private GregorianCalendar entregaDosResultados_dtInicio;
	private GregorianCalendar entregaDosResultados_dtFim;
	
	private GregorianCalendar periodoLetivo_dtInicio;
	private GregorianCalendar periodoLetivo_dtFim;
	
	private GregorianCalendar entregaDosCertificados_dtInicio;
	private GregorianCalendar entregaDosCertificados_dtFim;

	public Boolean validaCronogramaGeral() {
		if (Validacoes.validaSeDataInicioMaiorQueDataFim(this.publicacaoEdital_dtInicio, this.publicacaoEdital_dtFim)) return false;
		if (Validacoes.validaSeDataInicioMaiorQueDataFim(this.periodoInscricao_dtInicio, this.periodoInscricao_dtFim)) return false;
		if (Validacoes.validaSeDataInicioMaiorQueDataFim(this.periodoAvaliacao_dtInicio, this.periodoAvaliacao_dtInicio)) return false;
		if (Validacoes.validaSeDataInicioMaiorQueDataFim(this.entregaDosResultados_dtInicio, this.entregaDosResultados_dtFim)) return false;
		if (Validacoes.validaSeDataInicioMaiorQueDataFim(this.periodoLetivo_dtInicio, this.periodoLetivo_dtFim)) return false;
		if (Validacoes.validaSeDataInicioMaiorQueDataFim(this.entregaDosCertificados_dtInicio, this.entregaDosCertificados_dtFim)) return false;
		return true;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public GregorianCalendar getPublicacaoEdital_dtInicio() {
		return publicacaoEdital_dtInicio;
	}
	public void setPublicacaoEdital_dtInicio(GregorianCalendar publicacaoEdital_dtInicio) {
		this.publicacaoEdital_dtInicio = publicacaoEdital_dtInicio;
	}
	public GregorianCalendar getPublicacaoEdital_dtFim() {
		return publicacaoEdital_dtFim;
	}
	public void setPublicacaoEdital_dtFim(GregorianCalendar publicacaoEdital_dtFim) {
		this.publicacaoEdital_dtFim = publicacaoEdital_dtFim;
	}
	public GregorianCalendar getPeriodoInscricao_dtInicio() {
		return periodoInscricao_dtInicio;
	}
	public void setPeriodoInscricao_dtInicio(GregorianCalendar periodoInscricao_dtInicio) {
		this.periodoInscricao_dtInicio = periodoInscricao_dtInicio;
	}
	public GregorianCalendar getPeriodoInscricao_dtFim() {
		return periodoInscricao_dtFim;
	}
	public void setPeriodoInscricao_dtFim(GregorianCalendar periodoInscricao_dtFim) {
		this.periodoInscricao_dtFim = periodoInscricao_dtFim;
	}
	public GregorianCalendar getPeriodoAvaliacao_dtInicio() {
		return periodoAvaliacao_dtInicio;
	}
	public void setPeriodoAvaliacao_dtInicio(GregorianCalendar periodoAvaliacao_dtInicio) {
		this.periodoAvaliacao_dtInicio = periodoAvaliacao_dtInicio;
	}
	public GregorianCalendar getPeriodoAvaliacao_dtFim() {
		return periodoAvaliacao_dtFim;
	}
	public void setPeriodoAvaliacao_dtFim(GregorianCalendar periodoAvaliacao_dtFim) {
		this.periodoAvaliacao_dtFim = periodoAvaliacao_dtFim;
	}
	public GregorianCalendar getEntregaDosResultados_dtInicio() {
		return entregaDosResultados_dtInicio;
	}
	public void setEntregaDosResultados_dtInicio(GregorianCalendar entregaDosResultados_dtInicio) {
		this.entregaDosResultados_dtInicio = entregaDosResultados_dtInicio;
	}
	public GregorianCalendar getEntregaDosResultados_dtFim() {
		return entregaDosResultados_dtFim;
	}
	public void setEntregaDosResultados_dtFim(GregorianCalendar entregaDosResultados_dtFim) {
		this.entregaDosResultados_dtFim = entregaDosResultados_dtFim;
	}
	public GregorianCalendar getPeriodoLetivo_dtInicio() {
		return periodoLetivo_dtInicio;
	}
	public void setPeriodoLetivo_dtInicio(GregorianCalendar periodoLetivo_dtInicio) {
		this.periodoLetivo_dtInicio = periodoLetivo_dtInicio;
	}
	public GregorianCalendar getPeriodoLetivo_dtFim() {
		return periodoLetivo_dtFim;
	}
	public void setPeriodoLetivo_dtFim(GregorianCalendar periodoLetivo_dtFim) {
		this.periodoLetivo_dtFim = periodoLetivo_dtFim;
	}
	public GregorianCalendar getEntregaDosCertificados_dtInicio() {
		return entregaDosCertificados_dtInicio;
	}
	public void setEntregaDosCertificados_dtInicio(GregorianCalendar entregaDosCertificados_dtInicio) {
		this.entregaDosCertificados_dtInicio = entregaDosCertificados_dtInicio;
	}
	public GregorianCalendar getEntregaDosCertificados_dtFim() {
		return entregaDosCertificados_dtFim;
	}
	public void setEntregaDosCertificados_dtFim(GregorianCalendar entregaDosCertificados_dtFim) {
		this.entregaDosCertificados_dtFim = entregaDosCertificados_dtFim;
	}
}
