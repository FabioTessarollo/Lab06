package it.polito.tdp.meteo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.meteo.bean.Citta;
import it.polito.tdp.meteo.bean.Rilevamento;

public class MeteoDAO {
	
	public Set<String> getCitta() {
		final String sql = "SELECT Localita FROM situazione";
		
		Set<String> citta = new HashSet<String>();
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				citta.add(rs.getString("Localita"));
			}

			conn.close();
			return citta;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {
		
		final String sql = "SELECT Localita, Data, Umidita FROM situazione WHERE MONTH(data) = ? AND localita = ?";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();
		Integer month = mese;

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, month.toString());
			st.setString(2, localita);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		

	}

	public Double getAvgRilevamentiLocalitaMese(int mese, String localita) {
		int sommaUmi = 0;
		int count = 0;
		List<Rilevamento> rilevamenti = this.getAllRilevamentiLocalitaMese(mese, localita);
		for(Rilevamento r : rilevamenti) {
			sommaUmi += r.getUmidita();
			count++;
		}
		double media = sommaUmi/count;
		return media;
	}

}
