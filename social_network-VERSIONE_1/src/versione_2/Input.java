package versione_2;
import java.io.*;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
public class Input 
{
	public static Scanner input= new Scanner(System.in);
	public static final String ERRORE="Input non valido";
	//public static final String ERROR_NOT_FOUND="File non trovato";
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
			System.out.println(message);
			temp=input.nextLine();
			if(temp.length()>LUNGHEZZA_MASSIMA||temp.trim().length()==0||(temp.equals("*") && obbligatorio))System.out.println(ERRORE);
			else getInput=true;			
		}while(!getInput);
		return temp;
	}
	
	public static Date leggiData(String message,boolean obbligatorio) {
		String temp;
		boolean getInput=false;
		Date r=new Date();
		do {
			System.out.println(message);
			temp=input.nextLine();
			if(temp.trim().length()==0||(temp.equals("*")&& obbligatorio))System.out.println(ERRORE);
			else if(temp.equals("*")&& !obbligatorio)return null;
			else
			{
				SimpleDateFormat parser= new SimpleDateFormat("dd/MM/yyyy HH:mm");
				try {
					r=parser.parse(temp);
					getInput=true;
					System.out.println(r.toString());
				} catch (ParseException e) {
					getInput=false;
					System.out.println(ERRORE);
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
	public static float leggiNumerico(String message,boolean obbligatorio)
	{
		float temp=0;
		boolean getInput=false;
		do
		{
			System.out.println(message);
			try
			{
				temp=input.nextFloat();
				input.nextLine();
				getInput=true;
				if(temp<0 && temp!=-1 ||(temp==-1 && obbligatorio)) {
					getInput=false;
					System.out.println(ERRORE);
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println(ERRORE);
				String trash=input.next();
			}
		}while(!getInput);
		return temp;
	}
	
	
	public static void closeScanner()
	{
		input.close();
	}
	
	

}
