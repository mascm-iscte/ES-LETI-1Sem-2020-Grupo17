  
package com.projeto_es;

public class FeatureEnvyThresholds extends Thresholds{
	int ATFD_metric;
	double LAA_metric;
	
	/**
	 * 
	 * @param ATFD_metric new threshold for ATFD
	 * @param LAA_metric new Threshold for LAA
	 */
	FeatureEnvyThresholds(int ATFD_metric, double LAA_metric){
		this.ATFD_metric=ATFD_metric;
		this.LAA_metric=LAA_metric;
	}
	
	/**
	 * 
	 * @param ATFD method's ATFD
	 * @param LAA method's LAA
	 * @param ATFD_metric threshold for ATFD
	 * @param LAA_metric threshold for LAA
	 * @return True if ATFD>ATFD_metric && LAA<LAA_metric
	 */
	
	@Override
	public boolean isFeatureEnvy(int ATFD, double LAA, int ATFD_metric, double LAA_metric) {
		return ((ATFD>ATFD_metric && LAA<LAA_metric));
			
	}
}
