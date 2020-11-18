package com.projeto_es;

public class Thresholds {
	private int LOC_metric;
	private int CYCLO_metric;
	private int ATFD_metric;
	private int LAA_metric;
	private int NOFA_metric;
	
	public Thresholds(){
	}
	
	public void setLOC_metric(int LOC_metric) {
		this.LOC_metric = LOC_metric;
	}
	public void setCYCLO_metric(int CYCLO_metric) {
		this.CYCLO_metric = CYCLO_metric;
		}
	public void setATFD_metric(int ATFD_metric) {
		this.ATFD_metric = ATFD_metric;
	}
	public void setLAA_metric(int LAA_metric) {
		this.LAA_metric = LAA_metric;
	}
	
	public int getLOC_metric() {
		return this.LOC_metric;
	}
	
	public int getCYCLO_metric() {
		return this.CYCLO_metric;
	}
	public int getATFD_metric() {
		return this.ATFD_metric;
	}
	public double getLAA_metric() {
		return this.LAA_metric;
	}
	
	public boolean isLongMethod(int LOC, int CYCLO, int LOC_metric, int CYCLO_metric) {
		return (LOC>LOC_metric && CYCLO>CYCLO_metric);
	}
	
	
	public boolean isFeatureEnvy(int ATFD, double LAA, int NOFA, int ATFD_metric, double LAA_metric, int NOFA_metric) {
		return ((ATFD>ATFD_metric && LAA<LAA_metric) || (ATFD>ATFD_metric && LAA>LAA_metric && NOFA>NOFA_metric));
		
	}
	public boolean isGodClass (int WMCNAMM, int WMCNAMM_metric, int NOMNAMM, int NOMNAMM_metric, int RFC, int RFC_metric){
		return ((WMCNAMM < WMCNAMM_metric) || (WMCNAMM<= WMCNAMM_metric && NOMNAMM > NOMNAMM_metric && RFC>RFC_metric));
	}
	public boolean isDataClass (int NOAM, int NOAM_metric, int WMCNAMM, int WMCNAMM_metric, int NIM, int NIM_metric){
		return NOAM>NOAM_metric && WMCNAMM<WMCNAMM_metric && NIM<=NIM_metric; 
	}
	
	
	
	
	
}
