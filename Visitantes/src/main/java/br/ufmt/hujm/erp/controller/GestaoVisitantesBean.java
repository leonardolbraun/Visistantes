package br.ufmt.hujm.erp.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CaptureEvent;

import br.ufmt.hujm.erp.model.Setor;
import br.ufmt.hujm.erp.model.Visitante;
import br.ufmt.hujm.erp.model.TipoVisita;
import br.ufmt.hujm.erp.model.Visitas;
import br.ufmt.hujm.erp.repository.VisitanteDAO;
import br.ufmt.hujm.erp.service.CadastroVisitanteService;
import br.ufmt.hujm.erp.util.FacesMessages;

@Named
@ManagedBean
@ViewScoped
public class GestaoVisitantesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VisitanteDAO visitanteDAO;

	@Inject
	private CadastroVisitanteService cadastroVisitante;

	@Inject
	private CadastroVisitanteService cadastroSetor;

	@Inject
	private CadastroVisitanteService cadastroVisitas;

	@Inject
	private FacesMessages messages;

	private List<Visitante> todosVisitantes;

	private List<Visitas> todasVisitas;

	public List<Visitas> getTodasVisitas() {
		return todasVisitas;
	}

	private Visitas visitanteVisitas = new Visitas();

	private List<Setor> todosSetores;

	private List<Visitante> visitantesFiltrados;
	private List<Visitas> visitasFiltradas;

	private List<Setor> setoresFiltrados;

	private Visitante visitanteEdicao = new Visitante();
	private Visitante visitanteSelecionado;
	private Visitas visitaSelecionada;

	private Setor setorEdicao = new Setor();
	private Setor setorSelecionado;

	public void prepararNovoCadastro() {
		visitanteEdicao = new Visitante();
	}

	public void apagaFoto() {
		visitanteEdicao.setFoto("");
	}

	public void prepararNovoCadastroSetor() {
		setorEdicao = new Setor();
	}

	@PostConstruct
	public void consultar() {
		todosSetores = visitanteDAO.todosSetores();
		todosVisitantes = visitanteDAO.todas();
		todasVisitas = visitanteDAO.todasVisitas();
	}

	public void salvar() {
		cadastroVisitante.salvar(visitanteEdicao);
		consultar();

		messages.info("Visitante Salva com Sucesso");

		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm:msgs", "frm:visitantes-table"));

	}

	public void salvarSetor() {
		cadastroSetor.salvarSetor(setorEdicao);
		consultar();

		messages.info("Setor salvo com sucesso");

		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm:msgs", "frm:setores-table"));
	}

	public Setor getSetorEdicao() {
		return setorEdicao;
	}

	public void setSetorEdicao(Setor setorEdicao) {
		this.setorEdicao = setorEdicao;
	}

	public void oncapture(CaptureEvent captureEvent) {
		String filename = getRandomImageName();
		byte[] data = captureEvent.getData();

		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();

		String newFileName = servletContext.getRealPath("") + "resources"
				+ File.separator + "demo" + File.separator + "images"
				+ File.separator + "photocam" + File.separator + filename
				+ ".jpeg";

		this.visitanteEdicao.setFoto(filename);
		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(newFileName));
			imageOutput.write(data, 0, data.length);
			imageOutput.close();
		} catch (IOException e) {
			throw new FacesException("Error in writing captured image.", e);
		}

	}

	private String getRandomImageName() {
		int i = (int) (Math.random() * 10000000);

		return String.valueOf(i);
	}

	public void excluir() {
		cadastroVisitante.excluir(visitanteSelecionado);
		visitanteSelecionado = null;
		consultar();

		messages.info("Visitante excluído com Sucesso.");

		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm:msgs", "frm:visitantes-table"));

	}

	public Visitas getVisitanteVisitas() {
		return visitanteVisitas;
	}

	public void setVisitanteVisitas(Visitas visitanteVisitas) {
		this.visitanteVisitas = visitanteVisitas;
	}

	public void excluirSetor() {
		cadastroSetor.excluirSetor(setorSelecionado);
		setorSelecionado = null;

		consultar();

		messages.info("Setor excluído com Sucesso.");

	}

	public Visitas getVisitaSelecionada() {
		return visitaSelecionada;
	}

	public void setVisitaSelecionada(Visitas visitaSelecionada) {
		this.visitaSelecionada = visitaSelecionada;
	}

	public void salvaVisita() {

		if (visitanteEdicao != null) {
			visitanteVisitas
					.setDataVisita(new Date(System.currentTimeMillis()));
			visitanteVisitas.setVisitante(visitanteEdicao);
			cadastroVisitas.salvarVisita(visitanteVisitas);

			consultar();
		} else {

			visitanteVisitas
					.setDataVisita(new Date(System.currentTimeMillis()));
			visitanteVisitas.setVisitante(visitanteSelecionado);
			cadastroVisitas.salvarVisita(visitanteVisitas);

			consultar();
		}
		messages.info("Visita salva com sucesso");

	}

	public Visitante getVisitanteEdicao() {
		return visitanteEdicao;
	}

	public void setVisitanteEdicao(Visitante visitanteEdicao) {
		this.visitanteEdicao = visitanteEdicao;
	}

	public List<Visitante> getVisitantesFiltrados() {
		return visitantesFiltrados;
	}

	public List<Visitas> getVisitasFiltradas() {
		return visitasFiltradas;
	}

	public void setVisitasFiltradas(List<Visitas> visitasFiltradas) {
		this.visitasFiltradas = visitasFiltradas;
	}

	public List<Setor> getSetoresFiltrados() {
		return setoresFiltrados;
	}

	public void setVisitantesFiltrados(List<Visitante> visitantesFiltrados) {
		this.visitantesFiltrados = visitantesFiltrados;
	}

	public void setSetoresFiltrados(List<Setor> setoresFiltrados) {
		this.setoresFiltrados = setoresFiltrados;
	}

	public Visitante getVisitanteSelecionado() {
		return visitanteSelecionado;
	}

	public void setVisitanteSelecionado(Visitante visitanteSelecionado) {
		this.visitanteSelecionado = visitanteSelecionado;
	}

	public List<Visitante> getTodosVisitantes() {
		return todosVisitantes;
	}

	public List<Setor> getTodosSetores() {
		return todosSetores;
	}

	public TipoVisita[] getTiposVisitantes() {
		return TipoVisita.values();
	}

	public Setor getSetorSelecionado() {
		return setorSelecionado;
	}

	public void setSetorSelecionado(Setor setorSelecionado) {
		this.setorSelecionado = setorSelecionado;
	}

}
