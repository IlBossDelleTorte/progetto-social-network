package versione_5;

import java.util.ArrayList;

import view.Costanti;
import view.Messaggi;
import view.ObjectPrinter;

public class ControllerInviti {
	
	private static ArrayList<Utente> utentiCorrelati(Bacheca b,Proposta p) {
		ArrayList<Utente> utentiCorrelati = new ArrayList<>();
		for(Proposta x : b.getProposteInvalide()) {
			//prende le proposte che sono state create in passato dallo stesso utente
			if(x.getCreatore().getNome() == p.getCreatore().getNome()) {
				//prende le proposte concluse e chiuse che condividono la stessa categoria della proposta da aprire
				if(p.getCategoria().getNome().equals(x.getCategoria().getNome())) {
					if(x.getStato() == Stato.CHIUSA || x.getStato() == Stato.CONCLUSA) {
						for(Notificabile u : x.getPartecipanti()) {
							//Se il partecipante scelto dalla proposta non è contenuto nella lista degli utenti correlati, allora viene aggiunto
							if(!utentiCorrelati.contains(u)&& !u.equals(p.getCreatore()))
								utentiCorrelati.add((Utente) u);	
						}
					}
				}
			}
		}
		return utentiCorrelati;
	}

	public static void invita(Bacheca bacheca,Proposta selezionata) {
		int n;
		ArrayList<Utente>correlati=utentiCorrelati(bacheca,selezionata);
		if (correlati.size()!=0) {
			do {
				ObjectPrinter.stampaListaUtenti(correlati);
				Messaggi.stampa(Costanti.MESSAGGIO_INVITI);
				n=Input.leggiIntTra(false,1,correlati.size());
				if(n!=-1) {
					correlati.get(n-1).aggiungiInvito(selezionata);
					correlati.get(n-1).riceviNotifica(String.format(Costanti.NOTIFICA_INVITO,selezionata.getCreatore().getNome(),selezionata.getCategoria().getNome()));
				//In seguito alla scelta di invitarte un utente, questo riceve una notifica con il nome del creatore della proposta e la categoria di appartenenza
				correlati.remove(n-1);
			}
		}while(n!=-1 && correlati.size()!=0);
		}
		
	}
	
	
}
