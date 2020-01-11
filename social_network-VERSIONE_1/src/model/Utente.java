package model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

import controller.ControllerStato;



public class Utente implements Serializable,Notificabile {
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
	 categorieInteresse = categorie;
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
	ControllerStato.aggiornaProposta(p);
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



public void setProposteValide(ArrayList<Proposta> proposteValide) {
	this.proposteValide = proposteValide;
}


public ArrayList<Proposta> getInviti() {
	return inviti;
}
 
}