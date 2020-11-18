package com.projeto_es;

public class DefaultThresholds extends Thresholds {
	
	
	public DefaultThresholds () {
		
	}
	
	
	public boolean isLongMethod(int LOC, int CYCLO) {
		return (LOC>80 && CYCLO>10);
	}
	
	
	public boolean isFeatureEnvy(int ATFD, int LAA, int NOFA) {
		return ((ATFD>4 && LAA<0.428571) || (ATFD>4 && LAA>0.428571 && NOFA>6)); 
		
	}
	
	public boolean isGodClass(int WMCNAMM, int NOMNAMM, int RFC) {
		return (WMCNAMM>47 || (WMCNAMM <= 47 && NOMNAMM >9 && RFC > 34)); 
	}
	
	public boolean isDataClass(int NOAM, int WMCNAMM, int NIM) {
		return (NOAM > 2 && WMCNAMM < 21 && NIM <= 30); 
	}
	

}
