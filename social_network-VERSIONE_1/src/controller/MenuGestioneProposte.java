package controller;

import java.text.ParseException;

import model.Bacheca;
import model.ContainerDati;
import model.ListaUtenti;
import model.Proposta;
import model.Utente;
import view.Costanti;
import view.Messaggi;
import view.ObjectPrinter;

public class MenuGestioneProposte {

	public static void routineCompilazione(Utente utente) throws ParseException {
		FactoryControllerCompilazione fabbrica= new FactoryControllerCompilazione();
		Proposta prop=new Proposta(utente);
		ControllerCompilazione comp=fabbrica.sceltaCategoria(prop);
		comp.compilaProposta(prop);
	}

	public static void routinePubblicazione(Bacheca bacheca, ListaUtenti listaUtenti, Utente utente)
			throws ParseException {
		int selezione=0;
		do {
			ObjectPrinter.stampaListaProposte(utente.getProposteValide());
			if(utente.getProposteValide().size()== 0) break;
			selezione=Input.leggiInt(Costanti.MESSAGGIO_SELEZIONE_PROPOSTA, true);
			if(selezione<=utente.getProposteValide().size() && selezione>0)
				{
					ObjectPrinter.stampaProposta(utente.getProposteValide().get(selezione-1));
					int n=Input.leggiInt(Costanti.GESTIONE_PROPOSTA_VALIDA,true);
					if (n==1)
					{//pubblica una proposta valida (diviene aperta)
						Proposta selezionata=utente.getProposteValide().get(selezione-1);
						bacheca.aggiungiPropostaAperta(selezionata,listaUtenti);
						utente.rimuoviPropostaValida(selezionata);
						System.out.print(Costanti.PUBBLICAZIONE_EFFETTUATA);
							
						//ROUTINE DI INVIO DEGLI INVITI ALLA PROPOSTA CORRENTE
						ControllerInviti.invita(bacheca,selezionata);
							
					}
					if (n==2)//rimuove una proposta dall'elenco delle proposte valide
						utente.rimuoviPropostaValida(utente.getProposteValide().get(selezione-1));
	
				}
			}while(selezione!=0 && utente.getProposteValide().size()!= 0);
	}

	public static void routineDisiscrizione(Bacheca bacheca, Utente utente) {
		int selezione=0;
		do {
			ObjectPrinter.stampaListaProposte(bacheca.getIscrizioni(utente));
			if(bacheca.getIscrizioni(utente).size() == 0) break;
			selezione=Input.leggiInt(Costanti.MESSAGGIO_SELEZIONE_PROPOSTA, true);
			if(selezione <= bacheca.getIscrizioni(utente).size() && selezione>0){
				Proposta propostaSelezionata = bacheca.getIscrizioni(utente).get(selezione-1);
				ObjectPrinter.stampaProposta(propostaSelezionata);
				int n=Input.leggiInt(Costanti.GESTIONE_RITIRO_ISCRIZIONE, true);
	
				if (n==1 && propostaSelezionata.isRitirabile()) {
					propostaSelezionata.rimuoviPartecipante(utente);
					Messaggi.messaggioDisiscrizione();
				}
	
				if (n==1 && !propostaSelezionata.isRitirabile()){
					Messaggi.messaggioNonRitirabile();
				}
					
			}
					
		}while(selezione != 0 && bacheca.getIscrizioni(utente).size() != 0);
	}

	public static void routineRitiro(Bacheca bacheca, Utente utente) throws ParseException {
		int selezione=0;
		do {
			ObjectPrinter.stampaListaProposte(bacheca.getProposteCreatore(utente));
			if(bacheca.getProposteCreatore(utente).size() == 0) break;
			selezione=Input.leggiInt(Costanti.MESSAGGIO_SELEZIONE_PROPOSTA,true);
			if(selezione <= bacheca.getProposteCreatore(utente).size() && selezione>0) {
				Proposta propostaSelezionata = bacheca.getProposteCreatore(utente).get(selezione-1);
				ObjectPrinter.stampaProposta(propostaSelezionata);
				int n=Input.leggiInt(Costanti.GESTIONE_PROPOSTA_RITIRO, true);
	
				if (n==1 && propostaSelezionata.isRitirabile()) {
					bacheca.ritiraProposta(propostaSelezionata);
					Messaggi.messaggioSuccessoRitiro();
						
				}
				if (n==1 && !propostaSelezionata.isRitirabile()){
					Messaggi.messaggioNonRitirabile();
				}
					
			}
		}while(selezione!=0 && bacheca.getProposteCreatore(utente).size() != 0);
	}

	public static void gestioneProposte(ContainerDati dati, Bacheca bacheca, ListaUtenti listaUtenti, Utente utente)
			throws ParseException {
		final int NUOVA_PROPOSTA=1,PUBBLICAZIONE=2,DISISCRIZIONE=3,RITIRO=4;
		int selezione=0;
		do {
			selezione=Input.leggiInt(Costanti.MENU_PROPOSTE, true);
			switch(selezione) {
			case NUOVA_PROPOSTA://compilazione di una nuova proposta
				routineCompilazione(utente);
				IOFile.salvaDati(Costanti.DATI, dati);//salvataggio dei dati
				break;
				
			case PUBBLICAZIONE: //visualizzazione delle proposte valide e pubblicazione
				routinePubblicazione(bacheca, listaUtenti, utente);
				IOFile.salvaDati(Costanti.DATI, dati);//salvataggio dei dati
				break;
				
			case DISISCRIZIONE: //visualizzazione dell'elenco iscrizioni e ritiro dell'iscrizione 
				routineDisiscrizione(bacheca, utente);
				IOFile.salvaDati(Costanti.DATI, dati);
				break;
				
			case RITIRO://visualizzazione dell'elenco delle proposte create e ritiro di queste 
				routineRitiro(bacheca, utente);
				IOFile.salvaDati(Costanti.DATI, dati);
				break;
			}
			}while(selezione!=0);
	}

}
