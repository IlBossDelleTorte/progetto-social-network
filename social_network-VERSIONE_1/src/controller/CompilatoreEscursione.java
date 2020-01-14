package controller;

import java.text.ParseException;

import model.Categoria;
import model.Costanti;
import model.Proposta;

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
