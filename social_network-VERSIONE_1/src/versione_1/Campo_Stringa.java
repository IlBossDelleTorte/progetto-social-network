package versione_1;

public class Campo_Stringa extends Campo {
	
	private String valore;
	
	public Campo_Stringa (String nome, String descrizione, boolean obbligatorio) {
		super(nome,descrizione,obbligatorio);
	}
	
	public void setValore(String valore) {
		this.valore=valore;
	}
	
	public String getValore() {
		return valore;
	}
	public boolean isInizializzato(){
		if(valore!=null)return true;
		return false;
	}
	
	public String toString() {
		String str;
		str=super.toString();
		if(valore!=null)str=str+"\t"+valore;
		return str;
	}
	
}
