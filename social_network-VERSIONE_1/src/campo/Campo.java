package campo;

import java.io.Serializable;
import java.text.ParseException;

public abstract class Campo implements Serializable {
	
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
	
	public boolean isComposito() {
		return false;
	}
	
	public abstract void compila();
	
	public abstract String getValore();
	
	public abstract boolean isInizializzato();
	
	public abstract void setValore(String valore) throws ParseException;
	
}
