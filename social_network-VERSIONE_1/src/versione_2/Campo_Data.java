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
	 * Setter di valore.
	 * Fa uso di un oggetto della classe Calendar il quale a sua volta genere un oggetto di Date contenente il valore assegnato al campo
	 * @param anno
	 * @param mese
	 * @param giorno
	 * @throws ParseException 
	 */
	public void setValore(String data) throws ParseException {
		SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		valore=parser.parse(data);
		
	}
	
	public String getValore() {
		return Input.dateToString(valore);
		/*StringBuffer str = new StringBuffer();
		str.append(c.get(Calendar.DAY_OF_MONTH)+"/");
		str.append(c.get(Calendar.MONTH)+"/");
		str.append(c.get(Calendar.YEAR)+" ");
		str.append(c.get(Calendar.HOUR_OF_DAY)+":");
		str.append(c.get(Calendar.MINUTE));
		return str.toString();*/
	}//il metodo non ritorna l'oggetto data bensì una stringa formattata "dd/MM/yyyy HH:mm"
	
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
		if(this.isObbligatorio())str=str+Menu.LINEA;
		else str=str+Menu.FACOLTATIVO_DATA+Menu.LINEA;
		this.valore=Input.leggiData(str,this.isObbligatorio());
		
	}
	

}
