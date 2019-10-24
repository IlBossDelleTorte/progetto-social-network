package versione_2;

import java.io.Serializable;

public class Campo_Stringa extends Campo implements Serializable {
	
	private String valore;
	public static final String VALORE_NULLO="*";
	
	
	/**
	 * Costruttore della classe Campo_Stringa
	 * @param nome
	 * @param descrizione
	 * @param obbligatorio
	 */
	public Campo_Stringa (String nome, String descrizione, boolean obbligatorio) {
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
	 * Il metodo richiama lo stesso della classe padre e nel caso in cui  il campo sia stato inizializzato appende alla stringa padre valore
	 */
	public String toString() {
		String str;
		str=super.toString();
		if(!valore.trim().equals(VALORE_NULLO))str=str+"\t"+valore;
		return str;
	}
	
	/**
	 * Il metodo compila permette all'utente di assegnare il valore al campo mediante una procedura interattiva 
	 * per mezzo del metodo leggiStringa di Input
	 */
	public void compila() {
		System.out.print(this.toString()+Menu.LINEA);
		String str=Menu.COMPILAZIONE_STRINGA;
		if(this.isObbligatorio())str=str+Menu.LINEA;
		else str=str+Menu.FACOLTATIVO_STRINGA+Menu.LINEA;
		this.valore=Input.leggiStringa(str,this.isObbligatorio());
	}
	
}
