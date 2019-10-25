package versione_2;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

public class Bacheca implements Serializable {

	private ArrayList<Proposta> proposteAperte;
	private ArrayList<Proposta> proposteInvalide;
	
	public Bacheca() {
		this.proposteAperte = new ArrayList<Proposta>();
		this.proposteInvalide=new ArrayList<Proposta>();
	}
	
	/**Metodo che permette di aggiungere una proposta alla bacheca
	 * 
	 * @param p la proposta da aggiungere
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public void aggiungiPropostaAperta (Proposta p) throws NumberFormatException, ParseException {
		proposteAperte.add(p);
		p.aggiornaStato();
	}
	
	//da aggiungere controllo if per vedere se l'utente è creatore della proposta
	public void iscrizioneProposta(int n, Utente u) {
		if(proposteAperte.get(n).getStato() == Stato.CHIUSA)
			System.out.println("La proposta è già chiusa e non vi si può iscrivere.");
		else {
			if(proposteAperte.get(n).getPartecipanti().contains(u))
				System.out.println("Sei già iscritto a questa proposta");
			else {
				proposteAperte.get(n).aggiungiPartecipante(u);
				System.out.println("Iscrizione effettuata!");
		}
		}
	}
	
	public void rimuoviProposta(int n) {
		proposteInvalide.add(proposteAperte.get(n));
		proposteAperte.remove(n);
		
	}
	
	/**
	 * Metodo che aggiorna lo stato di tutte le proposte della bacheca in accordo con l'automa 
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	
	public void aggiorna() throws NumberFormatException, ParseException {
		for(int i = 0; i < proposteAperte.size(); i++) {
			proposteAperte.get(i).aggiornaStato();
		}
	}
	/**
	 * Metodo che permette la rimozione dalla bacheca delle proposte concluse o fallite.
	 * Prima della rimozione viene inviata un'opportuna notifica a tutti i partecipanti della proposta rimossa
	 */
	public void pulisci() {
		String notificas;
		String notificaf;
		for(int i = 0; i < proposteAperte.size(); i++) {
			HashSet<Utente> partecipanti = proposteAperte.get(i).getPartecipanti();
			if (proposteAperte.get(i).getStato() == Stato.CHIUSA)
			{
				notificas=Menu.NOTIFICA_SUCCESSO+"\t  "+proposteAperte.get(i).header();
				for(Utente u : partecipanti) {
					u.riceviNotifica(notificas);
				}
				this.rimuoviProposta(i);
			}
			else if(proposteAperte .get(i).getStato() == Stato.FALLITA)
			{
				notificaf=Menu.NOTIFICA_FALLIMENTO+"\t  "+proposteAperte.get(i).header();
				for(Utente u : partecipanti) {
					u.riceviNotifica(notificaf);
				}
				this.rimuoviProposta(i);
			}
		}
	}
	
	public String toString() {
		StringBuffer str= new StringBuffer(String.format(Menu.HEADER_BACHECA));
		str.append(Menu.LINEA);
		
		for(int i=0;i<proposteAperte.size();i++) {
			str.append(String.format("%2s %s", i+1,proposteAperte.get(i).header())).append("\n");
		}
		return str.toString();
		
	}

	public ArrayList<Proposta> getProposteAperte() {
		return proposteAperte;
	}

	public void setProposteAperte(ArrayList<Proposta> proposteAperte) {
		this.proposteAperte = proposteAperte;
	}

	public ArrayList<Proposta> getProposteInvalide() {
		return proposteInvalide;
	}

	public void setProposteInvalide(ArrayList<Proposta> proposteInvalide) {
		this.proposteInvalide = proposteInvalide;
	}
}
