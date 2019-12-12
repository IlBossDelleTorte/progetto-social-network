package versione_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import campo.Campo;
import view.Costanti;
import view.Messaggi;
import view.ObjectPrinter;

public class ControllerCompilazione {
	
	private static void sceltaCategoria(Proposta p) {
		ArrayList<String> elencoCategorie=new ArrayList<>(Arrays.asList(Costanti.ELENCO_CATEGORIE));
		
		ObjectPrinter.stampaLista(elencoCategorie);
		Messaggi.stampa(Costanti.SELEZIONE_CATEGORIA);//scelta della categoria della proposta 
		int n=Input.leggiIntTra(true,1,elencoCategorie.size());
		switch(n) {
		case 1:
			p.setCategoria(new PartitaDiCalcio());
			break;
		case 2:
			p.setCategoria(new Escursione());
			break;
		}
		
	}
	
	
	public static void compilaProposta(Proposta p) {
		sceltaCategoria(p);
		ArrayList<Campo> c = p.getCategoria().getCampi();
		int size = c.size();
		for(int i = 0; i < size; i++) {
			switch(i) {
			case Costanti.INDICE_DATA_INIZIO:
				c.get(i).compila();
				
				Date data_inizio=Input.stringToDate(p.valoreCampo(i));
				Date data_scadenza=Input.stringToDate(p.valoreCampo(Costanti.INDICE_SCADENZA_ISCRIZIONE));
				
				if(data_inizio.before(data_scadenza)) {
					System.out.println(Costanti.ERRORE_DATA_INIZIO);
					i--;
				}
				break;
			}
		}

	}
}
