package versione_2;

public class Menu {
	
	//Utilities
	public static String LINEA="\n_____________________________________________________________________________________________________________________________________________________________________________________\n";
	
	//Stringhe per il formato dei dati
	public static String FORMATO_DATA="dd/MM/yyyy HH:mm";
	public static String FORMATO_LOG="%-20s %-12s %-24s %2d";
	
	//Stringhe della fase di compilazione
	public static String COMPILAZIONE_FLOAT="INSERISCI IL VALORE NUMERICO NEL FORMATO XXX,YY";
	public static String COMPILAZIONE_INT="INSERISCI IL VALORE NUMERICO INTERO";
	public static String COMPILAZIONE_STRINGA="INSERISCI IL VALORE DEL CAMPO (NON PUÒ SUPERARE I TRENTA CARATTERI)";
	public static String COMPILAZIONE_DATA="INSERISCI LA DATA E ORA NEL FORMATO gg/mm/aaaa hh:mm";
	public static String COMPILAZIONE_EFFETTUATA="LA PROPOSTA È STATA AGGIUNTA CON SUCCESSO ALLA TUA LISTA DI PROPOSTE VALIDE!";
	
	//Stringhe dei campi facoltativi 
	public static String FACOLTATIVO_STRINGA=" OPPURE * PER SALTARE LA COMPILAZIONE DI QUESTO CAMPO";
	public static String FACOLTATIVO_NUMERICO=" OPPURE -1 PER SALTARE LA COMPILAZIONE DI QUESTO CAMPO";
	public static String FACOLTATIVO_DATA=" OPPURE * PER SALTARE LA COMPILAZIONE DI QUESTO CAMPO";
	
	//Stringhe di errore nella compilazione
	public static String ERRORE_DATA_INIZIO="LA DATA DI INIZIO NON PUÒ ESSERE PRECEDENTE AL TERMINE ULTIMO DI ISCRIZIONE";
	public static String ERRORE_DATA_FINE="LA DATA DI TERMINE EVENTO NON PUÒ ESSERE PRECEDENTE ALLA DATA DI INIZIO";
	
	//Stringhe della bacheca
	public static String MESSAGGIO_BACHECA =LINEA+"SELEZIONA UNA PROPOSTA CON IL NUMERO DI INDICE PER VISUALIZZARE I DETTAGLI ED EVENTUALMENTE ISCRIVERTI\n"
			+ "OPPURE 0 PER TORNARE INDIETRO";
	public static String HEADER_BACHECA=String.format("%-2s %-20s %-30s %-24s %-24s %-20s %-14s", "i","Creatore","Categoria","Data Evento","Scadenza"
			,"Luogo","Prezzo");
	public static String ISCRIZIONE_PROPOSTA="VUOI ISCRIVERTI ALLA PROPOSTA? (S/Y PER ACCETTARE N PER RIFIUTARE)"+LINEA;
	public static String ISCRIZIONE_RIDONDANTE="SEI GIÀ ISCRITTO A QUESTA PROPOSTA";
	public static String ISCRIZIONE_EFFETTUATA="ISCRIZIONE EFFETTUATA!";
	public static String PROPOSTA_CHIUSA="LA PROPOSTA È GIÀ CHIUSA E NON VI SI PUÒ ISCRIVERE.";
	//Stringhe del menu delle proposte
	public static String MESSAGGIO_PUBBLICAZIONE ="SELEZIONA UNA PROPOSTA CON IL NUMERO DI INDICE PER VISUALIZZARE I DETTAGLI E PUBBLICARLA O CANCELLARLA\n"
			+ "OPPURE 0 PER TORNARE INDIETRO"
			+LINEA;
	public static String GESTIONE_PROPOSTA="COSA VUOI FARE?\n"
			+ "1) PUBBLICA LA PROPOSTA\n"
			+ "2) ELIMINA LA PROPOSTA\n"
			+ "0) TORNA INDIETRO";
	public static String PUBBLICAZIONE_EFFETTUATA="LA PROPOSTA È STATA PUBBLICATA CON SUCCESSO.\n";
	
	
	//Notifiche inviate automaticamente
	public static String NOTIFICA_SUCCESSO="Uno degli eventi a cui sei iscritto è andato a buon fine, eccoti il resoconto\n\t"+HEADER_BACHECA+"\n";
	public static String NOTIFICA_FALLIMENTO="Uno degli eventi a cui sei iscritto è fallito, eccoti il resoconto\n\t"+HEADER_BACHECA+"\n";
	
	//Menu vari
	public static String MENU_INIZIALE="COSA VUOI FARE?\n"
			+ "1) VISUALIZZA BACHECA\n"
			+ "2) GESTISCI PROPOSTE\n"
			+ "3) ACCEDI ALLO SPAZIO PERSONALE\n"
			+ "0) SALVA E ESCI"+LINEA;
	public static String MENU_PROPOSTE="COSA VUOI FARE?\n"
			+ "1) COMPILA NUOVA PROPOSTA\n"
			+ "2) VISUALIZZA LE PROPOSTE VALIDE \n"
			+ "0) SALVA E ESCI"+LINEA;
	public static String MENU_SPAZIO_PERSONALE="SELEZIONA UNA NOTIFICA CON IL NUMERO DI INDICE PER RIMUOVERLA OPPURE 0 PER TORNARE INDIETRO"+LINEA;
	
	
	public static String RIMOZIONE_NOTIFICA="VUOI RIMUOVERE LA NOTIFICA SELEZIONATA? (S/Y PER ACCETTARE N PER RIFIUTARE)"+LINEA;
	public static String UTENTE="INSERISCI IL TUO NOME UTENTE..\n";
	public static String DATI="DATA.save";

	
}
