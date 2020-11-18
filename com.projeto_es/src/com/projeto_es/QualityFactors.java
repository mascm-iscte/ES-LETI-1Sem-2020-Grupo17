package com.projeto_es;

public class QualityFactors {
	
	public QualityFactors() {
	}
	/* Defeitos Corretamente Identificados*/
	public boolean DCI (int iPlasma, int def) {
		return iPlasma ==1 && def ==1;
	}
	/*Defeitos Incorretamente Identificados*/
	public boolean 	DII (int iPlasma, int def) {
		return iPlasma ==1 && def ==0;
	}
	/*Ausências de Defeitos Corretamente Identificadas*/
	public boolean ADCI (int iPlasma, int def) {
		return iPlasma ==0 && def ==0;
	}
	/*Ausências de Defeitos Incorretamente Identificadas*/
	public boolean ADII (int iPlasma, int def) {
		return iPlasma == 0 && def ==1;
	}
	/*para cada método do excel calcular o factor de qualidade
	 * e apresentar ao utilizador a enumeração de cada um deles
	 */
}
