package controller;

import model.Costanti;
import model.Proposta;
import model.Stato;
import view.ObjectPrinter;

public class CreatoreNotifiche {
	
	public static String creaNotifica(Proposta p) {
		String notifica=null;
		Stato stato=p.getStato();
		switch(stato) {
		case CHIUSA:
			notifica=Costanti.NOTIFICA_SUCCESSO+"\t   "+ObjectPrinter.stringaHeadP(p);
			break;
			
		case FALLITA:
			notifica=Costanti.NOTIFICA_FALLIMENTO+"\t   "+ObjectPrinter.stringaHeadP(p);
			break;
				
		case RITIRATA:
			notifica=Costanti.NOTIFICA_RITIRO+"\t   "+ObjectPrinter.stringaHeadP(p);
			break;
			
		default:break;
		}
		return notifica;
	}

}
