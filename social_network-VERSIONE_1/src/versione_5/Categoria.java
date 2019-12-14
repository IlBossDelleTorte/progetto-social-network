package versione_5;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

import campo.Campo;
import campo.CampoComposto;
import campo.CampoData;
import campo.CampoFloat;
import campo.CampoIntero;
import campo.CampoStringa;
import view.Costanti;

public abstract class  Categoria implements Serializable {
	
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
		this.nome=nome;
		this.descrizione=descrizione;
		this.campi=new ArrayList<Campo>();
		this.inizializzazione();
	}
	
	
	
	public  abstract boolean  haveOptionalChoice();
	
	/**
	 * Metodo che aggiunge tutti i campi generali ad un oggetto di tipo Categoria
	 */
	public void inizializzazione() {
		campi.add(new CampoStringa("Titolo", "Nome di fantasia attribuito all'evento",false));
		campi.add(new CampoIntero("Numero di partecipanti", "Numero di persone da coinvolgere nell’evento", true));
		campi.add(new CampoData("Termine ultimo di iscrizione","Ultimo giorno utile per iscriversi all’evento",true));
		campi.add(new CampoStringa("Luogo","L’indirizzo del luogo che ospiterà l’evento oppure il luogo di ritrovo dei partecipanti",true));
		campi.add(new CampoData("Data e Ora di inizio","Data e orario in cui l’evento proposto deve svolgersi o ha inizio",true));
		campi.add(new CampoFloat("Durata","Numero di ore e minuti di durata dell'evento",false));
		campi.add(new CampoFloat("Quota individuale","Spesa che ogni partecipante dovrà sostenere",true));
		campi.add(new CampoStringa("Compreso nella quota","Elenco delle voci comprese nella quota individuale",false));
		campi.add(new CampoData("Data e ora conclusiva","Data e orario che fissa la conclusione dell'evento",false));
		campi.add(new CampoIntero("Tolleranza partecipanti", "Quanti partecipanti sono accettabili in esubero rispetto al numero di partecipanti",false));
		campi.add(new CampoData("Termine ultimo ritiro iscrizione", "Data entro la quale è possibile rimuovere una iscrizione applicata",false));
		campi.add(new CampoStringa("Note","Informazioni aggiuntive",false));
	}
	
	
	
	
	public String getNome() {
		return nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}


	public ArrayList<Campo> getCampi() {
		return campi;
	}
	
	public String valoreDi(int i) {
		return campi.get(i).getValore();
	}
	
	public void impostaCampo(String s,int i) throws ParseException {
		campi.get(i).setValore(s);
	}
	
	public Campo getCampo(int i) {
		return campi.get(i);
	}

}
