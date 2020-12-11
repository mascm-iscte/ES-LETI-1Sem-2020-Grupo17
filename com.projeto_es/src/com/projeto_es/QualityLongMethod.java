package com.projeto_es;

import java.util.LinkedList;

public class QualityLongMethod {
	
	//- DCI, quando o valor da coluna correspondente à ferramenta (PMI ou iPlasma) é TRUE e a coluna
	//is_long_method também é TRUE;
	public LinkedList<String> isDCI (LinkedList <String> isLongMethod, LinkedList <String> iPlasma,
			LinkedList <String> PMD, boolean isPMD) {
		LinkedList<String> DCIList = new LinkedList<String>();
		isLongMethod.removeFirst();
		iPlasma.removeFirst();
		PMD.removeFirst();
		
		for(int i = 0; i < isLongMethod.size(); i++) {
			DCIList.add(isDCI_aux(isLongMethod.get(i),iPlasma.get(i), PMD.get(i), isPMD));
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
	
	public LinkedList<String> isDII (LinkedList <String> isLongMethod, LinkedList <String> iPlasma, 
			LinkedList <String> PMD, boolean isPMD) {
		LinkedList<String> DIIList = new LinkedList<String>();
		isLongMethod.removeFirst();
		iPlasma.removeFirst();
		PMD.removeFirst();
		
		for(int i = 0; i < isLongMethod.size(); i++) {
			DIIList.add(isDII_aux(isLongMethod.get(i),iPlasma.get(i), PMD.get(i), isPMD));
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
	
	public LinkedList<String> isADCI (LinkedList <String> isLongMethod, LinkedList <String> iPlasma, 
			LinkedList <String> PMD, boolean isPMD) {
		LinkedList<String> ADCIList = new LinkedList<String>();
		isLongMethod.removeFirst();
		iPlasma.removeFirst();
		PMD.removeFirst();
		
		for(int i = 0; i < isLongMethod.size(); i++) {
			ADCIList.add(isADCI_aux(isLongMethod.get(i),iPlasma.get(i), PMD.get(i), isPMD));
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

	public LinkedList<String> isADII (LinkedList <String> isLongMethod, LinkedList <String> iPlasma, 
			LinkedList <String> PMD, boolean isPMD) {
		LinkedList<String> ADIIList = new LinkedList<String>();
		isLongMethod.removeFirst();
		iPlasma.removeFirst();
		PMD.removeFirst();
		
		for(int i = 0; i < isLongMethod.size(); i++) {
			ADIIList.add(isADII_aux(isLongMethod.get(i),iPlasma.get(i), PMD.get(i), isPMD));
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
