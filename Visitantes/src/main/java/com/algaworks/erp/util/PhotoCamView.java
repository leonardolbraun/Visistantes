package com.algaworks.erp.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.primefaces.event.CaptureEvent;
import org.primefaces.model.CroppedImage;

@ManagedBean
@ViewScoped
public class PhotoCamView implements Serializable {

	private String filename;
	private boolean flagPhotocam;
	private boolean flagImageCropper;
	private boolean flagPhotoCropper;
	private CroppedImage croppedImage;
	private String photoCropped;
	private String photo;



	public void cancel() {
		flagPhotocam = true;
		flagImageCropper = false;
		flagPhotoCropper = false;
	}

	public String getFilename() {
		return filename;
	}

	public void crop() {

		try {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			String fileName = servletContext.getRealPath("") + "resources"
					+ File.separator + "demo" + File.separator + "images"
					+ File.separator + "photocam" + File.separator + filename
					+ ".jpeg";

			photoCropped = File.separator + "photocam" + File.separator + "fotoCortada.jpeg";
			FileImageOutputStream imageOutput;

			imageOutput = new FileImageOutputStream(new File(fileName));
			imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
			imageOutput.close();
			flagImageCropper = false;
			flagPhotoCropper = false;
		} catch (IOException e) {
			throw new FacesException("Error in writing captured image.", e);
		}

	}

	public boolean isFlagPhotoCam() {
		return flagPhotocam;
	}

	public void setFlagPhotoCam(boolean flagPhotoCam) {
		this.flagPhotocam = flagPhotoCam;
	}

	public boolean isFlagImageCropper() {
		return flagImageCropper;
	}

	public void setFlagImageCropper(boolean flagImageCropper) {
		this.flagImageCropper = flagImageCropper;
	}

	public boolean isFlagPhotoCropper() {
		return flagPhotoCropper;
	}

	public void setFlagPhotoCropper(boolean flagPhotoCropper) {
		this.flagPhotoCropper = flagPhotoCropper;
	}

	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}

	public String getPhotoCropped() {
		return photoCropped;
	}

	public void setPhotoCropped(String photoCropped) {
		this.photoCropped = photoCropped;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	
}