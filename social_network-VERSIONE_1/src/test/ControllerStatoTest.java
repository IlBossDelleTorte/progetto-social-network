package test;


import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import controller.ControllerStato;
import controller.Input;
import model.Categoria;
import model.PartitaDiCalcio;
import model.Proposta;
import model.Stato;
import model.Utente;
import view.Costanti;

class ControllerStatoTest {
	
	@Test
	void fromVuotaToValida() throws ParseException {
		Categoria c=new PartitaDiCalcio();
		Utente u=new Utente("Test",null,null);
		Proposta p=new Proposta(u);
		
		//valori place holder per compilare
		c.impostaCampo("0", Costanti.INDICE_PARTECIPANTI);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_SCADENZA_ISCRIZIONE);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_TERMINE_RITIRO);
		c.impostaCampo("0", Costanti.INDICE_TOLLERANZA_PARTECIPANTI);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_DATA_INIZIO);
		
		p.setCategoria(c);
		p.setStato(Stato.VUOTA);
		
		ControllerStato.aggiornaProposta(p);
		assertEquals(Stato.VALIDA,p.getStato());
		
	}
	
	@Test
	void fromValidaToFallitaWhenScaduta() throws ParseException {
		Categoria c=new PartitaDiCalcio();
		Utente u=new Utente("Test",null,null);
		Proposta p=new Proposta(u);
		//valori place holder per compilare
		c.impostaCampo("0", Costanti.INDICE_PARTECIPANTI);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_SCADENZA_ISCRIZIONE);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_TERMINE_RITIRO);
		c.impostaCampo("0", Costanti.INDICE_TOLLERANZA_PARTECIPANTI);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_DATA_INIZIO);
		
		p.setCategoria(c);
		p.setStato(Stato.VALIDA);
		
		ControllerStato.aggiornaProposta(p);
		assertEquals(Stato.FALLITA,p.getStato());
	}
	
	@Test
	void fromValidaToAperta() throws ParseException {
		Categoria c=new PartitaDiCalcio();
		Utente u=new Utente("Test",null,null);
		Proposta p=new Proposta(u);
		//valori place holder per compilare
		c.impostaCampo("0", Costanti.INDICE_PARTECIPANTI);
		c.impostaCampo("20/12/2021 10:00", Costanti.INDICE_SCADENZA_ISCRIZIONE);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_TERMINE_RITIRO);
		c.impostaCampo("0", Costanti.INDICE_TOLLERANZA_PARTECIPANTI);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_DATA_INIZIO);
		
		p.setCategoria(c);
		p.setStato(Stato.VALIDA);
		
		ControllerStato.aggiornaProposta(p);
		assertEquals(Stato.APERTA,p.getStato());
	}
	
	

	@Test
	void fromApertaToChiusaWhenScadutaAndPartecipantiEqualsMin() throws ParseException {
		Categoria c=new PartitaDiCalcio();
		Utente u=new Utente("Test",null,null);
		Proposta p=new Proposta(u);
		
		c.impostaCampo("1", Costanti.INDICE_PARTECIPANTI);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_SCADENZA_ISCRIZIONE);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_TERMINE_RITIRO);
		c.impostaCampo("0", Costanti.INDICE_TOLLERANZA_PARTECIPANTI);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_DATA_INIZIO);
		
		p.setStato(Stato.APERTA);
		p.setCategoria(c);
		p.aggiungiPartecipante(u, (float)0.0);
		
		
		ControllerStato.aggiornaProposta(p);
		assertEquals(Stato.CHIUSA,p.getStato());
		
	}
	
	@Test
	void fromApertaToChiusaWhenScadutaAndPartecipantiBetweenMinAndMax() throws ParseException {
		Categoria c=new PartitaDiCalcio();
		Utente u1=new Utente("Test1",null,null);
		Utente u2=new Utente("Test2",null,null);
		Proposta p=new Proposta(u1);
		
		c.impostaCampo("1", Costanti.INDICE_PARTECIPANTI);
		c.impostaCampo("2", Costanti.INDICE_TOLLERANZA_PARTECIPANTI);
		//1<=Partecipanti<=3
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_SCADENZA_ISCRIZIONE);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_TERMINE_RITIRO);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_DATA_INIZIO);
		
		p.setStato(Stato.APERTA);
		p.setCategoria(c);
		p.aggiungiPartecipante(u1, (float)0.0);
		p.aggiungiPartecipante(u2, (float)0.0);
		
		ControllerStato.aggiornaProposta(p);
		assertEquals(Stato.CHIUSA,p.getStato());
		
	}
	
	@Test
	void fromApertaToChiusaWhenNotRitirabileAndPartecipantiEqualsMax() throws ParseException {
		Categoria c=new PartitaDiCalcio();
		Utente u1=new Utente("Test1",null,null);
		Utente u2=new Utente("Test2",null,null);
		Proposta p=new Proposta(u1);
		
		c.impostaCampo("1", Costanti.INDICE_PARTECIPANTI);
		c.impostaCampo("1", Costanti.INDICE_TOLLERANZA_PARTECIPANTI);
		//1<=Partecipanti<=2
		c.impostaCampo("31/10/2020 20:00", Costanti.INDICE_SCADENZA_ISCRIZIONE);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_TERMINE_RITIRO);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_DATA_INIZIO);
		
		p.setStato(Stato.APERTA);
		p.setCategoria(c);
		p.aggiungiPartecipante(u1, (float)0.0);
		p.aggiungiPartecipante(u2, (float)0.0);
		
		ControllerStato.aggiornaProposta(p);
		assertEquals(Stato.CHIUSA,p.getStato());
	}
	
	@Test
	void fromApertaToFallitaWhenScadutaAndPartecipantiLowerThanMin() throws ParseException {
		Categoria c=new PartitaDiCalcio();
		Utente u1=new Utente("Test1",null,null);
		Proposta p=new Proposta(u1);
		
		c.impostaCampo("2", Costanti.INDICE_PARTECIPANTI);
		c.impostaCampo("1", Costanti.INDICE_TOLLERANZA_PARTECIPANTI);
		//2<=Partecipanti<=3
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_SCADENZA_ISCRIZIONE);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_TERMINE_RITIRO);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_DATA_INIZIO);
		
		p.setStato(Stato.APERTA);
		p.setCategoria(c);
		p.aggiungiPartecipante(u1, (float)0.0);
		
		ControllerStato.aggiornaProposta(p);
		assertEquals(Stato.FALLITA,p.getStato());
	}
	
	@Test
	void fromChiusaToConclusaOneDayAfterItEnds() throws ParseException {
		Categoria c=new PartitaDiCalcio();
		Utente u1=new Utente("Test1",null,null);
		Proposta p=new Proposta(u1);
		
		c.impostaCampo("2", Costanti.INDICE_PARTECIPANTI);
		c.impostaCampo("1", Costanti.INDICE_TOLLERANZA_PARTECIPANTI);
		// 2<=Partecipanti<=3
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_SCADENZA_ISCRIZIONE);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_TERMINE_RITIRO);
		c.impostaCampo("09/01/2020 14:00", Costanti.INDICE_DATA_INIZIO);
		c.impostaCampo("09/01/2020 15:00",Costanti.INDICE_DATA_FINE);
		p.setCategoria(c);
		p.aggiungiPartecipante(u1, (float)0.0);
		p.setStato(Stato.CHIUSA);
		
		
		
		ControllerStato.aggiornaProposta(p);
		assertEquals(Stato.CONCLUSA,p.getStato());
	}
	
	@Test
	void fromChiusaToChiusaWhenNotConclusa() throws ParseException {
		Categoria c=new PartitaDiCalcio();
		Utente u1=new Utente("Test1",null,null);
		Proposta p=new Proposta(u1);
		
		c.impostaCampo("2", Costanti.INDICE_PARTECIPANTI);
		c.impostaCampo("1", Costanti.INDICE_TOLLERANZA_PARTECIPANTI);
		// 2<=Partecipanti<=3
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_SCADENZA_ISCRIZIONE);
		c.impostaCampo(Input.dateToString(new Date()), Costanti.INDICE_TERMINE_RITIRO);
		c.impostaCampo("09/01/2020 14:00", Costanti.INDICE_DATA_INIZIO);
		c.impostaCampo(Input.dateToString(new Date()),Costanti.INDICE_DATA_FINE);
		
		p.setCategoria(c);
		p.aggiungiPartecipante(u1, (float)0.0);
		p.setStato(Stato.CHIUSA);
		
		
		
		ControllerStato.aggiornaProposta(p);
		assertEquals(Stato.CHIUSA,p.getStato());
	}

}
