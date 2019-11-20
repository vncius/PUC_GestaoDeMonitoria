package com.monitoria.puc.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@Entity(name = "orientador")
public class ModelOrientador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "fk_disciplina_id", referencedColumnName = "id")
	private ModelDisciplina disciplina;
	
	private String descricao;
    
    private boolean ehSelecionado;
    
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

	public boolean isEhSelecionado() {
		return ehSelecionado;
	}

	public void setEhSelecionado(boolean ehSelecionado) {
		this.ehSelecionado = ehSelecionado;
	}
}
