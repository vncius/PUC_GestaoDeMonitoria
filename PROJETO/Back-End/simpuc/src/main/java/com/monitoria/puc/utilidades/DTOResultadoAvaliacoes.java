package com.monitoria.puc.utilidades;

/*DESENVOLVEDOR: VINICIUS VIEIRA ABREU*/
/*DATA: 01/12/2019*/
public class DTOResultadoAvaliacoes {

	private String matricula;
	private double notaAluno;
	private String status;
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public double getNotaAluno() {
		return notaAluno;
	}
	public void setNotaAluno(double notaAluno) {
		this.notaAluno = notaAluno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
