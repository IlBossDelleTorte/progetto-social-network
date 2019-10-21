package versione_2;

import java.text.ParseException;

public class Test {
	
	public static void main (String args[]) throws ParseException {
		Proposta p=new Proposta(new Utente());
		p.compilazione();
	/*	Campo_Stringa s=new Campo_Stringa("Luogo","L’indirizzo del luogo che ospiterà l’evento oppure il luogo di ritrovo dei partecipanti",true);
		Campo_Stringa s1=new Campo_Stringa("Luogo","L’indirizzo del luogo che ospiterà l’evento oppure il luogo di ritrovo dei partecipanti",false);
		s.compila();
		System.out.println(s.toString());
		s1.compila();
		System.out.println(s1.toString()); */
	} 

}
