package com.projeto_es;

import java.util.LinkedList;

public class QualityFeatureEnvy {
	public LinkedList<String> isDCI (LinkedList <String> isFeatureEnvy, LinkedList <String> iPlasma,
			LinkedList <String> PMD, boolean isPMD) {
		LinkedList<String> DCIList = new LinkedList<String>();
		isFeatureEnvy.removeFirst();
		iPlasma.removeFirst();
		PMD.removeFirst();
		
		for(int i = 0; i < isFeatureEnvy.size(); i++) {
			DCIList.add(isDCI_aux(isFeatureEnvy.get(i),iPlasma.get(i), PMD.get(i), isPMD));
		}
		
		return DCIList;
	}
	
	public String isDCI_aux (String a, String b, String c, boolean isPMD) {
		if(isPMD) {
			if(a.equals("TRUE") && c.equals("TRUE")) {
				return "DCI";
			}
			
		}else {
			if(a.equals("TRUE") && b.equals("TRUE")) {
				return "DCI";
			}

				
		}return "";
	}
	
	public LinkedList<String> isDII (LinkedList <String> isFeatureEnvy, LinkedList <String> iPlasma, 
			LinkedList <String> PMD, boolean isPMD) {
		LinkedList<String> DIIList = new LinkedList<String>();
		isFeatureEnvy.removeFirst();
		iPlasma.removeFirst();
		PMD.removeFirst();
		
		for(int i = 0; i < isFeatureEnvy.size(); i++) {
			DIIList.add(isDII_aux(isFeatureEnvy.get(i),iPlasma.get(i), PMD.get(i), isPMD));
		}
		
		return DIIList;
	}
	
	public String isDII_aux (String a, String b, String c, boolean isPMD) {
		if(isPMD) {
			if(a.equals("FALSE") && c.equals("TRUE")) {
				return "DII";
			}else {
				if(a.equals("FALSE") && b.equals("TRUE")) {
					return "DII";
				}
			}
			
		} return "";
	}
	
	public LinkedList<String> isADCI (LinkedList <String> isFeatureEnvy, LinkedList <String> iPlasma, 
			LinkedList <String> PMD, boolean isPMD) {
		LinkedList<String> ADCIList = new LinkedList<String>();
		isFeatureEnvy.removeFirst();
		iPlasma.removeFirst();
		PMD.removeFirst();
		
		for(int i = 0; i < isFeatureEnvy.size(); i++) {
			ADCIList.add(isADCI_aux(isFeatureEnvy.get(i),iPlasma.get(i), PMD.get(i), isPMD));
		}
		
		return ADCIList;
	}
	
	public String isADCI_aux (String a, String b, String c, boolean isPMD) {
		if(isPMD) {
			if(a.equals("FALSE") && c.equals("FALSE")) {
				return "ADCI";
			}else {
				if(a.equals("FALSE") && b.equals("FALSE")) {
					return "ADCI";
				}
			}
			
		} return "";
	}

	public LinkedList<String> isADII (LinkedList <String> isFeatureEnvy, LinkedList <String> iPlasma, 
			LinkedList <String> PMD, boolean isPMD) {
		LinkedList<String> ADIIList = new LinkedList<String>();
		isFeatureEnvy.removeFirst();
		iPlasma.removeFirst();
		PMD.removeFirst();
		
		for(int i = 0; i < isFeatureEnvy.size(); i++) {
			ADIIList.add(isADII_aux(isFeatureEnvy.get(i),iPlasma.get(i), PMD.get(i), isPMD));
		}
		
		return ADIIList;
	}
	
	public String isADII_aux (String a, String b, String c, boolean isPMD) {
		if(isPMD) {
			if(a.equals("TRUE") && c.equals("FALSE")) {
				return "ADII";
			}else {
				if(a.equals("TRUE") && b.equals("FALSE")) {
					return "ADII";
				}
			}
			
		} return "";
	}

}
