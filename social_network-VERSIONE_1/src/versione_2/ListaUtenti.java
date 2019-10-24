package versione_2;

import java.io.Serializable;
import java.util.HashSet;

public class ListaUtenti implements Serializable {
	private HashSet<Utente> utenti=new HashSet<>();
	
	public void aggiungiUtente(Utente u) {
		utenti.add(u);
	}
	
	public void rimuoviUtente(Utente u) {
		utenti.remove(u);
	}

	public HashSet<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(HashSet<Utente> utenti) {
		this.utenti = utenti;
	}
	
	public boolean contiene(String str) {
		boolean r=false;
		for (Utente u:utenti)
		{
			if(u.getNome().equals(str))r=true;
		}
		return r;
	}
	
	public Utente estraiUtente(String str)
	{
		Utente usr=null;
		for (Utente u:utenti)
		{
			if(u.getNome().equals(str))usr=u;;
		}
		return usr;
	}

}
