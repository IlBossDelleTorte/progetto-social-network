package versione_2;

import java.util.ArrayList;

public class Proposta {
	private Categoria categoria;
	private Stato stato;
	private ArrayList<Utente> partecipanti=new ArrayList<Utente>();
	private Utente creatore;
	
	public Proposta(Utente creatore) {
		categoria=new Categoria(" "," ");
		categoria.partitaDiCalcio();
		this.creatore=creatore;
		stato=Stato.VUOTA;
	}
	
	public void compilazione() {
		int i=1;
		ArrayList c=categoria.getCampi();
		while (i!=0 && i!=-1) {
			System.out.print(categoria.toString());
			i=Input.readInt(Menu.COMPILAZIONE);
			
			if(c.get(i) instanceof Campo_Numerico)
			{
				float f=Input.readFloat(c.get(i).toString()+Menu.COMPILAZIONE_FLOAT);
			}
		}
	}

}
