package campo;

import java.io.Serializable;

import versione_5.Input;
import view.Costanti;

public class CampoFloat extends Campo implements Serializable {
	
	private float valore;
	public static final float VALORE_NULLO=-1;
	
	/**
	 * Costruttore della classe Campo_Float.
	 * L'assegnazione di VALORE_NULLO a valore è usata per determinare se è stato assegnato un valore alla classe.
	 * @param nome
	 * @param descrizione
	 * @param obbligatorio
	 */
	public CampoFloat(String nome, String descrizione, boolean obbligatorio) {
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
	 * Il metodo compila permette all'utente di assegnare il valore al campo mediante una procedura interattiva 
	 * per mezzo del metodo leggiNumerico di Input
	 */
	public void compila() {
		System.out.print(this.toString()+Costanti.LINEA);
		String str=Costanti.COMPILAZIONE_FLOAT;
		if(this.isObbligatorio())str=str;
		else str=str+Costanti.FACOLTATIVO_NUMERICO;
		this.valore=Input.leggiFloat(str,this.isObbligatorio());
	}
	

}
