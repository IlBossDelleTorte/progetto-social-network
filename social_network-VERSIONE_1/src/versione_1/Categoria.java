package versione_1;

import java.util.ArrayList;

public class Categoria {
	
	private String nome;
	private String descrizione;
	private ArrayList<Campo> campi;
	
	
	/**
	 * Costruttore della classe Categoria.
	 * All'interno viene richiamato il metodo inizializzazioneStandard() della stessa classe necessario per aggiungere il set base dei campi alla categoria
	 * @param nome
	 * @param descrizione
	 */
	public Categoria (String nome,String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.campi=new ArrayList<Campo>();
		this.inizializzazioneStandard();
	}
	
	/**
	 * Metodo che permette di aggiungere un campo all'elenco dei campi di una categoria 
	 * @param c campo da aggiungere
	 */
	public void aggiungiCampo(Campo c) {
		campi.add(c);
	}
	
	
	/**
	 * Metodo che aggiunge tutti i campi generali ad un oggetto di tipo Categoria
	 */
	public void inizializzazioneStandard() {
		campi.add(new Campo_Stringa("Titolo", "nome di fantasia attribuito all'evento",false));
		campi.add(new Campo_Numerico("Numero di partecipanti", "numero di persone da coinvolgere nell’evento", true));
		campi.add(new Campo_Data("Termine ultimo di iscrizione","ultimo giorno utile per iscriversi all’evento",true));
		campi.add(new Campo_Stringa("Luogo","l’indirizzo del luogo che ospiterà l’evento oppure il luogo di ritrovo dei partecipanti",true));
		campi.add(new Campo_Data("Data","data in cui l’evento proposto deve svolgersi o ha inizio",true));
		campi.add(new Campo_Ora("Ora","l’ora in cui i partecipanti dovranno ritrovarsi",true));
		campi.add(new Campo_Ora("Durata","Numero di ore e minuti di durata dell'evento",false));
		campi.add(new Campo_Numerico("Quota individuale","spesa che ogni partecipante dovrà sostenere",true));
		campi.add(new Campo_Stringa("Compreso nella quota","Elenco delle voci comporesi nella quota idividuale",false));
		campi.add(new Campo_Data("Data conclusiva","Data che fissa la conclusione dell'evento",false));
		campi.add(new Campo_Ora("Ora conclusiva","stima dell'ora conclusiva dell'evento",false));
		campi.add(new Campo_Stringa("Note","Informazioni aggiuntive",false));
	}
	
	/**
	 * Metodo che permette di inizializzare la categoria a partita di calcio
	 */
	public void partitaDiCalcio() {
		this.nome="Partita di calcio";
		this.descrizione="è una partita di calcio";
		campi.add(new Campo_Stringa("Genere", "Il genere dei partecipanti", true));
		campi.add(new Campo_Stringa("Fascia d'età", "Range d'età dei partecipanti", true));
		
	}
	
	/**
	 * Metodo toString di Categoria.
	 * Ad ogni linea della stringa viene aggiunto l'indice dell'ArrayList Campi utilizzato poi per la selezione degli stessi da linea di comando
	 */
	public String toString() {
		StringBuffer str = new StringBuffer("");
		int i=0;
		for(Campo c:this.campi){
			str.append(i+") "+c.toString()+"\n");
			i++;
		}
		return str.toString();
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
}
