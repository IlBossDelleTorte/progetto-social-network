package versione_1;

public class Campo_Numerico extends Campo {
	
	private float valore;
	
	public Campo_Numerico(String nome, String descrizione, boolean obbligatorio) {
		super(nome,descrizione,obbligatorio);
	}
	
	public void setValore(float valore) {
		this.valore=valore;
	}
	
	public float getValore() {
		return valore;
	}
	public boolean isInizializzato(){
		if(valore!=0)return true;//per il float non esiste null e quindi c'è bisogno di trovare un metodo agguntivo, per il momento viene comparato con zero 
		return false;
	}
	
	public String toString() {
		String str;
		str=super.toString();
		if(valore!=0)str=str+"\t"+valore;
		return str;
	}
	

}
