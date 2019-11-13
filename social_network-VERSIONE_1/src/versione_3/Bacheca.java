package versione_3;

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
	
	/**
	 * Metodo che permette ad un utente di isriversi ad una proposta, ovvero aggiungersi all'elenco dei partecipanti di un evento
	 * @param n: indice della proposta nell'array di Bacheca 
	 * @param u: utente che si vuole iscrivere
	 */
	public void iscrizioneProposta(int n, Utente u) {
		if(proposteAperte.get(n).getStato() == Stato.CHIUSA)
			System.out.println(Menu.PROPOSTA_CHIUSA);
		else {	
			if(proposteAperte.get(n).getPartecipanti().contains(u))
				System.out.println(Menu.ISCRIZIONE_RIDONDANTE);
			else if(proposteAperte.get(n).isFull())
				System.out.print(Menu.ISCRIZIONE_PIENA);
			else {
				proposteAperte.get(n).aggiungiPartecipante(u);
				System.out.println(Menu.ISCRIZIONE_EFFETTUATA);
		}
		}
	}
	
	public void rimuoviProposta(int n) {
		proposteInvalide.add(proposteAperte.get(n));
		proposteAperte.remove(n);
		
	}
	

	/**
	 * Metodo che aggiorna lo stato di tutte le proposte della bacheca in accordo con l'automa e
	 * permette la rimozione dalla bacheca delle proposte concluse o fallite.
	 * Prima della rimozione viene inviata un'opportuna notifica a tutti i partecipanti della proposta rimossa
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public void aggiorna() throws NumberFormatException, ParseException {
		String notifica;
		for(int i = 0; i < proposteAperte.size(); i++) {
			proposteAperte.get(i).aggiornaStato();
		}
		for(int i = 0; i < proposteAperte.size(); i++) {
			HashSet<Utente> partecipanti = proposteAperte.get(i).getPartecipanti();
			if (proposteAperte.get(i).getStato() == Stato.CHIUSA)
			{
				notifica=Menu.NOTIFICA_SUCCESSO+"\t   "+proposteAperte.get(i).header();
				for(Utente u : partecipanti) {
					u.riceviNotifica(notifica);
				}
				this.rimuoviProposta(i);
			}
			else if(proposteAperte.get(i).getStato() == Stato.FALLITA)
			{
				notifica=Menu.NOTIFICA_FALLIMENTO+"\t   "+proposteAperte.get(i).header();
				for(Utente u : partecipanti) {
					u.riceviNotifica(notifica);
				}
				this.rimuoviProposta(i);
			}
			else if(proposteAperte.get(i).getStato() == Stato.RITIRATA) {
				notifica=Menu.NOTIFICA_RITIRO+"\t   "+proposteAperte.get(i).header();
				for(Utente u : partecipanti) {
					u.riceviNotifica(notifica);
				}
				this.rimuoviProposta(i);
			}
				
		}
	}
	
	public String toString() {
		StringBuffer str= new StringBuffer(String.format(Menu.HEADER_BACHECA));
		str.append(Menu.LINEA);
		
		for(int i=0;i<proposteAperte.size();i++) {
			str.append(String.format("%-2s %s", i+1,proposteAperte.get(i).header())).append("\n");
		}
		return str.toString();
		
	}
	
	public ArrayList<Proposta> getProposteCreatore(Utente c){
		ArrayList<Proposta> pCreatore = new ArrayList<Proposta>();
		for(Proposta p : proposteAperte) {
			if (p.getCreatore() == c)
				pCreatore.add(p);
		}
		return pCreatore;
	}
	
	public ArrayList<Proposta> getIscrizioni(Utente c){
		ArrayList<Proposta> pIscrizioni = new ArrayList<Proposta>();
		for(Proposta p : proposteAperte) {
			if(!getProposteCreatore(c).contains(p) && p.getPartecipanti().contains(c))
				pIscrizioni.add(p);
				
		}
		return pIscrizioni;
	}
	
	public void ritiraProposta(Proposta p) throws NumberFormatException, ParseException {
		p.setStato(Stato.RITIRATA);
		this.aggiorna();
		
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
