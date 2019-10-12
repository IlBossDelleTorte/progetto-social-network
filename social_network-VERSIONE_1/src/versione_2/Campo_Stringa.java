package versione_2;

public class Campo_Stringa extends Campo {
	
	private String valore;
	
	
	/**
	 * Costruttore della classe Campo_Stringa
	 * @param nome
	 * @param descrizione
	 * @param obbligatorio
	 */
	public Campo_Stringa (String nome, String descrizione, boolean obbligatorio) {
		super(nome,descrizione,obbligatorio);
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
		if(valore!=null)return true;
		return false;
	}
	
	/**
	 * Il metodo richiama lo stesso della classe padre e nel caso in cui  il campo sia stato inizializzato appende alla stringa padre valore
	 */
	public String toString() {
		String str;
		str=super.toString();
		if(valore!=null)str=str+"\t"+valore;
		return str;
	}
	
}
