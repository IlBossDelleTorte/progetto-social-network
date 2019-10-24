package versione_2;

import java.io.Serializable;

/**
 * Enum che elenca gli stati assumibili da una proposta 
 *
 */
public enum Stato implements Serializable {
	VUOTA,
	VALIDA,
	APERTA,
	CHIUSA,
	CONCLUSA,
	FALLITA;

}
