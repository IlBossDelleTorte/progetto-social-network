package versione_2;

public class Menu {
	
	public static String LINEA="\n______________________________________________________________________________________________\n";
	
	public static String COMPILAZIONE_FLOAT="Inserisci il valore numerico nel formato xxx,yyy";
	public static String COMPILAZIONE_INT="Inserisci il valore numerico intero";
	public static String COMPILAZIONE_STRINGA="Inserisci il valore del campo (non può superare i trenta caratteri)";
	public static String COMPILAZIONE_DATA="Inserisci la data e ora nel formato gg/mm/aaaa hh:mm";
	public static String FACOLTATIVO_STRINGA=" oppure * per saltare la compilazione di questo campo";
	public static String FACOLTATIVO_NUMERICO=" oppure -1 per saltare la compilazione di questo campo";
	public static String FACOLTATIVO_DATA=" oppure * per saltare la compilazione di questo campo";
	
	public static String ERRORE_DATA_INIZIO="La data di inizio non può essere precedente al termine ultimo di iscrizione";
	public static String ERRORE_DATA_FINE="La data di termine evento non può essere precedente alla data di inizio";
	
	public static String HEADER_BACHECA=String.format("%2s %10s %20s %14s %14s %10s %4s", "i","Creatore","Categoria","Data Evento","Scadenza"
			,"Luogo","Prezzo");
	
	public static String NOTIFICA_SUCCESSO="Uno degli eventi a cui sei iscritto è andato a buon fine, eccoti il resoconto\n"+HEADER_BACHECA+"\n";
	public static String NOTIFICA_FALLIMENTO="Uno degli eventi a cui sei iscritto è afallito, eccoti il resoconto\n"+HEADER_BACHECA+"\n";

}
