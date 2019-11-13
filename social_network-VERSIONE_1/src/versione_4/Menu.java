package versione_4;

public class Menu {
	
	//Utilities
	public static String creaLinea() {
		StringBuffer linea = new StringBuffer();
		linea.append("\n");
		for(int i=0; i < 150; i++) {
			linea.append("_");
		}
		linea.append("\n");
		return linea.toString();
	}
	
	public static String LINEA=creaLinea();

	
	//Stringhe per il formato dei dati
	public static String FORMATO_DATA="dd/MM/yyyy HH:mm";
	public static String FORMATO_LOG="%-20s %-12s %-24s %2d";
	public static String FORMATO_RANGE="([1-9][0-9]-[1-9][0-9])|([1-9]-[1-9][0-9])|([1-9]-[1-9])";
	
	//Stringhe della fase di compilazione
	public static String COMPILAZIONE_FLOAT="INSERISCI IL VALORE NUMERICO NEL FORMATO XXX,YY";
	public static String COMPILAZIONE_INT="INSERISCI IL VALORE NUMERICO INTERO";
	public static String COMPILAZIONE_STRINGA="INSERISCI IL VALORE DEL CAMPO (NON PUÒ SUPERARE I TRENTA CARATTERI)";
	public static String COMPILAZIONE_DATA="INSERISCI LA DATA E ORA NEL FORMATO gg/mm/aaaa hh:mm";
	public static String COMPILAZIONE_EFFETTUATA="\nLA PROPOSTA È STATA AGGIUNTA CON SUCCESSO ALLA TUA LISTA DI PROPOSTE VALIDE!";
	
	//Stringhe dei campi facoltativi 
	public static String FACOLTATIVO_STRINGA=" OPPURE * PER SALTARE LA COMPILAZIONE DI QUESTO CAMPO";
	public static String FACOLTATIVO_NUMERICO=" OPPURE -1 PER SALTARE LA COMPILAZIONE DI QUESTO CAMPO";
	public static String FACOLTATIVO_DATA=" OPPURE * PER SALTARE LA COMPILAZIONE DI QUESTO CAMPO";
	
	//Stringhe di errore nella compilazione
	public static String ERRORE_DATA_INIZIO="LA DATA DI INIZIO NON PUÒ ESSERE PRECEDENTE AL TERMINE ULTIMO DI ISCRIZIONE";
	public static String ERRORE_DATA_FINE="LA DATA DI TERMINE EVENTO NON PUÒ ESSERE PRECEDENTE ALLA DATA DI INIZIO";
	public static String ERRORE_TERMINE_RITIRO = "LA DATA DI RITIRO NON PUO' SEGUIRE QUELLA DI ULTIMA ISCRIZIONE";
	//Stringhe della bacheca
	public static String MESSAGGIO_BACHECA =LINEA+"SELEZIONA UNA PROPOSTA CON IL NUMERO DI INDICE PER VISUALIZZARE I DETTAGLI ED EVENTUALMENTE ISCRIVERTI\n"
			+ "OPPURE 0 PER TORNARE INDIETRO";
	public static String HEADER_BACHECA=String.format("%-2s %-20s %-30s %-24s %-24s %-30s %-14s", "i","Creatore","Categoria","Data Evento","Scadenza"
			,"Luogo","Prezzo");
	public static String ISCRIZIONE_PROPOSTA=LINEA+"VUOI ISCRIVERTI ALLA PROPOSTA? (S/Y PER ACCETTARE N PER RIFIUTARE)\n";
	public static String ISCRIZIONE_RIDONDANTE="SEI GIÀ ISCRITTO A QUESTA PROPOSTA"+LINEA;
	public static String ISCRIZIONE_PIENA="LA PROPOSTA A CUI TI VUOI ISCRIVERE E' PIENA\n";
	public static String ISCRIZIONE_EFFETTUATA="ISCRIZIONE EFFETTUATA!\n";
	public static String PROPOSTA_CHIUSA="LA PROPOSTA È GIÀ CHIUSA E NON VI SI PUÒ ISCRIVERE.\n";
	public static String PROPOSTE_APERTE_VUOTO="NON È PRESENTE ALCUNA PROPOSTA APERTA NELLA BACHECA\n";
	
