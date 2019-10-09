package versione_1;

public class Campo_Ora extends Campo {
	private int ore;
	private int minuti;
	public final int VALORE_NULLO=-1;
	
	
	/**
	 * Costruttore della classe Campo_Ora.
	 * L'assegnazione di VALORE_NULLO a valore è usata per determinare se è stato assegnato un valore alla classe.
	 * @param nome
	 * @param descrizione
	 * @param obbligatorio
	 */
	public Campo_Ora(String nome,String descrizione,boolean obbligatorio) {
		super(nome,descrizione,obbligatorio);
		ore=VALORE_NULLO;
		minuti=VALORE_NULLO;
	}
	
	public void setOra(int ore,int minuti) {
		this.ore=ore;
		this.minuti=minuti;
	}
	
	public String toString() {
		String str = super.toString();
		if(ore != VALORE_NULLO && minuti != VALORE_NULLO)
			str = str +"\t" + ore + ":" + minuti;
		return str;
	}
	
	/**
	 * Metodo con il quale è possibile sapere se il campo ha valore o meno.
	 * Si utilizza la costante VALORE_NULLO con valore negativo per premettere l'assegnazione dello zero al campo.
	 * @return true se inizializzato false altrimenti
	 */
	public boolean isInizializzato() {
		if(ore!=VALORE_NULLO && minuti!=VALORE_NULLO)return true;
		return false;
		
	}

}
