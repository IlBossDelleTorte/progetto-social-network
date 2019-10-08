package versione_1;

public abstract class Campo {
	
	private String nome;
	private String descrizione;
	private boolean obbligatorio = false;
	private boolean inizializzato = false;
	
	public Campo(String nome, String descrizione, boolean obbligatorio) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.obbligatorio = obbligatorio;
		inizializzato = true;
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
	
	public boolean isInizializzato() {
		return inizializzato;
	}
	
	public abstract String toString();
	
	
	
}
