package it.polito.tdp.meteo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.meteo.bean.Citta;
import it.polito.tdp.meteo.bean.Rilevamento;
import it.polito.tdp.meteo.bean.SimpleCity;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {

	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	
	MeteoDAO db = new MeteoDAO();
	ArrayList<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

	public Model() {

	}
	

	public String getUmiditaMedia(int mese) {
		String answer = "";
		Set<String> citta = db.getCitta();
		for(String c : citta) {
			answer += c + " " + db.getAvgRilevamentiLocalitaMese(mese, c) + "% \n";
		}
		return answer;
	}

	public String trovaSequenza(int mese) {

		return "TODO!";
	}

	private Double punteggioSoluzione(List<SimpleCity> soluzioneCandidata) {

		double score = 0.0;
		return score;
	}

	private boolean controllaParziale(List<SimpleCity> parziale) {

		return true;
	}

}
