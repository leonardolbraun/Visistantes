package com.algaworks.erp.model;

public enum TipoVisitante {

	ACOMPANHANTE("Acompanhar paciente."), 
	VISITAR("Visitar paciente internado."),
	CONSULTA("Realizar uma consulta"),
	EXAME("Realizar um exame.");
	
	private String descricao;

	TipoVisitante(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}