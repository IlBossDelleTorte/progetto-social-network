package view;

public class Messaggi {
	public static final String ERRORE="ERRORE:Input non valido";
	public static final String ERRORE_CRITICO="Il programma è andato incontro ad un errore, riavviare l'applicazione.";
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

}
