package versione_3;

import java.io.Serializable;

public class ContainerDati implements Serializable {
	private Bacheca bacheca;
	private ListaUtenti listaUtenti;
	
	public ContainerDati(Bacheca b,ListaUtenti l) {
		bacheca=b;
		listaUtenti=l;
	}

	public Bacheca getBacheca() {
		return bacheca;
	}

	public void setBacheca(Bacheca bacheca) {
		this.bacheca = bacheca;
	}

	public ListaUtenti getListaUtenti() {
		return listaUtenti;
	}

	public void setListaUtenti(ListaUtenti listaUtenti) {
		this.listaUtenti = listaUtenti;
	}

}
