package versione_5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import view.Messaggi;

public class IOFile {
	/**
	 * Metodo con il quale è possibile se i dati di salvataggio del programma esistono o meno, il controllo viene fatto nella stessa cartella
	 * in cui è presente l'eseguibile del programma 
	 */
	public static boolean esistenzaDati(String name)
	{
		File f=new File(".",name);
		return f.exists();
	}
	
	/**
	 * Metodo che crea il salvataggio dei dati del programma nella cartella in cui è posizionato l'eseguibile 
	 */
	public static void creaDati(String name) {
		File f=new File(".",name);
		try {
			f.createNewFile();
		} catch (IOException e) {
			Messaggi.erroreIO();;
		}
	}
	
	/**
	 * Metodo che ritorna l'oggetto dei dati di salvataggio del programma 
	 * @return leggiDati ritorna un oggetto di Object, è quindi necessario fare un casting alla classe rappresentate (nel nostro caso a 
	 * ContainerDati)
	 */
	public static Object leggiDati(String name)
	{
		File f=new File(".",name);
		ObjectInputStream in;;
		Object o=null;
		try
		{
			in=new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			o=in.readObject();
			in.close();			
		}
		catch(FileNotFoundException fnf)
		{
			Messaggi.erroreFile();
		}
		catch(IOException io)
		{
			Messaggi.erroreIO();
		}
		catch(Exception e)
		{
			Messaggi.errore();
		}
		return o;

	}

	/**
	 * Metodo con il quale vengono salvati i dati del programma
	 * @param name: nome del file di salvataggio
	 * @param thing: classe container da salvare
	 */
	public static void salvaDati(String name,Object thing) 
	{		
		try
		{
			File file=new File(".",name);
			file.createNewFile();
			ObjectOutputStream o =new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			o.writeObject(thing);
			o.close();
		}
		catch(IOException io)
		{
			Messaggi.erroreIO();
		}
	}

}
