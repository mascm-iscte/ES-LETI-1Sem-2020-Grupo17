package com.projeto_es;

import java.util.InputMismatchException;

public class QualityFactors  { 

	public QualityFactors() {
	}
	
	/**
	 * Method for detection if defect is correctly detected(DCI)
	 *
	 * @param tool PMD or iPlasma tool
	 * @param def Defect for comparation
	 * @return return true if tool and def values are both "TRUE"
	 **/
	public boolean DCI(String tool, String def) {
		return tool.equals("TRUE") && def.equals("TRUE");
	}
	/**
	 * Method for detection if defect is incorrectly detected(DII)
	 * @param tool PMD or iPlasma tool
	 * @param def Defect for comparation
	 * @return return true if tool value is "TRUE" and def value is "FALSE"
	 **/
	public boolean DII(String tool, String def) {
		return tool.equals("TRUE") && def.equals("FALSE");
	}
	/**
	 * Method for detection if Defects Absence Correctly Identified(ADCI)
	 * @param tool PMD or iPlasma tool
	 * @param def Defect for comparation
	 * @return return true if tool and def are both "FALSE"
	 **/
	public boolean ADCI(String tool, String def) {
		return tool.equals("FALSE") && def.equals("FALSE");
	}
	/**
	 * Method for detection if Defects Absence Incorrectly Identified (ADII)
	 * @param tool PMD or iPlasma tool
	 * @param def Defect for comparation 
	 * @return return true if tool value is "FALSE" and def value is "TRUE"
	 **/
	public boolean ADII(String tool, String def) {
		return tool.equals("FALSE") && def.equals("TRUE");
	}
	/**
	 * Defines the quality factor by given parameters and returns respective String
	 * @param tool PMD or iPlasma tool
	 * @param def Defect for comparation
	 * @exception InputMismatchException When the input is not the expected
	 * @exception NullPointerException When the input is null
	 * @return return quality factor by string
	 **/
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
