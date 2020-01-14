package model;

import campo.Campo;
import campo.CampoComposto;

public class Escursione extends Categoria{
	
	
	private static final String DESCRIZIONE = "Organizza un esursione in montagna con i tuoi amici :)";
	private static final String NOME = "Escursione in montagna";

	public Escursione() {
		super(NOME,DESCRIZIONE);
		this.getCampi().add(new CampoComposto("Spese opzionali","", false, Costanti.speseOpzionaliEscursione()));
	}
	
	public boolean haveOptionalChoice() {
		Campo spese_opzionali=getCampi().get(Costanti.INDICE_SPESE_OPZIONALI);
		return spese_opzionali.isInizializzato();
	}
	
	
	

}
