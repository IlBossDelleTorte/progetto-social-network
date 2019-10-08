package versione_1;

import java.util.Calendar;
import java.util.Date;


public class Campo_Data extends Campo {
	
	private Date valore;

	public Campo_Data(String nome,String descrizione,boolean obbligatorio) {
		super(nome,descrizione,obbligatorio);
	}
	
	public void setValore(int anno,int mese, int giorno) {
		Calendar calendar=Calendar.getInstance();//viene istanziato un oggetto calendar con la data odierna
		calendar.set(anno,mese,giorno);//l'oggetto calendar viene settato al valore desiderato 
		valore=calendar.getTime();
		
	}
	
	public String getValore() {
		return valore.toString();
	}//il metodo non ritorna l'oggetto data bensì il suo metodo toString
	
	public boolean isInizializzato() {
		if(valore!=null)return true;
		return false;
	}
	

}
