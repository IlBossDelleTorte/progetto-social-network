package versione_2;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Campo_Data extends Campo implements Serializable {
	
	private Date valore;
	
	/**
	 * Costruttore della Classe Campo_Data
	 * @param nome
	 * @param descrizione
	 * @param obbligatorio
	 */
	public Campo_Data(String nome,String descrizione,boolean obbligatorio) {
		super(nome,descrizione,obbligatorio);
	}
	
	
	/**
	 * Metodo che imposta  valore attraverso una stringa formattata come indicato da Menu.FORMATO_DATA
	 */
	public void setValore(String data) throws ParseException {
		valore=Input.stringToDate(data);
		
	}
	
	/**
	 * Metodo che ritorna una stringa nel formato Menu.FORMATO_DATA che indica valore
	 */
	public String getValore() {
		return Input.dateToString(valore);
	}
	
	/**
	 * Metodo con il quale è possibile stabilire se è stato attribuito o meno un valore al campo
	 */
	public boolean isInizializzato() {
		if(valore!=null)return true;
		return false;
	}
	
	public String toString() {
		String str;
		str=super.toString();
		if(valore!=null)str=str+"\t"+getValore();
		return str;
	}
	
	/**
	 * Il metodo compila permette all'utente di assegnare il valore al campo mediante una procedura interattiva 
	 * per mezzo del metodo leggiData di Input
	 */
	
	public void compila() {
		System.out.print(this.toString()+Menu.LINEA);
		String str=Menu.COMPILAZIONE_DATA;
		if(this.isObbligatorio())str=str;
		else str=str+Menu.FACOLTATIVO_DATA;
		this.valore=Input.leggiData(str,this.isObbligatorio());
		
	}
	

}
