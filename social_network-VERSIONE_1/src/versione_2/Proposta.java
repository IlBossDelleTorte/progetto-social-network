package versione_2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Proposta {
	private Categoria categoria;
	private Stato stato;
	private ArrayList<Utente> partecipanti=new ArrayList<Utente>();
	private Utente creatore;
	
	public Proposta(Utente creatore) {
		categoria=new Categoria(" "," ");
		categoria.partitaDiCalcio();
		this.creatore=creatore;
		stato=Stato.VUOTA;
	}
	
	public void compilazione() throws ParseException {
		ArrayList<Campo> c = categoria.getCampi();
		int size = c.size();
		SimpleDateFormat parser_data = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		for(int i = 0; i < size; i++) {
			if(i == 4) {
				c.get(i).compila();
				if(parser_data.parse(c.get(i).getValore()).before(parser_data.parse(c.get(2).getValore()))) {
					System.out.println(Menu.ERRORE_DATA_INIZIO);
					i--;
				}
			}
				else if(i == 8) {
				if(c.get(5).isInizializzato()) {
					String str = c.get(5).getValore();
					Date data_inizio = parser_data.parse(c.get(4).getValore());
					Scanner s = new Scanner(str);
					s.useDelimiter(",");
					int ore = s.nextInt()*60;
					int min = s.nextInt();
					Calendar dataTerm = Calendar.getInstance();
					dataTerm.setTime(data_inizio);
					dataTerm.add(Calendar.MINUTE, ore+min);
					/*StringBuffer val = new StringBuffer();
					val.append(dataTerm.get(Calendar.DAY_OF_MONTH)+"/");
					val.append(dataTerm.get(Calendar.MONTH)+"/");
					val.append(dataTerm.get(Calendar.YEAR)+" ");
					val.append(dataTerm.get(Calendar.HOUR_OF_DAY)+":");
					val.append(dataTerm.get(Calendar.MINUTE));*/
					String val=Input.calToString(dataTerm);
					c.get(i).setValore(val);
				}
				else {
					c.get(i).compila();
					if(parser_data.parse(c.get(i).getValore()).before(parser_data.parse(c.get(4).getValore()))) {
						System.out.println(Menu.ERRORE_DATA_FINE);
						i--;
						}
				}
					
			}

			
			else 
				c.get(i).compila();
		}
		System.out.print(categoria);
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Utente getCreatore() {
		return creatore;
	}

	public void setCreatore(Utente creatore) {
		this.creatore = creatore;
	}

}
