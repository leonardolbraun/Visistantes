package br.ufmt.hujm.erp.model;

public enum TipoVisita {

	ACOMPANHANTE("Acompanhar paciente."), 
	VISITAR("Visitar paciente internado."),
	CONSULTA("Realizar uma consulta"),
	EXAME("Realizar um exame."),
	ADMINISTRACAO("Visitar Administração.");
	
	private String descricao;

	TipoVisita(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}