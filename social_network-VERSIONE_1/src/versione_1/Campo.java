package versione_1;

public class Campo {
	
	private String nome;
	private String descrizione;
	private boolean obbligatorio = false;
	
	
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
	
	public String toString() {
		String str;
		str=this.nome+"\t"+this.descrizione;
		if(this.obbligatorio)str=str+"\t*"; //se il campo è obbligatorio viene posto un asterisco dopo la descrizione
		return str;
	}
	
}
