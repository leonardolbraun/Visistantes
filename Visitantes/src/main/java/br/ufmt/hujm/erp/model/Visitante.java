package br.ufmt.hujm.erp.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Visitante implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	@Column(name = "nome_visitante", nullable = false, length = 80)
	private String nomeVisitante;

//	@NotEmpty
//	@Column(name = "motivo_visita", nullable = false, length = 120)
//	private String motivoVisita;

	@Column(name = "foto_visitante", nullable = false, length = 500)
	private String foto;

	@NotEmpty
	@CPF
	@Column(nullable = false, length = 18, unique = true)
	private String cpf;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_visita")
	private Date dataCadastro;

//	@NotNull
//	@Enumerated(EnumType.STRING)
//	private TipoVisita tipo;

///	@NotEmpty
//	@Column(name = "setor_visitado", nullable = false, length = 120)
//	private String setorVisitado;

	@OneToMany(mappedBy = "visitante", targetEntity = Visitas.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Visitas> visitas;

//	private Setor setor;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeVisitante() {
		return nomeVisitante;
	}

	public void setNomeVisitante(String nomeVisitante) {
		this.nomeVisitante = nomeVisitante;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public String getFoto() {
		return foto;

	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	public String getDataFormatada(){
		return new SimpleDateFormat("dd/MM/yyyy").format(getDataCadastro());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visitante other = (Visitante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}