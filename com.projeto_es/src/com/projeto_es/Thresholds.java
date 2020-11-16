package com.projeto_es;

public class Thresholds {
	
	public boolean isLongMethod(int LOC, int CYCLO, int LOC_metric, int CYCLO_metric) {
		return (LOC>LOC_metric && CYCLO>CYCLO_metric);
	}
	
	
	public boolean isFeatureEnvy(int ATFD, int LAA, int NOFA, int ATFD_metric, int LAA_metric, int NOFA_metric) {
		return ((ATFD>ATFD_metric && LAA<LAA_metric) || (ATFD>ATFD_metric && LAA>LAA_metric && NOFA>NOFA_metric));
		
	}
	
	
	
	
}
