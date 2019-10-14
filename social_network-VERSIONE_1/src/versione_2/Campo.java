package versione_2;

public abstract class Campo {
	
	private String nome;
	private String descrizione;
	private boolean obbligatorio = false;
	
	
	
	/**
	 * Costruttore per la classe Campo
	 * @param nome
	 * @param descrizione
	 * @param obbligatorio
	 */
	public Campo(String nome, String descrizione, boolean obbligatorio) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.obbligatorio = obbligatorio;
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public boolean isObbligatorio() {
		return obbligatorio;
	}

	public void setObbligatorio(boolean obbligatorio) {
		this.obbligatorio = obbligatorio;
	}
	
	/**
	 * Metodo toString di un Campo che ritorna una stringa nome descrizione ed eventualmente un asterisco che indica l'obbligatorietà del campo stesso
	 */
	public String toString() {
		String str = String.format("%-30s %-100s", this.nome, this.descrizione);
		if(this.obbligatorio)str=str+"\t*"; //se il campo è obbligatorio viene posto un asterisco dopo la descrizione
		return str;
	}
	
	public abstract void compila();
	
}
