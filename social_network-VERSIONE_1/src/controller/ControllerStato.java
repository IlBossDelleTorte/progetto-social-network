package controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import model.Bacheca;
import model.Proposta;
import model.Stato;
import model.Utente;
import view.Costanti;

public class ControllerStato {
	
	public static void aggiornaProposta(Proposta p) {
		Date scadenza_iscrizione=Input.stringToDate(p.valoreCampo(Costanti.INDICE_SCADENZA_ISCRIZIONE));
		Date termine_ritiro=Input.stringToDate(p.valoreCampo(Costanti.INDICE_TERMINE_RITIRO));
		int numero_partecipanti=Integer.parseInt(p.valoreCampo(Costanti.INDICE_PARTECIPANTI));
		
		switch (p.getStato()) {
		
		case VUOTA : 
			p.setStato(Stato.VALIDA);
			p.aggiungiLog();
			break;
			
		case VALIDA :
			//nel caso in cui l'utente avesse una proposta valida e le condizioni non fossero rispettate
			//prima che questa venga pubblicata, questa passa direttamente a fallita
			if(scadenza_iscrizione.before(new Date())) {
				p.setStato(Stato.FALLITA);
				p.aggiungiLog();
			}
			else {
			p.setStato(Stato.APERTA);
			p.aggiungiLog();
			}
			break;
			
		case APERTA :
			int massimo_partecipanti=Integer.parseInt(p.valoreCampo(Costanti.INDICE_TOLLERANZA_PARTECIPANTI))
			+numero_partecipanti;
			
			if((scadenza_iscrizione.after(new Date()) && termine_ritiro.before(new Date()) && p.getPartecipanti().size() == massimo_partecipanti)
				||(new Date().after(scadenza_iscrizione) && p.getPartecipanti().size()>=numero_partecipanti && p.getPartecipanti().size()<=massimo_partecipanti)){
				p.setStato(Stato.CHIUSA);
				p.aggiungiLog();
			}
			else if(p.getPartecipanti().size() < numero_partecipanti
					&& scadenza_iscrizione.before(new Date())) {
				p.setStato(Stato.FALLITA);
				p.aggiungiLog();
			}
			break;
			
		case CHIUSA :
			Calendar c = Calendar.getInstance();
			c.setTime(Input.stringToDate(p.valoreCampo(Costanti.INDICE_DATA_FINE)));
			c.add(Calendar.DAY_OF_MONTH, 1);
			Date d = c.getTime();
			if(new Date().after(d)) {
				p.setStato(Stato.CONCLUSA);
				p.aggiungiLog();
			}
			break;
			
		default :
			p.aggiungiLog();
			break;
		
			
		}
	}
	
	/**
	 * Metodo che aggiorna lo stato di tutte le proposte della bacheca in accordo con l'automa e
	 * permette la rimozione dalla bacheca delle proposte concluse o fallite.
	 */
	public static void aggiornaBacheca(Bacheca b) {
		//Aggiornamento di tutte le proposte aperte 
		b.getProposteAperte().forEach(p->aggiornaProposta(p));
		for(int i = 0; i < b.getProposteAperte().size(); i++) {
			if(b.getProposteAperte().get(i).getStato()!=Stato.APERTA)
				b.rimuoviPropostaAperta(i);
		}
		
	}
	
	/**
	 * Metodo che aggiorna l'elenco delle proposte affini e di quelle per cui si è ricevuto un invito di un utente 
	 * @param u: utente da aggiornare 
	 */
	public static void aggiornaElenchiUtente(Utente u) {
		for(int i = 0; i < u.getProposteAffini().size(); i++) {
			if(u.getProposteAffini().get(i).getStato() != Stato.APERTA)
				u.getProposteAffini().remove(i);
			else if(u.getProposteAffini().get(i).getPartecipanti().contains(u))
				u.getProposteAffini().remove(i);
		}
		for(int j = 0; j < u.getInviti().size(); j++) {
			if(u.getInviti().get(j).getStato() != Stato.APERTA)
				u.getInviti().remove(j);
			else if(u.getInviti().get(j).getPartecipanti().contains(u))
				u.getInviti().remove(j);
		}
			
	}
	
	public static void  aggiorna(Utente u, Bacheca b) {
		aggiornaElenchiUtente(u);
		aggiornaBacheca(b);
	}

}
