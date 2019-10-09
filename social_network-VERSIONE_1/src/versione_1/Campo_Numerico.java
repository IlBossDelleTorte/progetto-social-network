package versione_1;

public class Campo_Numerico extends Campo {
	
	private float valore;
	public final float VALORE_NULLO=-1;
	
	/**
	 * Costruttore della classe Campo_Numerico.
	 * L'assegnazione di VALORE_NULLO a valore è usata per determinare se è stato assegnato un valore alla classe.
	 * @param nome
	 * @param descrizione
	 * @param obbligatorio
	 */
	public Campo_Numerico(String nome, String descrizione, boolean obbligatorio) {
		super(nome,descrizione,obbligatorio);
		valore=VALORE_NULLO;
	}
	
	public void setValore(float valore) {
		this.valore=valore;
	}
	
	public float getValore() {
		return valore;
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
	 * Il metodo richiama lo stesso della classe padre e nel caso in cui  il campo sia stato inizializzato appende alla stringa padre valore
	 */
	public String toString() {
		String str;
		str=super.toString();
		if(valore!=0)str=str+"\t"+valore;
		return str;
	}
	

}
