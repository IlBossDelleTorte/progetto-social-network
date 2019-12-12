package campo;

import java.io.Serializable;

import versione_5.Input;
import view.Costanti;

public class CampoStringa extends Campo implements Serializable {
	
	private String valore;
	public static final String VALORE_NULLO="*";
	
	
	/**
	 * Costruttore della classe Campo_Stringa
	 * @param nome
	 * @param descrizione
	 * @param obbligatorio
	 */
	public CampoStringa (String nome, String descrizione, boolean obbligatorio) {
		super(nome,descrizione,obbligatorio);
		valore=VALORE_NULLO;
	}
	
	public void setValore(String valore) {
		this.valore=valore;
	}
	
	public String getValore() {
		return valore;
	}
	
	/**
	 * Metodo con il quale è possibile sapere se il campo ha valore o meno.
	 * @return true se inizializzato false altrimenti 
	 */
	public boolean isInizializzato(){
		if(valore.trim()!=VALORE_NULLO)return true;
		return false;
	}
	
	
	
	/**
	 * Il metodo compila permette all'utente di assegnare il valore al campo mediante una procedura interattiva 
	 * per mezzo del metodo leggiStringa di Input
	 */
	public void compila() {
		System.out.print(this.toString()+Costanti.LINEA);
		String str=Costanti.COMPILAZIONE_STRINGA;
		if(this.isObbligatorio())str=str;
		else str=str+Costanti.FACOLTATIVO_STRINGA;
		this.valore=Input.leggiStringa(str,this.isObbligatorio());
	}
	
}
