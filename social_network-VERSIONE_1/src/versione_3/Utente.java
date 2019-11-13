package versione_3;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;



public class Utente implements Serializable {
	private String nome;
	private ArrayList<String> spazioPersonale;
	private ArrayList<Proposta> proposteValide;

 public Utente(String nome) {
	 this.nome = nome;
	 spazioPersonale = new ArrayList<>();
	 proposteValide = new ArrayList<>();
 }


 public String getNome() {
	return nome;
 }


 public void setNome(String nome) {
	this.nome = nome;
 }
 
 /**
  * Il metodo permette ad utente di ricevere una notifica, ovvero di aggiungere una stringa esterna al proprio spazio personale
  * @param s: notifica ricevuta
  */
 public void riceviNotifica(String s) {
	 spazioPersonale.add(s);
 }
 
 /**
  * IL metodo effettua la cancellazione di una notifica dello spazio personale
  * @param i: indice della notifica da rimuovere
  */
 public void rimuoviNotifica(int i) {
	 spazioPersonale.remove(i);
 }

 public void aggiungiPropostaValida(Proposta p) throws NumberFormatException, ParseException {
	proposteValide.add(p);
	p.aggiornaStato();
 }
 public void rimuoviPropostaValida(Proposta p) {
	proposteValide.remove(p);
 }


public ArrayList<String> getSpazioPersonale() {
	return spazioPersonale;
}


public void setSpazioPersonale(ArrayList<String> spazioPersonale) {
	this.spazioPersonale = spazioPersonale;
}


public ArrayList<Proposta> getProposteValide() {
	return proposteValide;
}


public void setProposteValide(ArrayList<Proposta> proposteValide) {
	this.proposteValide = proposteValide;
}
 
/* public String elencoProposteValide() {
	StringBuffer str= new StringBuffer(String.format(Menu.HEADER_BACHECA));
	str.append(Menu.LINEA);
	
	for(int i=0;i<proposteValide.size();i++) {
		str.append(String.format("%2s %s", i+1,proposteValide.get(i).header())).append("\n");
	}
	return str.toString(); 
	
} */

public String elencoNotifiche() {
	StringBuffer str=new StringBuffer();
	for(int i=0;i<spazioPersonale.size();i++)
	{
		str.append(String.format("%-2s %s", i+1,spazioPersonale.get(i))).append("\n\n");
	}
	return str.toString();
}
 
}