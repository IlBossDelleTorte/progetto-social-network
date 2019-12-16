package controller;

import java.util.ArrayList;
import java.util.Arrays;

import model.Bacheca;
import model.ContainerDati;
import model.RangeEta;
import model.Utente;
import view.Costanti;
import view.Messaggi;
import view.ObjectPrinter;

public class MenuSpazioPersonale {

	public static void spazioPersonale(ContainerDati dati, Bacheca bacheca, Utente utente) {
		final int NOTIFICHE=1,DATI_PERSONALI=2,AFFINI=3,INVITI=4;
		int selezione=0;
		do {
			selezione=Input.leggiInt(Costanti.MENU_SPAZIO_PERSONALE, true);
			switch(selezione) {
			
			case NOTIFICHE://Accesso alle notifiche
				MenuSpazioPersonale.routineNotifiche(utente);					
				IOFile.salvaDati(Costanti.DATI, dati);//salvataggio dei dati
				break;
				
				
			case DATI_PERSONALI://accesso al menu di modifica dei dati personali
				MenuSpazioPersonale.routineModificaDati(utente);
				IOFile.salvaDati(Costanti.DATI, dati);//salvataggio dei dati
				break;
				
			case AFFINI://Accesso agli affini 
				bacheca.aggiorna();
				Main5.routineIscrizione(utente.getProposteAffini(),utente);
				IOFile.salvaDati(Costanti.DATI, dati);
				break;
				
			case INVITI: //Accesso agli inviti
				bacheca.aggiorna();
				Main5.routineIscrizione(utente.getInviti(),utente);
				IOFile.salvaDati(Costanti.DATI, dati);
				break;
				
				}
		}while(selezione!=0);
	}

	public static void routineModificaDati(Utente utente) {
		ObjectPrinter.stampaUtente(utente);
		Messaggi.stampa(Costanti.MESSAGGIO_RANGE+RangeEta.visualizzaRange());
		int n=Input.leggiIntTra(false,1,4);//lettura dell'indice del range 
		if(n!=-1) utente.setFasciaEta(RangeEta.values()[n-1].getRange());
		
		ArrayList<String> elencoCategorie=new ArrayList<>(Arrays.asList(Costanti.ELENCO_CATEGORIE));
		ArrayList<String> categorieInteresse=new ArrayList<>();
		do {
			Messaggi.stampa(Costanti.MESSAGGIO_INTERESSI);
			for(int k=0;k<elencoCategorie.size();k++) { //stampa dell'elenco di tutte le categorie disponibili
				Messaggi.stampa(k+1+")"+elencoCategorie.get(k));
			}
			n=Input.leggiIntTra(false,1,elencoCategorie.size());
			if(n!=-1) {
				categorieInteresse.add(elencoCategorie.get(n-1));
				elencoCategorie.remove(n-1);
			}
		}while(n!=-1 && elencoCategorie.size()!=0);
		if(!categorieInteresse.isEmpty())utente.setCategorieInteresse(categorieInteresse);//se l'utente ha scelto qualche categoria i dati sono aggiornati
		Messaggi.aggiornamentoDati();
		ObjectPrinter.stampaUtente(utente);
	}

	public static void routineNotifiche(Utente utente) {
		int selezione=0;
		do {
			ObjectPrinter.stampaNotifiche(utente.getNotifiche());
			if(utente.getNotifiche().size() == 0)break;
			selezione=Input.leggiInt(Costanti.MENU_NOTIFICHE, true);
			if(selezione<=utente.getNotifiche().size() && selezione>0) {
				int n=Input.yesNo(utente.getNotifiche().get(selezione-1)+Costanti.LINEA+Costanti.RIMOZIONE_NOTIFICA);
				if (n==1){
				utente.rimuoviNotifica(selezione-1);
				}
			}
				
		}while(selezione!=0);
	}

}
