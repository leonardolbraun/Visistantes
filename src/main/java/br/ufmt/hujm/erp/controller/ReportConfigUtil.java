package br.ufmt.hujm.erp.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.sql.*;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class ReportConfigUtil {

	/**
	 * PRIVATE METHODS
	 */
	private static void setCompileTempDir(ServletContext context, String uri) {
		System.setProperty("jasper.reports.compile.temp",
				context.getRealPath(uri));
	}

	/**
	 * PUBLIC METHODS
	 */
	public static boolean compileReport(ServletContext context,
			String compileDir, String filename) throws JRException {
		String jasperFileName = context.getRealPath(compileDir + filename
				+ ".jasper");
		File jasperFile = new File(jasperFileName);

		if (jasperFile.exists()) {
			return true; // jasper file already exists, do not compile again
		}
		try {
			// jasper file has not been constructed yet, so compile the xml file
			setCompileTempDir(context, compileDir);

			String xmlFileName = jasperFileName.substring(0,
					jasperFileName.indexOf(".jasper"))
					+ ".jrxml";
			JasperCompileManager.compileReportToFile(xmlFileName);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static JasperPrint fillReport(File reportFile,
			Map<String, Object> parameters, Connection conn) throws JRException {
		// parameters.put("BaseDir", reportFile.getParentFile());

		// Long id = new Long("1");
		// parameters.put("idVisitante", id);

		JasperPrint jasperPrint = JasperFillManager.fillReport(
				reportFile.getPath(), parameters, conn);

		return jasperPrint;
	}

	public static String getJasperFilePath(ServletContext context,
			String compileDir, String jasperFile) {
		return context.getRealPath(compileDir + jasperFile);
	}

}
