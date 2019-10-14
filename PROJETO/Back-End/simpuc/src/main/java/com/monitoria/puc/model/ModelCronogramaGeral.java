package com.monitoria.puc.model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.monitoria.puc.utilidades.Utilidades;

@Entity
public class ModelCronogramaGeral implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private GregorianCalendar publicacaoEdital_dtInicio;
	private GregorianCalendar publicacaoEdital_dtFim;

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
		if (Utilidades.validaSeDataInicioMaiorQueDataFim(this.publicacaoEdital_dtInicio, this.publicacaoEdital_dtFim))
			return false;
		if (Utilidades.validaSeDataInicioMaiorQueDataFim(this.periodoInscricao_dtInicio, this.periodoInscricao_dtFim))
			return false;
		if (Utilidades.validaSeDataInicioMaiorQueDataFim(this.periodoAvaliacao_dtInicio,
				this.periodoAvaliacao_dtInicio))
			return false;
		if (Utilidades.validaSeDataInicioMaiorQueDataFim(this.entregaDosResultados_dtInicio,
				this.entregaDosResultados_dtFim))
			return false;
		if (Utilidades.validaSeDataInicioMaiorQueDataFim(this.periodoLetivo_dtInicio, this.periodoLetivo_dtFim))
			return false;
		if (Utilidades.validaSeDataInicioMaiorQueDataFim(this.entregaDosCertificados_dtInicio,
				this.entregaDosCertificados_dtFim))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPublicacaoEdital_dtInicio() throws Exception {
		return Utilidades.convertDateParaString(publicacaoEdital_dtInicio);
	}

	public void setPublicacaoEdital_dtInicio(String publicacaoEdital_dtInicio) throws Exception {
		this.publicacaoEdital_dtInicio.setTime(Utilidades.convertDataStringEmDate(publicacaoEdital_dtInicio));
	}

	public String getPublicacaoEdital_dtFim() throws Exception {
		return Utilidades.convertDateParaString(publicacaoEdital_dtFim);
	}

	public void setPublicacaoEdital_dtFim(String publicacaoEdital_dtFim) throws Exception {
		this.publicacaoEdital_dtFim.setTime(Utilidades.convertDataStringEmDate(publicacaoEdital_dtFim));
	}

	public String getPeriodoInscricao_dtInicio() throws Exception {
		return Utilidades.convertDateParaString(periodoInscricao_dtInicio);
	}

	public void setPeriodoInscricao_dtInicio(String periodoInscricao_dtInicio) throws Exception {
		this.periodoInscricao_dtInicio.setTime(Utilidades.convertDataStringEmDate(periodoInscricao_dtInicio));
	}

	public String getPeriodoInscricao_dtFim() throws Exception {
		return Utilidades.convertDateParaString(periodoInscricao_dtFim);
	}

	public void setPeriodoInscricao_dtFim(String periodoInscricao_dtFim) throws Exception {
		this.periodoInscricao_dtFim.setTime(Utilidades.convertDataStringEmDate(periodoInscricao_dtFim));
	}

	public String getPeriodoAvaliacao_dtInicio() throws Exception {
		return Utilidades.convertDateParaString(periodoAvaliacao_dtInicio);
	}

	public void setPeriodoAvaliacao_dtInicio(String periodoAvaliacao_dtInicio) throws Exception {
		this.periodoAvaliacao_dtInicio.setTime(Utilidades.convertDataStringEmDate(periodoAvaliacao_dtInicio));
	}

	public String getPeriodoAvaliacao_dtFim() throws Exception {
		return Utilidades.convertDateParaString(periodoAvaliacao_dtFim);
	}

	public void setPeriodoAvaliacao_dtFim(String periodoAvaliacao_dtFim) throws Exception {
		this.periodoAvaliacao_dtFim.setTime(Utilidades.convertDataStringEmDate(periodoAvaliacao_dtFim));
	}

	public String getEntregaDosResultados_dtInicio() throws Exception {
		return Utilidades.convertDateParaString(entregaDosResultados_dtInicio);
	}

	public void setEntregaDosResultados_dtInicio(String entregaDosResultados_dtInicio) throws Exception {
		this.entregaDosResultados_dtInicio.setTime(Utilidades.convertDataStringEmDate(entregaDosResultados_dtInicio));
	}

	public String getEntregaDosResultados_dtFim() throws Exception {
		return Utilidades.convertDateParaString(entregaDosResultados_dtFim);
	}

	public void setEntregaDosResultados_dtFim(String entregaDosResultados_dtFim) throws Exception {
		this.entregaDosResultados_dtFim.setTime(Utilidades.convertDataStringEmDate(entregaDosResultados_dtFim));
	}

	public String getPeriodoLetivo_dtInicio() throws Exception {
		return Utilidades.convertDateParaString(periodoLetivo_dtInicio);
	}

	public void setPeriodoLetivo_dtInicio(String periodoLetivo_dtInicio) throws Exception {
		this.periodoLetivo_dtInicio.setTime(Utilidades.convertDataStringEmDate(periodoLetivo_dtInicio));
	}

	public String getPeriodoLetivo_dtFim() throws Exception {
		return Utilidades.convertDateParaString(periodoLetivo_dtFim);
	}

	public void setPeriodoLetivo_dtFim(String periodoLetivo_dtFim) throws Exception {
		this.periodoLetivo_dtFim.setTime(Utilidades.convertDataStringEmDate(periodoLetivo_dtFim));
	}

	public String getEntregaDosCertificados_dtInicio() throws Exception {
		return Utilidades.convertDateParaString(entregaDosCertificados_dtInicio);
	}

	public void setEntregaDosCertificados_dtInicio(String entregaDosCertificados_dtInicio) throws Exception {
		this.entregaDosCertificados_dtInicio.setTime(Utilidades.convertDataStringEmDate(entregaDosCertificados_dtInicio));
	}

	public String getEntregaDosCertificados_dtFim() throws Exception {
		return Utilidades.convertDateParaString(entregaDosCertificados_dtFim);
	}

	public void setEntregaDosCertificados_dtFim(String entregaDosCertificados_dtFim) throws Exception {
		this.entregaDosCertificados_dtFim.setTime(Utilidades.convertDataStringEmDate(entregaDosCertificados_dtFim));
	}
}
