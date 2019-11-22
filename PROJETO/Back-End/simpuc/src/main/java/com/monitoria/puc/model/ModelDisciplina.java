package com.monitoria.puc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@Entity(name = "disciplina")
public class ModelDisciplina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String codigoDisciplina;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "fk_curso_id", referencedColumnName = "id")
	private ModelCurso curso;
	
	private String descricao;
    
    private boolean ehSelecionado;
	
	@OneToMany(mappedBy = "disciplina")
    private List<ModelOrientador> orientadores;
	
	private int qtdeVgMonitoria;
	
	public boolean isEhSelecionado() {
		return ehSelecionado;
	}

	public void setEhSelecionado(boolean ehSelecionado) {
		this.ehSelecionado = ehSelecionado;
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

	public List<ModelOrientador> getOrientadores() {
		return orientadores;
	}

	public void setOrientadores(List<ModelOrientador> orientadores) {
		this.orientadores = orientadores;
	}

	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public int getQtdeVgMonitoria() {
		return qtdeVgMonitoria;
	}

	public void setQtdeVgMonitoria(int qtdeVgMonitoria) {
		this.qtdeVgMonitoria = qtdeVgMonitoria;
	}

	public ModelCurso getCurso() {
		return curso;
	}

	public void setCurso(ModelCurso curso) {
		this.curso = curso;
	}
	
	

}
