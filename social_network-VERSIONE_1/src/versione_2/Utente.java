package versione_2;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;



public class Utente implements Serializable {
	private String nome;
	private String fasciaEta = null;
	private ArrayList<String> categorieInteresse;
	
	private ArrayList<String> notifiche;
	private ArrayList<Proposta> proposteValide;
	private ArrayList<Proposta> proposteAffini;
	private ArrayList<Proposta> inviti;
			

 public Utente(String nome,String eta,ArrayList<String>categorie) {
	 this.nome = nome;
	 this.fasciaEta=eta;
	 this.categorieInteresse=categorie;
	 notifiche = new ArrayList<>();
	 proposteValide = new ArrayList<>();
	 categorieInteresse = new ArrayList<>();
	 proposteAffini = new ArrayList<>();
	 inviti = new ArrayList<>();
 }


 public String getFasciaEta() {
	return fasciaEta;
}


public void setFasciaEta(String fasciaEta) {
	this.fasciaEta = fasciaEta;
}


public ArrayList<String> getCategorieInteresse() {
	return categorieInteresse;
}


public void setCategorieInteresse(ArrayList<String> categorieInteresse) {
	this.categorieInteresse = categorieInteresse;
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
	 notifiche.add(s);
 }
 
 /**
  * IL metodo effettua la cancellazione di una notifica dello spazio personale
  * @param i: indice della notifica da rimuovere
  */
 public void rimuoviNotifica(int i) {
	 notifiche.remove(i);
 }

 public void aggiungiPropostaValida(Proposta p) throws NumberFormatException, ParseException {
	proposteValide.add(p);
	p.aggiornaStato();
 }
 public void rimuoviPropostaValida(Proposta p) {
	proposteValide.remove(p);
 }
 
 public void aggiungiPropostaAffine(Proposta p) {
	 proposteAffini.add(p);
 }
 
 public ArrayList<Proposta> getProposteAffini() {
	return proposteAffini;
}


public void rimuoviPropostaAffine(Proposta p) {
	 proposteAffini.remove(p);
 }
 
 public void aggiungiInvito(Proposta p) {
	 inviti.add(p);
 }
 
 public void rimuoviInvito(Proposta p) {
	 inviti.remove(p);
 }


public ArrayList<String> getNotifiche() {
	return notifiche;
}


public void setNotifiche(ArrayList<String> spazioPersonale) {
	this.notifiche = spazioPersonale;
}


public ArrayList<Proposta> getProposteValide() {
	return proposteValide;
}

public void aggiornaProposte() {

	for(int i = 0; i < proposteAffini.size(); i++) {
		if(proposteAffini.get(i).getStato() != Stato.APERTA)
			proposteAffini.remove(i);
		if(proposteAffini.get(i).getPartecipanti().contains(this))
			proposteAffini.remove(i);
	}
	for(int j = 0; j < inviti.size(); j++) {
		if(inviti.get(j).getStato() != Stato.APERTA)
			inviti.remove(j);
		if(inviti.get(j).getPartecipanti().contains(this))
			inviti.remove(j);
	}
		
}


public void setProposteValide(ArrayList<Proposta> proposteValide) {
	this.proposteValide = proposteValide;
}


public String elencoNotifiche() {
	StringBuffer str=new StringBuffer();
	for(int i=0;i<notifiche.size();i++)
	{
		str.append(String.format("%-2s %s", i+1,notifiche.get(i))).append("\n\n");
	}
	return str.toString();
}

/**
 * Metodo toString di utente che ritorna una stringa contenente
 * nome, fascia d'eta e categorie di interesse se queste sono disponibili
 */
public String toString() {
	StringBuffer str=new StringBuffer();
	str.append("NOME:"+nome);
	if(fasciaEta!=null) str.append(" FASCIA D'ETA':"+fasciaEta);
	if(categorieInteresse.size()!=0) {
		str.append(" CATEGORIE D'INTERESSE:");
		categorieInteresse.forEach(c->str.append(c+" "));
	}
	return str.toString();
}


public ArrayList<Proposta> getInviti() {
	return inviti;
}
 
}