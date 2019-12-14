package versione_5;

import java.text.ParseException;

import view.Costanti;

public class CompilatoreEscursione extends ControllerCompilazione {

	public void compilaProposta(Proposta p) throws ParseException {
		super.compilazioneStandard(p.getCategoria());
		compilazioneSpecifica(p.getCategoria());
		super.terminaCompilazione(p);
	}
	public void compilazioneSpecifica(Categoria c) {
		c.getCampo(Costanti.INDICE_SPESE_OPZIONALI).compila();

	}

}
