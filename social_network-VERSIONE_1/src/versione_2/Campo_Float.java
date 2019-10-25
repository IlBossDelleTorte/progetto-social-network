package versione_2;

import java.io.Serializable;

public class Campo_Float extends Campo implements Serializable {
	
	private float valore;
	public static final float VALORE_NULLO=-1;
	
	/**
	 * Costruttore della classe Campo_Float.
	 * L'assegnazione di VALORE_NULLO a valore è usata per determinare se è stato assegnato un valore alla classe.
	 * @param nome
	 * @param descrizione
	 * @param obbligatorio
	 */
	public Campo_Float(String nome, String descrizione, boolean obbligatorio) {
		super(nome,descrizione,obbligatorio);
		valore=VALORE_NULLO;
	}
	
	public void setValore(String s) {
		valore = Float.parseFloat(s);
	}
	
	public String getValore() {
		String str = String.format("%.2f", valore);
		return str;
	}
	
	/**
	 * Metodo con il quale è possibile sapere se il campo ha valore o meno.
	 * Si utilizza la costante VALORE_NULLO con valore negativo per premettere l'assegnazione dello zero al campo.
	 * @return true se inizializzato false altrimenti
	 */
	public boolean isInizializzato(){
		if(valore!=VALORE_NULLO)return true;
		return false;
	}
	
	/**
	 * Il metodo richiama lo stesso della classe padre e nel caso in cui  il campo sia stato inizializzato appende alla stringa padre valore
	 */
	public String toString() {
		String str;
		str=super.toString();
		if(valore!=VALORE_NULLO)str=str+"\t"+valore;
		return str;
	}
	
	/**
	 * Il metodo compila permette all'utente di assegnare il valore al campo mediante una procedura interattiva 
	 * per mezzo del metodo leggiNumerico di Input
	 */
	public void compila() {
		System.out.print(this.toString()+Menu.LINEA);
		String str=Menu.COMPILAZIONE_FLOAT;
		if(this.isObbligatorio())str=str+Menu.LINEA;
		else str=str+Menu.FACOLTATIVO_NUMERICO+Menu.LINEA;
		this.valore=Input.leggiFloat(str,this.isObbligatorio());
	}
	

}
