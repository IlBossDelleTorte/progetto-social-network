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

	/**
	 * Metodo che elabora la routine per assegnare i valori ai campi di una proposta, il metodo effettua anche i controlli della validità
	 * dei valori introdotti.
	 * Le letture da terminale vengono effettuate dalla classe statica Input
	 * @throws ParseException
	 */
	public void compilazione() throws ParseException {
		ArrayList<Campo> c = categoria.getCampi();
		ArrayList<String> elencoCategorie=new ArrayList<>(Arrays.asList(Costanti.ELENCO_CATEGORIE));
		
		for(int i=0;i<elencoCategorie.size();i++) { //stampa dell'elenco di tutte le categorie disponibili
			System.out.println(i+1+")"+elencoCategorie.get(i));
		}
		System.out.print(Costanti.SELEZIONE_CATEGORIA);//scelta della categoria della proposta 
		int n=Input.leggiIntTra(true,1,elencoCategorie.size());
		switch(n) {
		case 1:
			categoria=new PartitaDiCalcio();
			break;
		case 2:
			categoria=new Escursione();
			break;
		}
		
		int size = c.size();
		for(int i = 0; i < size; i++) {
			if(i == Costanti.INDICE_DATA_INIZIO) {
				c.get(i).compila();
				
				Date data_inizio=Input.stringToDate(c.get(i).getValore());
				Date data_scadenza=Input.stringToDate(c.get(Costanti.INDICE_SCADENZA_ISCRIZIONE).getValore());
				
				if(data_inizio.before(data_scadenza)) {
					System.out.println(Costanti.ERRORE_DATA_INIZIO);
					i--;
				}
			}
			else if(i == Costanti.INDICE_DATA_FINE) {
				if(c.get(Costanti.INDICE_DURATA).isInizializzato()) {//se durata è inizializzata la data di scadenza viene calcolata in modo automatico
					String str = c.get(Costanti.INDICE_DURATA).getValore();
					Date data_inizio = Input.stringToDate(c.get(Costanti.INDICE_DATA_INIZIO).getValore());
					Scanner s = new Scanner(str);
					s.useDelimiter(",");
					int ore = s.nextInt()*60;
					int min = s.nextInt();
					Calendar dataTerm = Calendar.getInstance();
					dataTerm.setTime(data_inizio);
					dataTerm.add(Calendar.MINUTE, ore+min);
					String val=Input.dateToString(dataTerm.getTime());
					c.get(i).setValore(val);
				}
				else {//se la durata non è inizializzata la scadenza viene compilata da utente
					c.get(i).compila();
					if(c.get(i).isInizializzato() && Input.stringToDate(c.get(i).getValore()).before(Input.stringToDate(c.get(4).getValore()))) {
						System.out.println(Costanti.ERRORE_DATA_FINE);
						i--;
					}
				}
					
			}

			else if(i == Costanti.INDICE_TERMINE_RITIRO) {
				c.get(i).compila();
				if(c.get(i).isInizializzato()) {
					if(Input.stringToDate(c.get(i).getValore()).after(Input.stringToDate(c.get(Costanti.INDICE_SCADENZA_ISCRIZIONE).getValore()))) {
						System.out.print(Costanti.ERRORE_TERMINE_RITIRO);
						i--;
					}
				}
				else
					c.get(i).setValore(c.get(Costanti.INDICE_SCADENZA_ISCRIZIONE).getValore());
				}
			else if(n==1 && i==Costanti.INDICE_GENERE) {//se la categoria è partita di calcio e si sta compilando il genere
				c.get(i).compila();
				String genere=c.get(i).getValore();
				if(!(genere.equalsIgnoreCase("MASCHIO")||genere.equalsIgnoreCase("FEMMINA")||genere.equalsIgnoreCase("MISTO"))){
					System.out.print(Costanti.ERRORE_GENERE);
					i--;
				}
			}
			
			else if (n==1 && i==Costanti.INDICE_RANGE) {//se la categoria è partita di calcio e si sta comilando il range d'eta
				System.out.print(c.get(i).toString()+Costanti.LINEA);
				String str;
				if(c.get(i).isObbligatorio())str=Costanti.COMPILAZIONE_STRINGA_RANGE;
				else str=Costanti.COMPILAZIONE_STRINGA_RANGE+Costanti.FACOLTATIVO_STRINGA;
				String v=Input.leggiStringaFormattata(str, Costanti.FORMATO_RANGE, c.get(i).isObbligatorio());
				c.get(i).setValore(v);
			}
			else
				c.get(i).compila();
		}
		if(!c.get(Costanti.INDICE_TOLLERANZA_PARTECIPANTI).isInizializzato()){
			c.get(Costanti.INDICE_TOLLERANZA_PARTECIPANTI).setValore("0");
		}
		aggiungiPartecipante(creatore,Float.parseFloat(c.get(Costanti.INDICE_QUOTA_BASE).getValore().replace(',', '.')));
		System.out.print(categoria);
		this.aggiornaStato();
		creatore.aggiungiPropostaValida(this);
		System.out.println(Costanti.COMPILAZIONE_EFFETTUATA);
	}
}
