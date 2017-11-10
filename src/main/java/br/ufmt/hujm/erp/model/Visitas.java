package br.ufmt.hujm.erp.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Visitas implements Serializable {

	private static final long serialVersionUID = 587800553867262655L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_visita")
	private Date dataVisita;

	@ManyToOne
	@JoinColumn(name = "visitante_id")
	private Visitante visitante;

	@NotEmpty
	@Column(name = "motivo_visita", nullable = false, length = 120)
	private String motivoVisita;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoVisita tipo;

	@NotEmpty
	@Column(name = "setor_visitado", nullable = false, length = 120)
	private String setorVisitado;

	public String getMotivoVisita() {
		return motivoVisita;
	}

	public void setMotivoVisita(String motivoVisita) {
		this.motivoVisita = motivoVisita;
	}

	public TipoVisita getTipo() {
		return tipo;
	}

	public void setTipo(TipoVisita tipo) {
		this.tipo = tipo;
	}

	public String getSetorVisitado() {
		return setorVisitado;
	}

	public void setSetorVisitado(String setorVisitado) {
		this.setorVisitado = setorVisitado;
	}

	public Visitante getVisitante() {
		return visitante;
	}

	public void setVisitante(Visitante visitante) {
		this.visitante = visitante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getDataFormatada() {
		return new SimpleDateFormat("dd/MM/yyyy").format(dataVisita);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visitas other = (Visitas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}