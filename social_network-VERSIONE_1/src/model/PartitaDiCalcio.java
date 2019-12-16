package model;

import campo.CampoComposto;
import campo.CampoStringa;
import view.Costanti;

public class PartitaDiCalcio extends Categoria {
	
	
	private static final String DESCRIZIONE = "Organizza una partita di calcio con i tuoi amici :)";
	private static final String NOME = "Partita di Calcio";

	public PartitaDiCalcio() {
		super(NOME,DESCRIZIONE);
		this.getCampi().add(new CampoStringa("Genere", "Il genere dei partecipanti", true));
		this.getCampi().add(new CampoStringa("Fascia d'età", "Range d'età dei partecipanti", true));
	}
	public boolean haveOptionalChoice() {
		return false;
	}

}
