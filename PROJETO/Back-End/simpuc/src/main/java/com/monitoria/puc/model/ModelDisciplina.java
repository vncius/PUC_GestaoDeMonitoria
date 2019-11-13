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

@Entity(name = "disciplina")
public class ModelDisciplina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "fk_curso_id", referencedColumnName = "id")
	private ModelCurso curso;
	
	private String descricao;
    
    private boolean ehSelecionado;
	
	@OneToMany(mappedBy = "disciplina")
    private List<ModelOrientador> orientadores;
	
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
}
