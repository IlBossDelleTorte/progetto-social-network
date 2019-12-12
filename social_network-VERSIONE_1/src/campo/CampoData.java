package campo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import versione_5.Input;
import view.Costanti;
import view.Messaggi;
import view.ObjectPrinter;


public class CampoData extends Campo implements Serializable {
	
	private Date valore;
	
	/**
	 * Costruttore della Classe Campo_Data
	 * @param nome
	 * @param descrizione
	 * @param obbligatorio
	 */
	public CampoData(String nome,String descrizione,boolean obbligatorio) {
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
	 * Metodo con il quale � possibile stabilire se � stato attribuito o meno un valore al campo
	 */
	public boolean isInizializzato() {
		if(valore!=null)return true;
		return false;
	}
	
	
	/**
	 * Il metodo compila permette all'utente di assegnare il valore al campo mediante una procedura interattiva 
	 * per mezzo del metodo leggiData di Input
	 */
	
	public void compila() {
		ObjectPrinter.stampaCampo(this);
		Messaggi.stampaLinea();
		String str=Costanti.COMPILAZIONE_DATA;
		if (!isObbligatorio())str=str+Costanti.FACOLTATIVO_DATA;
		this.valore=Input.leggiData(str,this.isObbligatorio());
		
	}
	

}
