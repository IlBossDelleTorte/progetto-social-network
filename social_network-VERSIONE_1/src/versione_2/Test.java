package versione_2;

public class Test {
	
	public static void main (String args[]) {
		/*Campo_Numerico c=new Campo_Numerico("Numero di partecipanti", "Numero di persone da coinvolgere nell�evento", true);
		c.compila();
		System.out.print(c.toString()+"\n");
		
		
		Campo_Stringa s=new Campo_Stringa("Titolo", "Nome di fantasia attribuito all'evento",true);
		s.compila();
		System.out.print(s.toString());
		*/
		
		Campo_Data d=new Campo_Data("Data e Ora di inizio","Data e orario in cui l�evento proposto deve svolgersi o ha inizio",true);
		d.compila();
		System.out.print(d.toString()+"\n");
		
	}

}
