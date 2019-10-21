package versione_2;

import java.util.ArrayList;

public class Proposta {
	private Categoria categoria;
	private Stato stato;
	private ArrayList<Utente> partecipanti=new ArrayList<Utente>();
	private Utente creatore;
	
	public Proposta(Utente creatore) {
		categoria=new Categoria(" "," ");
		categoria.partitaDiCalcio();
		this.creatore=creatore;
		stato=Stato.VUOTA;
	}
	
	public void compilazione() {
		
		categoria.getCampi().forEach(c->c.compila());
		System.out.print(categoria);
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Utente getCreatore() {
		return creatore;
	}

	public void setCreatore(Utente creatore) {
		this.creatore = creatore;
	}

}
