package versione_2;

import java.text.ParseException;
import java.util.ArrayList;

public class Campo_Composto extends Campo {

	private ArrayList<Campo> valore;
	
	public Campo_Composto(String nome,String descrizione,boolean obbligatorio,ArrayList<Campo>valore) {
		super(nome,descrizione,obbligatorio);
		this.valore=valore;
	}
	
	public void compila() {
		System.out.print(String.format("%-35s %-100s", this.getNome(), this.getDescrizione())+Menu.COMPILAZIONE_COMPOSTO);
		valore.forEach(c->c.compila());

	}
	
	public ArrayList<Campo> getElencoCampi()
	{
		return valore;
	}

	
	public String getValore() {
		StringBuffer str=new StringBuffer();
		for(int i=0;i<valore.size();i++) {
			str.append(valore.get(i).getValore()).append("|");
		}
		return str.toString();
	}

	
	public boolean isInizializzato() {
		boolean r=false;
		for(int i=0;i<valore.size();i++) {
			if (valore.get(i).isInizializzato()) r=true;
		}
		return r;
	}
	
	public String toString() {
		StringBuffer str=new StringBuffer(super.toString());
		str.append("\n");
		for(int i=0;i<valore.size();i++) {
			if(valore.get(i).isInizializzato())
			str.append("    ").append(valore.get(i).toString()).append("\n");
		}
		return str.toString();
	}

	@Override
	public void setValore(String valore) throws ParseException {
		// TODO Auto-generated method stub

	}

}
