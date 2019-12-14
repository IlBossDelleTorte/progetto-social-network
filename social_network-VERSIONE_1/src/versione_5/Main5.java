package versione_5;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import campo.Campo;
import campo.CampoComposto;
import view.Costanti;
import view.Messaggi;
import view.ObjectPrinter;
//
public class Main5 {
	
	public static void routineIscrizione(ArrayList<Proposta>array,Utente u) {
		u.aggiornaProposte();
		ObjectPrinter.stampaListaProposte(array);
		if(array.size()!=0){
			int b=0;
			do {
				b=Input.leggiInt(Costanti.MESSAGGIO_BACHECA, true);
				if(b<array.size()+1 && b!=0)
				{
					Proposta selezionata=array.get(b-1);
					int n=Input.yesNo(selezionata+Costanti.ISCRIZIONE_PROPOSTA);
					if (n==1) {
						float spesa=Float.parseFloat(selezionata.getCategoria().getCampi().get(Costanti.INDICE_QUOTA_BASE).getValore().replace(',', '.'));
						
						//Caso in cui la proposta selezionata abbia delle spese opzionali da scegliere
						if(selezionata.getCategoria().haveOptionalChoice()&& !selezionata.getPartecipanti().contains(u)){
							CampoComposto c=(CampoComposto)selezionata.getCategoria().getCampi().get(Costanti.INDICE_SPESE_OPZIONALI);
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
						selezionata.aggiungiPartecipante(u,spesa);
					}
				}
				u.aggiornaProposte();
			}while(b!=0 && array.size()!=0);
		}
		
	
		
		
	}

	public static void main(String[] args) throws ParseException {
		ContainerDati dati;
		Bacheca bacheca;
		ListaUtenti listaUtenti;

		if(IOFile.esistenzaDati(Costanti.DATI))//controllo dell'esistenza del salvataggio 
		{
			dati=(ContainerDati)IOFile.leggiDati(Costanti.DATI);
			bacheca=dati.getBacheca();
			listaUtenti=dati.getListaUtenti();
		}
		else//creazione di un nuovo salvataggio nel caso non sia presente
		{
			bacheca=new Bacheca();
			listaUtenti=new ListaUtenti();
			dati=new ContainerDati(bacheca,listaUtenti);
		}
		
		String nome;
		Utente utente;
		
		nome=Input.leggiStringa(Costanti.UTENTE, true);//lettura del nome utente
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
			System.out.println("UTENTE CREATO CON SUCCESSO\n"+utente.toString());
			IOFile.salvaDati(Costanti.DATI, dati);//salvataggio dei dati
		}
		
		int i=0;
		do
		{
			i=Input.leggiInt(Costanti.MENU_INIZIALE, true);
			switch(i) {
			case 1://Accesso alla bacheca per iscriversi ad una proposta
				bacheca.aggiorna();
				routineIscrizione(bacheca.getProposteAperte(),utente);
				IOFile.salvaDati(Costanti.DATI, dati);
				break;				
			case 2://Accesso al menu proposta
				bacheca.aggiorna();
				int p=0;
				do {
					p=Input.leggiInt(Costanti.MENU_PROPOSTE, true);
					switch(p) {
					case 1://compilazione di una nuova proposta
						FactoryControllerCompilazione fabbrica= new FactoryControllerCompilazione();
						Proposta prop=new Proposta(utente);
						ControllerCompilazione comp=fabbrica.sceltaCategoria(prop);
						comp.compilaProposta(prop);
						IOFile.salvaDati("DATA.save", dati);//salvataggio dei dati
						break;
					case 2: //visualizzazione delle proposte valide e pubblicazione
						int p2=0;
						if(utente.getProposteValide().size()== 0) {
							System.out.print(Costanti.PROPOSTE_VALIDE_VUOTO);
						}
							else {
								do {
									ObjectPrinter.stampaListaProposte(utente.getProposteValide());
									p2=Input.leggiInt(Costanti.MESSAGGIO_SELEZIONE_PROPOSTA, true);
									if(p2<=utente.getProposteValide().size() && p2>0)
									{
										int n=Input.leggiInt(utente.getProposteValide().get(p2-1)+Costanti.GESTIONE_PROPOSTA_VALIDA,true);
										if (n==1){//pubblica una proposta valida (diviene aperta)
											Proposta selezionata=utente.getProposteValide().get(p2-1);
											bacheca.aggiungiPropostaAperta(selezionata,listaUtenti);
											utente.rimuoviPropostaValida(selezionata);
											System.out.print(Costanti.PUBBLICAZIONE_EFFETTUATA);
											
											//ROUTINE DI INVIO DEGLI INVITI ALLA PROPOSTA CORRENTE
											ControllerInviti.invita(bacheca,selezionata);
											
										}
										if (n==2)//rimuove una proposta dall'elenco delle proposte valide
											utente.rimuoviPropostaValida(utente.getProposteValide().get(p2-1));
										if(utente.getProposteValide().size() == 0)
											break;
									}
								}while(p2!=0);
						}
						IOFile.salvaDati(Costanti.DATI, dati);//salvataggio dei dati
						break;
						
					case 3: //visualizzazione dell'elenco iscrizioni e ritiro dell'iscrizione 
						int p3=0;
						if(bacheca.getIscrizioni(utente).size()==0) {
							System.out.print(Costanti.ELENCO_ISCRIZIONE_VUOTO);
						}
						else {
							do {
								ObjectPrinter.stampaListaProposte(bacheca.getIscrizioni(utente));
								p3=Input.leggiInt(Costanti.MESSAGGIO_SELEZIONE_PROPOSTA, true);
								if(p3 <= bacheca.getIscrizioni(utente).size() && p3>0){
									Proposta propostaSelezionata = bacheca.getIscrizioni(utente).get(p3-1);
									int n=Input.leggiInt(propostaSelezionata+Costanti.GESTIONE_RITIRO_ISCRIZIONE, true);
				
									if (n==1 && propostaSelezionata.isRitirabile()) {
										propostaSelezionata.rimuoviPartecipante(utente);
										System.out.print(Costanti.MESSAGGIO_DISISCRIZIONE);
									}

									if (n==1 && !propostaSelezionata.isRitirabile())
									{
										System.out.print(Costanti.MESSAGGIO_NON_RITIRABILE);
									}
									if(utente.getProposteValide().size() == 0)
										break;
								}
									
							}while(p3 != 0);
						}
						IOFile.salvaDati(Costanti.DATI, dati);
						break;
						
					case 4://visualizzazione dell'elenco delle proposte create e ritiro di queste 
						int p4=0;
						if(bacheca.getProposteCreatore(utente).size()==0) {
							System.out.print(Costanti.ELENCO_CREAZIONI_VUOTO);
						}
						else {
							do {
								ObjectPrinter.stampaListaProposte(bacheca.getProposteCreatore(utente));
								p4=Input.leggiInt(Costanti.MESSAGGIO_SELEZIONE_PROPOSTA,true);
								if(p4 <= bacheca.getProposteCreatore(utente).size() && p4>0) {
									Proposta propostaSelezionata = bacheca.getProposteCreatore(utente).get(p4-1);
									int n=Input.leggiInt(propostaSelezionata+Costanti.GESTIONE_PROPOSTA_RITIRO, true);
				
									if (n==1 && propostaSelezionata.isRitirabile()) {
										bacheca.ritiraProposta(propostaSelezionata);
										System.out.print(Costanti.MESSAGGIO_RITIRO);
										
									}
									if (n==1 && !propostaSelezionata.isRitirabile())
									{
										System.out.print(Costanti.MESSAGGIO_NON_RITIRABILE);
									}
									if(utente.getProposteValide().size() == 0)
										break;
								}
							}while(p4!=0);
						}
						IOFile.salvaDati(Costanti.DATI, dati);
						break;
					}
					}while(p!=0);
				break;

			case 3: //Accesso allo spazio personale
				bacheca.aggiorna();
				int sp=0;
				do {
					sp=Input.leggiInt(Costanti.MENU_SPAZIO_PERSONALE, true);
					switch(sp) {
					case 1://Accesso alle notifiche
						int sp1=0;
						if(utente.getNotifiche().size() == 0)	{//se non ci sono notifiche visualizza un messaggio e torna indietro
							System.out.print(Costanti.SPAZIO_PERSONALE_VUOTO);
							break;
						}
						else {
							do {
								sp1=Input.leggiInt(utente.elencoNotifiche()+Costanti.MENU_NOTIFICHE, true);
								if(sp1<=utente.getNotifiche().size() && sp1>0) {
								
									int n=Input.yesNo(utente.getNotifiche().get(sp-1)+Costanti.LINEA+Costanti.RIMOZIONE_NOTIFICA);
									if (n==1)
										{
										utente.rimuoviNotifica(sp1-1);
										}
								}
								if(utente.getNotifiche().size() == 0)
									break;
							}while(sp1!=0);					
							IOFile.salvaDati(Costanti.DATI, dati);//salvataggio dei dati
							break;
						}
						
					case 2://accesso al menu di modifica dei dati personali
						System.out.print(utente.toString());
						System.out.print(Costanti.MESSAGGIO_RANGE+RangeEta.visualizzaRange());
						int n=Input.leggiIntTra(false,1,4);//lettura dell'indice del range 
						if(n!=-1) utente.setFasciaEta(RangeEta.values()[n-1].getRange());
						
						ArrayList<String> elencoCategorie=new ArrayList<>(Arrays.asList(Costanti.ELENCO_CATEGORIE));
						ArrayList<String> categorieInteresse=new ArrayList<>();
						do {
							System.out.print(Costanti.MESSAGGIO_INTERESSI);
							for(int k=0;k<elencoCategorie.size();k++) { //stampa dell'elenco di tutte le categorie disponibili
								System.out.println(k+1+")"+elencoCategorie.get(k));
							}
							n=Input.leggiIntTra(false,1,elencoCategorie.size());
							if(n!=-1) {
								categorieInteresse.add(elencoCategorie.get(n-1));
								elencoCategorie.remove(n-1);
							}
						}while(n!=-1 && elencoCategorie.size()!=0);
						if(!categorieInteresse.isEmpty())utente.setCategorieInteresse(categorieInteresse);//se l'utente ha scelto qualche categoria i dati sono aggiornati
						System.out.print("ECCO I DATI AGGIORNATI:\n"+utente.toString());
						IOFile.salvaDati(Costanti.DATI, dati);//salvataggio dei dati
						break;
						
					case 3://da fare:Accesso agli affini 
						bacheca.aggiorna();
						routineIscrizione(utente.getProposteAffini(),utente);
						IOFile.salvaDati(Costanti.DATI, dati);
						break;
						
					case 4: //Accesso agli inviti
						bacheca.aggiorna();
						routineIscrizione(utente.getInviti(),utente);
						IOFile.salvaDati(Costanti.DATI, dati);
						break;
						
						}
				}while(sp!=0);
				break;
				
		}
		}while (i!=0);
		IOFile.salvaDati(Costanti.DATI, dati);//salvataggio dei dati
	}

}
