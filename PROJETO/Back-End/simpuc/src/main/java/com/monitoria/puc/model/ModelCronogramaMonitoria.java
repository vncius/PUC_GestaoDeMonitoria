package com.monitoria.puc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.monitoria.puc.utilidades.Constantes;
import com.monitoria.puc.utilidades.Utilidades;

@Entity(name = "cronogramaMonitoria")
public class ModelCronogramaMonitoria implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonManagedReference
	@OneToOne()
	@NotNull(message = "Campo data Obrigatório!")
    @JoinColumn(name = "curso_id", referencedColumnName = "id",nullable = false)
	private ModelCurso curso;
	
	@Column(name = "data_edital_inicio", nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	@NotNull(message = "Campo data Obrigatório!")
    private Date dataEditalInicio;
    
    @Column(name = "data_edital_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Campo data Obrigatório!")
    private Date dataEditalFim;
    
    @Column(name = "data_inscricao_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Campo data Obrigatório!")
    private Date dataInscricaoInicio;
    
    @Column(name = "data_inscricao_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Campo data Obrigatório!")
    private Date dataInscricaoFim;
    
    @Column(name = "periodo_avaliacao_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Campo data Obrigatório!")
    private Date dataPeriodoAvaliacaoInicio;
    
    @Column(name = "periodo_avaliacao_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataPeriodoAvaliacaoFim;
    
    @Column(name = "entrega_resultados_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Campo data Obrigatório!")
    private Date dataEntregaResultadosInicio;
    
    @Column(name = "entrega_resultados_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Campo data Obrigatório!")
    private Date dataEntregaResultadosFim;
    
    @Column(name = "periodo_letivo_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Campo data Obrigatório!")
    private Date dataPeriodoLetivoInicio;
    
    @Column(name = "periodo_letivo_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Campo data Obrigatório!")
    private Date dataPeriodoLetivoFim;
    
    @Column(name = "entrega_certificado_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Campo data Obrigatório!")
    private Date dataEntregaCertificadoInicio;
    
    @Column(name = "entrega_certificado_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Campo data Obrigatório!")
    private Date dataEntregaCertificadoFim;
	
	public Boolean validaCronogramaMonitoria() {
		if (Utilidades.validaSeDataInicioMaiorQueDataFimDate(this.dataEditalInicio, this.dataEditalFim))
			return false;
		if (Utilidades.validaSeDataInicioMaiorQueDataFimDate(this.dataInscricaoInicio, this.dataInscricaoFim))
			return false;
		if (Utilidades.validaSeDataInicioMaiorQueDataFimDate(this.dataPeriodoAvaliacaoInicio,
				this.dataPeriodoAvaliacaoFim))
			return false;
		if (Utilidades.validaSeDataInicioMaiorQueDataFimDate(this.dataEntregaResultadosInicio,
				this.dataEntregaResultadosFim))
			return false;
		if (Utilidades.validaSeDataInicioMaiorQueDataFimDate(this.dataPeriodoLetivoInicio, this.dataPeriodoLetivoFim))
			return false;
		if (Utilidades.validaSeDataInicioMaiorQueDataFimDate(this.dataEntregaCertificadoInicio,
				this.dataEntregaCertificadoFim))
			return false;
		return true;
	}
	
	public String validaSeEstaNoPeriodoDeInscricao() {
		Date data = new Date(System.currentTimeMillis());
		if (data.after(this.dataEditalInicio) || data.before(this.dataEditalFim)) {
			return Constantes.PERIODO_PUBLICACAO_EDITAL;
		} else if (data.after(this.dataInscricaoInicio) || data.before(this.dataInscricaoFim)) {
			return Constantes.PERIODO_INSCRICAO;
		} else if (data.after(this.dataPeriodoAvaliacaoInicio) || data.before(this.dataPeriodoAvaliacaoFim)) {
			return Constantes.PERIODO_AVALIACAO;
		} else if (data.after(this.dataEntregaResultadosInicio) || data.before(this.dataEntregaResultadosFim)) {
			return Constantes.PERIODO_ENTREGA_RESULTADOS;
		} else if (data.after(this.dataPeriodoLetivoInicio) || data.before(this.dataPeriodoLetivoFim)) {
			return Constantes.PERIODO_LETIVO;
		} else if (data.after(this.dataEntregaCertificadoInicio) || data.before(this.dataEntregaCertificadoFim)) {
			return Constantes.PERIODO_ENTREGA_CERTIFICADOS;
		} 
		return Constantes.PERIODO_NENHUM;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ModelCurso getCurso() {
		return curso;
	}

	public void setCurso(ModelCurso curso) {
		this.curso = curso;
	}

	public Date getDataEditalInicio() {
		return dataEditalInicio;
	}

	public void setDataEditalInicio(Date dataEditalInicio) {
		this.dataEditalInicio = dataEditalInicio;
	}

	public Date getDataEditalFim() {
		return dataEditalFim;
	}

	public void setDataEditalFim(Date dataEditalFim) {
		this.dataEditalFim = dataEditalFim;
	}

	public Date getDataInscricaoInicio() {
		return dataInscricaoInicio;
	}

	public void setDataInscricaoInicio(Date dataInscricaoInicio) {
		this.dataInscricaoInicio = dataInscricaoInicio;
	}

	public Date getDataInscricaoFim() {
		return dataInscricaoFim;
	}

	public void setDataInscricaoFim(Date dataInscricaoFim) {
		this.dataInscricaoFim = dataInscricaoFim;
	}

	public Date getDataPeriodoAvaliacaoInicio() {
		return dataPeriodoAvaliacaoInicio;
	}

	public void setDataPeriodoAvaliacaoInicio(Date dataPeriodoAvaliacaoInicio) {
		this.dataPeriodoAvaliacaoInicio = dataPeriodoAvaliacaoInicio;
	}

	public Date getDataPeriodoAvaliacaoFim() {
		return dataPeriodoAvaliacaoFim;
	}

	public void setDataPeriodoAvaliacaoFim(Date dataPeriodoAvaliacaoFim) {
		this.dataPeriodoAvaliacaoFim = dataPeriodoAvaliacaoFim;
	}

	public Date getDataEntregaResultadosInicio() {
		return dataEntregaResultadosInicio;
	}

	public void setDataEntregaResultadosInicio(Date dataEntregaResultadosInicio) {
		this.dataEntregaResultadosInicio = dataEntregaResultadosInicio;
	}

	public Date getDataEntregaResultadosFim() {
		return dataEntregaResultadosFim;
	}

	public void setDataEntregaResultadosFim(Date dataEntregaResultadosFim) {
		this.dataEntregaResultadosFim = dataEntregaResultadosFim;
	}

	public Date getDataPeriodoLetivoInicio() {
		return dataPeriodoLetivoInicio;
	}

	public void setDataPeriodoLetivoInicio(Date dataPeriodoLetivoInicio) {
		this.dataPeriodoLetivoInicio = dataPeriodoLetivoInicio;
	}

	public Date getDataPeriodoLetivoFim() {
		return dataPeriodoLetivoFim;
	}

	public void setDataPeriodoLetivoFim(Date dataPeriodoLetivoFim) {
		this.dataPeriodoLetivoFim = dataPeriodoLetivoFim;
	}

	public Date getDataEntregaCertificadoInicio() {
		return dataEntregaCertificadoInicio;
	}

	public void setDataEntregaCertificadoInicio(Date dataEntregaCertificadoInicio) {
		this.dataEntregaCertificadoInicio = dataEntregaCertificadoInicio;
	}

	public Date getDataEntregaCertificadoFim() {
		return dataEntregaCertificadoFim;
	}

	public void setDataEntregaCertificadoFim(Date dataEntregaCertificadoFim) {
		this.dataEntregaCertificadoFim = dataEntregaCertificadoFim;
	}
}
