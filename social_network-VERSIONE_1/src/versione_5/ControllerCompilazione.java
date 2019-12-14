package versione_5;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import campo.Campo;
import view.Costanti;
import view.Messaggi;
import view.ObjectPrinter;

public class ControllerCompilazione {
	
	/**
	 * Metodo che permette all'utente di scegliere la categoria della proposta da compilare 
	 * @param p: proposta da compilare
	 */
	private static void sceltaCategoria(Proposta p) {
		ArrayList<String> elencoCategorie=new ArrayList<>(Arrays.asList(Costanti.ELENCO_CATEGORIE));
		
		ObjectPrinter.stampaLista(elencoCategorie);
		Messaggi.stampa(Costanti.SELEZIONE_CATEGORIA);//scelta della categoria della proposta 
		int n=Input.leggiIntTra(true,1,elencoCategorie.size());
		switch(n) {
		case 1:
			p.setCategoria(new PartitaDiCalcio());
			break;
		case 2:
			p.setCategoria(new Escursione());
			break;
		}
		
	}
	
	/**
	 * Metodo che permette di attribuire il valore a tutti i campi di una proposta per mezzo del loro metodo compila
	 * controllando anche la validità dei loro valori 
	 * @param p: proposta da compilare 
	 * @throws ParseException
	 */
	private static void compilaCampi(Proposta p) throws ParseException {
		ArrayList<Campo> c = p.getCategoria().getCampi();
		int size = c.size();
		Date dataInizio=null,dataScadenza=null;
		for(int i = 0; i < size; i++) {
			switch(i) {
			case Costanti.INDICE_DATA_INIZIO:
				c.get(i).compila();
				
				dataInizio=Input.stringToDate(p.valoreCampo(i));
				dataScadenza=Input.stringToDate(p.valoreCampo(Costanti.INDICE_SCADENZA_ISCRIZIONE));
				
				if(dataInizio.before(dataScadenza)) {
					Messaggi.erroreDataInizio();
					i--;
				}
				break;
				
			case Costanti.INDICE_DATA_FINE:
				if(c.get(Costanti.INDICE_DURATA).isInizializzato()) {
					String str = p.valoreCampo(Costanti.INDICE_DURATA);
					dataInizio = Input.stringToDate(p.valoreCampo(Costanti.INDICE_DATA_INIZIO));
					String val = Input.sommaAData(str, dataInizio);
					//se durata è inizializzata la data di scadenza viene calcolata in modo automatico
					c.get(i).setValore(val);
				}
				else {//se la durata non è inizializzata la scadenza viene compilata da utente
					c.get(i).compila();
					if(c.get(i).isInizializzato() && Input.stringToDate(p.valoreCampo(i)).before(dataInizio)) {
						Messaggi.errroreDataFine();
						i--;
					}
				}
				break;
				
			case Costanti.INDICE_TERMINE_RITIRO:
				c.get(i).compila();
				dataScadenza=Input.stringToDate(p.valoreCampo(Costanti.INDICE_SCADENZA_ISCRIZIONE));
				if(c.get(i).isInizializzato()) {//se l'utente ha inserito il valore del campo
					if(Input.stringToDate(c.get(i).getValore()).after(dataScadenza)) {
						System.out.print(Costanti.ERRORE_TERMINE_RITIRO);
						i--;
					}
				}
				else
					p.impostaCampo(p.valoreCampo(Costanti.INDICE_SCADENZA_ISCRIZIONE), i);
				break;
				
			case Costanti.INDICE_GENERE_E_SPESE_OPZIONALI:
				if(!p.haSpeseOpzionali()) {//se non ha spese opzionali è una partita di calcio 
					c.get(i).compila();
					String genere=p.valoreCampo(i);
					if(!(genere.equalsIgnoreCase("MASCHIO")||genere.equalsIgnoreCase("FEMMINA")||genere.equalsIgnoreCase("MISTO"))){
						Messaggi.erroreGenere();
						i--;
					}
				}
				else c.get(i).compila();//se è un'escursione si compila normalmente 
				break;
				
			case Costanti.INDICE_RANGE:
				ObjectPrinter.stampaCampo(c.get(i));
				Messaggi.stampaLinea();
				String str;
				if(c.get(i).isObbligatorio())str=Costanti.COMPILAZIONE_STRINGA_RANGE;
				else str=Costanti.COMPILAZIONE_STRINGA_RANGE+Costanti.FACOLTATIVO_STRINGA;
				String v=Input.leggiStringaFormattata(str, Costanti.FORMATO_RANGE, c.get(i).isObbligatorio());
				p.impostaCampo(v, i);
				break;
				
				default: c.get(i).compila();
				break;
			}
			if(!c.get(Costanti.INDICE_TOLLERANZA_PARTECIPANTI).isInizializzato()){
				p.impostaCampo("0", Costanti.INDICE_TOLLERANZA_PARTECIPANTI);
			}
			
		}
		
		

	}
	
	/**
	 * Metodo che opera tutta la routine di compilazione di una proposta 
	 * @param p: proposta da compilare 
	 * @throws ParseException
	 */
	public static void compilaProposta(Proposta p) throws ParseException {
			sceltaCategoria(p);
			compilaCampi(p);
			float spesa=Float.parseFloat(p.valoreCampo(Costanti.INDICE_QUOTA_BASE).replace(',', '.'));
			//Postcondizione: il creatore paga solo la quota base
			Utente creatore=p.getCreatore();
			p.aggiungiPartecipante(creatore, spesa);
			ObjectPrinter.stampaProposta(p);
			ControllerStato.aggiornaProposta(p);
			creatore.aggiungiPropostaValida(p);
			Messaggi.compilazioneEffettuata();
			
		}
}
