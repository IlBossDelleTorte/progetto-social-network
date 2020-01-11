package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import campo.Campo;
import campo.CampoComposto;
import model.Bacheca;
import model.ContainerDati;
import model.ListaUtenti;
import model.Proposta;
import model.RangeEta;
import model.Utente;
import view.Costanti;
import view.Messaggi;
import view.ObjectPrinter;
//
public class Main5 {
	
	private static final int ACCESSO_SPAZIO_PERSONALE = 3;
	private static final int ACCESSO_MENU_PROPOSTE = 2;
	private static final int ACCESSO_BACHECA = 1;

	public static void routineIscrizione(ArrayList<Proposta>array,Utente u,Bacheca b) {
		ControllerStato.aggiorna(u, b);
		if(array.size()!=0){
			int i=0;
			do {
				ObjectPrinter.stampaListaProposte(array);
				i=Input.leggiInt(Costanti.MESSAGGIO_BACHECA, true);
				if(i<array.size()+1 && i!=0)
				{
					Proposta selezionata=array.get(i-1);
					ObjectPrinter.stampaProposta(selezionata);
					int n=Input.yesNo(Costanti.ISCRIZIONE_PROPOSTA);
					if (n==1) {
						float spesa = calcoloSpesa(u, selezionata);
						selezionata.aggiungiPartecipante(u,spesa);
					}
				}
				ControllerStato.aggiorna(u, b);
			}while(i!=0 && array.size()!=0);
		}
		else ObjectPrinter.stampaListaProposte(array);
	}

	public static float calcoloSpesa(Utente u, Proposta selezionata) {
		int n;
		float spesa=Float.parseFloat(selezionata.valoreCampo(Costanti.INDICE_QUOTA_BASE).replace(',', '.'));
		
		//Caso in cui la proposta selezionata abbia delle spese opzionali da scegliere
		
		if(selezionata.getCategoria().haveOptionalChoice()&& !selezionata.getPartecipanti().contains(u)){
			
			CampoComposto c=(CampoComposto)selezionata.getCategoria().getCampo(Costanti.INDICE_SPESE_OPZIONALI);
			ArrayList<Campo> spese_opzionali=(ArrayList<Campo>)c.getElencoCampi().clone();
			Messaggi.stampa(Costanti.ISCRIZIONE_OPZIONALE);
			do {
				Messaggi.stampa(Costanti.MESSAGGIO_SPESE);
				ObjectPrinter.stampaListaCampi(spese_opzionali);
				n=Input.leggiIntTra(false,1,spese_opzionali.size());
				if(n!=-1) {
					spesa+=Float.parseFloat((spese_opzionali.get(n-1).getValore()).replace(',', '.'));
					spese_opzionali.remove(n-1);
				}
			}while(n!=-1 && spese_opzionali.size()!=0);
			
		}
		return spesa;
	}

	public static void main(String[] args) throws ParseException {
		ContainerDati dati=checkDati();
		Bacheca bacheca=dati.getBacheca();
		ListaUtenti listaUtenti=dati.getListaUtenti();
			
		
		String nome=Input.leggiStringa(Costanti.UTENTE, true);//lettura del nome utente;
		Utente utente= inizializzaUtente(listaUtenti, nome);
		IOFile.salvaDati(Costanti.DATI, dati);//salvataggio dei dati
		
		int i=0;
		do
		{
			i=Input.leggiInt(Costanti.MENU_INIZIALE, true);
			switch(i) {
			
			case ACCESSO_BACHECA:
				routineIscrizione(bacheca.getProposteAperte(),utente,bacheca);
				IOFile.salvaDati(Costanti.DATI, dati);
				break;		
				
			case ACCESSO_MENU_PROPOSTE://Accesso al menu proposta
				ControllerStato.aggiorna(utente,bacheca);
				MenuGestioneProposte.gestioneProposte(dati, bacheca, listaUtenti, utente);
				break;

			case ACCESSO_SPAZIO_PERSONALE: 
				ControllerStato.aggiornaBacheca(bacheca);
				MenuSpazioPersonale.spazioPersonale(dati, bacheca, utente);
				break;	
		}
		}while (i!=0);
		IOFile.salvaDati(Costanti.DATI, dati);//salvataggio dei dati
	}

	public static Utente inizializzaUtente(ListaUtenti listaUtenti, String nome) {
		Utente utente;
		if(listaUtenti.contiene(nome))
			utente=listaUtenti.estraiUtente(nome);
		else{  //routine per la creazione di un nuovo utente
			
			String range=null;
			System.out.print(Costanti.MESSAGGIO_RANGE+RangeEta.visualizzaRange());
			int n=Input.leggiIntTra(false,1,4);//lettura dell'indice del range 
			if(n!=-1) 
				range=RangeEta.values()[n-1].getRange(); //se l'utente ha deciso di inserire il range prende il rispettivo valore dall'enum
			
			ArrayList<String> elencoCategorie=new ArrayList<>(Arrays.asList(Costanti.ELENCO_CATEGORIE));//inizializzaione di un arraylist contente le categorie che l'utente può scegliere come interesse
			ArrayList<String> categorieInteresse=new ArrayList<>();
			do {
				System.out.print(Costanti.MESSAGGIO_INTERESSI);
				for(int i=0;i<elencoCategorie.size();i++) { //stampa dell'elenco di tutte le categorie disponibili
					System.out.println(i+1+")"+elencoCategorie.get(i));
				}
				n=Input.leggiIntTra(false,1,elencoCategorie.size());
				if(n!=-1) {
					categorieInteresse.add(elencoCategorie.get(n-1));
					elencoCategorie.remove(n-1);
				}
			}while(n!=-1 && elencoCategorie.size()!=0);
			utente=new Utente(nome,range,categorieInteresse);
			listaUtenti.aggiungiUtente(utente);
			Messaggi.creazioneUtente(utente);
		}
		return utente;
	}

	public static ContainerDati checkDati() {
		ContainerDati dati;
		if(IOFile.esistenzaDati(Costanti.DATI))//controllo dell'esistenza del salvataggio 
		{
			dati=(ContainerDati)IOFile.leggiDati(Costanti.DATI);
			
		}
		else//creazione di un nuovo salvataggio nel caso non sia presente
		{
			dati=new ContainerDati(new Bacheca(),new ListaUtenti());
		}
		return dati;
	}

}
