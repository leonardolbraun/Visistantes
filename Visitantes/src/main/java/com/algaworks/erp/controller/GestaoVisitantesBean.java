package com.algaworks.erp.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CaptureEvent;

import com.algaworks.erp.model.Setor;
import com.algaworks.erp.model.Visitante;
import com.algaworks.erp.model.TipoVisitante;
import com.algaworks.erp.repository.VisitanteDAO;
import com.algaworks.erp.service.CadastroVisitanteService;
import com.algaworks.erp.util.FacesMessages;
import com.algaworks.erp.util.PhotoCamView;

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
	private FacesMessages messages;

	private List<Visitante> todosVisitantes;

	private List<Setor> todosSetores;

	private List<Visitante> visitantesFiltrados;

	private Visitante visitanteEdicao = new Visitante();
	private Visitante visitanteSelecionado;

	private Setor setorEdicao = new Setor();
	private Setor setorSelecionado;

	public void prepararNovoCadastro() {
		visitanteEdicao = new Visitante();
	}
	
	public void prepararNovoCadastroSetor() {
		setorEdicao = new Setor();
	}

	@PostConstruct
	public void consultar() {
		todosSetores = visitanteDAO.todosSetores();
		todosVisitantes = visitanteDAO.todas();
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
		messages.info("Setor salvo com sucesso");
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

	public void setVisitantesFiltrados(List<Visitante> visitantesFiltrados) {
		this.visitantesFiltrados = visitantesFiltrados;
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

	public TipoVisitante[] getTiposVisitantes() {
		return TipoVisitante.values();
	}

	public Setor getSetorSelecionado() {
		return setorSelecionado;
	}

	public void setSetorSelecionado(Setor setorSelecionado) {
		this.setorSelecionado = setorSelecionado;
	}
	
}