	//Stringhe del menu delle proposte
	public static String MESSAGGIO_SELEZIONE_PROPOSTA =LINEA+"SELEZIONA UNA PROPOSTA CON IL NUMERO DI INDICE PER VISUALIZZARE I DETTAGLI \n"
			+ "OPPURE 0 PER TORNARE INDIETRO";
	public static String GESTIONE_PROPOSTA_RITIRO=LINEA+"COSA VUOI FARE? \n"
			+ "1) RITIRA LA PROPOSTA \n"
			+ "0) TORNA INDIETRO";
	public static String GESTIONE_RITIRO_ISCRIZIONE=LINEA+"COSA VUOI FARE? \n"
			+ "1) CANCELLA L'ISCRIZIONE DALLA PROPOSTA\n"
			+ "0) TORNA INDIETRO";
	public static String GESTIONE_PROPOSTA_VALIDA=LINEA+"COSA VUOI FARE?\n"
			+ "1) PUBBLICA LA PROPOSTA\n"
			+ "2) ELIMINA LA PROPOSTA\n"
			+ "0) TORNA INDIETRO";
	public static String PUBBLICAZIONE_EFFETTUATA="LA PROPOSTA È STATA PUBBLICATA CON SUCCESSO.\n";
	public static String PROPOSTE_VALIDE_VUOTO = "NON È PRESENTE ALCUNA PROPOSTA VALIDA\n";
	public static String ELENCO_ISCRIZIONE_VUOTO="NON SEI ISCRITTO AD ALCUNA PROPOSTA CHE NON SIA TUA\n";
	public static String ELENCO_CREAZIONI_VUOTO="NON HAI PUBBLICATO NESSUNA PROPOSTA\n";
	public static String MESSAGGIO_DISISCRIZIONE="HAI CANCELLATO L'ISCRIZIONE CON SUCCESSO\n";
	public static String MESSAGGIO_RITIRO="HAI RITIRATO LA PROPOSTA CON SUCCESSO\n";
	public static String ELENCO_VUOTO = "NON E' PRESENTE ALCUNA PROPOSTA IN QUESTA LISTA.\n";
	
	
	//Notifiche inviate automaticamente
	public static String NOTIFICA_SUCCESSO="Uno degli eventi a cui sei iscritto è andato a buon fine, eccoti il resoconto\n\t"+HEADER_BACHECA+"\n";
	public static String NOTIFICA_FALLIMENTO="Uno degli eventi a cui sei iscritto è fallito, eccoti il resoconto\n\t"+HEADER_BACHECA+"\n";
	public static String NOTIFICA_RITIRO="Il creatore di uno degli eventi a cui sei iscritto ha ritirato la proposta, eccoti il resoconto\n\t"+HEADER_BACHECA+"\n";
	public static String NOTIFICA_PROPOSTA_AFFINE="E' stata pubblicata una nuova proposta affine ai tuoi interessi, visualizzala nel tuo spazio personale\n";
	public static String NOTIFICA_INVITO="Hai ricevuto un'invito da %s riguardo ad una proposta della categoria %s. Vai nel tuo spazio personale\n"
			+ "per visualizzarla\n";
	//Menu vari
	public static String MENU_INIZIALE=LINEA+"COSA VUOI FARE?\n"
			+ "1) VISUALIZZA BACHECA\n"
			+ "2) GESTISCI PROPOSTE\n"
			+ "3) ACCEDI ALLO SPAZIO PERSONALE\n"
			+ "0) SALVA E ESCI";
	public static String MENU_PROPOSTE=LINEA+"COSA VUOI FARE?\n"
			+ "1) COMPILA NUOVA PROPOSTA\n"
			+ "2) VISUALIZZA LE PROPOSTE VALIDE \n"
			+ "3) VISUALIZZA LE ISCRIZIONI \n"
			+ "4) VISUALIZZA LE TUE PROPOSTE PUBBLICATE \n"
			+ "0) SALVA E ESCI";
	public static String MENU_NOTIFICHE=LINEA+"SELEZIONA UNA NOTIFICA CON IL NUMERO DI INDICE PER RIMUOVERLA OPPURE 0 PER TORNARE INDIETRO";
	public static String MENU_SPAZIO_PERSONALE=LINEA+"COSA VUOI FARE?\n"
			+ "1) VISUALIZZA NOTIFICHE\n"
			+ "2) MODIFICA I DATI PERSONALI\n"
			+ "3) VISUALIZZA LE TUE PROPOSTE AFFINI\n"
			+ "4) VISUALIZZA GLI INVITI\n"
			+ "0) SALVA E ESCI";
	
	public static String SPAZIO_PERSONALE_VUOTO="NON HAI NESSUNA NUOVA NOTIFICA\n";
	public static String RIMOZIONE_NOTIFICA="VUOI RIMUOVERE LA NOTIFICA SELEZIONATA? (S/Y PER ACCETTARE N PER RIFIUTARE)";
	public static String UTENTE="INSERISCI IL TUO NOME UTENTE..\n";
	public static String MESSAGGIO_RANGE=LINEA+"SELEZIONA IL RANGE DI ETA' A CUI APPARTIENI CON L'INDICE OPPURE -1 PER SLATARE LA COMPILAZIONE\n";
	public static String MESSAGGIO_INTERESSI=LINEA+"SELEZIONA LE TUE CATEGORIE DI INTERESSE CON L'INDICE (UNA PER VOLTA) PREMI -1 PER TERMINARE LA SELEZIONE\n";
	public static String MESSAGGIO_INVITI=LINEA+"IN QUESTA SEZIONE PUOI INVITARE GLI UTENTI CHE HANNO GIA' PARTECIPATO AD UN TUO EVENTO\n"
			+ "SELEZIONA GLI UTENTI DA INVITARE CON L'INDICE (UNO PER VOLTA) PREMI -1 PER TERMINARE LA SELEZIONE\n";
	public static String DATI="DATA4.save";

	
	public static int INDICE_DATA_INIZIO = 4;
	public static int INDICE_DATA_FINE = 8;
	public static int INDICE_DURATA = 5;
	public static int INDICE_SCADENZA_ISCRIZIONE = 2;
	public static int INDICE_PARTECIPANTI = 1;
	public static int INDICE_TERMINE_RITIRO = 10;
	public static int INDICE_TOLLERANZA_PARTECIPANTI = 9;
	
	public static String[] ELENCO_CATEGORIE= {"Partita di Calcio"};
	
	
}
