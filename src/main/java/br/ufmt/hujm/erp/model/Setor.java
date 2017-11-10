package br.ufmt.hujm.erp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Setor implements Serializable {

	private static final long serialVersionUID = 3189572780367765626L;

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty (message = "O campo Setor não pode estar vazio. Preencha o campo do setor")
	@Column(name = "setor_visitante", nullable = false, length = 80)
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}