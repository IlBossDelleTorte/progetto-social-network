package versione_2;

import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws ParseException {
		ContainerDati dati;
		Bacheca bacheca;
		ListaUtenti listaUtenti;

		if(IOFile.esistenzaDati(Menu.DATI))
		{
			dati=(ContainerDati)IOFile.leggiDati(Menu.DATI);
			bacheca=dati.getBacheca();
			listaUtenti=dati.getListaUtenti();
		}
		else
		{
			bacheca=new Bacheca();
			listaUtenti=new ListaUtenti();
			dati=new ContainerDati(bacheca,listaUtenti);
		}
		
		String nome;
		Utente utente;
		nome=Input.leggiStringa(Menu.UTENTE, true);
		if(listaUtenti.contiene(nome))
			utente=listaUtenti.estraiUtente(nome);
		else{
			utente=new Utente(nome);
			listaUtenti.aggiungiUtente(utente);
		}
		
		int i=0;
		do
		{
			i=Input.leggiInt(Menu.MENU_INIZIALE, true);
			switch(i) {
			case 1://Accesso alla bacheca per iscriversi ad una proposta
				bacheca.aggiorna();
				bacheca.pulisci();
				int b=0;
				do {
					b=Input.leggiInt(bacheca+Menu.MESSAGGIO_BACHECA, true);
					if(b<bacheca.getProposteAperte().size()+1 && b!=0)
					{
						int n=Input.yesNo(bacheca.getProposteAperte().get(b-1)+Menu.ISCRIZIONE_PROPOSTA);
						if (n==1)bacheca.iscrizioneProposta(b-1, utente);
						IOFile.salvaDati(Menu.DATI, dati);//salvataggio dei dati
					}
				}while(b!=0);
				break;
			case 2://Accesso al menu proposta
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
						do {
							p2=Input.leggiInt(utente.elencoProposteValide()+Menu.MESSAGGIO_PUBBLICAZIONE, true);
							if(p2<=utente.getProposteValide().size() && p2>0)
							{
								int n=Input.leggiInt(utente.getProposteValide().get(p2-1)+Menu.GESTIONE_PROPOSTA,true);
								if (n==1)
									{
									bacheca.aggiungiPropostaAperta(utente.getProposteValide().get(p2-1));
									utente.rimuoviPropostaValida(utente.getProposteValide().get(p2-1));
									System.out.print(Menu.PUBBLICAZIONE_EFFETTUATA);
									}
								if (n==2)
									utente.rimuoviPropostaValida(utente.getProposteValide().get(p2-1));
							}
						}while(p2!=0);
						IOFile.salvaDati(Menu.DATI, dati);//salvataggio dei dati
					}
				}while(p!=0);
				break;
			case 3: //Accesso allo spazio personale
				int sp=0;
				do {
					sp=Input.leggiInt(utente.elencoNotifiche()+Menu.MENU_SPAZIO_PERSONALE, true);
					if(sp<=utente.getSpazioPersonale().size() && sp>0) {
						
						int n=Input.yesNo(utente.getSpazioPersonale().get(sp-1)+Menu.LINEA+Menu.RIMOZIONE_NOTIFICA);
						if (n==1)
							{
							utente.rimuoviNotifica(sp-1);
							}
					}
				}while(sp!=0);
				IOFile.salvaDati(Menu.DATI, dati);//salvataggio dei dati
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
		}
		}while (i!=0);
		IOFile.salvaDati(Menu.DATI, dati);//salvataggio dei dati
	}

}
