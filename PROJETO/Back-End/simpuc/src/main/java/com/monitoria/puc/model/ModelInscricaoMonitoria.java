package com.monitoria.puc.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ModelInscricaoMonitoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	private String matricula;
	private long id_curso;
	private long id_disciplina;
	private long id_orientador;
	private String telefone;
	private String email;
	private String distribuicaoCargaHoraria;
	
	public boolean validaInscricaoMonitoria(){
		boolean retorno = true;
		
		if (nome.equals("")) {
			retorno = false;
		}
		
		return retorno;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDistribuicaoCargaHoraria() {
		return distribuicaoCargaHoraria;
	}
	public void setDistribuicaoCargaHoraria(String distribuicaoCargaHoraria) {
		this.distribuicaoCargaHoraria = distribuicaoCargaHoraria;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
