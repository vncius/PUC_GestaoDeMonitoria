package com.monitoria.puc.utilidades;

import com.monitoria.puc.model.ModelCurso;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
public class DTOFichaDeInscricao {

	private Long id;
	
	private String matricula;
	private String telefone;
	private boolean possuiBolsa;
	private boolean jaPossuiuBolsa;
	private String nome;
	private String email;
	
	private String carga_horaria_segunda;
	private String carga_horaria_terca;
	private String carga_horaria_quarta;
	private String carga_horaria_quinta;
	private String carga_horaria_sexta;
	private String carga_horaria_sabado;
	private String statusIncricao;
	private Long id_curso_selecionado;
	private Long id_disciplina_selecionada;
	private Long id_orientador_selecionado;
	private int nota_avaliacao;
	private int nota_coeficiente;
	
	public Long getId_disciplina_selecionada() {
		return id_disciplina_selecionada;
	}

	public void setId_disciplina_selecionada(Long id_disciplina_selecionada) {
		this.id_disciplina_selecionada = id_disciplina_selecionada;
	}

	public Long getId_orientador_selecionado() {
		return id_orientador_selecionado;
	}

	public void setId_orientador_selecionado(Long id_orientador_selecionado) {
		this.id_orientador_selecionado = id_orientador_selecionado;
	}

	private ModelCurso curso;

	public void InicializeParaNovoCadastro(ModelCurso curso, String matricula, String nome) {
		this.matricula = matricula;
		this.telefone = "";
		this.possuiBolsa = false;
		this.jaPossuiuBolsa = false;
		this.nome = nome;
		this.email = "";
		this.carga_horaria_segunda = "";
		this.carga_horaria_terca = "";
		this.carga_horaria_quarta = "";
		this.carga_horaria_quinta = "";
		this.carga_horaria_sexta = "";
		this.carga_horaria_sabado = "";
		this.curso = curso;
		this.nota_avaliacao = 0;
		this.nota_coeficiente = 0;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public boolean getPossuiBolsa() {
		return possuiBolsa;
	}
	public void setPossuiBolsa(boolean possuiBolsa) {
		this.possuiBolsa = possuiBolsa;
	}
	public boolean getJaPossuiuBolsa() {
		return jaPossuiuBolsa;
	}
	public void setJaPossuiuBolsa(boolean jaPossuiuBolsa) {
		this.jaPossuiuBolsa = jaPossuiuBolsa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCarga_horaria_segunda() {
		return carga_horaria_segunda;
	}
	public void setCarga_horaria_segunda(String carga_horaria_segunda) {
		this.carga_horaria_segunda = carga_horaria_segunda;
	}
	public String getCarga_horaria_terca() {
		return carga_horaria_terca;
	}
	public void setCarga_horaria_terca(String carga_horaria_terca) {
		this.carga_horaria_terca = carga_horaria_terca;
	}
	public String getCarga_horaria_quarta() {
		return carga_horaria_quarta;
	}
	public void setCarga_horaria_quarta(String carga_horaria_quarta) {
		this.carga_horaria_quarta = carga_horaria_quarta;
	}
	public String getCarga_horaria_quinta() {
		return carga_horaria_quinta;
	}
	public void setCarga_horaria_quinta(String carga_horaria_quinta) {
		this.carga_horaria_quinta = carga_horaria_quinta;
	}
	public String getCarga_horaria_sexta() {
		return carga_horaria_sexta;
	}
	public void setCarga_horaria_sexta(String carga_horaria_sexta) {
		this.carga_horaria_sexta = carga_horaria_sexta;
	}
	public ModelCurso getCursos() {
		return curso;
	}
	public void setCursos(ModelCurso cursos) {
		this.curso = cursos;
	}

	public String getCarga_horaria_sabado() {
		return carga_horaria_sabado;
	}

	public void setCarga_horaria_sabado(String carga_horaria_sabado) {
		this.carga_horaria_sabado = carga_horaria_sabado;
	}

	public String getStatusIncricao() {
		return statusIncricao;
	}

	public void setStatusIncricao(String statusIncricao) {
		this.statusIncricao = statusIncricao;
	}

	public Long getId_curso_selecionado() {
		return id_curso_selecionado;
	}

	public void setId_curso_selecionado(Long id_curso_selecionado) {
		this.id_curso_selecionado = id_curso_selecionado;
	}

	public int getNota_avaliacao() {
		return nota_avaliacao;
	}

	public void setNota_avaliacao(int nota_avaliacao) {
		this.nota_avaliacao = nota_avaliacao;
	}

	public int getNota_coeficiente() {
		return nota_coeficiente;
	}

	public void setNota_coeficiente(int nota_coeficiente) {
		this.nota_coeficiente = nota_coeficiente;
	}
}
