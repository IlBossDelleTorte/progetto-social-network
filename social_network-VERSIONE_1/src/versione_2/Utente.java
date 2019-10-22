package versione_2;

import java.util.ArrayList;



public class Utente {
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
 
 public void riceviNotifica(String s) {
	 spazioPersonale.add(s);
 }

 public void aggiungiPropostaValida(Proposta p) {
	proposteValide.add(p);
	p.setStato(Stato.VALIDA);
 }
 
 
}