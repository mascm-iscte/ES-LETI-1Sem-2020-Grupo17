package com.projeto_es;

import java.util.InputMismatchException;

public class QualityFactors {
	public QualityFactors() {
	}
	/*Defeito Corretamente Identificado*/
	public boolean DCI(String tool, String def) {
		return tool.equals("TRUE") && def.equals("TRUE");
	}
	/*Defeito Incorretamente Identificado*/
	public boolean DII(String tool, String def) {
		return tool.equals("TRUE") && def.equals("FALSE");
	}
	/*Ausência de Defeito Corretamente Identificado*/
	public boolean ADCI(String tool, String def) {
		return tool.equals("FALSE") && def.equals("FALSE");
	}
	/*Ausência de Defeito Incorretamente Identificado*/
	public boolean ADII(String tool, String def) {
		return tool.equals("FALSE") && def.equals("TRUE");
	}
	
	public String whatFactor(String tool, String def) {
		try {
			if(DCI(tool, def)) {
				return "DCI";
			}
			else if(DII(tool, def)) {
				return "DII";
			}
			else if(ADCI(tool, def)) {
				return "ADCI";
			}
			else if(ADII(tool, def)) {
				return "ADII";
			}
		}
	catch(InputMismatchException e){
		System.out.println("Input errado!");
		e.printStackTrace();
	}
	catch(NullPointerException e) {
		System.out.println("Null input");
		e.printStackTrace();
	}

	return null;
		
		
	}
}
