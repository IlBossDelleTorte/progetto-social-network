package versione_2;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class Proposta implements Serializable {
	private Categoria categoria;
	private Stato stato;
	private HashSet<Utente> partecipanti=new HashSet<Utente>();
	private Utente creatore;
	private ArrayList<String> logStati;

	
	
	/**
	 * Creatore della classe proposta
	 * In fase di creazione un oggetto viene settato allo stato vuoto e ne viene creata la prima riga del file di log corrispondente
	 * @param creatore: utente creatore della proposta
	 */
	public Proposta(Utente creatore) {
		categoria=new Categoria(" "," ");
		categoria.partitaDiCalcio();
		this.creatore=creatore;
		stato=Stato.VUOTA;
		logStati = new ArrayList<>();
		aggiungiLog();
	}
	
	/**
	 * Metodo che elabora la routine per assegnare i valori ai campi di una proposta, il metodo effettua anche i controlli della validità
	 * dei valori introdotti.
	 * Le letture da terminale vengono effettuate dalla classe statica Input
	 * @throws ParseException
	 */
	public void compilazione() throws ParseException {
		ArrayList<Campo> c = categoria.getCampi();
		int size = c.size();
		for(int i = 0; i < size; i++) {
			if(i == Menu.INDICE_DATA_INIZIO) {
				c.get(i).compila();
				if(Input.stringToDate(c.get(i).getValore()).before(Input.stringToDate(c.get(Menu.INDICE_SCADENZA_ISCRIZIONE).getValore()))) {
					System.out.println(Menu.ERRORE_DATA_INIZIO);
					i--;
				}
			}
			
			else if(i == Menu.INDICE_DATA_FINE) {
				if(c.get(Menu.INDICE_DURATA).isInizializzato()) {
					String str = c.get(Menu.INDICE_DURATA).getValore();
					Date data_inizio = Input.stringToDate(c.get(Menu.INDICE_DATA_INIZIO).getValore());
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
				
				else {
					c.get(i).compila();
					if(c.get(i).isInizializzato() && Input.stringToDate(c.get(i).getValore()).before(Input.stringToDate(c.get(4).getValore()))) {
						System.out.println(Menu.ERRORE_DATA_FINE);
						i--;
					}
				}
					
			}

			else if(i == Menu.INDICE_TERMINE_RITIRO) {
				c.get(Menu.INDICE_TERMINE_RITIRO).compila();
				if(c.get(i).isInizializzato()) {
					if(Input.stringToDate(c.get(i).getValore()).after(Input.stringToDate(c.get(Menu.INDICE_SCADENZA_ISCRIZIONE).getValore()))) {
						System.out.print(Menu.ERRORE_TERMINE_RITIRO);
						i--;
					}
				}
				else
					c.get(i).setValore(c.get(Menu.INDICE_SCADENZA_ISCRIZIONE).getValore());
	
					
			}
			else
				c.get(i).compila();
		}
		if(!c.get(Menu.INDICE_TOLLERANZA_PARTECIPANTI).isInizializzato()){
			c.get(Menu.INDICE_TOLLERANZA_PARTECIPANTI).setValore("0");
		}
		aggiungiPartecipante(creatore);
		System.out.print(categoria);
		this.aggiornaStato();
		creatore.aggiungiPropostaValida(this);
		System.out.println(Menu.COMPILAZIONE_EFFETTUATA);
	}
	
	public void aggiungiPartecipante(Utente u) {
		partecipanti.add(u);
	}
	
	/**
	 * Metodo con il quale viene cambiato lo stato di una proposta in accordo con le condizioni di transizione specifiche 
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	public void aggiornaStato() throws NumberFormatException, ParseException {
		
		Date scadenza_iscrizione=Input.stringToDate(categoria.getCampi().get(Menu.INDICE_SCADENZA_ISCRIZIONE).getValore());
		Date termine_ritiro=Input.stringToDate(categoria.getCampi().get(Menu.INDICE_TERMINE_RITIRO).getValore());
		int numero_partecipanti=Integer.parseInt(categoria.getCampi().get(Menu.INDICE_PARTECIPANTI).getValore());
		
		switch (this.stato) {
		case VUOTA : 
			stato = Stato.VALIDA;
			aggiungiLog();
			break;
		case VALIDA :
			//nel caso in cui l'utente avesse una proposta valida e le condizioni non fossero rispettate
			//prima che questa venga pubblicata, questa passa direttamente a fallita
			if(partecipanti.size() < numero_partecipanti
					&& scadenza_iscrizione.before(new Date())) {
				stato = Stato.FALLITA;
				aggiungiLog();
			}
			else {
			stato = Stato.APERTA;
			aggiungiLog();
			}
			break;
		case APERTA :
			int massimo_partecipanti=Integer.parseInt(categoria.getCampi().get(Menu.INDICE_TOLLERANZA_PARTECIPANTI).getValore())+numero_partecipanti;
			
			if((scadenza_iscrizione.after(new Date()) && termine_ritiro.before(new Date()) && partecipanti.size() == massimo_partecipanti)
				||(new Date().after(scadenza_iscrizione) && partecipanti.size()>=numero_partecipanti && partecipanti.size()<=massimo_partecipanti)){
				stato = Stato.CHIUSA;
				aggiungiLog();
			}
			else if(partecipanti.size() < numero_partecipanti
					&& scadenza_iscrizione.before(new Date())) {
				stato = Stato.FALLITA;
				aggiungiLog();
			}
			break;
		case CHIUSA :
			Calendar c = Calendar.getInstance();
			c.setTime(Input.stringToDate(categoria.getCampi().get(Menu.INDICE_DATA_INIZIO).getValore()));
			c.add(Calendar.DAY_OF_MONTH, 1);
			Date d = c.getTime();
			if(new Date().after(d)) {
				stato = Stato.CONCLUSA;
				aggiungiLog();
			}
			break;
		default :
			break;
		
				
			
		}
	}
	public boolean isRitirabile() {
		if(new Date().before(Input.stringToDate(categoria.getCampi().get(Menu.INDICE_TERMINE_RITIRO).getValore())))
			return true;
		else
			return false;
	}
	
	public void annullaIscrizione(Utente u) {
		if(this.creatore != u)
			this.partecipanti.remove(u);
	}
	
	/**
	 * Il metodo aggiunge una riga all'ArrayList dei log indicando:
	 * Nome_Creatore Stato Data_attuale Numero_partecipanti_attuali
	 */
	public void aggiungiLog() {
		logStati.add(String.format(Menu.FORMATO_LOG, creatore.getNome(), stato, Input.dateToString(new Date()), partecipanti.size())+"\n");
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

	public HashSet<Utente> getPartecipanti() {
		return partecipanti;
	}
	

	
	/**
	 * Il metodo ritorna una stringa che riassume le informazioni principali di una proposta
	 * @return
	 */
	public String header() {
		String str=String.format("%-20s ", creatore.getNome());
		str=str+categoria.header();
		return str;
		
	}
	
	public String toString() {
		StringBuffer str= new StringBuffer();
		str.append(creatore.getNome()).append(Menu.LINEA).append(categoria);
		return str.toString();
	}
	
	/**
	 * Metodo di debug, stampa a video i logi riguardanti una proposta
	 */
	public void log() {
		StringBuffer str=new StringBuffer();
		logStati.forEach(s->str.append(s));
		System.out.print(str.toString());
		
	}

}
