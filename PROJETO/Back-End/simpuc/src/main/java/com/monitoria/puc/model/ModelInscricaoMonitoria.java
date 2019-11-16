package com.monitoria.puc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.monitoria.puc.repository.RepositoryCurso;
import com.monitoria.puc.utilidades.DTOFichaDeInscricao;
import com.monitoria.puc.utilidades.Utilidades;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 16/11/2019*/
@Entity(name = "incricao_monitoria")
public class ModelInscricaoMonitoria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	private long id_curso;
	private long id_disciplina;
	private long id_orientador;
	
	public ModelInscricaoMonitoria() {
		super();
		this.matricula = "";
		this.telefone = "";
		this.possuiBolsa = false;
		this.jaPossuiuBolsa = false;
		this.nome = "";
		this.email = "";
		this.carga_horaria_segunda = "";
		this.carga_horaria_terca = "";
		this.carga_horaria_quarta = "";
		this.carga_horaria_quinta = "";
		this.carga_horaria_sexta = "";
		this.carga_horaria_sabado = "";
		this.id_curso = 0;
		this.id_disciplina = 0;
		this.id_orientador = 0;
	}

	public boolean validaInscricao() {
		Boolean retorno = true;
		if (!Utilidades.valorStringEstaPreenchido(this.matricula) || !Utilidades.quantidadeDeCaracteresEhValido(this.matricula, 0, 30)) {
			retorno = false;
		} else if (!Utilidades.valorStringEstaPreenchido(this.nome) || !Utilidades.quantidadeDeCaracteresEhValido(this.nome, 0, 100)) {
			retorno = false;
		} else if (!Utilidades.valorStringEstaPreenchido(this.email) || !Utilidades.quantidadeDeCaracteresEhValido(this.email, 0, 100)) {
			retorno = false;
		} else if (!Utilidades.valorStringEstaPreenchido(this.telefone) || !Utilidades.quantidadeDeCaracteresEhValido(this.telefone, 0, 50)) {
			retorno = false;
		} else if (this.id_curso <= 0) {
			retorno = false;
		} else if (this.id_disciplina <= 0) {
			retorno = false;
		} else if (this.id_orientador <= 0) {
			retorno = false;
		} 
		return retorno;
	}
	
	@SuppressWarnings("unused")
	public DTOFichaDeInscricao converteModelEmDTOParaConsulta(RepositoryCurso cursoRepository) {

		DTOFichaDeInscricao model = new DTOFichaDeInscricao();
		model.setId(this.id);
		model.setMatricula(this.matricula);
		model.setPossuiBolsa(this.possuiBolsa);
		model.setJaPossuiuBolsa(this.jaPossuiuBolsa);
		model.setNome(this.nome);
		model.setEmail(this.email);
		model.setTelefone(this.telefone);
		model.setCarga_horaria_segunda(this.carga_horaria_segunda);
		model.setCarga_horaria_terca(this.carga_horaria_terca);
		model.setCarga_horaria_quarta(this.carga_horaria_quarta);
		model.setCarga_horaria_quinta(this.carga_horaria_quinta);
		model.setCarga_horaria_sexta(this.carga_horaria_sexta);
		model.setCarga_horaria_sabado(this.carga_horaria_sabado);
		model.setStatusIncricao(this.statusIncricao);
		Iterable<ModelCurso> listCursos = cursoRepository.findAll();

		//PERCORRE A LISTA DE CURSOS E VERIFICA O ID DO CURSO INFORMADO NA FICHA DE INSCRICAO
		listCursos.forEach(curso -> {
			
			if (curso.getId().equals(this.id_curso)) {
				//PERCORRE A LISTA DE DISCIPLINAS E VERIFICA O ID DA DISCIPLINA INFORMADO NA FICHA DE INSCRICAO
				curso.getDisciplinas().forEach(disciplina -> {
					if (disciplina.getId().equals(this.id_disciplina)) {
						//MARCA DISCIPLINA COMO SELECIONADA PARA SER PRÉ SELECIONADO NO HTML
						disciplina.setEhSelecionado(true);

						//PERCORRE A LISTA DE ORIENTADORES E VERIFICA O ID DA DISCIPLINA INFORMADO NA FICHA DE INSCRICAO
						disciplina.getOrientadores().forEach(orientador -> {
							if (orientador.getId().equals(this.id_orientador)) {
								//MARCA DISCIPLINA COMO SELECIONADA PARA SER PRÉ SELECIONADO NO HTML
								orientador.setEhSelecionado(true);
							}
						});
					}
				});
				
				// SETA O CURSO NO OBJETO MODELO DE INSCRIÇÃO PARA SER EXIBIDO NO HTML ESTA INSCRIÇÃO JÁ CADASTRADA
				model.setCursos(curso);
			}
		});
		return model;
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

	public long getId_curso() {
		return id_curso;
	}

	public void setId_curso(long id_curso) {
		this.id_curso = id_curso;
	}

	public long getId_disciplina() {
		return id_disciplina;
	}

	public void setId_disciplina(long id_disciplina) {
		this.id_disciplina = id_disciplina;
	}

	public long getId_orientador() {
		return id_orientador;
	}

	public void setId_orientador(long id_orientador) {
		this.id_orientador = id_orientador;
	}

	public String getStatusIncricao() {
		return statusIncricao;
	}

	public void setStatusIncricao(String statusIncricao) {
		this.statusIncricao = statusIncricao;
	}

	public String getCarga_horaria_sabado() {
		return carga_horaria_sabado;
	}

	public void setCarga_horaria_sabado(String carga_horaria_sabado) {
		this.carga_horaria_sabado = carga_horaria_sabado;
	}
}
