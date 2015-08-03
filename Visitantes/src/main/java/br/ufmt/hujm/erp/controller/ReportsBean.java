package br.ufmt.hujm.erp.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import br.ufmt.hujm.erp.controller.AbstractReportBean;
import br.ufmt.hujm.erp.repository.Database;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "reportsBean")
@SessionScoped
public class ReportsBean extends AbstractReportBean implements Serializable {

	private final String COMPILE_FILE_NAME = "visitante";

	Long id = new Long(2);

	@Override
	protected String getCompileFileName() {
		return COMPILE_FILE_NAME;
	}

	@Override
	protected Map<String, Object> getReportParameters() {
		Map<String, Object> reportParameters = new HashMap<String, Object>();

		reportParameters.put("idVisita", id);

		return reportParameters;
	}

	Long idVisita = new Long(1);

	public Long idUltimaVisita() throws SQLException {
		Connection conn = Database.getConnection();

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt
				.executeQuery("SELECT id FROM Visitas ORDER BY id DESC LIMIT 1;");

		if (rs.next())
			idVisita = rs.getLong("id");

		rs.close();
		rs = null;

		return idVisita;
	}

	public String execute() {
		try {
			id = idUltimaVisita();
			super.prepareReport();
		} catch (Exception e) {
			// criar a exception
			e.printStackTrace();
		}

		return null;
	}
}