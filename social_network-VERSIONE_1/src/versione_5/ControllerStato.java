package versione_5;

import java.util.Calendar;
import java.util.Date;

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
			if(p.getPartecipanti().size() < numero_partecipanti
					&& scadenza_iscrizione.before(new Date())) {
				p.setStato(Stato.FALLITA);
				p.aggiungiLog();
			}
			else {
			p.setStato(Stato.APERTA);
			p.aggiungiLog();
			}
			break;
			
		case APERTA :
			int massimo_partecipanti=Integer.parseInt(p.valoreCampo(Costanti.INDICE_TOLLERANZA_PARTECIPANTI));
			
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
			c.setTime(Input.stringToDate(p.valoreCampo(Costanti.INDICE_DATA_INIZIO)));
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

}
