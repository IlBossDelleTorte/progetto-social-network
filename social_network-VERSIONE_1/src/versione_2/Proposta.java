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
	
	public Proposta(Utente creatore) {
		categoria=new Categoria(" "," ");
		categoria.partitaDiCalcio();
		this.creatore=creatore;
		stato=Stato.VUOTA;
		logStati = new ArrayList<>();
		logStati.add(String.format(FORMATO_LOG, creatore, stato, Input.dateToString(new Date()), partecipanti.size()));
	}
	
	public void compilazione() throws ParseException {
		ArrayList<Campo> c = categoria.getCampi();
		int size = c.size();
		SimpleDateFormat parser_data = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		for(int i = 0; i < size; i++) {
			if(i == 4) {
				c.get(i).compila();
				if(parser_data.parse(c.get(i).getValore()).before(parser_data.parse(c.get(2).getValore()))) {
					System.out.println(Menu.ERRORE_DATA_INIZIO);
					i--;
				}
			}
			
			else if(i == 8) {
				if(c.get(5).isInizializzato()) {
					String str = c.get(5).getValore();
					Date data_inizio = parser_data.parse(c.get(4).getValore());
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
					if(c.get(i).isInizializzato() && parser_data.parse(c.get(i).getValore()).before(parser_data.parse(c.get(4).getValore()))) {
						System.out.println(Menu.ERRORE_DATA_FINE);
						i--;
					}
				}
					
			}

			
			else 
				c.get(i).compila();
		}
		aggiungiPartecipante(creatore);
		System.out.print(categoria);
		this.aggiornaStato();
		creatore.aggiungiPropostaValida(this);
		System.out.println("La proposta è stata aggiunta con successo alla tua lista di proposte valide!");
	}
	
	public void aggiungiPartecipante(Utente u) {
		partecipanti.add(u);
	}
	
	public void aggiornaStato() throws NumberFormatException, ParseException {
		switch (this.stato) {
		case VUOTA : 
			stato = Stato.VALIDA;
			aggiungiLog();
			break;
		case VALIDA :
			//nel caso in cui l'utente avesse una proposta valida e le condizioni non fossero rispettate
			//prima che questa venga pubblicata, questa passa direttamente a fallita
			if(partecipanti.size() < Integer.parseInt(categoria.getCampi().get(1).getValore())
					&& Input.stringToDate(categoria.getCampi().get(2).getValore()).before(new Date())) {
				stato = Stato.FALLITA;
				aggiungiLog();
			}
			else {
			stato = Stato.APERTA;
			aggiungiLog();
			}
			break;
		case APERTA :
			if(Integer.parseInt(categoria.getCampi().get(1).getValore()) == partecipanti.size()
			&& Input.stringToDate(categoria.getCampi().get(2).getValore()).before(new Date())) {
				stato = Stato.CHIUSA;
				aggiungiLog();
			}
			else if(partecipanti.size() < Integer.parseInt(categoria.getCampi().get(1).getValore())
					&& Input.stringToDate(categoria.getCampi().get(2).getValore()).before(new Date())) {
				stato = Stato.FALLITA;
				aggiungiLog();
			}
			break;
		case CHIUSA :
			Calendar c = Calendar.getInstance();
			c.setTime(Input.stringToDate(categoria.getCampi().get(4).getValore()));
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
	
	public void aggiungiLog() {
		logStati.add(String.format(FORMATO_LOG, creatore, stato, Input.dateToString(new Date()), partecipanti.size()));
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
	
	public String header() {
		String str=String.format("%-20s ", creatore.getNome());
		str=str+categoria.header();
		return str;
		
	}
	
	public String toString() {
		StringBuffer str= new StringBuffer();
		str.append(creatore).append(Menu.LINEA).append(categoria);
		return str.toString();
	}

}
