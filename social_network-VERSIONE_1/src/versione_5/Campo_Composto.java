package versione_5;

import java.text.ParseException;
import java.util.ArrayList;

public class Campo_Composto extends Campo {

	private ArrayList<Campo> valore;
	
	public Campo_Composto(String nome,String descrizione,boolean obbligatorio,ArrayList<Campo>valore) {
		super(nome,descrizione,obbligatorio);
		this.valore=valore;
	}
	
	/**
	 * Metodo per attribuire un valore a Campo Composto.
	 * Essendo valore un ArrayList di Campi, compila viene di fatto eseguito su ogni singolo elemento di questo
	 */
	public void compila() {
		System.out.print(String.format("%-35s %-100s", this.getNome(), this.getDescrizione())+Menu.COMPILAZIONE_COMPOSTO);
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
	
	public String toString() {
		StringBuffer str=new StringBuffer(super.toString());
		str.append("\n");
		for(int i=0;i<valore.size();i++) {
			if(valore.get(i).isInizializzato())
			str.append("    ").append(valore.get(i).toString()).append("\n");
		}
		return str.toString();
	}


	public void setValore(String valore) throws ParseException {

	}

}
