package versione_2;
import java.io.*;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
public class Input 
{
	public static Scanner input= new Scanner(System.in);
	public static final String ERRORE="Input non valido";
	public static final String ERROR_NOT_FOUND="File non trovato";
	
	
	
	public static String readPhrase(String message)
	{
		String temp;
		boolean getInput=false;
		do
		{
			System.out.println(message);
			temp=input.nextLine();
			if(temp.trim().length()==0)System.out.println(ERRORE);
			else getInput=true;			
		}while(!getInput);
		return temp;
	}
	
	public static String readLimitedPhrase(int n)
	{
		String temp;
		do
		{
		temp=input.nextLine();
		if(temp.length()>n||temp.trim().length()==0)System.out.println(ERRORE);
		}while(temp.length()>n);
		
		return temp;
	}
	
	public static int readInt(String message)
	{
		int temp=0;
		boolean getInput=false;
		do
		{
			System.out.println(message);
			try
			{
				temp=input.nextInt();
				getInput=true;
				
			}
			catch(InputMismatchException e)
			{
				System.out.println(ERRORE);
				String trash=input.next();
			}
		}while(!getInput);
		
		return temp;
	}
	
	public static int readInt()
	{
		int temp=0;
		boolean getInput=false;
		do
		{
			try
			{
				temp=input.nextInt();
				getInput=true;
			}
			catch(InputMismatchException e)
			{
				System.out.println(ERRORE);
			}
		}while(!getInput);
		
		return temp;
	}
	
	public static int readIntBetween(String message,int min,int max)
	{
		int temp;
		do
		{
		temp=Input.readInt(message);
		if(temp<min||temp>max)System.out.println(ERRORE);
		}while(temp<min||temp>max);
		
		return temp;		
	}
	
	
	public static float readFloat(String message)
	{
		float temp=0;
		boolean getInput=false;
		do
		{
			System.out.println(message);
			try
			{
				temp=input.nextFloat();
				getInput=true;
				
			}
			catch(InputMismatchException e)
			{
				System.out.println(ERRORE);
				String trash=input.next();
			}
		}while(!getInput);
		return temp;
	}
	

	public static int yesNo()
	{
		String choice;
		choice=input.next();
		switch(choice.toUpperCase().charAt(0))
		{		
		case('Y'):return 1;
		
		case('S'):return 1;
		
		case('N'):return -1;
		
		default:return 0;			
		}		
	}
	
	public static char readCharUpper()
	{		
		return Character.toUpperCase(input.next().charAt(0));
	}
	
	
	public static void closeScanner()
	{
		input.close();
	}
	
	public static Vector<String> readTextualfile(String path)
	{
		Scanner w = null;
		Vector <String> v=new Vector<>();
		boolean getInput;
		do
		{
			try
			{
				w=new Scanner(new FileReader(path));
				getInput=true;
			
				while(w.hasNextLine())
				{
					v.add(w.nextLine());
				}
			}
			catch(FileNotFoundException e)
			{
				System.out.println(ERROR_NOT_FOUND);
				getInput=false;
			}
		}while(!getInput);
		
		w.close();
		return v;
	}

}
