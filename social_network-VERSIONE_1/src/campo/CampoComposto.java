package campo;

import java.text.ParseException;
import java.util.ArrayList;

import model.Costanti;
import view.Messaggi;

public class CampoComposto extends Campo {

	private ArrayList<Campo> valore;
	
	public CampoComposto(String nome,String descrizione,boolean obbligatorio,ArrayList<Campo>valore) {
		super(nome,descrizione,obbligatorio);
		this.valore=valore;
	}
	
	/**
	 * Metodo per attribuire un valore a Campo Composto.
	 * Essendo valore un ArrayList di Campi, compila viene di fatto eseguito su ogni singolo elemento di questo
	 */
	public void compila() {
		Messaggi.stampa(String.format("%-35s %-100s", this.getNome(), this.getDescrizione())+Costanti.COMPILAZIONE_COMPOSTO);
		valore.forEach(c->c.compila());

	}
	
	public ArrayList<Campo> getElencoCampi()
	{
		return valore;
	}

	/**
	 * Metodo che ritorna una stringa dei valor dei campi contenuti in campo composto separati da |
	 */
	public String getValore() {
		StringBuffer str=new StringBuffer();
		for(int i=0;i<valore.size();i++) {
			str.append(valore.get(i).getValore()).append("|");
		}
		return str.toString();
	}

	/** 
	 * Metodo con il quale è possibile stabilire se il campo è inizializzato.
	 * Un campo composto è inizializzato se almeno uno dei suo campi ha valore 
	 */
	public boolean isInizializzato() {
		boolean r=false;
		for(int i=0;i<valore.size();i++) {
			if (valore.get(i).isInizializzato()) r=true;
		}
		return r;
	}
	
	public boolean isComposito()
	{
		return true;
	}

	public void setValore(String valore) throws ParseException {

	}

}
