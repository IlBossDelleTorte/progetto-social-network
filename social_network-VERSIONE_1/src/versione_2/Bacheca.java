package versione_2;

import java.util.ArrayList;

public class Bacheca {

	private ArrayList<Proposta> proposte;
	
	public Bacheca() {
		this.proposte = new ArrayList<Proposta>();
	}
	
	/**Metodo che permette di aggiungere una proposta alla bacheca
	 * 
	 * @param p la proposta da aggiungere
	 */
	public void aggiungiProposta (Proposta p) {
		proposte.add(p);
		p.setStato(Stato.APERTA);
	}
	
	//da aggiungere controllo if per vedere se l'utente è creatore della proposta
	public void iscrizioneProposta(int n, Utente u) {
		if(proposte.get(n).getStato() == Stato.CHIUSA)
			System.out.println("La proposta è già chiusa e non vi si può iscrivere.");
		else {
			if(proposte.get(n).getPartecipanti().contains(u))
				System.out.println("Sei già iscritto a questa proposta");
			else {
				proposte.get(n).aggiungiPartecipante(u);
				System.out.println("Iscrizione effettuata!");
		}
		}
	}
	
	public void rimuoviProposta(int n) {
		proposte.remove(n);
	}
	/**
	 * Metodo che permette la rimozione dalla bacheca delle proposte concluse o fallite.
	 */
	public void pulizia() {
		for(int i = 0; i < proposte.size(); i++) {
			if (proposte.get(i).getStato() == Stato.CONCLUSA || proposte .get(i).getStato() == Stato.FALLITA)
					proposte.remove(i);		}
	}
	
	public String toString() {
		
	}
}
