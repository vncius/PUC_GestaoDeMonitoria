package com.monitoria.puc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "vaga")
public class ModelVaga implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int qtdeVgSolicitada;
	
	@ManyToOne
	@JoinColumn(name = "id_disciplina_fk")
	private ModelDisciplina modelDisciplina;
	
	@Column(name = "usuario_id", nullable = false)
	private String usuario_id;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQtdeVgSolicitada() {
		return qtdeVgSolicitada;
	}

	public void setQtdeVgSolicitada(int qtdeVgSolicitada) {
		this.qtdeVgSolicitada = qtdeVgSolicitada;
	}

	public String getUsuario_id() {
		return usuario_id;
	}
	
	public ModelDisciplina getModelDisciplina() {
		return modelDisciplina;
	}

	public void setModelDisciplina(ModelDisciplina modelDisciplina) {
		this.modelDisciplina = modelDisciplina;
	}

	public void setUsuario_id(String usuario_id) {
		this.usuario_id = usuario_id;
	}

	public ModelVaga() {
		super();
	}
	

}
