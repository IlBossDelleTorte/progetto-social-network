package versione_5;

import java.io.Serializable;

public class Campo_Intero extends Campo implements Serializable {
	private int valore;
	public final int VALORE_NULLO=-1;
	
	
	/**
	 * Costruttore della classe Campo_Ora.
	 * L'assegnazione di VALORE_NULLO a valore è usata per determinare se è stato assegnato un valore alla classe.
	 * @param nome
	 * @param descrizione
	 * @param obbligatorio
	 */
	public Campo_Intero(String nome,String descrizione,boolean obbligatorio) {
		super(nome,descrizione,obbligatorio);
		valore=VALORE_NULLO;
	}
	

	public String toString() {
		String str = super.toString();
		if(valore!=VALORE_NULLO)str=str+"\t"+valore;
		return str;
	}
	
	/**
	 * Metodo con il quale è possibile sapere se il campo ha valore o meno.
	 * Si utilizza la costante VALORE_NULLO con valore negativo per premettere l'assegnazione dello zero al campo.
	 * @return true se inizializzato false altrimenti
	 */
	public boolean isInizializzato() {
		if(valore!=VALORE_NULLO)return true;
		return false;
		
	}
	
	/**
	 * Il metodo compila permette all'utente di assegnare il valore al campo mediante una procedura interattiva 
	 * per mezzo del metodo leggiInt di Input
	 */
	public void compila() {
		System.out.print(this.toString()+Menu.LINEA);
		String str=Menu.COMPILAZIONE_INT;
		if(this.isObbligatorio())str=str;
		else str=str+Menu.FACOLTATIVO_NUMERICO;
		this.valore=Input.leggiInt(str,this.isObbligatorio());
	}
	
	public String getValore() {
		String str = String.format("%d", valore);
		return str;
	}
	
	public void setValore(String s) {
		valore = Integer.parseInt(s);
	}

}
