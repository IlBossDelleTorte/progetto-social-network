package versione_2;

import java.util.Calendar;
import java.util.Date;


public class Campo_Data extends Campo {
	
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
	 */
	public void setData(int anno,int mese, int giorno,int ore,int minuti) {
		Calendar calendar=Calendar.getInstance();//viene istanziato un oggetto calendar con la data odierna
		calendar.set(anno,mese,giorno,ore,minuti);//l'oggetto calendar viene settato al valore desiderato 
		valore=calendar.getTime();
		
	}
	
	public String getData() {
		return valore.toString();
	}//il metodo non ritorna l'oggetto data bensì il suo metodo toString
	
	public boolean isInizializzato() {
		if(valore!=null)return true;
		return false;
	}
	
	public String toString() {
		String str;
		str=super.toString();
		if(valore!=null)str=str+"\t"+valore;
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
