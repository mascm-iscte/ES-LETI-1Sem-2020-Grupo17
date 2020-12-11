package com.projeto_es;

public class Thresholds {
	private int LOC_metric;
	private int CYCLO_metric;
	private int ATFD_metric;
	private int LAA_metric;
	
	public Thresholds(){
	}
	
	/**
	 * 
	 * @param LOC_metric
	 */
	public void setLOC_metric(int LOC_metric) {
		this.LOC_metric = LOC_metric;
	}
	
	/**
	 * 
	 * @param CYCLO_metric
	 */
	public void setCYCLO_metric(int CYCLO_metric) {
		this.CYCLO_metric = CYCLO_metric;
		}
	
	/**
	 * 
	 * @param ATFD_metric
	 */
	public void setATFD_metric(int ATFD_metric) {
		this.ATFD_metric = ATFD_metric;
	}
	
	/**
	 * 
	 * @param LAA_metric
	 */
	public void setLAA_metric(int LAA_metric) {
		this.LAA_metric = LAA_metric;
	}
	
	/**
	 * 
	 * @return LOC_metric
	 */
	public int getLOC_metric() {
		return this.LOC_metric;
	}
	
	/**
	 * 
	 * @return CYCLO_metric
	 */
	public int getCYCLO_metric() {
		return this.CYCLO_metric;
	}
	
	/**
	 * 
	 * @return ATFD_metric
	 */
	public int getATFD_metric() {
		return this.ATFD_metric;
	}
	
	/**
	 * 
	 * @return LAA_metric
	 */
	public double getLAA_metric() {
		return this.LAA_metric;
	}
	
	/**
	 * 
	 * @param LOC
	 * @param CYCLO
	 * @param LOC_metric
	 * @param CYCLO_metric
	 * @return true if LOC>LOC_metric && CYCLO>CYCLO_metric
	 */
	public boolean isLongMethod(int LOC, int CYCLO, int LOC_metric, int CYCLO_metric) {
		return (LOC>LOC_metric && CYCLO>CYCLO_metric);
	}
	
	/**
	 * 
	 * @param ATFD
	 * @param LAA
	 * @param ATFD_metric
	 * @param LAA_metric
	 * @return true if ATFD>ATFD_metric && LAA<LAA_metric
	 */
	public boolean isFeatureEnvy(int ATFD, double LAA, int ATFD_metric, double LAA_metric) {
		return ((ATFD>ATFD_metric && LAA<LAA_metric));
		
	}
	
	
	
	
}

	

