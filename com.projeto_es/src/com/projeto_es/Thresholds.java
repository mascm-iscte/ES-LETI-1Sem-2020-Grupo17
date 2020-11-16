package com.projeto_es;

public class Thresholds {
	public Thresholds(){
	}
	
	public boolean isLongMethod(int LOC, int CYCLO, int LOC_metric, int CYCLO_metric) {
		return (LOC>LOC_metric && CYCLO>CYCLO_metric);
	}
	
	
	public boolean isFeatureEnvy(int ATFD, int LAA, int NOFA, int ATFD_metric, int LAA_metric, int NOFA_metric) {
		return ((ATFD>ATFD_metric && LAA<LAA_metric) || (ATFD>ATFD_metric && LAA>LAA_metric && NOFA>NOFA_metric));
		
	}
	public boolean isGodClass (int WMCNAMM, int WMCNAMM_metric, int NOMNAMM, int NOMNAMM_metric, int RFC, int RFC_metric){
		return ((WMCNAMM < WMCNAMM_metric) || (WMCNAMM<= WMCNAMM_metric && NOMNAMM > NOMNAMM_metric && RFC>RFC_metric));
	}
	public boolean isDataClass (int NOAM, int NOAM_metric, int WMCNAMM, int WMCNAMM_metric, int NIM, int NIM_metric){
		return NOAM>NOAM_metric && WMCNAMM<WMCNAMM_metric && NIM<=NIM_metric; 
	}
	
	
	
	
}
