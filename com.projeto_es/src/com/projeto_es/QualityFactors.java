package com.projeto_es;

public class QualityFactors {
	public QualityFactors() {
	}
	/*Defeito Corretamente Identificado*/
	public boolean DCI(int iPlasma, int def) {
		return iPlasma==1 && def==1;
	}
	/*Defeito Incorretamente Identificado*/
	public boolean DII(int iPlasma, int def) {
		return iPlasma==1 && def==0;
	}
	/*Ausência de Defeito Corretamente Identificado*/
	public boolean ADCI(int iPlasma, int def) {
		return iPlasma==0 && def==0;
	}
	/*Ausência de Defeito Incorretamente Identificado*/
	public boolean ADII(int iPlasma, int def) {
		return iPlasma==0 && def==1;
	}
}
