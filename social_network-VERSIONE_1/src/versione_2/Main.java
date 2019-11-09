package versione_2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
//
public class Main {
	
	public static void routineIscrizione(ArrayList<Proposta>array,Utente u) {
		u.aggiornaProposte();
		if(array.size() == 0) {
			System.out.print("ELENCO VUOTO");
		}
		else {
			int b=0;
			do {
				b=Input.leggiInt(Input.proposteToString(array)+Menu.MESSAGGIO_BACHECA, true);
				if(b<array.size()+1 && b!=0)
				{
					Proposta selezionata=array.get(b-1);
					int n=Input.yesNo(selezionata+Menu.ISCRIZIONE_PROPOSTA);
					if (n==1)selezionata.iscrizioneProposta(u,0);
				}
				u.aggiornaProposte();
			}while(b!=0 && array.size()!=0);
		}
		
	
		
		
	}

	public static void main(String[] args) throws ParseException {
		ContainerDati dati;
		Bacheca bacheca;
		ListaUtenti listaUtenti;

		if(IOFile.esistenzaDati(Menu.DATI))//controllo dell'esistenza del salvataggio 
		{
			dati=(ContainerDati)IOFile.leggiDati(Menu.DATI);
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
		
		nome=Input.leggiStringa(Menu.UTENTE, true);//lettura del nome utente
		if(listaUtenti.contiene(nome))
			utente=listaUtenti.estraiUtente(nome);
		else{  //routine per la creazione di un nuovo utente
			
			String range=null;
			System.out.print(Menu.MESSAGGIO_RANGE+Range_eta.visualizzaRange());
			int n=Input.leggiIntTra(false,1,4);//lettura dell'indice del range 
			if(n!=-1) 
				range=Range_eta.values()[n-1].getRange(); //se l'utente ha deciso di inserire il range prende il rispettivo valore dall'enum
			
			ArrayList<String> elencoCategorie=new ArrayList<>(Arrays.asList(Menu.ELENCO_CATEGORIE));//inizializzaione di un arraylist contente le categorie che l'utente può scegliere come interesse
			ArrayList<String> categorieInteresse=new ArrayList<>();
			do {
				System.out.print(Menu.MESSAGGIO_INTERESSI);
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
			IOFile.salvaDati(Menu.DATI, dati);//salvataggio dei dati
		}
		
		int i=0;
		do
		{
			i=Input.leggiInt(Menu.MENU_INIZIALE, true);
			switch(i) {
			case 1://Accesso alla bacheca per iscriversi ad una proposta
				bacheca.aggiorna();
				routineIscrizione(bacheca.getProposteAperte(),utente);
				IOFile.salvaDati(Menu.DATI, dati);
				break;				
			case 2://Accesso al menu proposta
				bacheca.aggiorna();
				int p=0;
				do {
					p=Input.leggiInt(Menu.MENU_PROPOSTE, true);
					switch(p) {
					case 1://compilazione di una nuova proposta
						new Proposta(utente).compilazione();
						IOFile.salvaDati("DATA.save", dati);//salvataggio dei dati
						break;
					case 2: //visualizzazione delle proposte valide e pubblicazione
						int p2=0;
						if(utente.getProposteValide().size()== 0) {
							System.out.print(Menu.PROPOSTE_VALIDE_VUOTO);
						}
							else {
								do {
									p2=Input.leggiInt(Input.proposteToString(utente.getProposteValide())+Menu.MESSAGGIO_SELEZIONE_PROPOSTA, true);
									if(p2<=utente.getProposteValide().size() && p2>0)
									{
										int n=Input.leggiInt(utente.getProposteValide().get(p2-1)+Menu.GESTIONE_PROPOSTA_VALIDA,true);
										if (n==1){//pubblica una proposta valida (diviene aperta)
											Proposta selezionata=utente.getProposteValide().get(p2-1);
											bacheca.aggiungiPropostaAperta(selezionata,listaUtenti);
											utente.rimuoviPropostaValida(selezionata);
											System.out.print(Menu.PUBBLICAZIONE_EFFETTUATA);
											
											//ROUTINE DI INVIO DEGLI INVITI ALLA PROPOSTA CORRENTE
											ArrayList<Utente>correlati=bacheca.utentiCorrelati(utente, selezionata);
											do {
												System.out.print(Input.nomeUtentetoString(correlati)+Menu.MESSAGGIO_INVITI);
												n=Input.leggiIntTra(false,1,correlati.size());
												if(n!=-1) {
													correlati.get(n-1).aggiungiInvito(selezionata);
													correlati.get(n-1).riceviNotifica(String.format(Menu.NOTIFICA_INVITO,selezionata.getCreatore().getNome(),selezionata.getCategoria().getNome()));
													//In seguito alla scelta di invitarte un utente, questo riceve una notifica con il nome del creatore della proposta e la categoria di appartenenza
													correlati.remove(n-1);
												}
											}while(n!=-1 && correlati.size()!=0);
										}
										if (n==2)//rimuove una proposta dall'elenco delle proposte valide
											utente.rimuoviPropostaValida(utente.getProposteValide().get(p2-1));
										if(utente.getProposteValide().size() == 0)
											break;
									}
								}while(p2!=0);
						}
						IOFile.salvaDati(Menu.DATI, dati);//salvataggio dei dati
						break;
						
					case 3: //visualizzazione dell'elenco iscrizioni e ritiro dell'iscrizione 
						int p3=0;
						if(bacheca.getIscrizioni(utente).size()==0) {
							System.out.print(Menu.ELENCO_ISCRIZIONE_VUOTO);
						}
						else {
							do {
								p3=Input.leggiInt(Input.proposteToString(bacheca.getIscrizioni(utente))+Menu.MESSAGGIO_SELEZIONE_PROPOSTA, true);
								if(p3 <= bacheca.getIscrizioni(utente).size() && p3>0){
									Proposta propostaSelezionata = bacheca.getIscrizioni(utente).get(p3-1);
									int n=Input.leggiInt(propostaSelezionata+Menu.GESTIONE_RITIRO_ISCRIZIONE, true);
				
									if (n==1 && propostaSelezionata.isRitirabile()) {
										propostaSelezionata.annullaIscrizione(utente);
										System.out.print(Menu.MESSAGGIO_DISISCRIZIONE);
									}
									if(utente.getProposteValide().size() == 0)
										break;
								}
									
							}while(p3 != 0);
						}
						IOFile.salvaDati(Menu.DATI, dati);
						break;
						
					case 4://visualizzazione dell'elenco delle proposte create e ritiro di queste 
						int p4=0;
						if(bacheca.getProposteCreatore(utente).size()==0) {
							System.out.print(Menu.ELENCO_CREAZIONI_VUOTO);
						}
						else {
							do {
								p4=Input.leggiInt(Input.proposteToString(bacheca.getProposteCreatore(utente))+Menu.MESSAGGIO_SELEZIONE_PROPOSTA,true);
								if(p4 <= bacheca.getProposteCreatore(utente).size() && p4>0) {
									Proposta propostaSelezionata = bacheca.getProposteCreatore(utente).get(p4-1);
									int n=Input.leggiInt(propostaSelezionata+Menu.GESTIONE_PROPOSTA_RITIRO, true);
				
									if (n==1 && propostaSelezionata.isRitirabile()) {
										bacheca.ritiraProposta(propostaSelezionata);
										System.out.print(Menu.MESSAGGIO_RITIRO);
										
									}
									if(utente.getProposteValide().size() == 0)
										break;
								}
							}while(p4!=0);
						}
						IOFile.salvaDati(Menu.DATI, dati);
						break;
					}
					}while(p!=0);
				break;

			case 3: //Accesso allo spazio personale
				bacheca.aggiorna();
				int sp=0;
				do {
					sp=Input.leggiInt(Menu.MENU_SPAZIO_PERSONALE, true);
					switch(sp) {
					case 1://Accesso alle notifiche
						int sp1=0;
						if(utente.getNotifiche().size() == 0)	{//se non ci sono notifiche visualizza un messaggio e torna indietro
							System.out.print(Menu.SPAZIO_PERSONALE_VUOTO);
							break;
						}
						else {
							do {
								sp1=Input.leggiInt(utente.elencoNotifiche()+Menu.MENU_NOTIFICHE, true);
								if(sp1<=utente.getNotifiche().size() && sp1>0) {
								
									int n=Input.yesNo(utente.getNotifiche().get(sp-1)+Menu.LINEA+Menu.RIMOZIONE_NOTIFICA);
									if (n==1)
										{
										utente.rimuoviNotifica(sp1-1);
										}
								}
								if(utente.getNotifiche().size() == 0)
									break;
							}while(sp1!=0);					
							IOFile.salvaDati(Menu.DATI, dati);//salvataggio dei dati
							break;
						}
						
					case 2://accesso al menu di modifica dei dati personali
						System.out.print(utente.toString());
						System.out.print(Menu.MESSAGGIO_RANGE+Range_eta.visualizzaRange());
						int n=Input.leggiIntTra(false,1,4);//lettura dell'indice del range 
						if(n!=-1) utente.setFasciaEta(Range_eta.values()[n-1].getRange());
						
						ArrayList<String> elencoCategorie=new ArrayList<>(Arrays.asList(Menu.ELENCO_CATEGORIE));
						ArrayList<String> categorieInteresse=new ArrayList<>();
						do {
							System.out.print(Menu.MESSAGGIO_INTERESSI);
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
						IOFile.salvaDati(Menu.DATI, dati);//salvataggio dei dati
						break;
						
					case 3://da fare:Accesso agli affini 
						bacheca.aggiorna();
						routineIscrizione(utente.getProposteAffini(),utente);
						IOFile.salvaDati(Menu.DATI, dati);
						break;
						
					case 4: //Accesso agli inviti
						bacheca.aggiorna();
						routineIscrizione(utente.getInviti(),utente);
						IOFile.salvaDati(Menu.DATI, dati);
						break;
						
						}
				}while(sp!=0);
				break;
				
			case 4:
				System.out.println("Spamming notifiche!!!\n\n");
				utente.riceviNotifica("NEGRO");
				break;
			case 5 :
				for(Proposta proposta : utente.getProposteValide())
					proposta.log();
				
				for(Proposta proposta : bacheca.getProposteAperte()) {
					proposta.log();
				}
				for(Proposta proposta : bacheca.getProposteInvalide()) {
					proposta.log();
				}
				break;
				
			case 6:System.out.println(Input.nomeUtentetoString(bacheca.utentiCorrelati(utente, null)));
			System.out.println(Input.proposteToString(bacheca.getProposteInvalide()));
		}
		}while (i!=0);
		IOFile.salvaDati(Menu.DATI, dati);//salvataggio dei dati
	}

}
