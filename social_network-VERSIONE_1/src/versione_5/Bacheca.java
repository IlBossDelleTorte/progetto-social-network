package versione_5;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import view.Costanti;

public class Bacheca implements Serializable {

	private ArrayList<Proposta> proposteAperte;
	private ArrayList<Proposta> proposteInvalide;
	
	
	public Bacheca() {
		this.proposteAperte = new ArrayList<Proposta>();
		this.proposteInvalide=new ArrayList<Proposta>();
	}
	
	/**
	 * Metodo che permette di aggiungere una proposta alla bacheca e mandare una notifica a tutti gli utenti che hanno tra 
	 * i propri interessi la particolare categoria della proposta (e aggiunge tale proposta alla lista di proposte affini dell'utente)
	 * 
	 * @param p la proposta da aggiungere
	 * @param l la lista degli utenti salvati nel programma
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public void aggiungiPropostaAperta (Proposta p, ListaUtenti l) throws NumberFormatException, ParseException {
		proposteAperte.add(p);
		for(Utente u : l.getUtenti()) {
			if(u.getCategorieInteresse().contains(p.getCategoria().getNome())) {
				u.riceviNotifica(Costanti.NOTIFICA_PROPOSTA_AFFINE);
				u.aggiungiPropostaAffine(p);
			}
		}
		p.aggiornaStato();
	}
	

	
	public void rimuoviPropostaAperta(int n) {
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
			proposteAperte.forEach(p->ControllerStato.aggiornaProposta(p));
		}
		for(int i = 0; i < proposteAperte.size(); i++) {
			Set<Notificabile> partecipanti = proposteAperte.get(i).getPartecipanti();
			if (proposteAperte.get(i).getStato() == Stato.CHIUSA)
			{
				notifica=Costanti.NOTIFICA_SUCCESSO+"\t   "+proposteAperte.get(i).header();
				for(Notificabile u : partecipanti) {
					String messaggio_spese=String.format(Costanti.NOTIFICA_SPESA_OPZIONALE, proposteAperte.get(i).spesaPersonale(u));
					u.riceviNotifica(notifica+messaggio_spese);
				}
				this.rimuoviPropostaAperta(i);
			}
			else if(proposteAperte.get(i).getStato() == Stato.FALLITA)
			{
				notifica=Costanti.NOTIFICA_FALLIMENTO+"\t   "+proposteAperte.get(i).header();
				for(Notificabile u : partecipanti) {
					u.riceviNotifica(notifica);
				}
				this.rimuoviPropostaAperta(i);
			}
			else if(proposteAperte.get(i).getStato() == Stato.RITIRATA) {
				notifica=Costanti.NOTIFICA_RITIRO+"\t   "+proposteAperte.get(i).header();
				for(Notificabile u : partecipanti) {
					u.riceviNotifica(notifica);
				}
				this.rimuoviPropostaAperta(i);
			}
				
		}
	}
	
	/**
	 * Il metodo genera l'elenco di tutti gli utenti correlati con l'utente c relativi alla proposta p
	 * @param c creatore della proposta
	 * @param p proposta da cui si attinge la categoria 
	 * @return un'arrayList di utenti correlati 
	 */
	public ArrayList<Utente> utentiCorrelati(Utente c, Proposta p) {
		ArrayList<Utente> utentiCorrelati = new ArrayList<>();
		for(Proposta x : proposteInvalide) {
			//prende le proposte che sono state create in passato dallo stesso utente
			if(x.getCreatore().getNome() == c.getNome()) {
				//prende le proposte concluse e chiuse che condividono la stessa categoria della proposta da aprire
				if(p.getCategoria().getNome().equals(x.getCategoria().getNome())) {
					if(x.getStato() == Stato.CHIUSA || x.getStato() == Stato.CONCLUSA) {
						for(Utente u : x.getPartecipanti()) {
							//Se il partecipante scelto dalla proposta non è contenuto nella lista degli utenti correlati, allora viene aggiunto
							if(!utentiCorrelati.contains(u)&& !u.equals(c))
								utentiCorrelati.add(u);	
						}
					}
				}
			}
		}
		return utentiCorrelati;
	}
	
	/**
	 * Metodo per inviare inviti ad una lista di utenti aggiungendo la proposta 
	 * invitata nell'array degli inviti di questi
	 * @param utentiInvitati: elenco degli utenti che ricevono l'invito
	 * @param p: proposta oggetto dell'invito
	 */
	public void invitaUtenti(ArrayList<Utente> utentiInvitati, Proposta p) {
		for(Utente u : utentiInvitati) {
			u.riceviNotifica(String.format(Costanti.NOTIFICA_INVITO, p.getCreatore().getNome(),p.getCategoria().getNome()));
			u.aggiungiInvito(p);
		}
		
	}
	
	
	
	/**
	 * Metodo che ritorna l'elenco delle proposte di cui c è creatore
	 * @param c : utente di cui si vogliono trovare le proposte create 
	 * @return ArrayList di proposte create
	 */
	public ArrayList<Proposta> getProposteCreatore(Utente c){
		ArrayList<Proposta> pCreatore = new ArrayList<Proposta>();
		for(Proposta p : proposteAperte) {
			if (p.getCreatore() == c)
				pCreatore.add(p);
		}
		return pCreatore;
	}
	
	/**
	 * Metodo che ritorna le proposte a cui è iscritto l'utente c e di cui non è creatore 
	 * @param c : utente di cui si vogliono trovare le iscrizioni
	 * @return ArrayList di proposte a cui l'utente è iscritto
	 */
	public ArrayList<Proposta> getIscrizioni(Utente c){
		ArrayList<Proposta> pIscrizioni = new ArrayList<Proposta>();
		for(Proposta p : proposteAperte) {
			if(!getProposteCreatore(c).contains(p) && p.getPartecipanti().contains(c))
				pIscrizioni.add(p);
				
		}
		return pIscrizioni;
	}
	
	/**
	 * Metodo che imposta una proposta ritirata effettuando anche la rimozione di questa dalla bacheca
	 * @param p : proposta da ritirare
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
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
