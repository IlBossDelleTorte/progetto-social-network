package versione_5;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import campo.Campo;
import view.Costanti;
import view.Messaggi;

public class Proposta implements Serializable {
	private Categoria categoria;
	private Stato stato;
	private HashMap<Notificabile,Float> partecipanti=new HashMap<Notificabile,Float>();
	private Utente creatore;
	private ArrayList<String> logStati;

	
	
	/**
	 * Creatore della classe proposta
	 * In fase di creazione un oggetto viene settato allo stato vuoto e ne viene creata la prima riga del file di log corrispondente
	 * @param creatore: utente creatore della proposta
	 */
	public Proposta(Utente creatore) {
		this.creatore=creatore;
		stato=Stato.VUOTA;
		logStati = new ArrayList<>();
		aggiungiLog();
	}

	
	
	public String valoreCampo(int i) {
		return this.categoria.valoreDi(i);
	}
	
	public void impostaCampo(String s,int i) throws ParseException {
		categoria.impostaCampo(s, i);
	}
	
	public boolean isFull() {
		
		int numero_partecipanti=Integer.parseInt(categoria.valoreDi(Costanti.INDICE_PARTECIPANTI));
		int massimo_partecipanti=Integer.parseInt(categoria.valoreDi(Costanti.INDICE_TOLLERANZA_PARTECIPANTI))+numero_partecipanti;
		if(partecipanti.size() == massimo_partecipanti)
			return true;
			else
				return false;
		
	}
	
	public boolean isRitirabile() {
		if(new Date().before(Input.stringToDate(categoria.valoreDi(Costanti.INDICE_TERMINE_RITIRO))))
			return true;
		else
			return false;
	}
	
	public boolean haSpeseOpzionali() {
		return categoria.haveOptionalChoice();
	}
	
	
	
	public Float spesaPersonale(Notificabile  u) {
		return partecipanti.get(u);
	}
	
	/**
	 * Metodo che permette ad un utente di isriversi ad una proposta, ovvero aggiungersi all'elenco dei partecipanti di un evento
	 * @param n: indice della proposta nell'array di Bacheca 
	 * @param u: utente che si vuole iscrivere
	 */
	public void aggiungiPartecipante(Notificabile u,float f ) {
		if(this.getStato() == Stato.CHIUSA)
			Messaggi.stampa(Costanti.PROPOSTA_CHIUSA);
		else {	
			if(this.getPartecipanti().contains(u))
				Messaggi.stampa(Costanti.ISCRIZIONE_RIDONDANTE);
			else if(this.isFull())
				Messaggi.stampa(Costanti.ISCRIZIONE_PIENA);
			else {
				partecipanti.put(u,new Float(f));
				Messaggi.stampa(Costanti.ISCRIZIONE_EFFETTUATA);
		}
		}
	}
	
	public void rimuoviPartecipante(Utente u) {
		if(this.creatore != u)
			this.partecipanti.remove(u);
	}
	
	/**
	 * Il metodo aggiunge una riga all'ArrayList dei log indicando:
	 * Nome_Creatore Stato Data_attuale Numero_partecipanti_attuali
	 */
	public void aggiungiLog() {
		logStati.add(String.format(Costanti.FORMATO_LOG, creatore.getNome(), stato, Input.dateToString(new Date()), partecipanti.size())+"\n");
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
		for(Notificabile u:this.getPartecipanti()) {
			u.riceviNotifica("Culo"));
		}
	}

	public Utente getCreatore() {
		return creatore;
	}
	
	public Set<Notificabile> getPartecipanti() {
		return partecipanti.keySet();
	}
	
	
	/**
	 * Metodo di debug, stampa a video i logi riguardanti una proposta
	 */
	public void log() {
		StringBuffer str=new StringBuffer();
		logStati.forEach(s->str.append(s));
		System.out.print(str.toString());
		
	}

	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria c) {
		this.categoria=c;
	}

}
