package com.monitoria.puc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.monitoria.puc.utilidades.Validacoes;

@Entity
public class UsuarioModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String senha;
	
	private String matricula;

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
		this.nome = nome.trim();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha.trim();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula.trim();
	}

	public Boolean validaSeCamposObrigatoriosPreenchidos() {
		if (!Validacoes.valorStringEstaPreenchido(this.matricula)) return false;
		if (!Validacoes.valorStringEstaPreenchido(this.nome)) return false;
		if (!Validacoes.valorStringEstaPreenchido(this.senha)) return false;
		return true;
	}
}
