package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import campo.Campo;
import model.Categoria;
import model.Costanti;
import model.Proposta;
import model.Utente;
import view.Messaggi;
import view.ObjectPrinter;

public abstract class ControllerCompilazione {
	
	
	public void compilazioneStandard(Categoria c) throws ParseException {
		
		
		Date dataInizio=null,dataScadenza=null;
		for(int i = 0; i < Costanti.NUMERO_CAMPI_STANDARD; i++) {
			switch(i) {
			case Costanti.INDICE_DATA_INIZIO:
				c.getCampo(i).compila();
				
				dataInizio=Input.stringToDate(c.valoreDi(i));
				dataScadenza=Input.stringToDate(c.valoreDi(Costanti.INDICE_SCADENZA_ISCRIZIONE));
				
				if(dataInizio.before(dataScadenza)) {
					Messaggi.erroreDataInizio();
					i--;
				}
				break;
				
			case Costanti.INDICE_DATA_FINE:
				if(c.getCampo(Costanti.INDICE_DURATA).isInizializzato()) {
					String str = c.valoreDi(Costanti.INDICE_DURATA);
					dataInizio = Input.stringToDate(c.valoreDi(Costanti.INDICE_DATA_INIZIO));
					String val = Input.sommaAData(str, dataInizio);
					//se durata è inizializzata la data di scadenza viene calcolata in modo automatico
					c.getCampo(i).setValore(val);
				}
				else {//se la durata non è inizializzata la scadenza viene compilata da utente
					c.getCampo(i).compila();
					if(c.getCampo(i).isInizializzato() && Input.stringToDate(c.valoreDi(i)).before(dataInizio)) {
						Messaggi.errroreDataFine();
						i--;
					}
				}
				break;
				
			case Costanti.INDICE_TERMINE_RITIRO:
				c.getCampo(i).compila();
				dataScadenza=Input.stringToDate(c.valoreDi(Costanti.INDICE_SCADENZA_ISCRIZIONE));
				if(c.getCampo(i).isInizializzato()) {//se l'utente ha inserito il valore del campo
					if(Input.stringToDate(c.valoreDi(i)).after(dataScadenza)) {
						Messaggi.stampa(Costanti.ERRORE_TERMINE_RITIRO);
						i--;
					}
				}
				else
					c.impostaCampo(c.valoreDi(Costanti.INDICE_SCADENZA_ISCRIZIONE), i);
				break;
				
				default: c.getCampo(i).compila();
				break;
			}
			if(!c.getCampo(Costanti.INDICE_TOLLERANZA_PARTECIPANTI).isInizializzato()){
				c.impostaCampo("0", Costanti.INDICE_TOLLERANZA_PARTECIPANTI);
			}
		}
			
	}
	
	public abstract void compilazioneSpecifica(Categoria c) throws ParseException;
	public abstract void compilaProposta(Proposta p) throws ParseException;

	/**
	 * Metodo con il quale si termina la compilazione di una proposta, ovvero:
	 * Si aggiunge il creatore ai partecipanti e si aggiunge la proposta a quelle valide di creatore (il quale potrà eventualmente pubblicarla)
	 * 
	 * @param p
	 * @throws ParseException
	 */
	public static void terminaCompilazione(Proposta p) throws ParseException {
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
