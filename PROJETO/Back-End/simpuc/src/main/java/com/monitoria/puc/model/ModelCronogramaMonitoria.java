package com.monitoria.puc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
import com.monitoria.puc.utilidades.Utilidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
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
}
