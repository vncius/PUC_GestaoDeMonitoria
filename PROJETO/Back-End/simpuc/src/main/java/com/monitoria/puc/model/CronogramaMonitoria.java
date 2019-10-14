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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity(name = "cronogramaMonitoria")
public class CronogramaMonitoria implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonManagedReference
	@OneToOne()
    @JoinColumn(name = "curso_id", referencedColumnName = "id",nullable = false)
	private Curso curso;
	
	@Column(name = "data_edital_inicio", nullable = false)
	@Temporal(TemporalType.DATE)
	 @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataEditalInicio;
    
    @Column(name = "data_edital_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataEditalFim;
    
    @Column(name = "data_inscricao_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataInscricaoInicio;
    
    @Column(name = "data_inscricao_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataInscricaoFim;
    
    @Column(name = "periodo_avaliacao_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataPeriodoAvaliacaoInicio;
    
    @Column(name = "periodo_avaliacao_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataPeriodoAvaliacaoFim;
    
    @Column(name = "entrega_resultados_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataEntregaResultadosInicio;
    
    @Column(name = "entrega_resultados_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataEntregaResultadosFim;
    
    @Column(name = "periodo_letivo_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataPeriodoLetivoInicio;
    
    @Column(name = "periodo_letivo_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataPeriodoLetivoFim;
    
    @Column(name = "entrega_certificado_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataEntregaCertificadoInicio;
    
    @Column(name = "entrega_certificado_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataEntregaCertificadoFim;
	
	
}
