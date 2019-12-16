package view;

import model.Utente;

public class Messaggi {
	public static final String ERRORE="ERRORE:Input non valido";
	public static final String ERRORE_CRITICO="Il programma � andato incontro ad un errore, riavviare l'applicazione.";
	public static final String IO_ERROR="Errore di input/output";
	public static final String FILE_NOT_FOUND="File non trovato";
	public static final String ERROR="Errore";
	
	public static void stampa(String m) {
		System.out.println(m);
	}
	
	public static void erroreInput() {
		System.out.println(ERRORE);
	}
	
	public static void erroreCritico() {
		System.out.println(ERRORE_CRITICO);
	}

	public static void erroreIO() {
		System.out.println(Messaggi.IO_ERROR);
	}

	

	public static void erroreFile() {
		System.out.println(FILE_NOT_FOUND);
	}

	public static void errore() {
		System.out.println(ERROR);
	}
	
	public static void stampaLinea() {
		System.out.print(Costanti.LINEA);
	}

	public static void erroreDataInizio() {
		System.out.println(Costanti.ERRORE_DATA_INIZIO);
	}

	public static void errroreDataFine() {
		System.out.println(Costanti.ERRORE_DATA_FINE);
	}

	public static void erroreGenere() {
		System.out.print(Costanti.ERRORE_GENERE);
	}

	public static void compilazioneEffettuata() {
		System.out.println(Costanti.COMPILAZIONE_EFFETTUATA);
	}

	public static void creazioneUtente(Utente utente) {
		System.out.println(Costanti.SUCCESSO_CREAZIONE);
		ObjectPrinter.stampaUtente(utente);
	}

	public static void aggiornamentoDati() {
		System.out.print("ECCO I DATI AGGIORNATI:\n");
	}

	public static void messaggioSuccessoRitiro() {
		System.out.print(Costanti.MESSAGGIO_RITIRO);
	}

	public static void messaggioNonRitirabile() {
		System.out.print(Costanti.MESSAGGIO_NON_RITIRABILE);
	}

	public static void messaggioDisiscrizione() {
		System.out.print(Costanti.MESSAGGIO_DISISCRIZIONE);
	}

}