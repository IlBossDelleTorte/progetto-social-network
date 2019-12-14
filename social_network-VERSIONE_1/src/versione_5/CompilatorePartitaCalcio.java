package versione_5;

import java.text.ParseException;

import campo.Campo;
import view.Costanti;
import view.Messaggi;
import view.ObjectPrinter;

public class CompilatorePartitaCalcio extends ControllerCompilazione {

	@Override
	public void compilazioneSpecifica(Categoria c) throws ParseException {
		boolean compilazioneOk=true;
		Campo campo=c.getCampo(Costanti.INDICE_GENERE);
		do {
			campo.compila();
			compilazioneOk=true;
			String genere=c.valoreDi(Costanti.INDICE_GENERE);
			if(!(genere.equalsIgnoreCase("MASCHIO")||genere.equalsIgnoreCase("FEMMINA")||genere.equalsIgnoreCase("MISTO"))){
				Messaggi.erroreGenere();
				compilazioneOk=false;
				}
		}while(!compilazioneOk);
		
		campo=c.getCampo(Costanti.INDICE_RANGE);
		ObjectPrinter.stampaCampo(campo);
		Messaggi.stampaLinea();
		String str;
		if(campo.isObbligatorio())
			str=Costanti.COMPILAZIONE_STRINGA_RANGE;
		else str=Costanti.COMPILAZIONE_STRINGA_RANGE+Costanti.FACOLTATIVO_STRINGA;
		String v=Input.leggiStringaFormattata(str, Costanti.FORMATO_RANGE, campo.isObbligatorio());
		c.impostaCampo(v, Costanti.INDICE_RANGE);
	
	}

	@Override
	public void compilaProposta(Proposta p) throws ParseException {
		super.compilazioneStandard(p.getCategoria());
		compilazioneSpecifica(p.getCategoria());
		super.terminaCompilazione(p);

	}

}
