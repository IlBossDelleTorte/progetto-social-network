package model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import controller.ControllerStato;

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
				//INVIO DI UNA NOTIFICA
				u.aggiungiPropostaAffine(p);
			}
		}
		ControllerStato.aggiornaProposta(p);
	}
	

	
	public void rimuoviPropostaAperta(int n) {
		proposteInvalide.add(proposteAperte.get(n));
		proposteAperte.remove(n);
		
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
		ControllerStato.aggiornaBacheca(this);
		
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
