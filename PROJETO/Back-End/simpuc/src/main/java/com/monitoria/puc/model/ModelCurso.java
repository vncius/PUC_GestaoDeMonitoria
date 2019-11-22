package com.monitoria.puc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;

@Entity(name = "curso")
public class ModelCurso implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public ModelCurso(Long id, String descricao, ModelCronogramaMonitoria cronogramaMonitoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.cronogramaMonitoria = cronogramaMonitoria;
	}
	
	public ModelCurso() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "text")
	private String descricao;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
	private List<ModelUsuario> usuarios;
	
	@JsonBackReference
	@OneToMany(mappedBy = "curso")
    private List<ModelDisciplina> disciplinas; 
	
	@JsonBackReference
	@OneToOne(mappedBy = "curso")
    private ModelCronogramaMonitoria cronogramaMonitoria;
	
	@JsonCreator
	public ModelCurso(Long id) {
		this.id = id;
	}
	
	@JsonCreator
	public ModelCurso(String id) {
		this.id = Long.parseLong(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ModelCronogramaMonitoria getCronogramaMonitoria() {
		return cronogramaMonitoria;
	}

	public void setCronogramaMonitoria(ModelCronogramaMonitoria cronogramaMonitoria) {
		this.cronogramaMonitoria = cronogramaMonitoria;
	}
	
	public List<ModelDisciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<ModelDisciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
}
