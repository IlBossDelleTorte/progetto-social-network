package versione_1;

import java.util.ArrayList;

public class Categoria {
	
	private String nome;
	private String descrizione;
	private ArrayList<Campo> campi;
	
	public Categoria (String nome,String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.campi=new ArrayList<Campo>();
		this.inizializzazioneStandard();
	}

	public void aggiungiCampo(Campo c) {
		campi.add(c);
	}
	
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
	
	public String toString() {
		String str="";
		for(Campo c:this.campi){
			str=c.toString()+"\n";
		}
		return str;
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
