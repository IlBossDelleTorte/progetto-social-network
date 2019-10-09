package versione_1;

public class Campo_Ora extends Campo {
	private int ore;
	private int minuti;
	
	public Campo_Ora(String nome,String descrizione,boolean obbligatorio) {
		super(nome,descrizione,obbligatorio);
	}
	
	public void setOra(int ore,int minuti) {
		this.ore=ore;
		this.minuti=minuti;
	}
	
	public String toString() {
		return ore+":"+minuti;
	}

}
