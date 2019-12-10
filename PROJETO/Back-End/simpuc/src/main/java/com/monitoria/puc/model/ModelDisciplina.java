package com.monitoria.puc.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@Entity(name = "disciplina")
public class ModelDisciplina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int qtdeVgMonitoria;
	
	private int qtdeVgDisponiveis;
	
	private String codigoDisciplina;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "fk_curso_id", referencedColumnName = "id")
	private ModelCurso curso;
	
	private String descricao;
    
    private boolean ehSelecionado;
	
	@OneToMany(mappedBy = "disciplina")
    private List<ModelOrientador> orientadores;
	
	@JsonIgnore
	@OneToMany(mappedBy = "modelDisciplina")
	private List<ModelVaga> vgdisciplinas;
	
	
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

	public ModelCurso getCurso() {
		return curso;
	}

	public void setCurso(ModelCurso curso) {
		this.curso = curso;
	}

	public int getQtdeVgMonitoria() {
		return qtdeVgMonitoria;
	}

	public void setQtdeVgMonitoria(int qtdeVgMonitoria) {
		this.qtdeVgMonitoria = qtdeVgMonitoria;
	}

	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	
	public List<ModelVaga> getVgdisciplinas() {
		return vgdisciplinas;
	}

	public void setVgdisciplinas(List<ModelVaga> vgdisciplinas) {
		this.vgdisciplinas = vgdisciplinas;
	}
	
	public int getQtdeVgDisponiveis() {
		return qtdeVgDisponiveis;
	}

	public void setQtdeVgDisponiveis(int qtdeVgDisponiveis) {
		this.qtdeVgDisponiveis = qtdeVgDisponiveis;
	}

	public ModelDisciplina(Long id, int qtdeVgMonitoria, String codigoDisciplina, ModelCurso curso, String descricao,
			boolean ehSelecionado, List<ModelOrientador> orientadores) {
		super();
		this.id = id;
		this.qtdeVgMonitoria = qtdeVgMonitoria;
		this.codigoDisciplina = codigoDisciplina;
		this.curso = curso;
		this.descricao = descricao;
		this.ehSelecionado = ehSelecionado;
		this.orientadores = orientadores;
	}

	public ModelDisciplina() {
		super();
	}
			
}
