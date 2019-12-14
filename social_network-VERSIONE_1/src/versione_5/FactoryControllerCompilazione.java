package versione_5;

import java.util.ArrayList;
import java.util.Arrays;

import view.Costanti;
import view.Messaggi;
import view.ObjectPrinter;

public class FactoryControllerCompilazione {
	
	/**
	 * Factory method che ritorna un istanza di ControllerCompilazione sulla base della categoria scelta dall'utente
	 * @param p
	 * @return
	 */
	public ControllerCompilazione sceltaCategoria(Proposta p) {
		ArrayList<String> elencoCategorie=new ArrayList<>(Arrays.asList(Costanti.ELENCO_CATEGORIE));
		ControllerCompilazione compilatore=null;
		ObjectPrinter.stampaLista(elencoCategorie);
		Messaggi.stampa(Costanti.SELEZIONE_CATEGORIA);//scelta della categoria della proposta 
		int n=Input.leggiIntTra(true,1,elencoCategorie.size());
		switch(n) {
		case 1:
			p.setCategoria(new PartitaDiCalcio());
			compilatore= new CompilatorePartitaCalcio();
			break;
		case 2:
			p.setCategoria(new Escursione());
			compilatore= new CompilatoreEscursione();
			break;
		}
		return compilatore;
	}
	
	

}
