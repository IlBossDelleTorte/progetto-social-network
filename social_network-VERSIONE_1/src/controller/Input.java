package controller;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import model.Costanti;
import view.Messaggi;


public class Input 
{
	public static Scanner input= new Scanner(System.in);
	public static SimpleDateFormat parser_data= new SimpleDateFormat(Costanti.FORMATO_DATA);
	public static final int LUNGHEZZA_MASSIMA=30;//lunghezza massima delle variabili di tipo stringa
	
	/**
	 * Metodo con il quale viene visualizzato un messaggio a video e poi letta una stringa da terminale
	 * @param message: messaggio che viene visualizzato
	 * @param obbligatorio: obligatorietà o meno della lettura (se non è obbligatoria può essere usato il carattere * per saltare la lettura)
	 * @return
	 */
	public static String leggiStringa(String message,boolean obbligatorio)
	{
		String temp;
		boolean getInput=false;
		do
		{
			Messaggi.stampa(message);
			temp=input.nextLine();
			if(temp.length()>LUNGHEZZA_MASSIMA||temp.trim().length()==0||(temp.equals("*") && obbligatorio))
				Messaggi.erroreInput();
			else getInput=true;			
		}while(!getInput);
		return temp;
	}
	
	/**
	 * Metodo con il quale viene visualizzato un messaggio a video e poi letta una stringa da terminale che viene poi parsata in un oggetto data da
	 * SimpleDataFormat
	 * @param message: messaggio che viene visualizzato
	 * @param obbligatorio: obligatorietà o meno della lettura (se non è obbligatoria può essere usato il carattere * per saltare la lettura)
	 * @return
	 */
	public static Date leggiData(String message,boolean obbligatorio) {
		String temp;
		boolean getInput=false;
		Date r=new Date();
		do {
			Messaggi.stampa(message);
			temp=input.nextLine();
			if(temp.trim().length()==0||(temp.equals("*")&& obbligatorio))
				Messaggi.erroreInput();
			else if(temp.equals("*")&& !obbligatorio)
				return null;
			else
			{
				try {
					r=parser_data.parse(temp);
					getInput=true;
					if(r.before(new Date()))
					{
						Messaggi.erroreInput();;
						getInput=false;
					}
				} catch (ParseException e) {
					getInput=false;
					Messaggi.erroreInput();;
				}
			}
		}while(!getInput);
		return r;
	}
	
	
	/**
	 * Metodo con il quale viene visualizzato un messaggio a video e poi letto un float da terminale
	 * @param message: messaggio che viene visualizzato
	 * @param obbligatorio: obbligatorietà della lettura (se non obbligatoria si può usare il valore -1 per saltare la lettura ) 
	 * @return
	 */
	public static float leggiFloat(String message,boolean obbligatorio)
	{
		float temp=0;
		boolean getInput=false;
		do
		{
			Messaggi.stampa(message);;
			try
			{
				temp=input.nextFloat();
				input.nextLine();
				getInput=true;
				if(temp<0 && temp!=-1 ||(temp==-1 && obbligatorio)) {
					getInput=false;
					Messaggi.erroreInput();;
				}
			}
			catch(InputMismatchException e)
			{
				Messaggi.erroreInput();;
				String trash=input.next();
			}
		}while(!getInput);
		return temp;
	}
	
	/**
	 * Metodo con il quale viene visualizzato un messaggio a video e poi letto un untero da terminale
	 * @param message: messaggio che viene visualizzato
	 * @param obbligatorio: obbligatorietà della lettura (se non obbligatoria si può usare il valore -1 per saltare la lettura ) 
	 * @return
	 */
	public static int leggiInt(String message,boolean obbligatorio)
	{
		int temp=0;
		boolean getInput=false;
		do
		{
			Messaggi.stampa(message);;
			try
			{
				temp=input.nextInt();
				input.nextLine();
				getInput=true;
				if(temp<0 && temp!=-1 ||(temp==-1 && obbligatorio)) {
					getInput=false;
					Messaggi.erroreInput();
				}
			}
			catch(InputMismatchException e)
			{
				Messaggi.erroreInput();
				String trash=input.next();
			}
		}while(!getInput);
		return temp;
	}
	
	/**
	 * Metodo con il quale viene letto un numero intero appartenenete ad un intervallo indicato 
	 * @param obbligatorio : boolean che  indica se la lettura è obbligatoria 
	 * @param min : estremo sinistro del range 
	 * @param max : estremo destro del range
	 * @return
	 */
	public static int leggiIntTra(boolean obbligatorio,int min,int max)
	{
		int temp=0;
		boolean getInput=false;
		do
		{
			try
			{
				temp=input.nextInt();
				input.nextLine();
				getInput=true;
				if((temp<min && temp!=-1)||temp>max||(temp==-1 && obbligatorio)) {
					getInput=false;
					Messaggi.erroreInput();;
				}
			}
			catch(InputMismatchException e)
			{
				Messaggi.erroreInput();;
				String trash=input.next();
			}
		}while(!getInput);
		return temp;
	}
	
	public static void closeScanner()
	{
		input.close();
	}
	
	/**
	 * Metodo che converte una data in una stringa nel formato dd/MM/yyyy HH:mm
	 * @param d data da convertire 
	 */
	public static String dateToString(Date d) {
		return parser_data.format(d);
		
	}
	
	/**
	 * Metodo che ritorna un oggetto Date parsato da una stringa nel formato dd/MM/yyyy HH:mm
	 * @param str stringa da parsare 
	 */
	public static Date stringToDate(String str)  {
		Date d = null;
		try {
				d = parser_data.parse(str);
		} catch (ParseException e) {
			System.out.println("Il programma è andato incontro ad un errore, riavviare l'applicazione.");
			System.exit(1);
			e.printStackTrace();
		}
		return d;
		
	}
	
	/**
	 * Metodo con il quale viene implementata la scelta S/N e stampato a video un messaggio
	 * @return 1 se si è selezionato S o Y, -1 se N e 0 in tutti gli altri casi 
	 */
	public static int yesNo(String message)
	{
		String choice;
		Messaggi.stampa(message);
		choice=input.next();
		switch(choice.toUpperCase().charAt(0))
		{		
		case('Y'):return 1;
		
		case('S'):return 1;
		
		case('N'):return -1;
		
		default:return 0;			
		}		
	}
	
	public static String leggiStringaFormattata(String message, String formato, boolean obbligatorio) {
		String temp;
		boolean getInput=false;
		do {
			Messaggi.stampa(message);;
			temp=input.nextLine();
			if(!temp.matches(formato))
				Messaggi.erroreInput();
			else
				getInput=true;
		}while(!getInput);
		return temp;
	}

	/**
	 * Il metodo accetta una stringa nel formato minuti,secondi e la somma a data inizio ritornando una stringa nel formato Costanti.FORMATO_DATA
	 * @param str
	 * @param data_inizio
	 * @return
	 */
	public static String sommaAData(String str, Date data_inizio) {
		Scanner s = new Scanner(str);
		s.useDelimiter(",");
		int ore = s.nextInt()*60;
		int min = s.nextInt();
		Calendar dataTerm = Calendar.getInstance();
		dataTerm.setTime(data_inizio);
		dataTerm.add(Calendar.MINUTE, ore+min);
		String val=dateToString(dataTerm.getTime());
		return val;
	}
	
	

}
