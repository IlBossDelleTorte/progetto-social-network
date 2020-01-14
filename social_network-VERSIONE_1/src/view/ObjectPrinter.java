package view;

import java.util.ArrayList;
import java.util.Arrays;

import campo.Campo;
import campo.CampoComposto;
import model.Bacheca;
import model.Categoria;
import model.Costanti;
import model.Proposta;
import model.Utente;

public class ObjectPrinter {
	
	
	/**
	 * Metodo con il quale è possibile stampare a video i dati riassuntivi di un elenco di proposte  
	 * @param l : elenco delle proposte da riassumere 
	 * @return
	 */
	public static void stampaListaProposte(ArrayList<Proposta> l) {
		if (l.size()==0)
				System.out.print(Costanti.ELENCO_VUOTO);
		else
		{
			
			System.out.print(String.format(Costanti.HEADER_BACHECA)+Costanti.LINEA);
			StringBuffer str=new StringBuffer();
			for(int i=0;i<l.size();i++) {
				
				str.append(String.format("%2s ", i+1));
				str.append(stringaHeadP(l.get(i)));
				str.append("\n");
				
			}
			System.out.print(str.toString());
		}
	}
	
	
	/**
	 * Metodo con il quale è possibile stampare a video un elenco di utenti 
	 * @return
	 */
	public static void stampaListaUtenti(ArrayList<Utente>l) {
		if(l.size()==0)
			System.out.print(Costanti.ELENCO_VUOTO);
		else
			{
				StringBuffer str=new StringBuffer();
			for(int i=0;i<l.size();i++) {
				str.append(i+1).append(". ").append(l.get(i).getNome()).append("\n");
			}
			System.out.print(str.toString());
		}
		
		
	}
	
	
	/** 
	 * Metodo con il quale viene stampato a video il contenuto di un campo
	 * @param c
	 */
	public static void stampaCampo(Campo c) {
		String s = String.format("%-35s %-100s", c.getNome(), c.getDescrizione());
		StringBuffer str=new StringBuffer(s);
		
		if(c.isObbligatorio())str.append("*"); //se il campo è obbligatorio viene posto un asterisco dopo la descrizione
		else str.append(" ");
		
		if(c.isComposito())
		{
			
			str.append("\n");
			ArrayList<Campo> valore = ((CampoComposto) c).getElencoCampi();
			System.out.print(str);
			for(int i=0;i<valore.size();i++) {
				if(valore.get(i).isInizializzato()) {
					System.out.print("    ");
					stampaCampo(valore.get(i));
					System.out.print("\n");
				}
			}
		}
		else {
			if(c.isInizializzato())
			str.append("\t").append(c.getValore());//se il campo ha valore esso viene stampato
			System.out.print(str);
		}
		
		
	}
	
	/**
	 * Metodo con il quale vengono stampati a video i dati di una categoria 
	 * @param c
	 */
	public static void stampaCategoria(Categoria c) {
		int i=1;
		System.out.print(c.getNome() + "\n" + c.getDescrizione() + "\n");
		for(Campo x:c.getCampi()){
			System.out.print(String.format("%2d",i)+") ");
			stampaCampo(x);
			System.out.print("\n");
			i++;
		}
	}
	
	public static void stampaUtente(Utente u) {
		StringBuffer str=new StringBuffer();
		str.append("NOME:"+u.getNome());
		if(u.getFasciaEta()!=null) str.append(" FASCIA D'ETA':"+u.getFasciaEta());
		if(u.getCategorieInteresse().size()!=0) {
			str.append(" CATEGORIE D'INTERESSE:");
			u.getCategorieInteresse().forEach(c->str.append(c+" "));
		}
		System.out.print(str.toString());
	}
	
	public static void stampaProposta(Proposta p) {
		StringBuffer str= new StringBuffer();
		str.append(p.getCreatore().getNome()).append(Costanti.LINEA);
		System.out.print(str);
		stampaCategoria(p.getCategoria());
	}
	
	public static void headCategoria(Categoria c) {
		String str=String.format("%-30s %-24s %-24s %-30s %-14s",
				c.getNome(),c.valoreDi(4),c.valoreDi(2),c.valoreDi(3),c.valoreDi(6)+"€");
		System.out.print(str);
	}
	
	public static void headProposta(Proposta p) {
		String str=String.format("%-20s ", p.getCreatore().getNome());
		System.out.print(str);
		headCategoria(p.getCategoria());
	}
	public static String stringaHeadC(Categoria c) {
		String str=String.format("%-30s %-24s %-24s %-30s %-14s",
				c.getNome(),c.valoreDi(4),c.valoreDi(2),c.valoreDi(3),c.valoreDi(6)+"€");
		return str;
	}
	public static String stringaHeadP(Proposta p) {
		String str=String.format("%-20s ", p.getCreatore().getNome());
		str=str+stringaHeadC(p.getCategoria());
		return str;
		
		
	}
	
	public static void stampaBacheca(Bacheca b) {
		StringBuffer str= new StringBuffer(String.format(Costanti.HEADER_BACHECA));
		str.append(Costanti.LINEA);
		
		for(int i=0;i<b.getProposteAperte().size();i++) {
			str.append(String.format("%-2s", i+1));
			System.out.print(str);
			headProposta(b.getProposteAperte().get(i));
			System.out.print("\n");
		}
	}


	public static void stampaListaCampi(ArrayList<Campo> lista) {
		for(int i=0;i<lista.size();i++) { //stampa dell'elenco di tutte le categorie disponibili
			System.out.print(i+1+")");
			stampaCampo(lista.get(i));
			System.out.print("\n");
		}
	}
	
	public static void stampaLista(ArrayList<String> lista) {
		for(int i=0;i<lista.size();i++) { //stampa dell'elenco di tutte le categorie disponibili
			System.out.println(i+1+")"+lista.get(i));
		}
	}
	
	public static void stampaNotifiche(ArrayList<String>l) {
		if(l.size()==0) {
			System.out.print(Costanti.SPAZIO_PERSONALE_VUOTO);
		}
		else {
			StringBuffer str=new StringBuffer();
			for(int i=0;i<l.size();i++){
				str.append(String.format("%-2s %s", i+1,l.get(i))).append("\n\n");
			}
			System.out.print(str.toString());
		}
	}

}
